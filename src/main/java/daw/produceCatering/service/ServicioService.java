package daw.produceCatering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import daw.produceCatering.entity.ServicioEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.repository.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    SalonService oSalonService;

    @Autowired
    UsuarioService oUsuarioService;

    @Autowired
    AuthService oAuthService;

    @Autowired
    ServicioRepository oServicioRepository;

    public ServicioEntity get(Long id) {
        validate(id);
        return oServicioRepository.getById(id);
    }

    public void validate(Long id) {
        if (!oServicioRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " no existe");
        }
    }

    public Long count() {
        // falta añadir onlyAdmin si hace falta
        return oServicioRepository.count();
    }

  
    
}
