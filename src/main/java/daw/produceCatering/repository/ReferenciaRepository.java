package daw.produceCatering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.ReferenciaEntity;

public interface ReferenciaRepository extends JpaRepository<ReferenciaEntity, Long>{
    
    public Page<ReferenciaEntity> findByNombreIgnoreCaseContaining(String strFilter, Pageable oPageable);

    public Page<ReferenciaEntity> findById(Long longTipoPlato, Pageable oPageable);

    public Page<ReferenciaEntity> findByIdAndNombreIgnoreCaseContaining(Long longTipoPlato, String strFilter, Pageable oPageable);
    


}
