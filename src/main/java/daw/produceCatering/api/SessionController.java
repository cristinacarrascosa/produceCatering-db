package daw.produceCatering.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daw.produceCatering.bean.UsuarioBean;
import daw.produceCatering.entity.UsuarioEntity;
import daw.produceCatering.service.AuthService;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    AuthService oAuthService;

    
    
    @PostMapping
    public ResponseEntity<String> login(@org.springframework.web.bind.annotation.RequestBody UsuarioBean oUserBean) {
        return new ResponseEntity<String>("\"" + oAuthService.login(oUserBean) + "\"", HttpStatus.OK);
    }

    
}
