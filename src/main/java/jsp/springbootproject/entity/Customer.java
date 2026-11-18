package jsp.springbootproject.entity;

import jakarta.validation.constraints.Pattern;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@Column(unique=true ,nullable=false)
	private String email;
	
	@Column(unique=true ,nullable=false)
	@Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
	private String phoneNumber;
	
	private String address;

	  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	  @JsonIgnore
	  private List<Shipment> shipments;
	
	
	
}
