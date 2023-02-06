package daw.produceCatering.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import daw.produceCatering.entity.ServicioEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.helper.ValidationHelper;
import daw.produceCatering.repository.SalonRepository;
import daw.produceCatering.repository.ServicioRepository;
import daw.produceCatering.repository.UsuarioRepository;

@Service
public class ServicioService {

    @Autowired
    SalonService oSalonService;

    @Autowired
    UsuarioService oUsuarioService;

    @Autowired
    AuthService oAuthService;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    SalonRepository oSalonRepository;

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

    public Page<ServicioEntity> getPage(Pageable oPageable, String strFilter, Long id_usuario, Long id_salon) {
        //oAuthService.OnlyAdminsOrUsers();
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<ServicioEntity> oPage = null;
        if (id_usuario != null && id_salon != null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oServicioRepository.findByUsuarioIdAndSalonId(id_usuario, id_salon, oPageable);
            } else {
                oPage = oServicioRepository.findByUsuarioIdAndSalonIdAndFechahoraContaining( id_usuario, id_salon, strFilter, oPageable);
            }
        }
        else if (id_usuario != null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oServicioRepository.findByUsuarioId(id_usuario, oPageable);
            } else {
                oPage = oServicioRepository.findByUsuarioIdAndFechahoraContaining( id_usuario,strFilter, oPageable);
            }
        } else if (id_salon != null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oServicioRepository.findBySalonId(id_salon, oPageable);
            } else {
                oPage = oServicioRepository.findBySalonIdAndFechahoraContaining(id_salon, strFilter, oPageable);
            }
        } else {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oServicioRepository.findAll(oPageable);
            } else {
                oPage = oServicioRepository.findByFechahoraContaining( strFilter,oPageable);
            }
        }
        
        return oPage;
    }


    public Long count() {
        // falta añadir onlyAdmin si hace falta
        return oServicioRepository.count();
    }

    public void validate(ServicioEntity oServicioEntity) {
        oUsuarioService.validate(oServicioEntity.getUsuario().getId());
        oSalonService.validate(oServicioEntity.getSalon().getId());
    }

    @Transactional
    public Long create(ServicioEntity oServicioEntity) {
       // oAuthService.OnlyAdmins();
        validate(oServicioEntity);
        oUsuarioService.validate(oServicioEntity.getUsuario().getId());
        oServicioEntity.setUsuario(oUsuarioService.get(oServicioEntity.getUsuario().getId()));
        oSalonService.validate(oServicioEntity.getSalon().getId());
        oServicioEntity.setSalon(oSalonService.get(oServicioEntity.getSalon().getId()));
        oServicioEntity.setId(null);
        return ((ServicioEntity) oServicioRepository.save(oServicioEntity)).getId();
    }

    @Transactional
    public Long update(ServicioEntity oServicioEntity) {
        //oAuthService.OnlyAdmins();
        validate(oServicioEntity.getId());
        validate(oServicioEntity);
        //LocalDateTime.parse(oServicioEntity.getFechaHora());
        oUsuarioService.validate(oServicioEntity.getUsuario().getId());
        oServicioEntity.setUsuario(oUsuarioService.get(oServicioEntity.getUsuario().getId()));
        oSalonService.validate(oServicioEntity.getSalon().getId());
        oServicioEntity.setSalon(oSalonService.get(oServicioEntity.getSalon().getId()));
        return oServicioRepository.save(oServicioEntity).getId();
    }

    public Long delete(Long id) {
        //oAuthService.OnlyAdmins();
        validate(id);
        oServicioRepository.deleteById(id);
        return id;
    }

  
    
}
