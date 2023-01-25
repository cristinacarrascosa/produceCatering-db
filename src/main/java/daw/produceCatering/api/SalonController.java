package daw.produceCatering.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daw.produceCatering.entity.SalonEntity;
import daw.produceCatering.service.SalonService;

@RestController
@RequestMapping("/salon")
public class SalonController {

    @Autowired
    SalonService oSalonService;

    @GetMapping("/{id}")
    public ResponseEntity<SalonEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<SalonEntity>(oSalonService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oSalonService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oSalonService.delete(id), HttpStatus.OK);
    }
    
}
