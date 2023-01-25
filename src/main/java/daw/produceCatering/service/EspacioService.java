package daw.produceCatering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import daw.produceCatering.entity.EspacioEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.repository.EspacioRepository;
import daw.produceCatering.repository.SalonRepository;
import daw.produceCatering.helper.ValidationHelper;


import org.springframework.data.domain.Pageable;


@Service
public class EspacioService {

    @Autowired
    EspacioRepository oEspacioRepository;

    @Autowired
    SalonRepository oSalonRepository;

    public EspacioEntity get(Long id){

        try {
            return oEspacioRepository.findById(id).get();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("id "+ id + " no existe");
        }

    }

    // public Page<EspacioEntity> getPage(Pageable oPageable, String strFilter, Long lSalon) {

    //     ValidationHelper.validateRPP(oPageable.getPageSize());
    //     Page<EspacioEntity> oPage = null;
    //     if (lSalon == null) {
    //         if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
    //             oPage = oSalonRepository.findAll(oPageable);
    //         } else {
    //             oPage = oSalonRepository
    //                     .findByDniIgnoreCaseContainingOrNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(
    //                             strFilter, strFilter, strFilter, oPageable);
    //         }
    //     } else {
    //         if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
    //             oPage = oSalonRepository.findByTipousuarioId(lSalon, oPageable);
    //         } else {
    //             oPage = oSalonRepository
    //                     .findByTipousuarioIdAndDniIgnoreCaseContainingOrNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(
    //                             lSalon, strFilter, strFilter, strFilter, oPageable);
    //         }
    //     }
    //     return oPage;
    // }

    public Long count() {
        // falta añadir onlyAdmin si hace falta
        return oEspacioRepository.count();
    }
    
}
