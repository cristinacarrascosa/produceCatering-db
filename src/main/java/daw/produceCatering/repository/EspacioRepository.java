package daw.produceCatering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.EspacioEntity;

public interface EspacioRepository extends JpaRepository<EspacioEntity, Long>{
    
    public Page<EspacioEntity> findByNombreIgnoreCaseContaining(String strFilter, Pageable oPageable);

    public Page<EspacioEntity> findById(Long id, Pageable oPageable);

    public Page<EspacioEntity> findByIdAndNombreIgnoreCaseContaining (Long longEspacioId, String strFilter, Pageable oPageable);
    
}
