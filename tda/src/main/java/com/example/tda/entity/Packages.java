package com.example.tda.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "packages")
public class Packages {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "premium")
    private String premium;

    @Column(name = "insuranceDetail")
    private String insuranceDetail;

    @Column(name = "title")
    private String title;



    // public List<Order> getOrderList() {
	// 	return orderList;
	// }

	// public void setOrderList(List<Order> orderList) {
	// 	this.orderList = orderList;
	// }

	public Packages(){}

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getInsuranceDetail() {
        return insuranceDetail;
    }

    public void setInsuranceDetail(String insuranceDetail) {
        this.insuranceDetail = insuranceDetail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
