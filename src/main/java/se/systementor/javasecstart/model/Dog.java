package se.systementor.javasecstart.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.UUID;

@Entity
@Table(name="Dog")
public class Dog {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="Id")
    private UUID id;

    @Pattern(regexp="[a-zA-Z ]{1,30}", message="Age must be only letters and 1-30 characters long")
    @Column(name="Age")
    private String age;

    @Pattern(regexp="[a-zA-Z ]{1,30}", message="Gender must be only letters and 1-30 characters long")
    @Column(name="Gender")
    private String gender;

    @Pattern(regexp="[a-zA-Z ]{1,30}", message="Breed must be only letters and 1-30 characters long")
    @Column(name = "Breed")
    private String breed;

    @Pattern(regexp="[a-zA-Z\\s- ]{1,30}", message="SoldTo must be only letters, spaces, or dashes and 1-30 characters long")
    @Column(name="SoldTo")
    private String soldTo;

    @Min(value = 1, message = "Price must be at least 1 digit long")
    @Digits(integer=100, fraction=0, message="Price must be a number between 1 and 100 digits long")
    @Column(name="Price")
    private int price;

    @Pattern(regexp="[a-zA-Z ]{1,30}", message="Name must be only letters and 1-30 characters long")
    @Column(name="Name")
    private String name;

    @Pattern(regexp="[a-zA-Z ]{1,30}", message="Size must be only letters and 1-30 characters long")
    @Column(name="Size")
    private String size;

    @Size(min = 1, max = 10000, message = "Image must be between 1 and 10000 characters long")
    @Column(name="Image")
    private String image;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String hender) {
        this.gender = hender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSoldTo() {
        return soldTo;
    }

    public void setSoldTo(String soldTo) {
        this.soldTo = soldTo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}