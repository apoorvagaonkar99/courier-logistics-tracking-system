package jsp.springbootproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jsp.springbootproject.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

	Optional<Customer> findByEmail(String email);

	Optional<Customer> findByContact(Long contactNumber);



}
