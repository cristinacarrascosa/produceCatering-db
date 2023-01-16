package daw.produceCatering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daw.produceCatering.entity.EspacioEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.repository.EspacioRepository;

@Service
public class EspacioService {

    @Autowired
    EspacioRepository oEspacioRepository;

    public EspacioEntity get(Long id){

        try {
            return oEspacioRepository.findById(id).get();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("id "+ id + " no existe");
        }

    }
    
}
