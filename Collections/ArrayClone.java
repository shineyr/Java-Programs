package com.ria.gradle.collections;


import com.ria.gradle.collections.utils.Department;
import com.ria.gradle.collections.utils.Employee;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Only condition to use SerializationUtils is that classes stored in array must be Serializable.
 */
public class ArrayClone {
    public static void main(String[] args) {
        Employee[] employees = new Employee[2];
        employees[0] = new Employee(100, "Ria", "Yang", new Department(1, "HR"));
        employees[1] = new Employee(200, "Jeff", "Shen", new Department(2, "Finance"));

        System.out.println(employees[0]);
        System.out.println(employees[0].getDepartment());


        // Shallow copy
        Employee[] clonedEmployees = employees.clone();

        // Deep copy
        Employee[] copiedEmployees = SerializationUtils.clone(employees);


        employees[0].setFirstName("Unknown");
        employees[0].getDepartment().setName("Unknown");

        // Verify the copied objects
        System.out.println(clonedEmployees[0].getFirstName()); // Unknown
        System.out.println(clonedEmployees[0].getDepartment().getName()); // Unknown

        System.out.println(copiedEmployees[0].getFirstName()); // Ria
        System.out.println(copiedEmployees[0].getDepartment().getName()); // HR

    }
}
