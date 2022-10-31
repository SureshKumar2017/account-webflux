/**
 * 
 */
package com.web.account.vo;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author dsure
 *
 */
@Data
public class AccountVO implements Serializable {

	private Integer accID;

	private Integer custID;

	@NotNull(message = "Account Name cannot be null")
	@NotEmpty(message = "Account Name cannot be empty")
	private String acctname;

	@NotNull(message = "Account Type cannot be null")
	@NotEmpty(message = "Account Type cannot be empty")
	private String accttype;
	
	private Double acctbalance;

}
