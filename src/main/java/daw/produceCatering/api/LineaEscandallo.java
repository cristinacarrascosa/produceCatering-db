package daw.produceCatering.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daw.produceCatering.entity.LineaEscandalloEntity;
import daw.produceCatering.service.LineaEscandalloService;

@RestController
@RequestMapping("/lineaescandallo")
public class LineaEscandallo {

    @Autowired
    LineaEscandalloService oLineaEscanadalloService;

    @GetMapping("/{id}")
    public ResponseEntity<LineaEscandalloEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<LineaEscandalloEntity>(oLineaEscanadalloService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oLineaEscanadalloService.count(), HttpStatus.OK);
    }

    

    
    
}
