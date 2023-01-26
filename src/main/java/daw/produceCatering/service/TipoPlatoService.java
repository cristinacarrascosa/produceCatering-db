package daw.produceCatering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import daw.produceCatering.entity.TipoPlatoEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.exception.ResourceNotModifiedException;
import daw.produceCatering.helper.ValidationHelper;
import daw.produceCatering.repository.TipoPlatoRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class TipoPlatoService {

    @Autowired
    TipoPlatoRepository oTipoPlatoRepository;

    @Autowired
    AuthService oAuthService;

    public void validate(Long id) {
        if (!oTipoPlatoRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(TipoPlatoEntity oTipoPlatoEntity) {
        ValidationHelper.validateStringLength(oTipoPlatoEntity.getNombre(), 2, 100, "campo nombre TipoPlato (el campo debe tener longitud de 2 a 100 caracteres)");
    }

    public TipoPlatoEntity get(@PathVariable(value = "id") Long id) {
        if (oTipoPlatoRepository.existsById(id)) {
            return oTipoPlatoRepository.getById(id);
        } else {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public Long count() {
        return oTipoPlatoRepository.count();
    }

    public Page<TipoPlatoEntity> getPage(Pageable oPageable, String strFilter) {
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<TipoPlatoEntity> oPage = null;
        if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
            oPage = oTipoPlatoRepository.findAll(oPageable);
        } else {
            oPage = oTipoPlatoRepository.findByNombreIgnoreCaseContaining(strFilter, oPageable);
        }
        return oPage;
    }

    public Long create(@RequestBody TipoPlatoEntity oTipoPlatoEntity) {
        //oAuthService.OnlyAdmins();
        validate(oTipoPlatoEntity);
        oTipoPlatoEntity.setId(null);
        return oTipoPlatoRepository.save(oTipoPlatoEntity).getId();
    }

    public Long delete(@PathVariable(value = "id") Long id) {
        //oAuthService.OnlyAdmins();
        if (oTipoPlatoRepository.existsById(id)) {
            oTipoPlatoRepository.deleteById(id);
            if (oTipoPlatoRepository.existsById(id)) {
                throw new ResourceNotModifiedException("No se puede borrar el registro: " + id);
            } else {
                return id;
            }
        } else {
            return 0L;
        }
    }
    public Long update(Long id, TipoPlatoEntity oTipoPlatoEntity) {
        //oAuthService.OnlyAdmins();
        oTipoPlatoEntity.setId(id);
        validate(id);
        validate(oTipoPlatoEntity);
        return oTipoPlatoRepository.save(oTipoPlatoEntity).getId();
    }

    
}
