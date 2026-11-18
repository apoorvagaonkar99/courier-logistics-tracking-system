package jsp.springbootproject.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jsp.springbootproject.entity.Shipment;
import jsp.springbootproject.entity.Warehouse;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Integer>{

	List<Shipment> findByCustomer_CustomerId(Integer customerId, String string);

	List<Warehouse> findByWarehouse_WarehouseId(Integer warehouseId, String string);

	List<Shipment> findByDeliveryAgent_IdAndStatus(Integer agentId, String string);

	Optional<Shipment> findByTrackingNumber(String trackingNumber);

	List<Shipment> findByCustomerId(Integer customerId);

	List<Shipment> findByWarehouseId(Integer warehouseId);

	List<Shipment> findByDeliveryAgentId(Integer agentId);

	List<Shipment> findBySourceAndDestination(String source, String destination);

	List<Shipment> findByDeliveryDate(LocalDateTime deliveryDate);


	

}
