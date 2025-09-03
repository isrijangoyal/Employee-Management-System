package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Employee Management System
 * Simple Java program to perform CRUD operations on employees with input validation.
 */
public class EmployeeManagementSystem {

    // Employee class to store employee details
    static class Employee {
        int id;
        String name;
        int age;
        String department;

        Employee(int id, String name, int age, String department) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.department = department;
        }

        @Override
        public String toString() {
            return "ID: " + id +
                    ", Name: " + name +
                    ", Age: " + age +
                    ", Department: " + department;
        }
    }

    private static final ArrayList<Employee> employees = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Employee Management System ====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = getPositiveIntInput();
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> {
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);
                }
                default -> System.out.println("⚠ Invalid choice. Try again!");
            }
        }
    }

    // Add new employee with validation
    private static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = getPositiveIntInput();

        System.out.print("Enter Employee Name: ");
        String name = getValidStringInput();

        System.out.print("Enter Employee Age: ");
        int age = getPositiveIntInput();

        System.out.print("Enter Department: ");
        String department = getValidStringInput();

        employees.add(new Employee(id, name, age, department));
        System.out.println("✅ Employee added successfully!");
    }

    // View all employees
    private static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("⚠ No employees found.");
        } else {
            System.out.println("\n--- Employee List ---");
            employees.forEach(System.out::println);
        }
    }

    // Update employee by ID
    private static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = getPositiveIntInput();

        for (Employee emp : employees) {
            if (emp.id == id) {
                System.out.print("Enter new Name: ");
                emp.name = getValidStringInput();

                System.out.print("Enter new Age: ");
                emp.age = getPositiveIntInput();

                System.out.print("Enter new Department: ");
                emp.department = getValidStringInput();

                System.out.println("✅ Employee updated successfully!");
                return;
            }
        }
        System.out.println("❌ Employee not found!");
    }

    // Delete employee by ID
    private static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = getPositiveIntInput();

        boolean removed = employees.removeIf(emp -> emp.id == id);
        if (removed) {
            System.out.println("✅ Employee deleted successfully.");
        } else {
            System.out.println("❌ Employee not found!");
        }
    }

    // Utility: Get positive integer input with validation
    private static int getPositiveIntInput() {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value > 0) return value;
                else System.out.print("⚠ Enter a positive integer: ");
            } catch (NumberFormatException e) {
                System.out.print("⚠ Enter correct value (integer only): ");
            }
        }
    }

    // Utility: Get valid string input (only alphabets, no empty strings)
    private static String getValidStringInput() {
        while (true) {
            String input = sc.nextLine().trim();
            if (input.matches("[a-zA-Z ]+") && !input.isEmpty()) {
                return input;
            } else {
                System.out.print("⚠ Enter correct value (alphabets only): ");
            }
        }
    }
}
