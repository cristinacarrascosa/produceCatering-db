package daw.produceCatering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.LineaEscandalloEntity;

public interface LineaEscandalloRepository extends JpaRepository<LineaEscandalloEntity, Long>{
    
    Page<LineaEscandalloEntity> findByEscandalloIdAndReferenciaId(Long id_escandallo, Long id_referencia, Pageable oPageable);

    Page<LineaEscandalloEntity> findByEscandalloId(Long id_escandallo, Pageable oPageable);

    Page<LineaEscandalloEntity> findByReferenciaId(Long id_referencia, Pageable oPageable);

}
