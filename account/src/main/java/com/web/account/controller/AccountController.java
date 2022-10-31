/**
 * 
 */
package com.web.account.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.account.exception.AccountException;
import com.web.account.service.AccountService;
import com.web.account.service.JSONResponse;
import com.web.account.validation.CustomValidation;
import com.web.account.vo.CustomerVO;

import reactor.core.publisher.Mono;

/**
 * @author dsure
 *
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	public AccountService accountService;
	
	@Autowired
	public CustomValidation customValidation;
	
	@GetMapping("/sample")
	public ResponseEntity<String> getSample(){
		LOGGER.info(" Inside customer account controller");
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerVO>> getAllAccount(){
		LOGGER.info(" Inside customer account controller");
		return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
	}
	
	@GetMapping("/nonflux")
	public ResponseEntity<JSONResponse> getAllAccountNonFlux(){
		LOGGER.info(" Inside customer account controller");
		
		JSONResponse resp = accountService.getAllAccountNonFlux();
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@GetMapping("/flux")
	public ResponseEntity<Mono<JSONResponse>> getAllAccountWebFlux(){
		LOGGER.info(" Inside customer account controller");
		
		Mono<JSONResponse> customerVOs = accountService.getAllAccountFlux();
		
		return new ResponseEntity<>(customerVOs, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerVO> getCustomerAccountByID(@PathVariable String id) throws AccountException{
		Long idLong =null;
		LOGGER.info(" Inside customer account controller"+idLong);
		if(id==null) {
			throw new AccountException("Account id is mandatory !!!");
		}else {
			if(!customValidation.isNumeric(id)) {
				throw new AccountException("Account number should be numeric");
			}
			idLong = Long.valueOf(id);
		}
		return new ResponseEntity<>(accountService.getAccountByID(idLong), HttpStatus.OK);
	}
	

	@PostMapping
	public ResponseEntity<CustomerVO> saveAccount(@RequestBody @Valid CustomerVO customerVO){
		System.out.println(" Inside customer save account ");
		return new ResponseEntity<>(accountService.saveAccount(customerVO), HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccountByID(@PathVariable String id){
		System.out.println(" Inside customer accpunt controller");
		Long idLong =null;
		if(id==null) {
			//error
		}else {
			idLong = Long.valueOf(id);
		}
		return new ResponseEntity<>(accountService.deleteAccountByID(idLong), HttpStatus.OK);
	}
	

	@PatchMapping("/{customerVO}")
	public ResponseEntity<CustomerVO> updateAccount(@RequestBody CustomerVO customerVO){
		System.out.println(" Inside customer accpunt controller");
		return new ResponseEntity<>(accountService.updateAccount(customerVO), HttpStatus.OK);
	}
}
