package daw.produceCatering.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import daw.produceCatering.entity.LineaEscandalloEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.exception.ResourceNotModifiedException;
import daw.produceCatering.helper.ValidationHelper;
import daw.produceCatering.repository.LineaEscandalloRepository;

@Service
public class LineaEscandalloService {

    @Autowired
    LineaEscandalloRepository oLineaEscandalloRepository;

    @Autowired
    EscandalloService oEscandalloService;

    @Autowired
    ReferenciaService oReferenciaService;

    @Autowired
    AuthService oAuthService;

    

   
    public LineaEscandalloEntity get(Long id) {
        //validate(id);
        // oEscandalloService.validate(oLineaEscandalloRepository.getById(id).getId());
        // oReferenciaService.validate(oLineaEscandalloRepository.getById(id).getId());
        return oLineaEscandalloRepository.findById(id).get();
    }

    public Long count() {
        //oAuthService.OnlyAdmins();
        return oLineaEscandalloRepository.count();
    }

    public Page<LineaEscandalloEntity> getPage(Pageable oPageable, Long lEscandallo, Long lReferencia) {
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<LineaEscandalloEntity> oPage = null;
        if (lEscandallo == null) 
            
                oPage = oLineaEscandalloRepository.findAll(oPageable);
            
    
            
       
        return oPage;
    }

    public Long create(LineaEscandalloEntity oLineaEscandalloEntity) {
        validate(oLineaEscandalloEntity);
        oLineaEscandalloEntity.setId(0L);
        oEscandalloService.validate(oLineaEscandalloEntity.getEscandallo().getId());
        oReferenciaService.validate(oLineaEscandalloEntity.getReferencia().getId());
        oLineaEscandalloEntity.setEscandallo(oEscandalloService.get(oLineaEscandalloEntity.getEscandallo().getId()));
        oLineaEscandalloEntity.setReferencia(oReferenciaService.get(oLineaEscandalloEntity.getReferencia().getId()));
        oLineaEscandalloEntity.setId(null);
        return oLineaEscandalloRepository.save(oLineaEscandalloEntity).getId();
    }


    public void validate(Long id) {
        if (!oLineaEscandalloRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(LineaEscandalloEntity oLineaEscandalloEntity) {
        oEscandalloService.validate(oLineaEscandalloEntity.getEscandallo().getId());
        oReferenciaService.validate(oLineaEscandalloEntity.getReferencia().getId());
    }

    public Long delete(Long id) {
        // falta añadir onlyAdmin si hace falta
        if (oLineaEscandalloRepository.existsById(id)) {
            oLineaEscandalloRepository.deleteById(id);
            if (oLineaEscandalloRepository.existsById(id)) {
                throw new ResourceNotModifiedException("id " + id + " no se ha podido borrar");
            } else {
                return id;
            }

        } else {
            throw new ResourceNotFoundException("id " + id + " no existe");
        }
    }

    //@Transactional
    public Long update(LineaEscandalloEntity oLineaEscandalloEntity) {
        validate(oLineaEscandalloEntity.getId());
        validate(oLineaEscandalloEntity);
        oEscandalloService.validate(oLineaEscandalloEntity.getEscandallo().getId());
        oReferenciaService.validate(oLineaEscandalloEntity.getReferencia().getId());
        oLineaEscandalloEntity.setEscandallo(oEscandalloService.get(oLineaEscandalloEntity.getEscandallo().getId()));
        oLineaEscandalloEntity.setReferencia(oReferenciaService.get(oLineaEscandalloEntity.getReferencia().getId()));
        return oLineaEscandalloRepository.save(oLineaEscandalloEntity).getId();
    }


    

    
}
