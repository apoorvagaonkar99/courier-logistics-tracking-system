package jsp.springbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.DeliveryAgent;
import jsp.springbootproject.service.DeliveryAgentService;

@RestController
@RequestMapping("/deliveryAgent")
public class DeliveryAgentController {

	@Autowired
	private DeliveryAgentService deliveryAgentService;
	
	 @PostMapping("/create")
	    public ResponseEntity<ResponseStructure<DeliveryAgent>> createAgent(@RequestBody DeliveryAgent agent) {
	        return new ResponseEntity<>(deliveryAgentService.createAgent(agent),HttpStatus.CREATED);
	    }
	
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getAllAgents(){
		return new ResponseEntity<>(deliveryAgentService.getAllAgents(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<DeliveryAgent>> getAgentById(@PathVariable int id) {
        return new ResponseEntity<>(deliveryAgentService.getAgentById(id),HttpStatus.OK);
    }
	
	@GetMapping("/vehicle/{vehicleNo}")
    public ResponseEntity<ResponseStructure<DeliveryAgent>> getAgentByVehicleNo(@PathVariable String vehicleNo) {
        return new ResponseEntity<>(deliveryAgentService.getByVehicleNo(vehicleNo),HttpStatus.OK);
	}
    
	
	@GetMapping("/contact/{contactNumber}")
    public ResponseEntity<ResponseStructure<DeliveryAgent>> getAgentByContact(@PathVariable String contactNumber) {
        return new ResponseEntity<>(deliveryAgentService.getByContact(contactNumber),HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> deleteAgent(@PathVariable Integer id) {
	    return new ResponseEntity<>(deliveryAgentService.deleteAgent(id),HttpStatus.OK);
	}
	
	@PatchMapping("/{id}/availability")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> updateAgentAvailability(@PathVariable Integer id,@RequestParam Boolean availabilityStatus) {
	    return new ResponseEntity<>(deliveryAgentService.updateAgentAvailability(id, availabilityStatus),HttpStatus.OK);
	}


	
}