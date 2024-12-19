package com.study_flow.modules.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequestPayload(
        @Schema(example = "userex1#", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Pattern(regexp = "^\\S{5,20}$",
                message = "The username must have min of 5 and max of 20 characters and no spaces.")
        String username,
        @Schema(example = "example@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Email
        String email,
        @Schema(example = "Example39#", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Pattern(regexp = "^(?=>*\\d)(?=.*[@#$^&+=!].{10,25}$)",
                message = "The password should contain at least one number and one uppercase letter. The min of characters is 10 and de max 25.")
        String password
) {
}
