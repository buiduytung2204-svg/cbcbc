/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bbbcc;

/**
 *
 * @author tungb
 */
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// --- BÀI 1: GENERIC METHOD ---
class GenericMethodDemo {
    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

// --- BÀI 2: SETTER INJECTION ---
interface MessageService {
    void sendMessage(String message);
}

class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Gửi qua Email: " + message);
    }
}

class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Gửi qua SMS: " + message);
    }
}

class Notification {
    private MessageService messageService;

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String message) {
        if (messageService != null) {
            messageService.sendMessage(message);
        } else {
            System.out.println("Chưa thiết lập dịch vụ gửi tin!");
        }
    }
}

// --- BÀI 3: XỬ LÝ OBJECT & COLLECTORS ---
class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
}

// --- LỚP CHẠY CHÍNH (MAIN) ---
public class Main {
    public static void main(String[] args) {
        
        System.out.println("=== BÀI 1: GENERIC METHOD ===");
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"Java", "Python", "C++"};
        System.out.print("Mảng Integer: ");
        GenericMethodDemo.printArray(intArray);
        System.out.print("Mảng String: ");
        GenericMethodDemo.printArray(strArray);
        System.out.println();

        System.out.println("=== BÀI 2: SETTER INJECTION ===");
        Notification notification = new Notification();
        // Inject EmailService
        notification.setMessageService(new EmailService());
        notification.notifyUser("Chào bạn, hệ thống vừa cập nhật!");
        // Inject SMSService
        notification.setMessageService(new SMSService());
        notification.notifyUser("Mã OTP của bạn là 123456.\n");

        System.out.println("=== BÀI 3: STREAM & COLLECTORS ===");
        List<Employee> employees = Arrays.asList(
            new Employee(1, "David", 1500),
            new Employee(2, "Alice", 900),
            new Employee(3, "Bob", 1200),
            new Employee(4, "Charlie", 800)
        );
        
        // Dùng Stream API để lọc, lấy tên, sắp xếp và gom thành List
        List<String> highSalaryNames = employees.stream()
            .filter(e -> e.getSalary() > 1000)
            .map(Employee::getName)
            .sorted()
            .collect(Collectors.toList());
            
        System.out.println("Nhân viên có lương > 1000 (đã sắp xếp): " + highSalaryNames);
    }
}