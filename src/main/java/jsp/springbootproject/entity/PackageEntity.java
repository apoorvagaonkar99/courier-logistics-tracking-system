package jsp.springbootproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class PackageEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private PackageType packageType;
	
	private Boolean fragile;
	private Double dimensions;
	
	@OneToOne(mappedBy = "packageEntity")
	@JsonIgnore
	private Shipment shipment;

	public boolean isFragile() {
		return false;
	}

	public Object getPackageNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
