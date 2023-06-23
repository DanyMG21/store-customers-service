package com.store.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.customer.entity.Country;
import com.store.customer.entity.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByCountry(Country country);

	@Query(value = "SELECT * FROM customer WHERE last_name LIKE %:lastname%", nativeQuery = true)
	List<Customer> buscarClienteByLastName(@Param("lastname") String lastname);

	@Query(value = "SELECT * FROM storecustomer.customer ssc inner join storecustomer.country sc on sc.id= ssc.country_id where sc.name like %:name%", nativeQuery = true)
	List<Customer> buscarClienteByPais(@Param("name") String name);

}
