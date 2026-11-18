package jsp.springbootproject.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jsp.springbootproject.dto.ResponseStructure;



@RestControllerAdvice
public class GlobalExceptionHandler {
		 @ExceptionHandler(NoCustomerFoundException.class)
			public ResponseEntity<ResponseStructure<String>> handleNCFE(NoCustomerFoundException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(CustomerIdNotFoundException.class)
			public ResponseEntity<ResponseStructure<String>> handleCINFE(CustomerIdNotFoundException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(CustomerEmailNotFoundException.class)
			public ResponseEntity<ResponseStructure<String>> handleCENFE(CustomerEmailNotFoundException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(ActiveShipmentExistsException.class)
			public ResponseEntity<ResponseStructure<String>> handleASEE(ActiveShipmentExistsException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(CustomerContactNotFoundException.class)
			public ResponseEntity<ResponseStructure<String>> handleCCNFE(CustomerContactNotFoundException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(WarehouseNotFoundException.class)
			public ResponseEntity<ResponseStructure<String>> handleWNFE(WarehouseNotFoundException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(WarehouseIdNotFoundException.class)
			public ResponseEntity<ResponseStructure<String>> handleWINFE(WarehouseIdNotFoundException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}


		 @ExceptionHandler(NoAgentFoundException.class)
			public ResponseEntity<ResponseStructure<String>> handleNAFE(NoAgentFoundException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(PackageNotFoundException.class)
			public ResponseEntity<ResponseStructure<String>> handlePNFE(PackageNotFoundException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}

		 @ExceptionHandler(TrackingHistoryNotFoundException.class)
			public ResponseEntity<ResponseStructure<String>> handleTHNFE(TrackingHistoryNotFoundException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(PaymentNotFoundException.class)
			public ResponseEntity<ResponseStructure<String>> handlePNFE(PaymentNotFoundException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(AgentUnavailableException.class)
			public ResponseEntity<ResponseStructure<String>> handleANAE(AgentUnavailableException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(ShipmentCreationException.class)
			public ResponseEntity<ResponseStructure<String>> handleSCE(ShipmentCreationException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(PackageAlreadyExistsException.class)
			public ResponseEntity<ResponseStructure<String>> handlePAEE(PackageAlreadyExistsException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
		 
		 @ExceptionHandler(ShipmentNotFoundException.class)
			public ResponseEntity<ResponseStructure<String>> handleSNFE(ShipmentNotFoundException exception)
			{
				ResponseStructure<String> res=new ResponseStructure<>();
				res.setStatusCode(HttpStatus.NOT_FOUND.value());
				res.setMessage(exception.getMessage());
				res.setData("Failure");
				return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
				
			}
}
