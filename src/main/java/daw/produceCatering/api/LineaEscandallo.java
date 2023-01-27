package daw.produceCatering.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daw.produceCatering.entity.LineaEscandalloEntity;
import daw.produceCatering.service.LineaEscandalloService;

@RestController
@RequestMapping("/lineaescandallo")
public class LineaEscandallo {

    @Autowired
    LineaEscandalloService oLineaEscandalloService;

    @GetMapping("/{id}")
    public ResponseEntity<LineaEscandalloEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<LineaEscandalloEntity>(oLineaEscandalloService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oLineaEscandalloService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oLineaEscandalloService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody LineaEscandalloEntity oLineaEscandalloEntity) {
        return new ResponseEntity<Long>(oLineaEscandalloService.create(oLineaEscandalloEntity), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Long> update(@RequestBody LineaEscandalloEntity oLineaEscandalloEntity) {
        return new ResponseEntity<Long>(oLineaEscandalloService.update(oLineaEscandalloEntity), HttpStatus.OK);
    }


    

    
    
}
