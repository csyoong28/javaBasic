package com.learn.hutool;

import cn.hutool.core.util.ObjectUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class L02DeepClone {

    // Main class to be cloned
    static class Department implements Serializable {
        private static final long serialVersionUID = 1L; // Recommended for Serializable
        private String name;
        private List<Employee> employees;

        public Department(String name, List<Employee> employees) {
            this.name = name;
            this.employees = employees;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }

        @Override
        public String toString() {
            return "Department{" +
                    "name='" + name + '\'' +
                    ", employees=" + employees +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Department that = (Department) o;
            return Objects.equals(name, that.name) && Objects.equals(employees, that.employees);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, employees);
        }
    }

    // Nested class, also needs to be Serializable for deep cloning via serialization
    static class Employee implements Serializable {
        private static final long serialVersionUID = 1L; // Recommended for Serializable
        private String name;
        private int age;

        public Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return age == employee.age && Objects.equals(name, employee.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }

    public static void main(String[] args) {
        // 1. Create original objects
        Employee emp1 = new Employee("Alice", 30);
        Employee emp2 = new Employee("Bob", 25);

        List<Employee> originalEmployees = new ArrayList<>();
        originalEmployees.add(emp1);
        originalEmployees.add(emp2);

        Department originalDepartment = new Department("HR", originalEmployees);

        System.out.println("Original Department: " + originalDepartment);
        System.out.println("Original Employees List Reference: " + originalDepartment.getEmployees());
        System.out.println("Original Employee 1 Reference: " + originalDepartment.getEmployees().get(0));

        // 2. Perform deep clone using ObjectUtil.cloneByStream()
        Department clonedDepartment = ObjectUtil.cloneByStream(originalDepartment);

        System.out.println("\nCloned Department: " + clonedDepartment);
        System.out.println("Cloned Employees List Reference: " + clonedDepartment.getEmployees());
        System.out.println("Cloned Employee 1 Reference: " + clonedDepartment.getEmployees().get(0));


        // 3. Verify deep cloning
        System.out.println("\n--- Verification ---");

        // Check if the Department objects themselves are different instances
        System.out.println("Are Department objects the same instance? " + (originalDepartment == clonedDepartment));

        // Check if the List of Employees is a different instance
        System.out.println("Are Employees lists the same instance? " + (originalDepartment.getEmployees() == clonedDepartment.getEmployees()));

        // Check if the individual Employee objects are different instances
        System.out.println("Are Employee 1 objects the same instance? " + (originalDepartment.getEmployees().get(0) == clonedDepartment.getEmployees().get(0)));

        // 4. Modify the cloned object and see if the original is affected
        System.out.println("\n--- Modifying Cloned Object ---");
        clonedDepartment.setName("Marketing");
        clonedDepartment.getEmployees().get(0).setAge(32);
        clonedDepartment.getEmployees().add(new Employee("Charlie", 40)); // Add a new employee to cloned list

        System.out.println("Original Department after cloning and modification: " + originalDepartment);
        System.out.println("Cloned Department after modification: " + clonedDepartment);

        System.out.println("\n--- Final Verification ---");
        System.out.println("Original Employee 1's age: " + originalDepartment.getEmployees().get(0).getAge());
        System.out.println("Cloned Employee 1's age: " + clonedDepartment.getEmployees().get(0).getAge());
        System.out.println("Original Employees list size: " + originalDepartment.getEmployees().size());
        System.out.println("Cloned Employees list size: " + clonedDepartment.getEmployees().size());
    }
}
