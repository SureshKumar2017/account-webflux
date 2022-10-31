/**
 * 
 */
package com.web.account.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.web.account.entity.Account;
import com.web.account.entity.Customer;
import com.web.account.repo.CustomerRepo;
import com.web.account.vo.AccountVO;
import com.web.account.vo.CustomerVO;

import reactor.core.publisher.Mono;

/**
 * @author dsure
 *
 */
@Service
public class AccountServiceImpl implements AccountService{

	public CustomerRepo getCustomerRepo() {
		return customerRepo;
	}

	public void setCustomerRepo(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Autowired
	public CustomerRepo customerRepo;
	
	@PersistenceContext
	public EntityManager entityManager;
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public JSONResponse getAllAccountNonFlux() {
		Query que = entityManager.createNativeQuery("select * from customer");
		JSONResponse jsonResponse = new JSONResponse();
		
		
		/*try {
			Class.forName("org.postgresql.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
	        System.out.println("ok"+conn);
			Statement st = conn.createStatement();
			boolean fla = st.execute("select * from customer");
			System.out.println("Connection Object--------->"+fla);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		jsonResponse.setObj(que.getResultList());
		return jsonResponse;
	}
	
	@Override
	public Mono<JSONResponse> getAllAccountFlux() {
		Query que = entityManager.createNativeQuery("select * from customer");
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setObj(que.getResultList());
		
		return Mono.just(jsonResponse);
	}
	
	@Override
	public List<CustomerVO> getAllAccount() {
		// TODO Auto-generated method stub
		List<CustomerVO> listVO = new ArrayList<>();
		CustomerVO customerVO = null;
		List<Customer> customers = customerRepo.findAll();
		for(Customer ac : customers) {
			customerVO = new CustomerVO();
			setCustomerObj(customerVO, ac);
			
			//BeanUtils.copyProperties(ac, customerVO);
			listVO.add(customerVO);
		}
		
		return listVO;
	}

	@Override
	public CustomerVO getAccountByID(Long id) {
		Optional<Customer> customerObj = customerRepo.findById(id);
		Customer customer = customerObj.get();
		
		CustomerVO customerVO = new CustomerVO();
		setCustomerObj(customerVO, customer);
		
		return customerVO;
	}

	@Override
	public CustomerVO saveAccount(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		customer.setCustomerName(customerVO.getCustomerName());
		customer.setAge(customerVO.getAge());
		customer.setOccupation(customerVO.getOccupation());
		customer.setCreatedBy("admin");
		//customer.setCreatedTime(LocalDateTime.now());
		
		Set set = new HashSet<>();
		customerVO.getAccountVOs().forEach(accVO->{
			Account account = new Account();
			account.setAcctbalance(accVO.getAcctbalance());
			account.setAcctname(accVO.getAcctname());
			account.setAccttype(accVO.getAccttype());
			set.add(account);
		});
		
		//customer.setAccounts(set);
		
		Customer cus = customerRepo.save(customer);
		customerVO.setCustID(cus.getCustid());
		
		return customerVO;
	}

	@Override
	public String deleteAccountByID(Long id) {
		Customer cu = customerRepo.getOne(id);
		customerRepo.delete(cu);
		return "Deleted Sucessfully";
	}

	@Override
	public CustomerVO updateAccount(CustomerVO customerVO) {
		Customer customer = new Customer();
		customer.setCustomerName(customerVO.getCustomerName());
		customer.setAge(customerVO.getAge());
		
		Customer cus = entityManager.merge(customer);
		customerVO.setCustID(cus.getCustid());
		return customerVO;
	}
	
	private void setCustomerObj(CustomerVO customerVO, Customer ac) {
		customerVO.setAge(ac.getAge());
		customerVO.setCustID(ac.getCustid());
		customerVO.setCustomerName(ac.getCustomerName());
		customerVO.setOccupation(ac.getOccupation());
		customerVO.setCreatedBy(ac.getCreatedBy());
		//customerVO.setCreatedTime(String.valueOf(ac.getCreatedTime()));
		
		List<AccountVO> set = new ArrayList<AccountVO>();
		
	/*	if(ac.getAccounts()!=null) {
			ac.getAccounts().forEach(acc->{
				AccountVO accVO = new AccountVO();
				accVO.setAcctbalance(acc.getAcctbalance());
				accVO.setAcctname(acc.getAcctname());
				accVO.setAccttype(acc.getAccttype());
				accVO.setAccID(acc.getAcctid());
				accVO.setCustID(Integer.valueOf(acc.getCustid()));
				set.add(accVO);
			}
					);
		}*/
		
		customerVO.setAccountVOs(set);
	}
	
	
}
