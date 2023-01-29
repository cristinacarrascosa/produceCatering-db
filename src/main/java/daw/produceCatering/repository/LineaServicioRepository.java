package daw.produceCatering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.LineaServicioEntity;

public interface LineaServicioRepository extends JpaRepository<LineaServicioEntity, Long>{
    
    Page<LineaServicioEntity> findByEscandalloIdAndServicioIdAndPaxContaining(Long id_escandallo, Long id_servicio,Integer pax, Pageable oPageable);

    Page<LineaServicioEntity> findByEscandalloId(Long id_escandallo, Pageable oPageable);

    Page<LineaServicioEntity> findByServicioId(Long id_servicio, Pageable oPageable);

}
