package daw.produceCatering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.EspacioEntity;

public interface EspacioRepository extends JpaRepository<EspacioEntity, Long>{
    

}
