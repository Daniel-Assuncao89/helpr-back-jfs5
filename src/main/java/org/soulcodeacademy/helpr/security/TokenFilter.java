package org.soulcodeacademy.helpr.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UsuarioSecurityService service;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!validarCabecalho(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = this.extrairToken(request);


        if (!this.tokenUtil.validarToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }


        String email = this.tokenUtil.extrairEmail(token);
        UserDetails usuario = this.service.loadUserByUsername(email);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities()));
        filterChain.doFilter(request, response);

    }


    private String extrairToken(HttpServletRequest request) {

        String cabecalho = request.getHeader("Authorization");

        return cabecalho.substring(7);
    }


    private boolean validarCabecalho(HttpServletRequest request) {

        String cabecalho = request.getHeader("Authorization");


        return cabecalho != null && cabecalho.startsWith("Bearer");
    }
}
