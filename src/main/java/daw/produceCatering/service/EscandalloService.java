package daw.produceCatering.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import daw.produceCatering.entity.EscandalloEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.helper.ValidationHelper;
import daw.produceCatering.repository.EscandalloRepository;

@Service
public class EscandalloService {

    @Autowired
    TipoPlatoService oTipoPlatoService;

    @Autowired
    EscandalloRepository oEscandalloRepository;
    
    public void validate(Long id) {
        if (!oEscandalloRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(EscandalloEntity oEscandalloEntity) {
        ValidationHelper.validateStringLength(oEscandalloEntity.getNombre(), 2, 255, "codigo en ProductoService (el campo debe tener longitud de 2 a 255 caracteres)");
        oTipoPlatoService.validate(oEscandalloEntity.gettipoplato().getId());
    }

    public EscandalloEntity get(Long id) {
        validate(id);
        return oEscandalloRepository.findById(id).get();
    }

    public Long count() {
        return oEscandalloRepository.count();
    }

    public Page<EscandalloEntity> getPage(Pageable oPageable, String strFilter, Long lTipoPlato) {
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<EscandalloEntity> oPage = null;
        if (lTipoPlato == null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oEscandalloRepository.findAll(oPageable);
            } else {
                oPage = oEscandalloRepository.findByNombreIgnoreCaseContaining(strFilter, oPageable);
            }
        } else {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oEscandalloRepository.findByTipoplatoId(lTipoPlato, oPageable);
            } else {
                oPage = oEscandalloRepository.findByTipoplatoIdAndNombre(lTipoPlato, strFilter,oPageable);
            }
        }
        return oPage;
    }

    @Transactional
    public Long create(EscandalloEntity oEscandalloEntity) {
       // oAuthService.OnlyAdmins();
        validate(oEscandalloEntity);
        oTipoPlatoService.validate(oEscandalloEntity.gettipoplato().getId());
        oEscandalloEntity.settipoplato(oTipoPlatoService.get(oEscandalloEntity.gettipoplato().getId()));
        oEscandalloEntity.setId(null);
        return ((EscandalloEntity) oEscandalloRepository.save(oEscandalloEntity)).getId();
    }

    @Transactional
    public Long update(EscandalloEntity oEscandalloEntity) {
        //oAuthService.OnlyAdmins();
        validate(oEscandalloEntity.getId());
        validate(oEscandalloEntity);
        oTipoPlatoService.validate(oEscandalloEntity.gettipoplato().getId());
        oEscandalloEntity.settipoplato(oTipoPlatoService.get(oEscandalloEntity.gettipoplato().getId()));
        return oEscandalloRepository.save(oEscandalloEntity).getId();
    }

    public Long delete(Long id) {
        //oAuthService.OnlyAdmins();
        validate(id);
        oEscandalloRepository.deleteById(id);
        return id;
    }
}
