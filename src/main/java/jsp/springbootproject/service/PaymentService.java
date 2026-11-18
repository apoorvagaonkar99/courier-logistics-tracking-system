package jsp.springbootproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.Payment;
import jsp.springbootproject.entity.PaymentStatus;
import jsp.springbootproject.exceptions.PaymentNotFoundException;
import jsp.springbootproject.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public ResponseStructure<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();

        ResponseStructure<List<Payment>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("All payments retrieved successfully");
        res.setData(payments);

        return res;
    }


	public ResponseStructure<Payment> getById(Integer id) {
        Optional<Payment> optional = paymentRepository.findById(id);

        ResponseStructure<Payment> res = new ResponseStructure<>();

        if (optional.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Payment retrieved successfully with id: " + id);
            res.setData(optional.get());
            return res;
        } else {
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }
    }
	
	public ResponseStructure<Payment> updateStatus(Integer id, PaymentStatus newStatus) {
        Optional<Payment> optional = paymentRepository.findById(id);

        ResponseStructure<Payment> res = new ResponseStructure<>();

        if (optional.isPresent()) {
            Payment payment = optional.get();
            payment.setPaymentStatus(newStatus);

            Payment updatedPayment = paymentRepository.save(payment);

            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Payment status updated successfully for id: " + id);
            res.setData(updatedPayment);
            return res;
        } else {
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }
    }
	
	public ResponseStructure<String> delete(Integer id) {
        ResponseStructure<String> res = new ResponseStructure<>();

        // if payment exists
        if (paymentRepository.findById(id).isPresent()) {
            Payment payment = paymentRepository.findById(id).get();
            paymentRepository.delete(payment);

            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Payment deleted successfully");
            res.setData("Deleted payment with id: " + id);
            return res;
        } 
        else 
        	throw new PaymentNotFoundException("Payment not found with id: " + id);
    }
}
	

