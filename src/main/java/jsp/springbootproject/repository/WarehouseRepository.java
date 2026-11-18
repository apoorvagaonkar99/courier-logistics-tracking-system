package jsp.springbootproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jsp.springbootproject.entity.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Integer>{

	List<Warehouse> findByLocation(String location);

	List<Warehouse> findByCapacityGreaterThan(Integer capacity);





}
