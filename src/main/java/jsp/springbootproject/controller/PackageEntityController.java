package jsp.springbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.PackageEntity;
import jsp.springbootproject.entity.PackageType;
import jsp.springbootproject.service.PackageEntityService;

@RestController
@RequestMapping("/packageEntity")
public class PackageEntityController {

	@Autowired
	private PackageEntityService packageEntityService;
	
	 @GetMapping
	    public ResponseEntity<ResponseStructure<List<PackageEntity>>> getAllPackage() {
	        return new ResponseEntity<>(packageEntityService.getAllPackage(),HttpStatus.OK);
	    }
	 
	 @GetMapping("/id/{id}")
	    public ResponseEntity<ResponseStructure<PackageEntity>> getPackageById(@PathVariable Integer id) {
	        return new ResponseEntity<>(packageEntityService.getPackageById(id),HttpStatus.OK);
	    }
	 
	 @GetMapping("/type/{type}")
	    public ResponseEntity<ResponseStructure<List<PackageEntity>>> getPackageByType(@PathVariable PackageType type) {
	        return new ResponseEntity<>(packageEntityService.getPackageByType(type),HttpStatus.OK);
	    }
}
