package com.lti.repo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.lti.entity.Customer;;

@Repository
public class CustomerRepoImpl implements CustomerRepo{
     
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void Add(Customer cust) {
		// TODO Auto-generated method stub
		em.persist(cust);
	}

	@Override
	public List<Customer> fetchAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Customer").getResultList();
	}

	@Override
	public Customer Fetch(int custid) {
		// TODO Auto-generated method stub
		Customer emp=em.find(Customer.class,custid);
		return emp;
	}

	@Transactional(value=TxType.REQUIRED)
	public void delete(int custid) {
		// TODO Auto-generated method stub
		Customer c1= em.find(Customer.class,custid);
		 em.remove(c1);
		
	}
   
}
