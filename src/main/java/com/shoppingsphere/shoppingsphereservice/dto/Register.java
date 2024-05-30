package com.shoppingsphere.shoppingsphereservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Register {
    @Size(min = 6, max = 45, message="Họ và tên không hợp lệ!")
    private String fullname;

    @Size(min = 6, max = 45, message="Tài khoản phải trong khoảng từ 6 - 45 ký tự!")
    private String username;

    @Size(min = 6, max = 32, message="Mật khẩu phải trong khoảng từ 6 - 32 ký tự!")
    private String password;

    @NotBlank(message="Không được để trống trường này!")
    private String verifyPassword;

    @NotBlank(message="Không được để trống!")
    @Email(message="Email không đúng định dạng!")
    private String email;

    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", message = "Không đúng định dạng Số điện thoại!")
    private String phoneNumber;
}
