package com.chaturvediji.customer;

public class CustomerAddition {
    String id;
    String name;
    String age;
    String gender;
    String contact;
    String address;

    public CustomerAddition() {

    }

    public CustomerAddition(String id, String name, String age, String gender, String contact, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }
}
