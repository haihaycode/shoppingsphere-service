    package com.shoppingsphere.shoppingsphereservice.config;


    import com.shoppingsphere.shoppingsphereservice.auth.Role;
    import com.shoppingsphere.shoppingsphereservice.entity.User;
    import com.shoppingsphere.shoppingsphereservice.service.NotificationService;
    import com.shoppingsphere.shoppingsphereservice.service.user.UserService;

    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationProvider;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.AuthenticationFailureHandler;
    import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    public class WebSecurityConfig {
        @Autowired
        NotificationService notificationService;
        @Autowired
        UserService userService;
        @Autowired
        AuthenticationProvider authenticationProvider;
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(
                                    "/public/**",
                                    "/",
                                    "/home",
                                    "/detail/**",
                                    "/reset-password/**",
                                    "/forgot-password",
                                    "/api/v1/**",
                                    "/search/**",
                                    "/show",
                                    "/login",
                                    "/register",
                                    "/login"
                            ).permitAll()
                            .requestMatchers(
                                    "/cart/**",
                                    "/cart",
                                    "/checkout/**",
                                    "/checkout",
                                    "/order/all",
                                    "/account",
                                    "/account/**"
                            ).hasAnyRole(Role.CUSTOMER.name(), Role.ADMIN.name())
                            .requestMatchers(
                                    "/admin/**",
                                    "/user-manager"
                            ).hasAnyRole(Role.ADMIN.name())
                            .anyRequest().authenticated()
                    )
                    .authenticationProvider(authenticationProvider)
                    .formLogin(form -> form
                            .loginPage("/login")
                            .defaultSuccessUrl("/home", true)
                            .failureUrl("/account/login?error=true")
                            .failureHandler(getCustomAuthenticationFailureHandler())
                            .successHandler(getCustomAuthenticationSuccessHandler())
                            .permitAll())
                    .logout(logout -> logout
                            .logoutUrl("/logout") // chỉnh sửa đường dẫn đăng xuất ở đây
                            .logoutSuccessUrl("/") // thiết lập URL chuyển hướng sau khi đăng xuất thành công
                            .permitAll());

            return http.build();
        }

        private AuthenticationFailureHandler getCustomAuthenticationFailureHandler() {
            return (request, response, exception) -> {
                if (exception instanceof UsernameNotFoundException) {
                    notificationService.addError(exception.getMessage());
                } else {
                    notificationService.addError("Password is incorrect");
                }
                response.sendRedirect("/login");
            };
        }

        private AuthenticationSuccessHandler getCustomAuthenticationSuccessHandler() {
            return (request, response, authentication) -> {

                String redirect = request.getParameter("to");
                User user = (User) userService.loadUserByUsername(authentication.getName());

                notificationService.addInfo("Welcome back " + user.getFullname() + " !");
                response.sendRedirect(redirect != null ? redirect : "/");
            };
        }
    }