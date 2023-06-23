package com.store.customer.service;

import java.util.List;

import com.store.customer.entity.Country;
import com.store.customer.entity.Customer;

public interface ICustomerService {

	List<Customer> listCustomers();

	Customer registerCustomer(Customer customer);

	Customer findCustomer(Long id);

	Customer updateCustomer(Customer customer);

	void deleteCustomer(Long id);

	List<Customer> findByCountry(Country country);

	List<Customer> buscarLastName(String lastName);

	List<Customer> buscarPais(String name);
}
