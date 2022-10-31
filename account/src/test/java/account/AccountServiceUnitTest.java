package account;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.web.account.entity.Customer;
import com.web.account.repo.CustomerRepo;
import com.web.account.service.AccountServiceImpl;
import com.web.account.vo.CustomerVO;

//@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceUnitTest {
	
	@InjectMocks
	AccountServiceImpl accountService;
	
	@Mock
	CustomerRepo customerRepo;
	
	/*
	 * @Before public void before() { this.customerRepo=
	 * Mockito.mock(CustomerRepo.class); }
	 */
	
	@Test
	public void testFindTheGreatestFromAllData() {
		List<CustomerVO> custList = new ArrayList<>();
		CustomerVO customerVO = new CustomerVO();
		customerVO.setCustomerName("Test");
		custList.add(customerVO);
		
		List<Customer> customers = new ArrayList<>();
		Customer customer = new Customer();
		customer.setAge(32);
		customers.add(customer);
		
		Mockito.when(customerRepo.findAll()).thenReturn(customers);
		
	//	AccountServiceImpl accountService= new AccountServiceImpl();
	//	accountService.setCustomerRepo(customerRepo);
		
		List<CustomerVO> testName = accountService.getAllAccount();
		Assert.assertTrue(testName.size()>0);
	}
}
