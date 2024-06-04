    package com.shoppingsphere.shoppingsphereservice.config;


    import com.shoppingsphere.shoppingsphereservice.auth.Role;
    import com.shoppingsphere.shoppingsphereservice.entity.User;
    import com.shoppingsphere.shoppingsphereservice.service.NotificationService;
    import com.shoppingsphere.shoppingsphereservice.service.user.UserService;

    import com.shoppingsphere.shoppingsphereservice.util.KeyGenerator;
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
    import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
    import org.springframework.ui.Model;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
            http    .exceptionHandling(exceptionHandling -> exceptionHandling
                            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login?error=plzLogin"))
                    //nếu chưa xác thực thì chuyển đến trang login
                    )
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(
                                    "/public/**",
                                    "/products/**",
                                    "/",
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
                            .failureHandler(getCustomAuthenticationFailureHandler())
                            .successHandler(getCustomAuthenticationSuccessHandler())
                            .permitAll())
                    .rememberMe(rememberMe -> rememberMe
                            .rememberMeParameter("remember-me")
                            .key(KeyGenerator.getKey())
                            .tokenValiditySeconds(86400)

                    )
                    .logout(logout -> logout
                            .logoutUrl("/logout") // chỉnh sửa đường dẫn đăng xuất ở đây
                            .logoutSuccessUrl("/login?logout").deleteCookies("remember-me") // thiết lập URL chuyển hướng sau khi đăng xuất thành công
                            .permitAll());

            return http.build();
        }

        private AuthenticationFailureHandler getCustomAuthenticationFailureHandler() {
            return (request, response, exception) -> {
                if (exception instanceof UsernameNotFoundException) {
                    request.getSession().setAttribute("error", "Tài khoản không tồn tại");
                } else {
                    request.getSession().setAttribute("error", "Mật khẩu sai");
                }

                response.sendRedirect("/login");
            };
        }

        private AuthenticationSuccessHandler getCustomAuthenticationSuccessHandler() {
            return (request, response, authentication) -> {
                request.getSession().setAttribute("success", "Đăng nhập thành công");
                response.sendRedirect("/?DangNhapThanhCong");
            };
        }
    }