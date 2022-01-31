package com.example.task2.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {

    @NotEmpty
    @Size(min=8, max=20, message = "Username should contain 8-20 characters")
    private String username;

    @NotEmpty
    @Size(min=6, max=20, message = "Email should contain 6-20 characters")
    @Email(message = "Enter valid email address.")
    @Pattern(
            regexp = "^\\w[a-zA-Z0-9.!#$%&\u2019*+/=?^_`{|}~\"-]{0,34}@((\\[?[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}]?)|(([a-zA-Z0-9][a-zA-Z\\-0-9]*\\.)+[a-zA-Z]+))$",
            message = "Email should contain only digits, latin letters and special characters."
    )
    private String email;

    @NotEmpty
    @Size(min=8, max=20, message = "Password should contain 8-20 characters")
    private String password;
}