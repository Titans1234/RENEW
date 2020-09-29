package com.lti.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Customer;
import com.lti.repo.CustomerRepo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class TestCustomer {
	@Autowired
	private CustomerRepo repo;
	
	@Test
	public void testAdd()
	{
		//ApplicationContext context=new ClassPathXmlApplicationContext("stereotypeConfig.xml");
		Customer cust=new Customer();
	     cust.setName("Saurabh");
	     cust.setAge(22);
	     cust.setEmail("sahisaurabh50@gmail.com");
	     cust.setPassword("Titans");
	     cust.setContact("8873056889");
	     cust.setGender("Male");
		repo.Add(cust);
	}
	
	@Test 
	public void testFetch()
	{
		
	 Customer f=repo.Fetch(10103); 
	 System.out.println(f.toString());
	
	}
	@Test
	public void testList()
	{
		
		  List<Customer> p=new ArrayList<Customer>();
		  p=repo.fetchAll(); 
		  for(Customer c:p) 
		  { 
			  System.out.println(c.toString()); 
		  }
		 
	}
	@Test
	public void deleteCustomer()
	{
		repo.delete(10101);
	}
	
}
