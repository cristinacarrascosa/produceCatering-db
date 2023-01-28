package daw.produceCatering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.ServicioEntity;

public interface ServicioRepository extends JpaRepository<ServicioEntity, Long>{
    
    public Page<ServicioEntity> findByUsuarioId(Long id_usuario, Pageable oPageable);

    public Page<ServicioEntity> findBySalonId(Long id_salon, Pageable oPageable);

    public Page<ServicioEntity> findByUsuarioIdAndSalonId(Long id_usuario, Long id_salon, Pageable oPageable);

    public Page<ServicioEntity> findByFechaHoraContaining(String fecha_hora, Pageable oPageable);

    public Page<ServicioEntity> findByUsuarioIdAndFechaHoraContaining(Long id_usuario, String fecha_hora, Pageable oPageable);

    public Page<ServicioEntity> findBySalonIdAndFechaHoraContaining(Long id_salon, String fecha_hora, Pageable oPageable);

    public Page<ServicioEntity> findByUsuarioIdAndSalonIdAndFechaHoraContaining(Long id_usuario, Long id_salon, String fecha_hora, Pageable oPageable);

    public Page<ServicioEntity> findByComensalesContaining(Integer comensales, Pageable oPageable);

    public Page<ServicioEntity> findByUsuarioIdAndComensalesContaining(Long id_usuario, Integer comensales, Pageable oPageable);

    public Page<ServicioEntity> findBySalonIdAndComensalesContaining(Long id_salon, Integer comensales, Pageable oPageable);

    public Page<ServicioEntity> findByUsuarioIdAndSalonIdAndComensalesContaining(Long id_usuario, Long id_salon, Integer comensales, Pageable oPageable);

    public Page<ServicioEntity> findByFechaHoraContainingAndComensalesContaining(String fecha_hora, Integer comensales, Pageable oPageable);

    public Page<ServicioEntity> findByUsuarioIdAndFechaHoraContainingAndComensalesContaining(Long id_usuario, String fecha_hora, Integer comensales, Pageable oPageable);

    public Page<ServicioEntity> findBySalonIdAndFechaHoraContainingAndComensalesContaining(Long id_salon, String fecha_hora, Integer comensales, Pageable oPageable);

    public Page<ServicioEntity> findByUsuarioIdAndSalonIdAndFechaHoraContainingAndComensalesContaining(Long id_usuario, Long id_salon, String fecha_hora, Integer comensales, Pageable oPageable);
}
