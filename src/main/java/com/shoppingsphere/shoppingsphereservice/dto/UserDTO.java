package com.shoppingsphere.shoppingsphereservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private	Boolean enabled;
    private Date Birthday;

    private Boolean Gender;
    private String FullName;
    private String Avatar;
}
