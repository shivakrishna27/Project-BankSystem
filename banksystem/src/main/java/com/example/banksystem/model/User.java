package com.example.banksystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String formNo; //Encapsulation & Access are private,public

    //1. OOP Concepts   Encapsulation →  All fields (name, dob, email, etc.) are declared as private and accessed
    @NotBlank(message = "Name is required")
    private String name;

    private String fatherName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private String gender;

    @Email(message = "Invalid email format")
    private String email;

    private String maritalStatus;

    private String address;

    private String city;

    private String pinCode;

    private String state;

    private String religion;

    private String category;

    private String income;

    private String education;

    private String occupation;

    private String pan;

    private String aadhar;

    private Boolean seniorCitizen;

    private Boolean existingAccount;

    private String accountType;

    private String cardNumber;

    private String pin;

    private String services;

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }
    //OOP encapsulation
    public @NotBlank(message = "Name is required") String getName() {
        return name;
    } //Access & Modifiers

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email format") String email) {
        this.email = email;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public Boolean getSeniorCitizen() {
        return seniorCitizen;
    }

    public void setSeniorCitizen(Boolean seniorCitizen) {
        this.seniorCitizen = seniorCitizen;
    }

    public Boolean getExistingAccount() {
        return existingAccount;
    }

    public void setExistingAccount(Boolean existingAccount) {
        this.existingAccount = existingAccount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public User() { //default Constroctor
    }

    // Overloaded & constructor for key fields
    public User(String formNo, String name, String cardNumber, String pin) {
        this.formNo = formNo;
        this.name = name;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.seniorCitizen = false;
        this.existingAccount = false;
    }
}