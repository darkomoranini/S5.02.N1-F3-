package cat.itacademy.barcelonactiva.morandini.darko.s05.t02.n01.f3.jwt.authentication.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import cat.itacademy.barcelonactiva.morandini.darko.s05.t02.n01.f3.jwt.service.JwtService;


public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // Obtiene el token del encabezado de la solicitud
        String token = extractTokenFromHeader(req);
        
        // Si se encuentra un token válido, establece la autenticación
        if (token != null && jwtService.validateToken(token)) {
            // Extrae el nombre de usuario de los claims del token
            String username = jwtService.extractUsername(token);
            // Crea una autenticación
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);     
            // Establece la autenticación
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(req, res);
    }

    private String extractTokenFromHeader(ServletRequest req) {
      
        // Obtiene el encabezado "Authorization" de la solicitud
        String header = ((HttpServletRequest) req).getHeader("Authorization");

        // Verifica si el encabezado contiene un token válido
        if (header != null && header.startsWith("Bearer ")) {
            return header.replace("Bearer ", "");
        }

        return null; // Si no se encuentra
    }
}

