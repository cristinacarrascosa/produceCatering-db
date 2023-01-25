package daw.produceCatering.api;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("")
    public ResponseEntity<Page<SalonEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "espacio", required = false) Long lEspacio) {
        return new ResponseEntity<Page<SalonEntity>>(oSalonService.getPage(oPageable, strFilter, lEspacio), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody SalonEntity oSalonEntity) {
        return new ResponseEntity<Long>(oSalonService.create(oSalonEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oSalonService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Long> update(@RequestBody SalonEntity oSalonEntity) {
        return new ResponseEntity<Long>(oSalonService.update(oSalonEntity), HttpStatus.OK);
    }
    
}
