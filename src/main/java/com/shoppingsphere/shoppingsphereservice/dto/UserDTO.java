package com.shoppingsphere.shoppingsphereservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String phoneNumber;
    private	Boolean enabled;
}
