package com.riwi.PruebaDesempeno.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
    
    @NotBlank(message = "the user name is required")
    @Size(max = 100, message = "the user name maximum lenght is 100 characters")
    private String name;

    @Email(message = "the user email is required")
    @Size(
        min = 5,
        max = 100,
        message = "the email must be between 5 and 100 characters"
    )
    private String email;

    @NotBlank(message = "the user password is required")
    @Size(max = 255, message = "the user password maximum lenght is 255 characters")
    private String password;

    @NotNull(message = "the user status is required")
    private Boolean active;

}
