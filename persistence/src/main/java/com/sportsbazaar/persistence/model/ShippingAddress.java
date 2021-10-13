package com.sportsbazaar.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shipping_address")
public class ShippingAddress extends BaseEntity {

    @Column(name = "house_no")
    private String houseNo;

    private String street1;

    private String street2;

    private String city;

    private String state;

    @Size(min = 6, max = 6)
    @Column(name = "zip_code")
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getHouseNo() {
	return houseNo;
    }

    public void setHouseNo(String houseNo) {
	this.houseNo = houseNo;
    }

    public String getStreet1() {
	return street1;
    }

    public void setStreet1(String street1) {
	this.street1 = street1;
    }

    public String getStreet2() {
	return street2;
    }

    public void setStreet2(String street2) {
	this.street2 = street2;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getZipCode() {
	return zipCode;
    }

    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @Override
    public String toString() {
	return String.format(
		"ShippingAddress [houseNo=%s, street1=%s, street2=%s, city=%s, state=%s, zipCode=%s, id=%s]", houseNo,
		street1, street2, city, state, zipCode, id);
    }
}
