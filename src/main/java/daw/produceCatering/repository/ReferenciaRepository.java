package daw.produceCatering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.ReferenciaEntity;

public interface ReferenciaRepository extends JpaRepository<ReferenciaEntity, Long>{
    
}
