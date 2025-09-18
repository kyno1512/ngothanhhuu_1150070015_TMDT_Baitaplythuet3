package com.example.bai3;

public class Contact {
    private int id;
    private String name;
    private String phoneNumber;

    public Contact() {}

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // ✅ Thêm phương thức toString() để in ra thông tin
    @Override
    public String toString() {
        return "Id: " + id + ", Name: " + name + ", Phone: " + phoneNumber;
    }
}
