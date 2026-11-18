package jsp.springbootproject.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

	private Integer id;
	private Double amount;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@CreationTimestamp
	private LocalDateTime time;
	
	@OneToOne(mappedBy = "payment")
	@JsonIgnore
	private Shipment shipment;
}
