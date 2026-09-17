package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {

    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private BigDecimal salary;
    private String department;
}
