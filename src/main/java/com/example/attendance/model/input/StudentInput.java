package com.example.attendance.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentInput {

    @Pattern(regexp = "^[0-9]{10}", message = "Card ID must be 10 digits long and cannot contain any letters or symbols.")
    private String cardId;

    @NotNull(message = "You need to provide a first name")
    @NotBlank(message = "You need to provide a first name")
    @Pattern(regexp = "[a-zA-Z]+", message = "First name cannot contain any numbers or symbols.")
    private String firstName;

    @NotNull(message = "You need to provide a last name")
    @NotBlank(message = "You need to provide a last name")
    @Pattern(regexp = "[a-zA-Z]+", message = "Last name cannot contain any numbers or symbols.")
    private String lastName;

    @NotNull(message = "You need to provide a valid email.")
    @Email(message = "Email should be valid.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    private String phoneNumbers;

    @Pattern(regexp = "(AE|EM|CS|EE)", message = "Please select one of the supported majors: AE, EM, CS, EE")
    private String major;
}
