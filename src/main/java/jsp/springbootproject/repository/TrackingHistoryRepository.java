package jsp.springbootproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jsp.springbootproject.entity.ShipmentStatus;
import jsp.springbootproject.entity.TrackingHistory;

@Repository
public interface TrackingHistoryRepository extends JpaRepository<TrackingHistory,Integer>{

	List<TrackingHistory> findByShipment_TrackingNumber(String trackingNumber);

	List<TrackingHistory> findByStatus(ShipmentStatus status);


}
