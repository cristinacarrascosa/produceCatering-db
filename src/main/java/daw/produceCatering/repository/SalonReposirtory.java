package daw.produceCatering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.SalonEntity;

public interface SalonReposirtory extends JpaRepository<SalonEntity, Long>{
    
}
