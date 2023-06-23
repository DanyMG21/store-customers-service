package com.store.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.customer.entity.Country;
import com.store.customer.entity.Customer;
import com.store.customer.service.ICustomerService;

@RestController
@RequestMapping("/api/store/customer")
public class CustomerController {

	@Autowired // BUSCA SI EXISTE UN BEAN QUE PUEDA PROPORCIONAR
	private ICustomerService customerService; // conectar controlador con services

	@GetMapping
	ResponseEntity<List<Customer>> listCustomer(@RequestParam(name = "idCountry", required = false) Integer idCountry) { // required
																															// =
																															// false
																															// ->
																															// dato
																															// opcional
		if (idCountry != null) {
			Country country = new Country();

			country.setId(idCountry);
			List<Customer> listCustomerByCountry = customerService.findByCountry(country);
			return ResponseEntity.ok(listCustomerByCountry);
		}
		List<Customer> listCustomers = customerService.listCustomers(); // stream

		if (listCustomers != null) {
			return ResponseEntity.ok(listCustomers);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// return productService.listProducts();

	}

	@GetMapping("/{id}")
	Customer findCustomer(@PathVariable("id") Long id) {

		return customerService.findCustomer(id);
	}

	@PostMapping
	Customer registerProduct(@Valid @RequestBody Customer customer) {

		return customerService.registerCustomer(customer);

	}

	@PutMapping("/{id}")
	Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {

		customer.setId(id);

		return customerService.updateCustomer(customer);
	}

	@DeleteMapping("/{id}")
	void deleteCustomer(@PathVariable("id") Long id) {

		customerService.deleteCustomer(id);
	}

	@GetMapping("/buscarPorApellido")
	List<Customer> buscarPorApellido(@RequestParam("lastName") String lastName) {
		return customerService.buscarLastName(lastName);
	}

	@GetMapping("/buscarPorPaisNombre")
	List<Customer> buscarPorPais(@RequestParam("name") String name) {
		return customerService.buscarPais(name);
	}
}
