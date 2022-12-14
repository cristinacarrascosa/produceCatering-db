package daw.produceCatering.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daw.produceCatering.entity.UsuarioEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.exception.ResourceNotModifiedException;
import daw.produceCatering.exception.ValidationException;
import daw.produceCatering.helper.ValidationHelper;
import daw.produceCatering.repository.TipousuarioRepository;
import daw.produceCatering.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    TipoUsuarioService oTipoUsuarioService;

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

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
        validate(oNewUsuarioEntity);
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

   
    public Long update (UsuarioEntity oUsuarioEntity){
        validate(oUsuarioEntity.getId());
        UsuarioEntity oOldUsuarioEntity = oUsuarioRepository.getById(oUsuarioEntity.getId());
        oUsuarioEntity.setPassword(oOldUsuarioEntity.getPassword());
        return oUsuarioRepository.save(oUsuarioEntity).getId();
    }


    public void validate(Long id) {
        if( !oUsuarioRepository.existsById(id)){
            throw new ResourceNotFoundException("id "+ id + " no existe");
        }
    }


    public void validate(UsuarioEntity oUsuarioEntity) {
        
        ValidationHelper.validateStringLength(oUsuarioEntity.getNombre(), 2, 50, "campo nombre de Usuario (el campo debe tener longitud de 2 a 50 caracteres");
        ValidationHelper.validateStringLength(oUsuarioEntity.getApellidos(), 2, 100, "campo apellidos de Usuario (el campo debe tener longitud de 2 a 100 caracteres");
        ValidationHelper.validateDNI(oUsuarioEntity.getDni(), "campo DNI de Usuario");
        ValidationHelper.validateEmail(oUsuarioEntity.getEmail(), "campo email de Usuario");
        ValidationHelper.validateLogin(oUsuarioEntity.getLogin(), "campo login de Usuario");
        if ( oUsuarioRepository.existsByLogin(oUsuarioEntity.getLogin()) ){
            throw new ValidationException("el campo login está repetido");
        }
        oTipoUsuarioService.validate(oUsuarioEntity.getTipousuario().getId());
        
    }

    


    
}
