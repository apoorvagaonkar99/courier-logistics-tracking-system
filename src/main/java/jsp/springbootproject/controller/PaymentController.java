package jsp.springbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.Payment;
import jsp.springbootproject.entity.PaymentStatus;
import jsp.springbootproject.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	 @Autowired
	 private PaymentService paymentService;
	

	@GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayments() {
        return new ResponseEntity<>(paymentService.getAllPayments(),HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Payment>> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(paymentService.getById(id),HttpStatus.OK);
    }
	
	@PutMapping("/{id}/status/{status}")
    public ResponseEntity<ResponseStructure<Payment>> updateStatus(@PathVariable Integer id,@PathVariable PaymentStatus status) {
        return new ResponseEntity<>(paymentService.updateStatus(id, status),HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(paymentService.delete(id),HttpStatus.OK);
    }
}
