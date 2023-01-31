package daw.produceCatering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import daw.produceCatering.entity.ServicioEntity;

public interface ServicioRepository extends JpaRepository<ServicioEntity, Long>{
    
    public Page<ServicioEntity> findByUsuarioId(Long id_usuario, Pageable oPageable);

    public Page<ServicioEntity> findBySalonId(Long id_salon, Pageable oPageable);

    public Page<ServicioEntity> findByUsuarioIdAndSalonId(Long id_usuario, Long id_salon, Pageable oPageable);

    @Query(value = "SELECT * FROM servicio WHERE fechaHora LIKE %?1%" , nativeQuery = true)
    public Page<ServicioEntity> findByFechahoraContaining(String fecha_hora, Pageable oPageable);

    @Query(value = "SELECT * FROM servicio WHERE fechaHora LIKE %?2% AND id_usuario = ?1", nativeQuery = true)
    public Page<ServicioEntity> findByUsuarioIdAndFechahoraContaining(Long id_usuario, String fecha_hora, Pageable oPageable);

    @Query(value = "SELECT * FROM servicio WHERE fechaHora LIKE %?2% AND id_salon = ?1", nativeQuery = true)
    public Page<ServicioEntity> findBySalonIdAndFechahoraContaining(Long id_salon, String fecha_hora, Pageable oPageable);

    @Query(value = "SELECT * FROM servicio WHERE fechaHora LIKE %?3% AND id_usuario = ?1 AND id_salon = ?2", nativeQuery = true)
    public Page<ServicioEntity> findByUsuarioIdAndSalonIdAndFechahoraContaining(Long id_usuario, Long id_salon, String fecha_hora, Pageable oPageable);
}
