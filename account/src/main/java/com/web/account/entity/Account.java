/**
 * 
 */
package com.web.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author dsure
 *
 */
@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer acctid;
	
	@Column(name = "custid")
	private Integer custid;
    
	@Column(name = "acctname")
    private String acctname;
    
	@Column(name = "accttype")
    private String accttype;
    
	@Column(name = "acctbalance")
    private Double acctbalance;


	public Integer getAcctid() {
		return acctid;
	}

	public void setAcctid(Integer acctid) {
		this.acctid = acctid;
	}

	public Integer getCustid() {
		return custid;
	}

	public void setCustid(Integer custid) {
		this.custid = custid;
	}

	public String getAcctname() {
		return acctname;
	}

	public void setAcctname(String acctname) {
		this.acctname = acctname;
	}

	public String getAccttype() {
		return accttype;
	}

	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}

	public Double getAcctbalance() {
		return acctbalance;
	}

	public void setAcctbalance(Double acctbalance) {
		this.acctbalance = acctbalance;
	}

}
