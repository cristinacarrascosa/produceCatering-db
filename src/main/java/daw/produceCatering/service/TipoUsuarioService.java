package daw.produceCatering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import daw.produceCatering.entity.TipousuarioEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
import daw.produceCatering.repository.TipousuarioRepository;

@Service
public class TipoUsuarioService {

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    AuthService oAuthService;

    public TipousuarioEntity get(Long id) {
        validate(id);
        return oTipousuarioRepository.getById(id);
    }

    public Page<TipousuarioEntity> getPage(int page, int size) {
        Pageable oPageable = PageRequest.of(page, size);

        return oTipousuarioRepository.findAll(oPageable);
    }

    public Long count() {
        return oTipousuarioRepository.count();
    }

    public Long update(TipousuarioEntity oTipousuarioEntity) {
        //añadir luego el onlyAdmin
        validate(oTipousuarioEntity.getId());
        //validate(oTipousuarioEntity); //añadir luego con el onlyAdmin y con el validationHelper
        return oTipousuarioRepository.save(oTipousuarioEntity).getId();
    }

    public void validate(Long id) {
        if (!oTipousuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("id "+ id + "no existe");
        }
    }

    
    
}
