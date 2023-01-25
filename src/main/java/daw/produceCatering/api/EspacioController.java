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
    
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oEspacioService.count(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<EspacioEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return new ResponseEntity<Page<EspacioEntity>>(oEspacioService.getPage(oPageable, strFilter), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oEspacioService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody EspacioEntity oEspacioEntity) {
        return new ResponseEntity<Long>(oEspacioService.create(oEspacioEntity), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Long> update(@RequestBody EspacioEntity oEspacioEntity) {
        return new ResponseEntity<Long>(oEspacioService.update(oEspacioEntity.getId(), oEspacioEntity), HttpStatus.OK);
    }

}
