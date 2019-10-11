package com.ria.gradle.collections.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Employee implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private Department department;

    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
