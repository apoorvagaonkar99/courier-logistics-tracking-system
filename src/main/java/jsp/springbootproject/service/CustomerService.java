package jsp.springbootproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.Customer;
import jsp.springbootproject.entity.Shipment;
import jsp.springbootproject.exceptions.ActiveShipmentExistsException;
import jsp.springbootproject.exceptions.CustomerContactNotFoundException;
import jsp.springbootproject.exceptions.CustomerEmailNotFoundException;
import jsp.springbootproject.exceptions.CustomerIdNotFoundException;
import jsp.springbootproject.exceptions.NoCustomerFoundException;
import jsp.springbootproject.repository.CustomerRepository;
import jsp.springbootproject.repository.ShipmentRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
	
	public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

	public ResponseStructure<Customer> getCustomerById(Integer id) {
        Optional<Customer> opt= customerRepository.findById(id);
        ResponseStructure<Customer> res=new ResponseStructure<>();

        if (opt.isPresent()) {
            Customer customer = opt.get();
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Customer record retrieved successfully");
            res.setData(customer);
            return res;
        }
        else {
            
            throw new CustomerIdNotFoundException("Customer not found with id: " + id);
        }
    }
	

	public ResponseStructure<Customer> getCustomerByEmail(String email) {
	    Optional<Customer> opt = customerRepository.findByEmail(email);
	    ResponseStructure<Customer> res = new ResponseStructure<>();

	    if (opt.isPresent()) {
	        Customer customer = opt.get();
	        res.setStatusCode(HttpStatus.OK.value());
	        res.setMessage("Customer record retrieved successfully by email");
	        res.setData(customer);
	        return res;
	    } else {
	        throw new CustomerEmailNotFoundException("Customer not found with email: " + email);
	    }
	}

	
	public ResponseStructure<Customer> deleteCustomer(Integer customerId){
		Optional<Customer> opt=customerRepository.findById(customerId);
		ResponseStructure<Customer> res=new ResponseStructure<>();
		if(opt.isPresent()) 
		{
			Customer customer=opt.get();
			List<Shipment> activeShipment=shipmentRepository.findByCustomer_CustomerId(customerId,"ACTIVE");
			if(activeShipment.isEmpty()) 
			{
				customerRepository.delete(customer);
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Customer deleted successfully");
	            res.setData( customer);
	            return res;

			}
			else
			{
				throw new ActiveShipmentExistsException("Customer cannot be deleted,because active shipment exist");
			}
		}
		else
		{
			throw new CustomerIdNotFoundException("Customer not found with id:"+customerId);
		}
	}


	public ResponseStructure<Customer> updateCustomer(Integer customerId, Customer updatedCustomer) {
	    Optional<Customer> opt = customerRepository.findById(customerId);
	    ResponseStructure<Customer> res = new ResponseStructure<>();

	    if (opt.isPresent()) {
	        Customer customer = opt.get();

	        // Update only the fields you want to allow changes for
	        customer.setName(updatedCustomer.getName());
	        customer.setEmail(updatedCustomer.getEmail());
	        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
	        customer.setAddress(updatedCustomer.getAddress());
	        // add other fields as needed

	        Customer savedCustomer = customerRepository.save(customer);

	        res.setStatusCode(HttpStatus.OK.value());
	        res.setMessage("Customer updated successfully");
	        res.setData(savedCustomer);
	        return res;
	    } else {
	        throw new CustomerIdNotFoundException("Customer not found with id: " + customerId);
	    }
	}
	
	public ResponseStructure<Customer> getByContact(Long contactNumber) {
	    Optional<Customer> opt = customerRepository.findByContact(contactNumber);
	    ResponseStructure<Customer> res = new ResponseStructure<>();

	    if (opt.isPresent()) {
	        Customer customer = opt.get();
	        res.setStatusCode(HttpStatus.OK.value());
	        res.setMessage("Customer record retrieved successfully by contact number");
	        res.setData(customer);
	        return res;
	    }
	    else {
	        throw new CustomerContactNotFoundException("Customer not found with contact number: " + contactNumber);
	    }
	}
	
	public ResponseStructure<Page<Customer>> getCustomerByPaginationAndSorting(int pageNumber, int pageSize, String fieldName) {
	    Page<Customer> pages = customerRepository.findAll( PageRequest.of(pageNumber, pageSize, Sort.by(fieldName).ascending()));
	    ResponseStructure<Page<Customer>> res = new ResponseStructure<>();

	    if (!pages.isEmpty()) {
	        res.setStatusCode(HttpStatus.OK.value());
	        res.setMessage("Customer records retrieved with pagination and sorting");
	        res.setData(pages);
	        return res;
	    }
	    else {
	        throw new NoCustomerFoundException("No customers found for given page and sorting");
	    }
	}



	
}