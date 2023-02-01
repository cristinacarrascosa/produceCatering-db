package daw.produceCatering.api;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import daw.produceCatering.entity.EscandalloEntity;
import daw.produceCatering.service.AuthService;
import daw.produceCatering.service.EscandalloService;

@RestController
@RequestMapping("/escandallo")
public class EscandalloController {

    @Autowired
    EscandalloService oEscandalloService;

    @Autowired
    AuthService oAuthService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        //System.out.println("ProductoController petición: get: producto:" + id + " usuario: " + oAuthService.getUserID() + " is uaser: " + oAuthService.isUser());
            return new ResponseEntity<EscandalloEntity>(oEscandalloService.get(id), HttpStatus.OK);
        
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oEscandalloService.count(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<EscandalloEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "tipoplato", required = false) Long lTipoPlato) {
        return new ResponseEntity<Page<EscandalloEntity>>(oEscandalloService.getPage(oPageable, strFilter, lTipoPlato), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody EscandalloEntity oEscandalloEntity) {
        return new ResponseEntity<Long>(oEscandalloService.create(oEscandalloEntity), HttpStatus.OK);
    }

   
    @PutMapping
    public ResponseEntity<Long> update(@RequestBody EscandalloEntity oEscandalloEntity) {
        return new ResponseEntity<Long>(oEscandalloService.update(oEscandalloEntity), HttpStatus.OK);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oEscandalloService.delete(id), HttpStatus.OK);
    }
}
