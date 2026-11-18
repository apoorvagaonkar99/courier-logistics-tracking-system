package jsp.springbootproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.springbootproject.dto.ResponseStructure;
import jsp.springbootproject.entity.PackageEntity;
import jsp.springbootproject.entity.PackageType;
import jsp.springbootproject.exceptions.PackageNotFoundException;
import jsp.springbootproject.repository.PackageEntityRepository;

@Service
public class PackageEntityService {

	@Autowired
	private PackageEntityRepository packageEntityRepository;
	
	public ResponseStructure<List<PackageEntity>> getAllPackage() {
        ResponseStructure<List<PackageEntity>> res = new ResponseStructure<>();
        List<PackageEntity> packages = packageEntityRepository.findAll();

        if (!packages.isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("List of all packages");
            res.setData(packages);
            return res;
        } else {
            throw new PackageNotFoundException("No packages found in the database");
        }
    }

	
	public ResponseStructure<PackageEntity> getPackageById(Integer packageId) {
	    Optional<PackageEntity> opt = packageEntityRepository.findById(packageId);
	    ResponseStructure<PackageEntity> res = new ResponseStructure<>();

	    if (opt.isPresent()) {
	        PackageEntity pkg = opt.get();
	        res.setStatusCode(HttpStatus.OK.value());
	        res.setMessage("Package found successfully");
	        res.setData(pkg);
	        return res;
	    } else {
	        throw new PackageNotFoundException("Package not found with id: " + packageId);
	    }
	}
	
	public ResponseStructure<List<PackageEntity>> getPackageByType(PackageType type) {
        List<PackageEntity> packages = packageEntityRepository.findByPackageType(type);
        ResponseStructure<List<PackageEntity>> res = new ResponseStructure<>();

        if (!packages.isEmpty()) 
        {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Packages of type " + type + " retrieved successfully");
            res.setData(packages);
            return res;
        }
        else {
            throw new PackageNotFoundException("No packages found with type: " + type);
        }
    }



}
