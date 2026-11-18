package jsp.springbootproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.Warehouse;
import jsp.springbootproject.exceptions.ActiveShipmentExistsException;
import jsp.springbootproject.exceptions.WarehouseIdNotFoundException;
import jsp.springbootproject.exceptions.WarehouseNotFoundException;
import jsp.springbootproject.repository.ShipmentRepository;
import jsp.springbootproject.repository.WarehouseRepository;

@Service
public class WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	
	public ResponseStructure<Warehouse> createWarehouse(Warehouse warehouse) {
	    Warehouse savedWarehouse = warehouseRepository.save(warehouse);

	    ResponseStructure<Warehouse> res = new ResponseStructure<>();
	    res.setStatusCode(HttpStatus.CREATED.value());
	    res.setMessage("Warehouse created successfully");
	    res.setData(savedWarehouse);

	    return res;
	}
	
	public ResponseStructure<List<Warehouse>> getAllWarehouses(){
		
		ResponseStructure<List<Warehouse>> res=new ResponseStructure<>();
		List<Warehouse> warehouses=warehouseRepository.findAll();
		if(!warehouses.isEmpty()) {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("All Warehouses retrived Succesfully");
			res.setData(warehouses);
			return res;
		}
		else {
			throw new WarehouseNotFoundException("No Warehouse found in the DB");
		}
	} 
	
	public ResponseStructure<Warehouse> getById(Integer id){
		Optional<Warehouse> opt=warehouseRepository.findById(id);
		ResponseStructure<Warehouse> res=new ResponseStructure<>();
		
		if(opt.isPresent()) {
			Warehouse warehouse=opt.get();
			
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("warehouse is succesfully retrived with id:"+id);
			res.setData(warehouse);
			return res;
		}
		else {
			throw new WarehouseIdNotFoundException("No warehouse found with id:"+id);
		}
	}

	  public ResponseStructure<List<Warehouse>> getByLocation(String location) {
	        List<Warehouse> warehouses = warehouseRepository.findByLocation(location);
	        ResponseStructure<List<Warehouse>> res = new ResponseStructure<>();

	        if (!warehouses.isEmpty()) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Warehouses retrieved successfully by location");
	            res.setData(warehouses);
	            return res;
	        }
	        else {
	            throw new WarehouseNotFoundException("No warehouses found at location: " + location);
	        }
	       
	  }
	  
	  
	  public ResponseStructure<List<Warehouse>> getByCapacityGreaterThanValue(Integer capacity) {
	        List<Warehouse> warehouses = warehouseRepository.findByCapacityGreaterThan(capacity);
	        ResponseStructure<List<Warehouse>> res = new ResponseStructure<>();

	        if (!warehouses.isEmpty()) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Warehouses retrieved successfully with capacity greater than " + capacity);
	            res.setData(warehouses);
	            return res;
	        }
	        else {
	            throw new WarehouseNotFoundException("No warehouses found with capacity greater than " + capacity);
	        }
	       
	    }
	  
	  public ResponseStructure<Warehouse> updateWarehouse(Integer warehouseId, Warehouse updatedWarehouse) {
	        Optional<Warehouse> opt = warehouseRepository.findById(warehouseId);
	        ResponseStructure<Warehouse> res = new ResponseStructure<>();

	        if (opt.isPresent()) {
	            Warehouse existingWarehouse = opt.get();
	            existingWarehouse.setName(updatedWarehouse.getName());
	            existingWarehouse.setLocation(updatedWarehouse.getLocation());
	            existingWarehouse.setCapacity(updatedWarehouse.getCapacity());

	            Warehouse savedWarehouse = warehouseRepository.save(existingWarehouse);

	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Warehouse updated successfully");
	            res.setData(savedWarehouse);
	            return res;
	        }
	        else {
	            throw new WarehouseNotFoundException("Warehouse not found with id: " + warehouseId);
	        }
	    }
	  
	  public ResponseStructure<Warehouse> deleteWarehouse(Integer warehouseId){
		  Optional<Warehouse> opt=warehouseRepository.findById(warehouseId);
		  ResponseStructure<Warehouse> res=new ResponseStructure<>();
		  if(opt.isPresent()) {
			  Warehouse warehouse=opt.get();
			  List<Warehouse>activeShipment=shipmentRepository.findByWarehouse_WarehouseId(warehouseId,"ACTIVE");
			 if(activeShipment.isEmpty()) {
				 warehouseRepository.delete(warehouse);
				 res.setStatusCode(HttpStatus.OK.value());
				 res.setMessage("warehouse deleted succesfully");
				 res.setData(warehouse);
				 return res;
			 }
			 else
				{
					throw new ActiveShipmentExistsException("Warehouse cannot be deleted,because active shipment exist");
				} 
		  }
		  else
			  throw new WarehouseIdNotFoundException("warehouse not found with id:"+warehouseId);
				  
	  }
	}





