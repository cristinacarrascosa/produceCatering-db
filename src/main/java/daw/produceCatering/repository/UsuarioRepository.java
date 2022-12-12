package daw.produceCatering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import daw.produceCatering.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

    UsuarioEntity findByLoginAndPassword(String login, String password);

    boolean existsByLogin(String login);
    
}
