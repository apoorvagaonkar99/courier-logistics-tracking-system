package jsp.springbootproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jsp.springbootproject.entity.DeliveryAgent;

@Repository
public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent,Integer>{

	Optional<DeliveryAgent> findByVehicleNo(String vehicleNo);

	Optional<DeliveryAgent> findByContactNumber(String contactNumber);

	List<DeliveryAgent> findByRatingGreaterThan(double rating);



}
