package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Relations relations = new Relations();

        try {
            String studentsFilePath = "Students.txt";
            String teachersFilePath = "Teachers.txt";


            Files.lines(Paths.get(studentsFilePath)).forEach(line -> {
                String[] parts = line.split(" ");
                String name = parts[0];
                String surname = parts[1];
                int age = Integer.parseInt(parts[2]);
                boolean gender = parts[3].equalsIgnoreCase("Male");
                List<Integer> grades = new ArrayList<>();
                for (int i = 4; i < parts.length; i++) {
                    grades.add(Integer.parseInt(parts[i]));
                }
                relations.addMember(new Student(name, surname, age, gender, grades));
            });


            Files.lines(Paths.get(teachersFilePath)).forEach(line -> {
                String[] parts = line.split(" ");
                String name = parts[0];
                String surname = parts[1];
                int age = Integer.parseInt(parts[2]);
                boolean gender = parts[3].equalsIgnoreCase("Male");
                String subject = parts[4];
                int yearsOfExperience = Integer.parseInt(parts[5]);
                int salary = Integer.parseInt(parts[6]);
                relations.addMember(new Teacher(name, surname, age, gender, subject, yearsOfExperience, salary));
            });

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }


        System.out.println(relations);


        relations.getMembers().stream()
                .filter(member -> member instanceof Student)
                .map(member -> (Student) member)
                .forEach(student -> System.out.println(student + " GPA: " + student.calculateGPA()));
    }
}
