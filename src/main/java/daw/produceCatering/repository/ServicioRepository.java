package daw.produceCatering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.ServicioEntity;

public interface ServicioRepository extends JpaRepository<ServicioEntity, Long>{
    
}
