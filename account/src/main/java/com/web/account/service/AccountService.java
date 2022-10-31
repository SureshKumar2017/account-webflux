package com.web.account.service;

import java.util.List;

import com.web.account.vo.CustomerVO;

import reactor.core.publisher.Mono;

public interface AccountService {
	public List<CustomerVO> getAllAccount();
	public CustomerVO getAccountByID(Long id);
	public CustomerVO saveAccount(CustomerVO customerVO);
	public String deleteAccountByID(Long id);
	public CustomerVO updateAccount(CustomerVO customerVO);
	
	public Mono<JSONResponse> getAllAccountFlux();
	public JSONResponse getAllAccountNonFlux();
}
