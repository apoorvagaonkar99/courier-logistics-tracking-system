package jsp.springbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.Warehouse;
import jsp.springbootproject.service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
	@Autowired
	private WarehouseService warehouseService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseStructure<Warehouse>> createWarehouse(@RequestBody Warehouse warehouse){
		return new ResponseEntity<>(warehouseService.createWarehouse(warehouse),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Warehouse>>> getAllWarehouses(){
		return new ResponseEntity<>(warehouseService.getAllWarehouses(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Warehouse>> getById(@PathVariable Integer id){
		return new ResponseEntity<>(warehouseService.getById(id),HttpStatus.OK);
	}
	
	@GetMapping("/location/{location}")
    public ResponseEntity<ResponseStructure<List<Warehouse>>> getByLocation(@PathVariable String location) {
        return new ResponseEntity<>(warehouseService.getByLocation(location), HttpStatus.OK);
    }

	@GetMapping("/capacity/{capacity}")
    public ResponseEntity<ResponseStructure<List<Warehouse>>> getByCapacityGreaterThanValue(@PathVariable Integer capacity) {
        return new ResponseEntity<>( warehouseService.getByCapacityGreaterThanValue(capacity), HttpStatus.OK);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Warehouse>> updateWarehouse(@PathVariable Integer id,@RequestBody Warehouse warehouse) {
        return new ResponseEntity<>(warehouseService.updateWarehouse(id, warehouse), HttpStatus.OK);
    }
	
	@DeleteMapping("/{warehouseId}")
	public ResponseEntity<ResponseStructure<Warehouse>> deleteWarehouse(@PathVariable Integer WarehouseId){
		return new ResponseEntity<>(warehouseService.deleteWarehouse(WarehouseId),HttpStatus.OK);
	}
}
