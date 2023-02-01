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

import daw.produceCatering.entity.ServicioEntity;
import daw.produceCatering.service.ServicioService;

@RestController
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    ServicioService oServicioService;

    @GetMapping("/{id}")
    public ResponseEntity<ServicioEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<ServicioEntity>(oServicioService.get(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "usuario", required = false) Long lUsuario,
            @RequestParam(name = "salon", required = false) Long lSalon) {
        return new ResponseEntity<Page<ServicioEntity>>(oServicioService.getPage(oPageable, strFilter, lUsuario, lSalon), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oServicioService.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody ServicioEntity oServicioEntity) {
        return new ResponseEntity<Long>(oServicioService.create(oServicioEntity), HttpStatus.OK);
    }

   
    @PutMapping
    public ResponseEntity<Long> update(@RequestBody ServicioEntity oServicioEntity) {
        return new ResponseEntity<Long>(oServicioService.update(oServicioEntity), HttpStatus.OK);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oServicioService.delete(id), HttpStatus.OK);
    }

    
}
