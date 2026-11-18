package jsp.springbootproject.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Shipment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String trackingNumber;
	
	private String source;
	private String destination;
	private Double weight;
	
	@CreationTimestamp
	private LocalDateTime shipmentDateTime;
	
	@CreationTimestamp
	private LocalDateTime deliveryDate;
	
	@Enumerated(EnumType.STRING)
	private ShipmentStatus status;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "package_id")
	private PackageEntity packageEntity;
	
	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	private Warehouse warehouse;
	
	@ManyToOne
	@JoinColumn(name = "delivery_agent_id")
	private DeliveryAgent deliveryAgent;
	
	@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
	private List<TrackingHistory> trackingHistory;
	
	
}
