/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsc.slo.tecinfo.estudante.security;

import ifsc.slo.tecinfo.estudante.modelo.Usuario;
import ifsc.slo.tecinfo.estudante.repositorio.UsuarioRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramao
 */
@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{

    @Autowired
    private UsuarioRepository ur;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = ur.findByLogin(login);
        
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario nao encontrado!"); 
        }
        return new User(usuario.getLogin(), usuario.getSenha(), true,true,true,true,usuario.getAuthorities());
    }
    
}
