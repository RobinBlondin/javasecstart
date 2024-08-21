package se.systementor.javasecstart.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="Dog")
public class Dog {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="Id")
    private UUID id;

    @Column(name="Age")
    private String age;

    @Column(name="Gender")
    private String gender;

    @Column(name = "Breed")
    private String breed;

    @Column(name="SoldTo")
    private String soldTo;


    @Column(name="Price")
    private int price;

    @Column(name="Name")
    private String name;

    @Column(name="Size")
    private String size;


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