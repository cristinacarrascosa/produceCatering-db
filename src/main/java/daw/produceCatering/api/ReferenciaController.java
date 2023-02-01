package daw.produceCatering.api;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.data.domain.Sort;

import daw.produceCatering.entity.ReferenciaEntity;
import daw.produceCatering.service.ReferenciaService;

@RestController
@RequestMapping("/referencia")
public class ReferenciaController {

    @Autowired
    ReferenciaService oReferenciaService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ReferenciaEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<ReferenciaEntity>(oReferenciaService.get(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<ReferenciaEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return new ResponseEntity<Page<ReferenciaEntity>>(oReferenciaService.getPage(oPageable, strFilter), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oReferenciaService.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody ReferenciaEntity oReferenciaEntity) {
        return new ResponseEntity<Long>(oReferenciaService.create(oReferenciaEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oReferenciaService.delete(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Long> update(@RequestBody ReferenciaEntity oReferenciaEntity) {
        return new ResponseEntity<Long>(oReferenciaService.update(oReferenciaEntity.getId(), oReferenciaEntity), HttpStatus.OK);
    }
}
