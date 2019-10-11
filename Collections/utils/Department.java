package com.ria.gradle.collections.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public String toString() {
        return "Department [id=" + id + ", name=" + name + "]";
    }
}
