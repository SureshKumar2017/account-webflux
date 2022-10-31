package com.web.account.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerVO implements Serializable{
	
	private Long custID;

	@NotNull(message = "Customer Name cannot be null")
	@NotEmpty(message = "Customer Name cannot be empty")
	private String customerName;

	@Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
	private int age;

	private String occupation;

	private String createdBy;

	private String createdTime;
	
	private List<AccountVO> accountVOs=new ArrayList<>();
	
	public List<AccountVO> getAccountVOs() {
		return accountVOs;
	}

	public void setAccountVOs(List<AccountVO> accountVOs) {
		this.accountVOs = accountVOs;
	}

	public Long getCustID() {
		return custID;
	}

	public void setCustID(Long custID) {
		this.custID = custID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

}
