package daw.produceCatering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import daw.produceCatering.entity.EspacioEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
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

    public Long count() {
        // falta añadir onlyAdmin si hace falta
        return oEspacioRepository.count();
    }
    
}
