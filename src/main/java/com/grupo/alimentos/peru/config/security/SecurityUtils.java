package com.grupo.alimentos.peru.config.security;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    
    private SecurityUtils() {}

    public static Long getTiendaIdActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails details) {
            return details.getTiendaId();
        }
        return null;
    }
    public static boolean tieneRol(String rol) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals(rol));
    }

    public static boolean esGerenteOperaciones(){
        return tieneRol("GERENTE_OPERACIONES");
    }

}
