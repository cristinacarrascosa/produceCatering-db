package daw.produceCatering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daw.produceCatering.entity.LineaEscandalloEntity;
import daw.produceCatering.exception.ResourceNotFoundException;
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

    public void validate(Long id) {
        if (!oLineaEscandalloRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

   
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

    

    
}
