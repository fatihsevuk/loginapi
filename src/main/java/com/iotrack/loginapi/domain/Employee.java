package com.iotrack.loginapi.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Employee implements Serializable{

    private static final long serialVersionUID=345423354L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id" , nullable=false , updatable=false)
    private long id;

    private String firstname;

    private String lastname;

    private String imageUrl;

    private String email;

    private String phone;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", imageurl='" + imageUrl + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
