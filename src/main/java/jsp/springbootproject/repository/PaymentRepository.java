package jsp.springbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jsp.springbootproject.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>{

}
