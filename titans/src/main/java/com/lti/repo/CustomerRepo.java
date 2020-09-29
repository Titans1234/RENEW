package com.lti.repo;

import java.util.List;

import com.lti.entity.Customer;

public interface CustomerRepo {
	public void Add(Customer cust);
	public List<Customer> fetchAll();
	public Customer Fetch(int custid);
	public void delete(int custid);
}
