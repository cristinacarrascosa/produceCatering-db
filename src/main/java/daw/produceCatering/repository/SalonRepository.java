package daw.produceCatering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.SalonEntity;

public interface SalonRepository extends JpaRepository<SalonEntity, Long>{
    
    Page<SalonEntity> findByNombreIgnoreCaseContaining(String strFilter, Pageable oPageable);

    Page<SalonEntity> findById(Long id, Pageable oPageable);

    
}
