package jsp.springbootproject.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.Shipment;
import jsp.springbootproject.entity.ShipmentStatus;
import jsp.springbootproject.service.ShipmentService;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

	
	@Autowired
    private ShipmentService shipmentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseStructure<Shipment>> createShipment(@RequestBody Shipment shipment) {
        return new ResponseEntity<>(shipmentService.createShipment(shipment),HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Shipment>>> getAllShipment() {
        return new ResponseEntity<>(shipmentService.getAllShipment(),HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Shipment>> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(shipmentService.getById(id),HttpStatus.OK);
    }
    
    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<ResponseStructure<Shipment>> getByTrackingNumber(@PathVariable String trackingNumber) {
        return new ResponseEntity<>(shipmentService.getByTrackingNumber(trackingNumber),HttpStatus.OK);
    }
    
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<ResponseStructure<Shipment>> updateStatus(@PathVariable Integer id,@PathVariable ShipmentStatus status) {
        return new ResponseEntity<>(shipmentService.updateStatus(id, status),HttpStatus.OK);
    }
    
    @PutMapping("/{shipmentId}/assign-agent/{agentId}")
    public ResponseEntity<ResponseStructure<Shipment>> assignDeliveryAgent(@PathVariable Integer shipmentId,@PathVariable Integer agentId) {
        return new ResponseEntity<>(shipmentService.assignDeliveryAgent(shipmentId, agentId),HttpStatus.OK);
    }
    
    @PutMapping("/{shipmentId}/assign-warehouse/{warehouseId}")
    public ResponseEntity<ResponseStructure<Shipment>> assignWarehouse( @PathVariable Integer shipmentId,@PathVariable Integer warehouseId) {
        return new ResponseEntity<>(shipmentService.assignWarehouse(shipmentId, warehouseId),HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteShipment(@PathVariable Integer id) {
        return new ResponseEntity<>(shipmentService.deleteShipment(id),HttpStatus.OK);
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ResponseStructure<List<Shipment>>> getByCustomer(@PathVariable Integer customerId) {
        return new ResponseEntity<>(shipmentService.getByCustomer(customerId),HttpStatus.OK);
    }
    
    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<ResponseStructure<List<Shipment>>> getByWarehouse(@PathVariable Integer warehouseId) {
        return new ResponseEntity<>(shipmentService.getByWarehouse(warehouseId),HttpStatus.OK);
    }
    
    @GetMapping("/agent/{agentId}")
    public ResponseEntity<ResponseStructure<List<Shipment>>> getByDeliveryAgent(@PathVariable Integer agentId) {
        return new ResponseEntity<>(shipmentService.getByDeliveryAgent(agentId),HttpStatus.OK);
    }
    
    @GetMapping("/route")
    public ResponseEntity<ResponseStructure<List<Shipment>>> getBySourceAndDestination(@RequestParam String source,@RequestParam String destination) {
        return new ResponseEntity<>(shipmentService.getBySourceAndDestination(source, destination),HttpStatus.OK);
    }
    
    @GetMapping("/delivery-date")
    public ResponseEntity<ResponseStructure<List<Shipment>>> getByDeliveryDate(@RequestParam LocalDateTime deliveryDate) {
        return new ResponseEntity<>(shipmentService.getByDeliveryDate(deliveryDate),HttpStatus.OK);
    }
    
    @GetMapping("/page/{pageNumber}/{pageSize}/sort/{fieldName}")
    public ResponseEntity<ResponseStructure<Page<Shipment>>> getByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String fieldName) {            
        return new ResponseEntity<>(shipmentService.getByPaginationAndSorting(pageNumber, pageSize, fieldName),HttpStatus.OK);
    }
    
//    @GetMapping("/page/{pageNumber}/{pageSize}/sort/{fieldName}/{direction}")
//    public ResponseEntity<ResponseStructure<Page<Shipment>>> getByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String fieldName,@PathVariable String direction) {            
//        return new ResponseEntity<>(shipmentService.getByPaginationAndSorting(pageNumber, pageSize, fieldName, direction),HttpStatus.OK);
//    }
}
