package daw.produceCatering.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import daw.produceCatering.entity.SalonEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.exception.ResourceNotModifiedException;
import daw.produceCatering.helper.ValidationHelper;
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

    public void validate(Long id) {
        if (!oSalonRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(SalonEntity oSalonEntity) {
        ValidationHelper.validateStringLength(oSalonEntity.getNombre(), 2, 255, "Nombre en SalonService (el campo debe tener longitud de 2 a 255 caracteres)");
        oEspacioService.validate(oSalonEntity.getEspacio().getId());
    }


    @Transactional
    public Long create(SalonEntity oSalonEntity) {
        //oAuthService.OnlyAdmins();
        validate(oSalonEntity);
        oEspacioService.validate(oSalonEntity.getEspacio().getId());
        oSalonEntity.setEspacio(oEspacioService.get(oSalonEntity.getEspacio().getId()));
        oSalonEntity.setId(null);
        return ((SalonEntity) oSalonRepository.save(oSalonEntity)).getId();
    }

    @Transactional
    public Long update(SalonEntity oSalonEntity) {
        //oAuthService.OnlyAdmins();
        validate(oSalonEntity.getId());
        validate(oSalonEntity);
        oEspacioService.validate(oSalonEntity.getEspacio().getId());
        oSalonEntity.setEspacio(oEspacioService.get(oSalonEntity.getEspacio().getId()));
        return oSalonRepository.save(oSalonEntity).getId();
    }
    
   

    
}
