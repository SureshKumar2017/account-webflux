/**
 * 
 */
package com.web.account.validation;

import org.springframework.stereotype.Component;

/**
 * @author dsure
 *0
 */
@Component
public class CustomValidation {
	public boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
