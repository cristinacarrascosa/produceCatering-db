package daw.produceCatering.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import daw.produceCatering.bean.UsuarioBean;
import daw.produceCatering.entity.UsuarioEntity;
import daw.produceCatering.exception.UnauthorizedException;
import daw.produceCatering.helper.JwtHelper;
import daw.produceCatering.helper.TipoUsuarioHelper;
import daw.produceCatering.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private HttpServletRequest oRequest;

    @Autowired
    UsuarioRepository oUsuarioRepository;

  


    public String login(@RequestBody UsuarioBean oUsuarioBean) {
        if (oUsuarioBean.getPassword() != null) {
            UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByLoginAndPassword(oUsuarioBean.getLogin(), oUsuarioBean.getPassword());
            if (oUsuarioEntity != null) {
                return JwtHelper.generateJWT(oUsuarioBean.getLogin(), oUsuarioEntity.getTipousuario().getId());
            } else {
                throw new UnauthorizedException("login or password incorrect");
            }
        } else {
            throw new UnauthorizedException("wrong password");
        }
    }

    

    public boolean isAdmin() {
        UsuarioEntity oUsuarioSessionEntity = oUsuarioRepository.findByLogin((String) oRequest.getAttribute("usuario"));
        if (oUsuarioSessionEntity != null) {
            if (oUsuarioSessionEntity.getTipousuario().getId().equals(TipoUsuarioHelper.ADMIN)) {
                return true;
            }
        }
        return false;
    }

    public void OnlyAdmins() {
        UsuarioEntity oUsuarioSessionEntity = oUsuarioRepository.findByLogin((String) oRequest.getAttribute("usuario"));
        if (oUsuarioSessionEntity == null) {
            throw new UnauthorizedException("this request is only allowed to admin role");
        } else {
            if (!oUsuarioSessionEntity.getTipousuario().getId().equals(TipoUsuarioHelper.ADMIN)) {
                throw new UnauthorizedException("this request is only allowed to admin role");
            }
        }
    }

    public boolean isLoggedIn() {
        String strUsuarioName = (String) oRequest.getAttribute("usuario");
        if (strUsuarioName == null) {
            return false;
        } else {
            return true;
        }
    }

    public Long getUserID() {
        String strUsuarioName = (String) oRequest.getAttribute("usuario");
        UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByLogin(strUsuarioName);
        if (oUsuarioEntity != null) {
            return oUsuarioEntity.getId();
        } else {
            throw new UnauthorizedException("this request is only allowed to auth Usuarios");
        }
    }

    
    public boolean isUsuario() {
        String strUsuarioName = (String) oRequest.getAttribute("usuario");
        UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByLogin(strUsuarioName);
        if (oUsuarioEntity != null) {
            if (oUsuarioEntity.getTipousuario().getId().equals(TipoUsuarioHelper.USER)) {
                return true;
            }
        }
        return false;
    }

    public void OnlyUsuarios() {
        String strUsuarioName = (String) oRequest.getAttribute("usuario");
        UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByLogin(strUsuarioName);
        if (oUsuarioEntity == null) {
            throw new UnauthorizedException("this request is only allowed to usuario role");
        } else {
            if (!oUsuarioEntity.getTipousuario().getId().equals(TipoUsuarioHelper.USER)) {
                throw new UnauthorizedException("this request is only allowed to usuario role");
            }
        }
    }

    public void OnlyAdminsOrUser() {
        String strUsuarioName = (String) oRequest.getAttribute("usuario");
        UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByLogin(strUsuarioName);
        if (oUsuarioEntity == null) {
            throw new UnauthorizedException("this request is only allowed to admin or reviewer role");
        } else {
            if (oUsuarioEntity.getTipousuario().getId().equals(TipoUsuarioHelper.ADMIN)) {
            } else {
                if (oUsuarioEntity.getTipousuario().getId().equals(TipoUsuarioHelper.USER)) {
                } else {
                    throw new UnauthorizedException("this request is only allowed to admin or reviewer role");
                }
            }
        }
    }

    public void OnlyOwnUsuariosData(Long id) {
        String strUsuarioName = (String) oRequest.getAttribute("usuario");
        UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByLogin(strUsuarioName);
        if (oUsuarioEntity != null) {
            if (oUsuarioEntity.getTipousuario().getId().equals(TipoUsuarioHelper.USER)) {
                if (!oUsuarioEntity.getId().equals(id)) {
                    throw new UnauthorizedException("this request is only allowed for your own usuario data");
                }
            } else {
                throw new UnauthorizedException("this request is only allowed to usuario role");
            }
        } else {
            throw new UnauthorizedException("this request is only allowed to usuario role");
        }
    }

   

}
