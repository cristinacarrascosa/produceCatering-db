package daw.produceCatering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import daw.produceCatering.entity.EspacioEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.exception.ResourceNotModifiedException;
import daw.produceCatering.repository.EspacioRepository;

import daw.produceCatering.helper.ValidationHelper;


import org.springframework.data.domain.Pageable;


@Service
public class EspacioService {

    @Autowired
    EspacioRepository oEspacioRepository;

    @Autowired
    AuthService oAuthService;

    

    public EspacioEntity get(Long id){

        try {
            return oEspacioRepository.findById(id).get();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("id "+ id + " no existe");
        }

    }

    public Page<EspacioEntity> getPage(Pageable oPageable, String strFilter) {
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<EspacioEntity> oPage = null;
        if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
            oPage = oEspacioRepository.findAll(oPageable);
        } else {
            oPage = oEspacioRepository.findByNombreIgnoreCaseContaining(strFilter, oPageable);
        }
        return oPage;
    }


    public void validate(Long id) {
        if (!oEspacioRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " no existe");
        }
    }

    public void validate(EspacioEntity oEspacioEntity) {
        ValidationHelper.validateStringLength(oEspacioEntity.getNombre(), 2, 100, "campo nombre Espacio (el campo debe tener longitud de 2 a 100 caracteres)");
    }

    public Long create(@RequestBody EspacioEntity oEspacioEntity) {
        //oAuthService.OnlyAdmins();
        validate(oEspacioEntity);
        oEspacioEntity.setId(null);
        return oEspacioRepository.save(oEspacioEntity).getId();
    }

    public Long update(Long id, EspacioEntity oEspacioEntity) {
        //oAuthService.OnlyAdmins();
        oEspacioEntity.setId(id);
        validate(id);
        validate(oEspacioEntity);
        return oEspacioRepository.save(oEspacioEntity).getId();
    }

    public Long count() {
        // falta añadir onlyAdmin si hace falta
        return oEspacioRepository.count();
    }

    public Long delete(@PathVariable(value = "id") Long id) {
       // oAuthService.OnlyAdmins();
        if (oEspacioRepository.existsById(id)) {
            oEspacioRepository.deleteById(id);
            if (oEspacioRepository.existsById(id)) {
                throw new ResourceNotModifiedException("No se puede borrar el registro " + id);
            } else {
                return id;
            }
        } else {
            return 0L;
        }
    }
    
}
