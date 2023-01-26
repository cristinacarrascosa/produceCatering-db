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

import daw.produceCatering.entity.TipoPlatoEntity;
import daw.produceCatering.service.TipoPlatoService;

@RestController
@RequestMapping("/tipoplato")
public class TipoPlatoController {

    @Autowired
    TipoPlatoService oTipoPlatoService;

    @GetMapping("/{id}")
    public ResponseEntity<TipoPlatoEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<TipoPlatoEntity>(oTipoPlatoService.get(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<TipoPlatoEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return new ResponseEntity<Page<TipoPlatoEntity>>(oTipoPlatoService.getPage(oPageable, strFilter), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oTipoPlatoService.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody TipoPlatoEntity oTipoPlatoEntity) {
        return new ResponseEntity<Long>(oTipoPlatoService.create(oTipoPlatoEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oTipoPlatoService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Long> update(@RequestBody TipoPlatoEntity oTipoPlatoEntity) {
        return new ResponseEntity<Long>(oTipoPlatoService.update(oTipoPlatoEntity.getId(), oTipoPlatoEntity), HttpStatus.OK);
    }
    
}
