package daw.produceCatering.service;

import org.springframework.stereotype.Service;

import daw.produceCatering.entity.LineaServicioEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.exception.ResourceNotModifiedException;
import daw.produceCatering.repository.LineaServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Service
public class LineaServicioService {

    @Autowired
    LineaServicioRepository oLineaServicioRepository;

    @Autowired
    EscandalloService oEscandalloService;

    @Autowired
    ServicioService oServicioService;

    @Autowired
    AuthService oAuthService;

    public LineaServicioEntity get(Long id) {
        //validate(id);
        // oEscandalloService.validate(oLineaServicioRepository.getById(id).getId());
        // oServicioService.validate(oLineaEscandalloRepository.getById(id).getId());
        return oLineaServicioRepository.findById(id).get();
    }

    public Long count() {
        //oAuthService.OnlyAdmins();
        return oLineaServicioRepository.count();
    }


    public Page<LineaServicioEntity> getPage(Long id_escandallo, Long id_servicio, int page, int size, Integer pax) {
        //oAuthService.OnlyAdmins();
        Pageable oPageable = PageRequest.of(page, size);
        if (id_escandallo == null && id_servicio == null) {
            return oLineaServicioRepository.findAll(oPageable);
        } else if (id_escandallo == null) {
            return oLineaServicioRepository.findByServicioId(id_servicio, oPageable);
        } else if (id_servicio == null) {
            return oLineaServicioRepository.findByEscandalloId(id_escandallo, oPageable);
        } else {
            return oLineaServicioRepository.findByEscandalloIdAndServicioIdAndPaxContaining(id_escandallo, id_servicio,pax ,oPageable);
        }
    }

    public Long create(LineaServicioEntity oLineaServicioEntity) {
        validate(oLineaServicioEntity);
        oLineaServicioEntity.setId(0L);
        oEscandalloService.validate(oLineaServicioEntity.getEscandallo().getId());
        oServicioService.validate(oLineaServicioEntity.getServicio().getId());
        oLineaServicioEntity.setEscandallo(oEscandalloService.get(oLineaServicioEntity.getEscandallo().getId()));
        oLineaServicioEntity.setServicio(oServicioService.get(oLineaServicioEntity.getServicio().getId()));
        oLineaServicioEntity.setId(null);
        return oLineaServicioRepository.save(oLineaServicioEntity).getId();
    }


    public void validate(Long id) {
        if (!oLineaServicioRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(LineaServicioEntity oLineaServicioEntity) {
        oEscandalloService.validate(oLineaServicioEntity.getEscandallo().getId());
        oServicioService.validate(oLineaServicioEntity.getServicio().getId());
    }

    public Long delete(Long id) {
        // falta añadir onlyAdmin si hace falta
        if (oLineaServicioRepository.existsById(id)) {
            oLineaServicioRepository.deleteById(id);
            if (oLineaServicioRepository.existsById(id)) {
                throw new ResourceNotModifiedException("id " + id + " no se ha podido borrar");
            } else {
                return id;
            }

        } else {
            throw new ResourceNotFoundException("id " + id + " no existe");
        }
    }

    //@Transactional
    public Long update(LineaServicioEntity oLineaServicioEntity) {
        validate(oLineaServicioEntity.getId());
        validate(oLineaServicioEntity);
        oEscandalloService.validate(oLineaServicioEntity.getEscandallo().getId());
        oServicioService.validate(oLineaServicioEntity.getServicio().getId());
        oLineaServicioEntity.setEscandallo(oEscandalloService.get(oLineaServicioEntity.getEscandallo().getId()));
        oLineaServicioEntity.setServicio(oServicioService.get(oLineaServicioEntity.getServicio().getId()));
        return oLineaServicioRepository.save(oLineaServicioEntity).getId();
    }


    

    
}
