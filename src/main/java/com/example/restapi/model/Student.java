package com.example.restapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="stu_name")
    private String name;
    @Column(name="stu_age")
    private int age;
    @Column(name= "stu_email")
    private String email;

}
