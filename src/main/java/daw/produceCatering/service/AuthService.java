package daw.produceCatering.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import daw.produceCatering.bean.UsuarioBean;
import daw.produceCatering.entity.UsuarioEntity;
import daw.produceCatering.exception.UnauthorizedException;
import daw.produceCatering.helper.TipoUsuarioHelper;
import daw.produceCatering.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    public UsuarioEntity login(@RequestBody UsuarioBean oUsuarioBean) {
        if (oUsuarioBean.getPassword() != null) {
            UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByLoginAndPassword(oUsuarioBean.getLogin(), oUsuarioBean.getPassword());
            if (oUsuarioEntity != null) {
                oHttpSession.setAttribute("usuario", oUsuarioEntity);
                return oUsuarioEntity;
            } else {
                throw new UnauthorizedException("login o password incorecto");
            }
        } else {
            throw new UnauthorizedException("password incorrecto");
        }
        
    }

    public void logout() {
        oHttpSession.invalidate();
    }

    public UsuarioEntity check() {
        UsuarioEntity oUsuarioSessionEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioSessionEntity != null) {
            return oUsuarioSessionEntity;
        } else {
            throw new UnauthorizedException("usuario no logueado");
        }
    }

    public boolean isLoggedIn() {
        UsuarioEntity oUsuarioSessionEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioSessionEntity == null) {
            return false;
        } else {
            return true;
        }
    }

    public UsuarioEntity getUser() {
        UsuarioEntity oUsuarioSessionEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioSessionEntity != null) {
            return oUsuarioSessionEntity;
        } else {
            throw new UnauthorizedException("solicitud permitida solo a usuarios autenticados");
        }
    }

    public Long getUserID() {
        UsuarioEntity oUsuarioSessionEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioSessionEntity != null) {
            return oUsuarioSessionEntity.getId();
        } else {
            throw new UnauthorizedException("solicitud permitida solo a usuarios autenticados");
        }
    }

    public boolean isAdmin() {
        UsuarioEntity oUsuarioSessionEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioSessionEntity != null) {
            if (oUsuarioSessionEntity.getTipousuario().getId().equals(TipoUsuarioHelper.ADMIN)) {
                return true;
            } 
        } 
        return false;
    }

    public boolean isUser() {
        UsuarioEntity oUsuarioSessionEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioSessionEntity != null) {
            if (oUsuarioSessionEntity.getTipousuario().getId().equals(TipoUsuarioHelper.USER)) {
                return true;
            } 
        } 
        return false;
    }

    //* copiado hasta aquí, faltarían OnlyUsers, OnlyAdminsOrUsers , etc.. Mirar a lo largo del proyecto si hacen falta.*/
}
