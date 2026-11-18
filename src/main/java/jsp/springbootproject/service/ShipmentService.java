package jsp.springbootproject.service;

import java.time.LocalDateTime;
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
import jsp.springbootproject.entity.DeliveryAgent;
import jsp.springbootproject.entity.Shipment;
import jsp.springbootproject.entity.ShipmentStatus;
import jsp.springbootproject.entity.Warehouse;
import jsp.springbootproject.exceptions.ActiveShipmentExistsException;
import jsp.springbootproject.exceptions.AgentUnavailableException;
import jsp.springbootproject.exceptions.CustomerIdNotFoundException;
import jsp.springbootproject.exceptions.PackageAlreadyExistsException;
import jsp.springbootproject.exceptions.ShipmentCreationException;
import jsp.springbootproject.exceptions.ShipmentNotFoundException;
import jsp.springbootproject.exceptions.WarehouseNotFoundException;
import jsp.springbootproject.repository.CustomerRepository;
import jsp.springbootproject.repository.DeliveryAgentRepository;
import jsp.springbootproject.repository.PackageEntityRepository;
import jsp.springbootproject.repository.PaymentRepository;
import jsp.springbootproject.repository.ShipmentRepository;
import jsp.springbootproject.repository.WarehouseRepository;

@Service
public class ShipmentService {

	 @Autowired
	    private ShipmentRepository shipmentRepository;

	    @Autowired
	    private CustomerRepository customerRepository;

	    @Autowired
	    private WarehouseRepository warehouseRepository;

	    @Autowired
	    private DeliveryAgentRepository deliveryAgentRepository;

	    @Autowired
	    private PackageEntityRepository packageEntityRepository;

	    @Autowired
	    private PaymentRepository paymentRepository;    
	    
	    public ResponseStructure<Shipment> createShipment(Shipment shipment) {
	        ResponseStructure<Shipment> res = new ResponseStructure<>();

	        // Validate Customer
	        Optional<Customer> customerOpt = customerRepository.findById(shipment.getCustomer().getId());
	        if (customerOpt.isPresent()) 
	        {
	            shipment.setCustomer(customerOpt.get());
	        }
	        else 
	        {
	            throw new CustomerIdNotFoundException("Invalid customer ID: " + shipment.getCustomer().getId());
	        }

	        // Validate Warehouse
	        Optional<Warehouse> warehouseOpt = warehouseRepository.findById(shipment.getWarehouse().getId());
	        if (warehouseOpt.isPresent()) 
	        {
	            shipment.setWarehouse(warehouseOpt.get());
	        }
	        else 
	        {
	            throw new WarehouseNotFoundException("Invalid warehouse ID: " + shipment.getWarehouse().getId());
	        }

	        // Validate DeliveryAgent availability
	        Optional<DeliveryAgent> agentOpt = deliveryAgentRepository.findById(shipment.getDeliveryAgent().getId());
	        if (agentOpt.isPresent()) {
	            DeliveryAgent agent = agentOpt.get();
	            if (agent.isAvailable()) 
	            {
	                shipment.setDeliveryAgent(agent);
	            }
	            else 
	            {
	                throw new AgentUnavailableException("Agent " + agent.getName() + " is currently unavailable");
	            }
	        }
	        else 
	        {
	            throw new ShipmentCreationException("Invalid delivery agent ID: " + shipment.getDeliveryAgent().getId());
	        }

	        // Ensure Package No is unique
	        if (packageEntityRepository.existsByPackageNo(shipment.getPackageEntity().getPackageNo())) 
	        {
	            throw new PackageAlreadyExistsException("Package number already exists: " + shipment.getPackageEntity().getPackageNo());
	        }
	        else 
	        {
	            packageEntityRepository.save(shipment.getPackageEntity());
	        }

	        // Calculate Pricing
	        double basePrice = shipment.getWeight() * 50; // Example: ₹50 per kg
	        if (shipment.getPackageEntity().isFragile()) 
	        {
	            basePrice += 200; // Extra charge for fragile items
	        }
	        shipment.getPayment().setAmount(basePrice);

	        // Save Payment
	        paymentRepository.save(shipment.getPayment());

	        // Save Shipment
	        Shipment savedShipment = shipmentRepository.save(shipment);

	        res.setStatusCode(HttpStatus.CREATED.value());
	        res.setMessage("Shipment created successfully with tracking number: " + savedShipment.getTrackingNumber());
	        res.setData(savedShipment);

	        return res;
	    }
	    
	    public ResponseStructure<List<Shipment>> getAllShipment() {
	        ResponseStructure<List<Shipment>> res = new ResponseStructure<>();

	        List<Shipment> shipments = shipmentRepository.findAll();

	        if (!shipments.isEmpty()) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("All shipments retrieved successfully");
	            res.setData(shipments);
	            return res;
	        }
	        else 
	            throw new ShipmentNotFoundException("No shipments found in the system");
	        

	      
	    }
	    
	    public ResponseStructure<Shipment> getById(Integer id) {
	        ResponseStructure<Shipment> res = new ResponseStructure<>();

	        Optional<Shipment> shipmentOpt = shipmentRepository.findById(id);

	        if (shipmentOpt.isPresent()) {
	            Shipment shipment = shipmentOpt.get();
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Shipment retrieved successfully with id: " + id);
	            res.setData(shipment);
	            return res;
	        }
	        else 
	            throw new ShipmentNotFoundException("Shipment not found with id: " + id);
	        	        
	    }
	    
	    public ResponseStructure<Shipment> getByTrackingNumber(String trackingNumber) {
	        ResponseStructure<Shipment> res = new ResponseStructure<>();
	        Optional<Shipment> shipmentOpt = shipmentRepository.findByTrackingNumber(trackingNumber);

	        if (shipmentOpt.isPresent()) {
	            Shipment shipment = shipmentOpt.get();
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Shipment retrieved successfully with tracking number: " + trackingNumber);
	            res.setData(shipment);
	            return res;
	        }
	        else 
	            throw new ShipmentNotFoundException("Shipment not found with tracking number: " + trackingNumber);	       
	        
	    }
	    
	    public ResponseStructure<Shipment> updateStatus(Integer id, ShipmentStatus newStatus) {
	        ResponseStructure<Shipment> res = new ResponseStructure<>();

	        Optional<Shipment> shipmentOpt = shipmentRepository.findById(id);

	        if (shipmentOpt.isPresent()) {
	            Shipment shipment = shipmentOpt.get();
	            shipment.setStatus(newStatus);

	            Shipment updatedShipment = shipmentRepository.save(shipment);

	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Shipment status updated successfully for id: " + id);
	            res.setData(updatedShipment);
	            return res;
	        }
	        else 
	            throw new ShipmentNotFoundException("Shipment not found with id: " + id);
	        

	        
	    }
	    
	    public ResponseStructure<Shipment> assignDeliveryAgent(Integer shipmentId, Integer agentId) {
	        ResponseStructure<Shipment> res = new ResponseStructure<>();

	        Optional<Shipment> shipmentOpt = shipmentRepository.findById(shipmentId);
	        Optional<DeliveryAgent> agentOpt = deliveryAgentRepository.findById(agentId);

	        // Check shipment existence
	        if (shipmentOpt.isPresent()) 
	        {
	            Shipment shipment = shipmentOpt.get();

	            // Check agent existence
	            if (agentOpt.isPresent()) 
	            {
	                DeliveryAgent agent = agentOpt.get();

	                // Check agent availability
	                if (agent.getAvailabiltyStatus() != null && agent.getAvailabiltyStatus()) 
	                {
	                    shipment.setDeliveryAgent(agent);
	                    Shipment updatedShipment = shipmentRepository.save(shipment);

	                    res.setStatusCode(HttpStatus.OK.value());
	                    res.setMessage("Delivery agent assigned successfully to shipment id: " + shipmentId);
	                    res.setData(updatedShipment);
	                    return res;
	                }
	                else 
	                {
	                    throw new AgentUnavailableException("Agent " + agent.getName() + " is currently unavailable");
	                }
	            }
	            else 
	            {
	                throw new AgentUnavailableException("Invalid delivery agent ID: " + agentId);
	            }
	        }
	        else 
	        {
	            throw new ShipmentNotFoundException("Shipment not found with id: " + shipmentId);
	        }

	       
	    }
	    
	    public ResponseStructure<Shipment> assignWarehouse(Integer shipmentId, Integer warehouseId) {
	        ResponseStructure<Shipment> res = new ResponseStructure<>();

	        Optional<Shipment> shipmentOpt = shipmentRepository.findById(shipmentId);
	        Optional<Warehouse> warehouseOpt = warehouseRepository.findById(warehouseId);

	        // Check shipment existence
	        if (shipmentOpt.isPresent()) 
	        {
	            Shipment shipment = shipmentOpt.get();

	            // Check warehouse existence
	            if (warehouseOpt.isPresent()) 
	            {
	                Warehouse warehouse = warehouseOpt.get();

	                shipment.setWarehouse(warehouse);
	                Shipment updatedShipment = shipmentRepository.save(shipment);

	                res.setStatusCode(HttpStatus.OK.value());
	                res.setMessage("Warehouse assigned successfully to shipment id: " + shipmentId);
	                res.setData(updatedShipment);
	                return res;
	            }
	            else {
	                throw new WarehouseNotFoundException("Warehouse not found with id: " + warehouseId);
	            }
	        }
	        else {
	            throw new ShipmentNotFoundException("Shipment not found with id: " + shipmentId);
	        }
	       
	    }


	    public ResponseStructure<String> deleteShipment(Integer id) {
	        ResponseStructure<String> res = new ResponseStructure<>();

	        Optional<Shipment> shipmentOpt = shipmentRepository.findById(id);

	        if (shipmentOpt.isPresent()) {
	            Shipment shipment = shipmentOpt.get();

	            // Prevent deletion if shipment is ACTIVE
	            if (shipment.getStatus() != null && shipment.getStatus().name().equalsIgnoreCase("ACTIVE")) 
	            {
	                throw new ActiveShipmentExistsException("Shipment cannot be deleted because it is ACTIVE");
	            }
	            else {
	                shipmentRepository.delete(shipment);

	                res.setStatusCode(HttpStatus.OK.value());
	                res.setMessage("Shipment deleted successfully");
	                res.setData("Deleted shipment with id: " + id);
	                return res;
	            }
	        }
	        else {
	            throw new ShipmentNotFoundException("Shipment not found with id: " + id);
	        }

	        
	    }
	    
	    public ResponseStructure<List<Shipment>> getByCustomer(Integer customerId) {
	        ResponseStructure<List<Shipment>> res = new ResponseStructure<>();

	        List<Shipment> shipments = shipmentRepository.findByCustomerId(customerId);

	        if (!shipments.isEmpty()) 
	        {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Shipments retrieved successfully for customer id: " + customerId);
	            res.setData(shipments);
	            return res;
	        }
	        else 
	            throw new ShipmentNotFoundException("No shipments found for customer id: " + customerId);
	        
	    }
	    
	    public ResponseStructure<List<Shipment>> getByWarehouse(Integer warehouseId) {
	        ResponseStructure<List<Shipment>> res = new ResponseStructure<>();

	        List<Shipment> shipments = shipmentRepository.findByWarehouseId(warehouseId);

	        if (!shipments.isEmpty()) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Shipments retrieved successfully for warehouse id: " + warehouseId);
	            res.setData(shipments);
	            return res;
	        }
	        else 
	            throw new ShipmentNotFoundException("No shipments found for warehouse id: " + warehouseId);
	        	        
	    }
	    
	    public ResponseStructure<List<Shipment>> getByDeliveryAgent(Integer agentId) {
	        ResponseStructure<List<Shipment>> res = new ResponseStructure<>();

	        List<Shipment> shipments = shipmentRepository.findByDeliveryAgentId(agentId);

	        if (!shipments.isEmpty()) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Shipments retrieved successfully for delivery agent id: " + agentId);
	            res.setData(shipments);
	            return res;
	        }
	        else 
	            throw new ShipmentNotFoundException("No shipments found for delivery agent id: " + agentId);
	        	        
	    }
	     
	    public ResponseStructure<List<Shipment>> getBySourceAndDestination(String source, String destination) {
	        ResponseStructure<List<Shipment>> res = new ResponseStructure<>();

	        List<Shipment> shipments = shipmentRepository.findBySourceAndDestination(source, destination);

	        if (!shipments.isEmpty()) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Shipments retrieved successfully from " + source + " to " + destination);
	            res.setData(shipments);
	            return res;
	        }
	        else 
	            throw new ShipmentNotFoundException("No shipments found from " + source + " to " + destination);
	        

	        
	    }
	    
	    public ResponseStructure<List<Shipment>> getByDeliveryDate(LocalDateTime deliveryDate) {
	        ResponseStructure<List<Shipment>> res = new ResponseStructure<>();

	        List<Shipment> shipments = shipmentRepository.findByDeliveryDate(deliveryDate);

	        if (!shipments.isEmpty()) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Shipments retrieved successfully for delivery date: " + deliveryDate);
	            res.setData(shipments);
	            return res;
	        }
	        else 
	            throw new ShipmentNotFoundException("No shipments found for delivery date: " + deliveryDate);
	        	      
	    }
	    
	    public ResponseStructure<Page<Shipment>> getByPaginationAndSorting(int pageNumber, int pageSize, String fieldName) {
	        ResponseStructure<Page<Shipment>> res = new ResponseStructure<>();

	        //  sort ascending by the given field
	        Page<Shipment> shipments = shipmentRepository.findAll(
	                PageRequest.of(pageNumber, pageSize, Sort.by(fieldName).ascending()));
	       
	        if (!shipments.isEmpty()) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Shipments retrieved successfully with pagination and sorting by " + fieldName);
	            res.setData(shipments);
	            return res;
	        }
	        else 
	            throw new ShipmentNotFoundException("No shipments found for given pagination and sorting criteria");	        
	        
	    }

		
	    
//	    public ResponseStructure<Page<Shipment>> getByPaginationAndSorting(int pageNumber, int pageSize, String fieldName, String sortDirection) {
//	        ResponseStructure<Page<Shipment>> res = new ResponseStructure<>();
//
//	        Sort sort;
//	        if ("asc".equalsIgnoreCase(sortDirection)) 
//	        {
//	            sort = Sort.by(fieldName).ascending();
//	        }
//	        else if ("desc".equalsIgnoreCase(sortDirection)) 
//	        {
//	            sort = Sort.by(fieldName).descending();
//	        }
//	        else 
//	        {
//	            throw new ShipmentNotFoundException("Invalid sort direction: " + sortDirection);
//	        }
//
//	        Page<Shipment> shipments = shipmentRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
//
//	        if (!shipments.isEmpty()) {
//	            res.setStatusCode(HttpStatus.OK.value());
//	            res.setMessage("Shipments retrieved successfully with pagination and sorting");
//	            res.setData(shipments);
//	            return res;
//	        }
//	        else 
//	            throw new ShipmentNotFoundException("No shipments found for given pagination and sorting criteria");
//	        
//
//	        
//	    }
}

