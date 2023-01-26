package daw.produceCatering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import daw.produceCatering.entity.ReferenciaEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.exception.ResourceNotModifiedException;
import daw.produceCatering.helper.ValidationHelper;
import daw.produceCatering.repository.ReferenciaRepository;

@Service
public class ReferenciaService {

    @Autowired
    ReferenciaRepository oReferenciaRepository;

    @Autowired
    AuthService oAuthService;

    public void validate(Long id) {
        if (!oReferenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(ReferenciaEntity oReferenciaEntity) {
        ValidationHelper.validateStringLength(oReferenciaEntity.getNombre(), 2, 100, "campo nombre referencia (el campo debe tener longitud de 2 a 100 caracteres)");
    }

    public ReferenciaEntity get(@PathVariable(value = "id") Long id) {
        if (oReferenciaRepository.existsById(id)) {
            return oReferenciaRepository.getById(id);
        } else {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }
    
    public Long count() {
        return oReferenciaRepository.count();
    }

    public Long create(@RequestBody ReferenciaEntity oReferenciaEntity) {
        //oAuthService.OnlyAdmins();
        validate(oReferenciaEntity);
        oReferenciaEntity.setId(null);
        return oReferenciaRepository.save(oReferenciaEntity).getId();
    }

    public Long delete(@PathVariable(value = "id") Long id) {
        //oAuthService.OnlyAdmins();
        if (oReferenciaRepository.existsById(id)) {
            oReferenciaRepository.deleteById(id);
            if (oReferenciaRepository.existsById(id)) {
                throw new ResourceNotModifiedException("No se puede borrar el registro: " + id);
            } else {
                return id;
            }
        } else {
            return 0L;
        }
    }
    public Long update(Long id, ReferenciaEntity oReferenciaEntity) {
        //oAuthService.OnlyAdmins();
        oReferenciaEntity.setId(id);
        validate(id);
        validate(oReferenciaEntity);
        return oReferenciaRepository.save(oReferenciaEntity).getId();
    }

    public Page<ReferenciaEntity> getPage(Pageable oPageable, String strFilter) {
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<ReferenciaEntity> oPage = null;
        if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
            oPage = oReferenciaRepository.findAll(oPageable);
        } else {
            oPage = oReferenciaRepository.findByNombreIgnoreCaseContaining(strFilter, oPageable);
        }
        return oPage;
    }


}
