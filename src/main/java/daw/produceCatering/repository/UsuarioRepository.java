package daw.produceCatering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

    UsuarioEntity findByLoginAndPassword(String login, String password);

    boolean existsByLogin(String login);

    Page<UsuarioEntity> findByDniIgnoreCaseContainingOrNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(String dni, String nombre, String apellidos, Pageable pageable);

    Page<UsuarioEntity> findByTipousuarioIdAndDniIgnoreCaseContainingOrNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(Long filtertype, String dni, String nombre, String apellidos,Pageable oPageable);

    Page<UsuarioEntity> findByTipousuarioId(Long id_tipousuario, Pageable oPageable);
}
