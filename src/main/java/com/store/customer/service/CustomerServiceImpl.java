package com.store.customer.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.customer.entity.Country;
import com.store.customer.entity.Customer;
import com.store.customer.repository.ICustomerRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public List<Customer> listCustomers() {

		List<Customer> lista = customerRepository.findAll().stream().filter(x -> x.getStatus().equals("ACTIVO"))
				.collect(Collectors.toList());

		return lista;

	}

	@Override
	public Customer registerCustomer(Customer customer) {
		// save es como un insert o update
		customer.setStatus("ACTIVO");
		customer.setCreatedAt(LocalDateTime.now());
		return customerRepository.save(customer);
	}

	@Override
	public Customer findCustomer(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer customerBD = findCustomer(customer.getId());

		if (customerBD != null) {
			LocalDateTime fechaCreacion = customerBD.getCreatedAt();
			customer.setCreatedAt(fechaCreacion);

			customer.setUpdatedAt(LocalDateTime.now());
			return customerRepository.save(customer);
		}

		return null;
	}

	@Override
	public void deleteCustomer(Long id) {

		Customer customerDB = findCustomer(id);

		if (customerDB != null) {
			customerDB.setStatus("NO ACTIVO");
			customerDB.setDeletedAt(LocalDateTime.now());
			customerRepository.save(customerDB);

		}

	}

	@Override
	public List<Customer> findByCountry(Country country) {
		return customerRepository.findByCountry(country);
	}

	@Override
	public List<Customer> buscarLastName(String lastName) {
		return customerRepository.buscarClienteByLastName(lastName);
	}

	@Override
	public List<Customer> buscarPais(String name) {
		return customerRepository.buscarClienteByPais(name);
	}

}
