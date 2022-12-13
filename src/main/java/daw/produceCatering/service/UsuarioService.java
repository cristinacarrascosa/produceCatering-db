package daw.produceCatering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daw.produceCatering.entity.UsuarioEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.exception.ResourceNotModifiedException;
import daw.produceCatering.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository oUsuarioRepository;

    private final String PC_DEFAULT_PASSWORD = "1234";

    public UsuarioEntity get(Long id) {

        //faltaria el onlyAdminiOrUserData si hace falta agregarlo luego
        try {
            return oUsuarioRepository.findById(id).get();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("id "+ id + " no existe");
        }
    }

    public Long count() {
        // falta añadir onlyAdmin si hace falta
        return oUsuarioRepository.count();
    }

    public Long create(UsuarioEntity oNewUsuarioEntity) {
        oNewUsuarioEntity.setId(0L);
        oNewUsuarioEntity.setPassword(PC_DEFAULT_PASSWORD);
        return oUsuarioRepository.save(oNewUsuarioEntity).getId();
    }

    public Long delete(Long id) {
        // falta añadir onlyAdmin si hace falta
        if(oUsuarioRepository.existsById(id)){
            oUsuarioRepository.deleteById(id);
            if( oUsuarioRepository.existsById(id)){
                throw new ResourceNotModifiedException("id "+ id + " no se ha podido borrar");
            } else {
                return id;
            }

        } else {
            throw new ResourceNotFoundException("id "+ id + " no existe");
        }
    }
    
}
