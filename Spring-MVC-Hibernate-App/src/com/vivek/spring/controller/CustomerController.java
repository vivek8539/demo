package com.vivek.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vivek.spring.DAO.CustomerDAO;
import com.vivek.spring.hibernate.entity.Customer;
import com.vivek.spring.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject the DAO
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomer(Model model) {
		// get customer from Service
		List<Customer> theCustomers = customerService.getCustomers();
		// add the customer to the model
		model.addAttribute("customers", theCustomers);
		return "list-customers";
	}
	@GetMapping("/showFormForAdd")
	public String AddCustomer(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	@PostMapping("/saveForm")
	public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		System.out.println(customer);
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	@GetMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int theId, Model model) {
		// get the customer
		Customer customer = customerService.getCustomer(theId);
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	@GetMapping("/formForDelete")
	public String deleteCustomer(@RequestParam ("customerId") int theId) {
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}
	@GetMapping("/searchCustomer")
	public String Customer(@RequestParam("search") String search,Model model) {
		System.out.println(search);
		List<Customer> customers=customerService.getSearchCustomerList(search);
		model.addAttribute("customers", customers);
		
		model.addAttribute("search", search);
		return "list-customers";
	}
}
