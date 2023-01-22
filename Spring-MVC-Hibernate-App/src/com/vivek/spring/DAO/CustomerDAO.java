package com.vivek.spring.DAO;

import java.util.List;

import com.vivek.spring.hibernate.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> getSearchCustomerList(String search);
	

	
	
}
