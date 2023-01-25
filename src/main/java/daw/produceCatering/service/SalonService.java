package daw.produceCatering.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import daw.produceCatering.entity.SalonEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.exception.ResourceNotModifiedException;
import daw.produceCatering.repository.EspacioRepository;
import daw.produceCatering.repository.SalonRepository;

@Service
public class SalonService {

    @Autowired
    SalonRepository oSalonRepository;

    @Autowired
    EspacioService oEspacioService;

    @Autowired
    EspacioRepository oEspacioRepository;

    @Autowired
    AuthService oAuthService;

    public SalonEntity get(Long id) {
        // faltaria el onlyAdminiOrUserData si hace falta agregarlo luego
        try {
            return oSalonRepository.findById(id).get();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("id " + id + " no existe");
        }
    }

    public Long count() {
        // falta añadir onlyAdmin si hace falta
        return oSalonRepository.count();
    }

    public Long delete(Long id) {
        // falta añadir onlyAdmin si hace falta
        if (oSalonRepository.existsById(id)) {
            oSalonRepository.deleteById(id);
            if (oSalonRepository.existsById(id)) {
                throw new ResourceNotModifiedException("id " + id + " no se ha podido borrar");
            } else {
                return id;
            }

        } else {
            throw new ResourceNotFoundException("id " + id + " no existe");
        }
    }
    
   

    
}
