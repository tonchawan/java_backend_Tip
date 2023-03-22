package com.example.tda.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "agent")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username" ,unique=true )
    private String username;

    @Column(name = "password",nullable=false)
    private String password;

    @Column(name = "type",nullable=false)
    private String type;

    @Column(name = "prefix" ,nullable=false)
    private String prefix;

    @Column(name = "name" ,nullable=false)
    private String firstName;

    @Column(name = "lastname",nullable=false)
    private String lastName;

    @Column(name = "sub_district",nullable=false)
    private String subDistrict;

    @Column(name = "district",nullable=false)
    private String district;

    @Column(name = "province",nullable=false)
    private String province;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email",nullable=false )
    private String email;

    @Column(name = "license_id", unique=true)
    private String licenseId;

    @Column(name = "license_expire" ,nullable=false)
    private String licenseExpire;

    public Agent(){}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getLicenseExpire() {
        return licenseExpire;
    }

    public void setLicenseExpire(String licenseExpire) {
        this.licenseExpire = licenseExpire;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @PrePersist
    public void prePersist() {
        createAt=new Date();
    }

    @PostUpdate
    public void postUpdate() {
        updateAt = new Date();
    }

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "create_at")
    private Date createAt;

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}