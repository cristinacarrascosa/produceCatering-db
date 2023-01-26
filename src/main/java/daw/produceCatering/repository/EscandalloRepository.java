package daw.produceCatering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import daw.produceCatering.entity.EscandalloEntity;

public interface EscandalloRepository extends JpaRepository<EscandalloEntity, Long>{

    Page<EscandalloEntity> findByNombreIgnoreCaseContaining(String nombre, Pageable oPageable);

    Page<EscandalloEntity> findByTipoplatoId(Long id_tipoplato, Pageable oPageable);

    @Query(value = "SELECT * FROM escandallo WHERE id_tipoplato = ?1 AND (nombre LIKE  %?2%)", nativeQuery = true)
    Page<EscandalloEntity> findByTipoplatoIdAndNombre(long id_tipoplato, String nombre, Pageable oPageable);
    
}
