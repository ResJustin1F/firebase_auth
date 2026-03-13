/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.csc325_firebase_webview_auth.model;

/**
 *
 * @author MoaathAlrajab
 *
 *
 *
 *
 * Model:
 *
 *
 */
public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String department;
    private String major;
    private String email;
    private String imageUrl;

    public Person(String id, String firstName, String lastName, String department, String major, String email, String imageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.major = major;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getDepartment() {return department;}
    public void setDepartment(String department) {this.department = department;}

    public String getMajor() {return major;}
    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
