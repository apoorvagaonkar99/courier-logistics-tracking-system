package jsp.springbootproject.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Warehouse {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String location;
	private String capacity;
	
	@Column(unique=true,nullable=false)
	@Pattern(regexp = "\\d{10}", message = "Contact number must be exactly 10 digits")
	private String contactNo;
	
	@OneToMany(mappedBy = "warehouse")
	@JsonIgnore
	private List<Shipment> shipments;
}
