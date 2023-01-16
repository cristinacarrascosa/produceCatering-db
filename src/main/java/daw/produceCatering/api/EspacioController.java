package daw.produceCatering.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daw.produceCatering.entity.EspacioEntity;
import daw.produceCatering.service.EspacioService;

@RestController
@RequestMapping("/espacio")
public class EspacioController {

    @Autowired
    EspacioService oEspacioService;

    @GetMapping("/{id}")
    public ResponseEntity<EspacioEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<EspacioEntity>(oEspacioService.get(id), HttpStatus.OK);
    }
    
}
