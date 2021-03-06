package com.moodle.account.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

/**
 * Created by andrewlarsen on 11/4/17.
 */
//Default table name that will be overridden at runtime
@DynamoDBTable(tableName = "ACCOUNTS_TABLE")
public class Account {

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "id-index", attributeName = "id")
    private String id;
    private String name;
    @DynamoDBHashKey
    private String email;
    private String phoneNumber;
    private String companyName;
    private String address;
    private String userName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
