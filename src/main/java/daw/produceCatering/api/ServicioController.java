package daw.produceCatering.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daw.produceCatering.entity.ServicioEntity;
import daw.produceCatering.service.ServicioService;

@RestController
@RequestMapping("/servicio")
public class ServicioController {

    ServicioService oServicioService;

    @GetMapping("/{id}")
    public ResponseEntity<ServicioEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<ServicioEntity>(oServicioService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oServicioService.count(), HttpStatus.OK);
    }

    
}
