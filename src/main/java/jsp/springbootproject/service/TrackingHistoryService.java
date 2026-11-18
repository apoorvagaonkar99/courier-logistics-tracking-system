package jsp.springbootproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.ShipmentStatus;
import jsp.springbootproject.entity.TrackingHistory;
import jsp.springbootproject.exceptions.TrackingHistoryNotFoundException;
import jsp.springbootproject.repository.TrackingHistoryRepository;

@Service
public class TrackingHistoryService {

	@Autowired
	private TrackingHistoryRepository trackingHistoryRepository;
	
	
	public ResponseStructure<List<TrackingHistory>> getAllTrackingHistory() {
        ResponseStructure<List<TrackingHistory>> res = new ResponseStructure<>();
        List<TrackingHistory> histories = trackingHistoryRepository.findAll();

        if (!histories.isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("List of all tracking histories");
            res.setData(histories);
            return res;
        } else {
            throw new TrackingHistoryNotFoundException("No tracking history records found");
        }
    }
	
	public ResponseStructure<TrackingHistory> getTrackingHistoryById(Integer historyId) {
        Optional<TrackingHistory> opt = trackingHistoryRepository.findById(historyId);
        ResponseStructure<TrackingHistory> res = new ResponseStructure<>();

        if (opt.isPresent())
        {
            TrackingHistory history = opt.get();
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Tracking history found successfully");
            res.setData(history);
            return res;
        }
        else 
            throw new TrackingHistoryNotFoundException("Tracking history not found with id: " + historyId);
        
    }
	
//	public ResponseStructure<List<TrackingHistory>> getByTrackingNo(String trackingNumber) {
//        List<TrackingHistory> histories = trackingHistoryRepository.findByShipment_TrackingNumber(trackingNumber);
//        ResponseStructure<List<TrackingHistory>> res = new ResponseStructure<>();
//
//        if (!histories.isEmpty()) {
//            res.setStatusCode(HttpStatus.OK.value());
//            res.setMessage("Tracking history retrieved successfully for tracking number: " + trackingNumber);
//            res.setData(histories);
//            return res;
//        } else {
//            throw new TrackingHistoryNotFoundException("No tracking history found for tracking number: " + trackingNumber);
//        }
//    }


	 public ResponseStructure<List<TrackingHistory>> getByStatus(ShipmentStatus status) {
	        List<TrackingHistory> histories = trackingHistoryRepository.findByStatus(status);

	        ResponseStructure<List<TrackingHistory>> res = new ResponseStructure<>();

	        if (!histories.isEmpty()) 
	        {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Tracking histories retrieved successfully for status: " + status);
	            res.setData(histories);
	            return res;
	        }
	        else {
	            throw new TrackingHistoryNotFoundException("No tracking history found with status: " + status);
	        }
	    }

	 
	
}
