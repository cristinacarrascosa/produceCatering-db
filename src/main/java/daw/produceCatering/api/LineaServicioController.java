package daw.produceCatering.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import daw.produceCatering.entity.LineaEscandalloEntity;
import daw.produceCatering.entity.LineaServicioEntity;
import daw.produceCatering.service.LineaEscandalloService;
import daw.produceCatering.service.LineaServicioService;

@RestController
@RequestMapping("/lineaservicio")
public class LineaServicioController {
    
    @Autowired
    LineaServicioService oLineaServicioService;

    @GetMapping("/{id}")
    public ResponseEntity<LineaServicioEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<LineaServicioEntity>(oLineaServicioService.get(id), HttpStatus.OK);
    }

    @GetMapping("")
	public ResponseEntity<Page<LineaServicioEntity>> getPage(
        	@RequestParam(value = "escandallo", required = false) Long id_escandallo,
            @RequestParam(value = "servicio", required = false) Long id_servicio,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "pax", required = false) Integer pax
            ) {
    	return new ResponseEntity<Page<LineaServicioEntity>>(oLineaServicioService.getPage(id_escandallo,id_servicio, page, size, pax), HttpStatus.OK);
	}

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oLineaServicioService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oLineaServicioService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody LineaServicioEntity oLineaServicioEntity) {
        return new ResponseEntity<Long>(oLineaServicioService.create(oLineaServicioEntity), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Long> update(@RequestBody LineaServicioEntity oLineaServicioEntity) {
        return new ResponseEntity<Long>(oLineaServicioService.update(oLineaServicioEntity), HttpStatus.OK);
    }


    
    
}
