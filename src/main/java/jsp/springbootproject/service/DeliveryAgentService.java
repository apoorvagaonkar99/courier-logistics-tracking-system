package jsp.springbootproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.DeliveryAgent;
import jsp.springbootproject.entity.Shipment;
import jsp.springbootproject.exceptions.ActiveShipmentExistsException;
import jsp.springbootproject.exceptions.NoAgentFoundException;
import jsp.springbootproject.repository.DeliveryAgentRepository;
import jsp.springbootproject.repository.ShipmentRepository;

@Service
public class DeliveryAgentService {

	@Autowired
	private DeliveryAgentRepository deliveryAgentRepository;
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	public ResponseStructure<DeliveryAgent> createAgent(DeliveryAgent agent) {
        ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
        DeliveryAgent savedAgent = deliveryAgentRepository.save(agent);

        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Delivery Agent created successfully");
        res.setData(savedAgent);
        return res;
    }
	
	public ResponseStructure<List<DeliveryAgent>> getAllAgents() {
        ResponseStructure<List<DeliveryAgent>> response = new ResponseStructure<>();
        List<DeliveryAgent> agents = deliveryAgentRepository.findAll();
        if(!agents.isEmpty()) 
        {
        	response.setStatusCode(HttpStatus.OK.value());
        	response.setMessage("List of all delivery agents");
        	response.setData(agents);
        	return response;
        }
        else 
        	throw new NoAgentFoundException("No delivery agents found in the database");
	}
	

	public ResponseStructure<DeliveryAgent> getAgentById(int id) {
	        ResponseStructure<DeliveryAgent> response = new ResponseStructure<>();
	        Optional<DeliveryAgent> optionalAgent = deliveryAgentRepository.findById(id);

	        if (optionalAgent.isPresent()) {
	            response.setStatusCode(HttpStatus.OK.value());
	            response.setMessage("Delivery Agent found successfully");
	            response.setData(optionalAgent.get());
	            return response;
	        } else {
	            throw new NoAgentFoundException("Delivery Agent with ID " + id + " not found");
	        }
	}
	
	public ResponseStructure<DeliveryAgent> getByVehicleNo(String vehicleNo) {
        ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
        Optional<DeliveryAgent> opt = deliveryAgentRepository.findByVehicleNo(vehicleNo);
        if (opt.isPresent()) 
        {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Delivery Agent found successfully");
            res.setData(opt.get());
            return res;
        }
        else 
            throw new NoAgentFoundException("Delivery Agent with Vehicle No " + vehicleNo + " not found");
        
    }
	
	public ResponseStructure<DeliveryAgent> getByContact(String contactNumber) {
        ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
        Optional<DeliveryAgent> opt = deliveryAgentRepository.findByContactNumber(contactNumber);

        if (opt.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Delivery Agent found successfully");
            res.setData(opt.get());
            return res;
        }
        else 
            throw new NoAgentFoundException("Delivery Agent with contact number " + contactNumber + " not found");
      
	}
	
	public ResponseStructure<List<DeliveryAgent>> getByRatingGreaterThan(double rating) {
        ResponseStructure<List<DeliveryAgent>> response = new ResponseStructure<>();
        List<DeliveryAgent> agents = deliveryAgentRepository.findByRatingGreaterThan(rating);

        if (!agents.isEmpty()) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Agents with rating greater than " + rating);
            response.setData(agents);
            return response;
        }
        else 
            throw new NoAgentFoundException("No agents found with rating greater than " + rating);
        
    }
	
	public ResponseStructure<DeliveryAgent> updateAgent(Integer agentId, DeliveryAgent updatedAgent) {
	    Optional<DeliveryAgent> opt = deliveryAgentRepository.findById(agentId);
	    ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();

	    if (opt.isPresent()) 
	    {
	        DeliveryAgent existingAgent = opt.get();

	        // Update fields
	        existingAgent.setName(updatedAgent.getName());
	        existingAgent.setPhoneNumber(updatedAgent.getPhoneNumber());
	        existingAgent.setVehicleNo(updatedAgent.getVehicleNo());
	        existingAgent.setAvailabiltyStatus(updatedAgent.getAvailabiltyStatus());
	        existingAgent.setRating(updatedAgent.getRating());

	        DeliveryAgent savedAgent = deliveryAgentRepository.save(existingAgent);

	        res.setStatusCode(HttpStatus.OK.value());
	        res.setMessage("Delivery Agent updated successfully");
	        res.setData(savedAgent);
	        return res;
	    }
	    else 
	        throw new NoAgentFoundException("Delivery Agent not found with id: " + agentId);
	    
	}
	
	public ResponseStructure<DeliveryAgent> deleteAgent(Integer agentId) {
	    Optional<DeliveryAgent> opt = deliveryAgentRepository.findById(agentId);
	    ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();

	    if (opt.isPresent()) 
	    {
	        DeliveryAgent agent = opt.get();

	        // Check if active shipments are assigned to this agent
	        List<Shipment> activeShipments = shipmentRepository.findByDeliveryAgent_IdAndStatus(agentId, "ACTIVE");

	        if (activeShipments.isEmpty()) {
	            deliveryAgentRepository.delete(agent);
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Delivery Agent deleted successfully");
	            res.setData(agent);
	            return res;
	        }
	        else {
	            throw new ActiveShipmentExistsException("Delivery Agent cannot be deleted because active shipments are assigned");
	        }
	    }
	    else 
	        throw new NoAgentFoundException("Delivery Agent not found with id: " + agentId);
	    
	}

	public ResponseStructure<DeliveryAgent> updateAgentAvailability(Integer agentId, Boolean availabilityStatus) {
	    Optional<DeliveryAgent> opt = deliveryAgentRepository.findById(agentId);
	    ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();

	    if (opt.isPresent()) {
	        DeliveryAgent agent = opt.get();
	        agent.setAvailabiltyStatus(availabilityStatus);

	        DeliveryAgent savedAgent = deliveryAgentRepository.save(agent);

	        res.setStatusCode(HttpStatus.OK.value());
	        res.setMessage("Agent availability updated successfully");
	        res.setData(savedAgent);
	        return res;
	    } else {
	        throw new NoAgentFoundException("Delivery Agent not found with id: " + agentId);
	    }
	}


}
