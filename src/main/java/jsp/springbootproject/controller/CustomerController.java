package jsp.springbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.Customer;
import jsp.springbootproject.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController{
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer),HttpStatus.OK);
    }
	
	@GetMapping("/all")
	 public ResponseEntity<List<Customer>> getAllCustomers() {
	        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
	    }
	
	@GetMapping("/id/{id}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable Integer id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }
	
	@GetMapping("/email/{email}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByEmail(@PathVariable String email) {
        return new ResponseEntity<>(customerService.getCustomerByEmail(email), HttpStatus.OK);
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@PathVariable Integer customerId) {
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);
    }
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@PathVariable Integer id,@RequestBody Customer updatedCustomer){
	    return new ResponseEntity<>(customerService.updateCustomer(id, updatedCustomer), HttpStatus.OK);
	}
	
	@GetMapping("/contact/{contact}")
	public ResponseEntity<ResponseStructure<Customer>> getByContact(@PathVariable Long contactNumber) {
        return new ResponseEntity<>(customerService.getByContact(contactNumber), HttpStatus.OK);
    }
	
	@GetMapping("/customers/page/{pageNumber}/{pageSize}/sort/{fieldName}")
	public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize, @PathVariable String fieldName) {
	    return new ResponseEntity<>(customerService.getCustomerByPaginationAndSorting(pageNumber, pageSize, fieldName), HttpStatus.OK);
	}

}