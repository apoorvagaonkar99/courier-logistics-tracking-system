package jsp.springbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.ShipmentStatus;
import jsp.springbootproject.entity.TrackingHistory;
import jsp.springbootproject.service.TrackingHistoryService;

@RestController
@RequestMapping("/trackingHistory")
public class TrackingHistoryController {

	@Autowired
	private TrackingHistoryService trackingHistoryService;
	
	
	@GetMapping
    public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getAllTrackingHistory() {
        return new ResponseEntity<>(trackingHistoryService.getAllTrackingHistory(),HttpStatus.OK);
    }
	

	@GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<TrackingHistory>> getTrackingHistoryById(@PathVariable Integer id) {
        return new ResponseEntity<>(trackingHistoryService.getTrackingHistoryById(id),HttpStatus.OK);
    }
    
//	 @GetMapping("/tracking/{trackingNumber}")
//	    public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getByTrackingNo(
//	            @PathVariable String trackingNumber) {
//	        return ResponseEntity.ok(trackingHistoryService.getByTrackingNo(trackingNumber));
//	    }
	
	@GetMapping("/status/{status}")
    public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getByStatus(@PathVariable ShipmentStatus status) {
        return ResponseEntity.ok(trackingHistoryService.getByStatus(status));
    }
	
}
