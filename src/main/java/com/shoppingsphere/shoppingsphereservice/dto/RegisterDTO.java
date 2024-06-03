package com.shoppingsphere.shoppingsphereservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDTO {
    @Size(min = 6, max = 45, message="Họ và tên không hợp lệ!")
    private String fullname;

    @Size(min = 6, max = 45, message="Tài khoản phải trong khoảng từ 6 - 45 ký tự!")
    private String username;

//    @NotNull(message = "Vui lòng chọn giới tính")
    private boolean gender;

    @Size(min = 6, max = 32, message="Mật khẩu phải trong khoảng từ 6 - 32 ký tự!")
    private String password;

    @NotBlank(message="Không được để trống trường này!")
    private String verifyPassword;

    @NotBlank(message="Không được để trống!")
    @Email(message="Email không đúng định dạng!")
    private String email;

//    @NotNull(message = "Vui lòng chọn ngày sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

//    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", message = "Không đúng định dạng Số điện thoại!")
//    private String phoneNumber;
}
