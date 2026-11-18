package jsp.springbootproject.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAgent {

	private Integer id;
	private String name;
	
	@Column(unique=true,nullable=false)
	@Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
	private String phoneNumber;
	
	@Column(unique=true,nullable=false)
	private String vehicleNo;
	
	private Boolean availabiltyStatus;
	
	private Double rating;
	
	@OneToMany(mappedBy = "deliveryAgent")
	@JsonIgnore
	private List<Shipment> shipments;

	public boolean isAvailable() {
		return false;
	}


	
	
	
}
