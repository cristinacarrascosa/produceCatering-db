package daw.produceCatering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.TipoPlatoEntity;

public interface TipoPlatoRepository extends JpaRepository<TipoPlatoEntity, Long>{

    public Page<TipoPlatoEntity> findByNombreIgnoreCaseContaining(String strFilter, Pageable oPageable);

    public Page<TipoPlatoEntity> findById(Long longTipoPlato, Pageable oPageable);

    public Page<TipoPlatoEntity> findByIdAndNombreIgnoreCaseContaining(Long longTipoPlato, String strFilter, Pageable oPageable);
    
}
