package daw.produceCatering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import daw.produceCatering.entity.UsuarioEntity;
import daw.produceCatering.exception.CannotPerformOperationException;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.exception.ResourceNotModifiedException;
import daw.produceCatering.exception.ValidationException;
import daw.produceCatering.helper.RandomHelper;
import daw.produceCatering.helper.TipoUsuarioHelper;
import daw.produceCatering.helper.ValidationHelper;
import daw.produceCatering.repository.TipousuarioRepository;
import daw.produceCatering.repository.UsuarioRepository;
import org.springframework.data.domain.Pageable;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    TipoUsuarioService oTipoUsuarioService;

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    private final String DNI_LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";
    private final String PC_DEFAULT_PASSWORD = "1234";
    private final String[] NOMBRES = {"Jose", "Mark", "Elen", "Toni", "Hector", "Jose", "Laura", "Vika", "Sergio",
        "Javi", "Marcos", "Pere", "Daniel", "Jose", "Javi", "Sergio", "Aaron", "Rafa", "Lionel", "Borja"};

    private final String[] APELLIDOS = {"Penya", "Tatay", "Coronado", "Cabanes", "Mikayelyan", "Gil", "Martinez",
        "Bargues", "Raga", "Santos", "Sierra", "Arias", "Santos", "Kuvshinnikova", "Cosin", "Frejo", "Marti",
        "Valcarcel", "Sesa", "Lence", "Villanueva", "Peyro", "Navarro", "Navarro", "Primo", "Gil", "Mocholi",
        "Ortega", "Dung", "Vi", "Sanchis", "Merida", "Aznar", "Aparici", "Tarazón", "Alcocer", "Salom", "Santamaría"};


    public UsuarioEntity get(Long id) {
        //faltaria el onlyAdminiOrUserData si hace falta agregarlo luego
        try {
            return oUsuarioRepository.findById(id).get();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("id "+ id + " no existe");
        }
    }

    public Page<UsuarioEntity> getPage(Pageable oPageable, String strFilter, Long lTipoUsuario) {
      
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<UsuarioEntity> oPage = null;
        if (lTipoUsuario == null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oUsuarioRepository.findAll(oPageable);
            } else {
                oPage = oUsuarioRepository.findByDniIgnoreCaseContainingOrNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(
                        strFilter, strFilter, strFilter, oPageable);
            }
        } else {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oUsuarioRepository.findByTipousuarioId(lTipoUsuario, oPageable);
            } else {
                oPage = oUsuarioRepository.findByTipousuarioIdAndDniIgnoreCaseContainingOrNombreIgnoreCaseContainingOrApellidosIgnoreCaseContaining(
                        lTipoUsuario, strFilter, strFilter, strFilter, oPageable);
            }
        }
        return oPage;
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

    public UsuarioEntity generate() {
        //oAuthService.OnlyAdmins();
        return generateRandomUser();
    }

    public Long generateSome(Integer amount) {
        //oAuthService.OnlyAdmins();
        List<UsuarioEntity> userList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            UsuarioEntity oUsuarioEntity = generateRandomUser();
            oUsuarioRepository.save(oUsuarioEntity);
            userList.add(oUsuarioEntity);
        }
        return oUsuarioRepository.count();
    }

    public UsuarioEntity getOneRandom() {        
        if (count() > 0) {
            List<UsuarioEntity> usuarioList = oUsuarioRepository.findAll();
            int iPosicion = RandomHelper.getRandomInt(0, (int) oUsuarioRepository.count() - 1);
            return oUsuarioRepository.getById(usuarioList.get(iPosicion).getId());            
        } else {
            throw new CannotPerformOperationException("ho hay usuarios en la base de datos");
        }
    }

    private UsuarioEntity generateRandomUser() {
        UsuarioEntity oUserEntity = new UsuarioEntity();
        oUserEntity.setDni(generateDNI());
        oUserEntity.setNombre(generateName());
        oUserEntity.setApellidos(generateApellido());
        
        oUserEntity.setLogin(oUserEntity.getNombre() + "_" + oUserEntity.getApellidos());
        oUserEntity.setPassword(PC_DEFAULT_PASSWORD); // wildcart
        oUserEntity.setEmail(generateEmail(oUserEntity.getNombre(), oUserEntity.getApellidos()));
        
        if (RandomHelper.getRandomInt(0, 10) > 1) {
            oUserEntity.setTipousuario(oTipousuarioRepository.getById(TipoUsuarioHelper.USER));
        } else {
            oUserEntity.setTipousuario(oTipousuarioRepository.getById(TipoUsuarioHelper.ADMIN));
        }
        // oUserEntity.setValidado(false);
        // oUserEntity.setActivo(false);
        return oUserEntity;
    }

    private String generateDNI() {
        String dni = "";
        int dniNumber = RandomHelper.getRandomInt(11111111, 99999999 + 1);
        dni += dniNumber + "" + DNI_LETTERS.charAt(dniNumber % 23);
        return dni;
    }

    private String generateName() {
        return NOMBRES[RandomHelper.getRandomInt(0, NOMBRES.length - 1)].toLowerCase();
    }


    public void validate(Long id) {
        if( !oUsuarioRepository.existsById(id)){
            throw new ResourceNotFoundException("id "+ id + " no existe");
        }
    }

    private String generateApellido() {
        return APELLIDOS[RandomHelper.getRandomInt(0, APELLIDOS.length - 1)].toLowerCase();
    }

    private String generateEmail(String name, String surname) {
        List<String> list = new ArrayList<>();
        list.add(name);
        list.add(surname);
        return getFromList(list) + "_" + getFromList(list) + "@daw.tk";
    }

    private String getFromList(List<String> list) {
        int randomNumber = RandomHelper.getRandomInt(0, list.size() - 1);
        String value = list.get(randomNumber);
        list.remove(randomNumber);
        return value;
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
