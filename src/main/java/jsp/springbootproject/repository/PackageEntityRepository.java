package jsp.springbootproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jsp.springbootproject.entity.PackageEntity;
import jsp.springbootproject.entity.PackageType;

@Repository
public interface PackageEntityRepository extends JpaRepository<PackageEntity,Integer>{

	List<PackageEntity> findByPackageType(PackageType type);

	boolean existsByPackageType(PackageType packageType);

	boolean existsByPackageNo(Object packageNo);


}
