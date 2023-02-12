package com.example.attendance.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutput implements Serializable {

    private Long id;
    private String cardId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumbers;
    private String major;
}
