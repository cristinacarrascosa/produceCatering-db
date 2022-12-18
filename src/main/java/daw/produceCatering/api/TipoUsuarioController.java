package daw.produceCatering.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import daw.produceCatering.entity.TipousuarioEntity;
import daw.produceCatering.service.TipoUsuarioService;

@RestController
@RequestMapping("/tipousuario")
public class TipoUsuarioController {

    @Autowired
    TipoUsuarioService oTipoUsuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<TipousuarioEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<TipousuarioEntity>(oTipoUsuarioService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oTipoUsuarioService.count(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<TipousuarioEntity>> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return new ResponseEntity<>(oTipoUsuarioService.getPage(page, size), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable(value = "id") Long id, TipousuarioEntity oTipousuarioEntity) {
        return new ResponseEntity<Long>(oTipoUsuarioService.update(oTipousuarioEntity), HttpStatus.OK);
    }
    
}
