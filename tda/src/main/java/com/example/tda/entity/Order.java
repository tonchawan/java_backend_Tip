package com.example.tda.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date updatedAt;
  
    private Date createdAt;

	@Column
    (name = "agent_id")
    private Integer agentId;

    @Column(name = "packageId")
    private Integer packageId;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "name")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "Identity")
    private String identity;

    @Column(name = "address")
    private String address;

    @Column(name = "sub_district")
    private String subDistrict;

    @Column(name = "district")
    private String district;

    @Column(name = "province")
    private String province;

    @Column(name = "zip_code")
    private String zipCode;
    
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    private String dob;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "benefiaial")
    private String benefiaial;

    @Column(name = "order_status")
    private Integer orderStatus;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name= "package_id")
    // private Packages packages;




    // public Order(Integer agentId, Integer packageId, String prefix, String firstName, String lastName, String identity,
    //         String address, String subDistrict, String district, String province, String zipCode, String phone,
    //         String email, String dob, String startDate, String endDate,
    //         String benefiaial, Integer orderStatus) {
    //     this.agentId = agentId;
    //     this.packageId = packageId;
    //     this.prefix = prefix;
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.identity = identity;
    //     this.address = address;
    //     this.subDistrict = subDistrict;
    //     this.district = district;
    //     this.province = province;
    //     this.zipCode = zipCode;
    //     this.phone = phone;
    //     this.email = email;
    //     this.dob = dob;
    //     this.startDate = startDate;
    //     this.endDate = endDate;
    //     this.benefiaial = benefiaial;
    //     this.orderStatus = orderStatus;
    // }

    // public Packages getPackages() {
	// 	return packages;
	// }

	// public void setPackages(Packages packages) {
	// 	this.packages = packages;
	// }

	public Order() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBenefiaial() {
        return benefiaial;
    }

    public void setBenefiaial(String benefiaial) {
        this.benefiaial = benefiaial;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

    // for auto generate Create Update
    
    
    

    // @PrePersist
    // public void prePersist() {
    //     this.createdAt =new Date();
    // }

    // @PostUpdate
    // public void postUpdate() {
    //     this.updatedAt = new Date();
    // }
}

