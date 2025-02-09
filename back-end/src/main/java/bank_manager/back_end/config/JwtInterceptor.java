package bank_manager.back_end.config;

import bank_manager.back_end.enums.EntityType;
import bank_manager.back_end.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String token = request.getHeader("Authorization").substring(7);

        if (token == null || !JwtUtils.isTokenValid(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            return false;
        }

        Claims claims = JwtUtils.getAllClaims(token);

        Long entityId = JwtUtils.getEntityId(claims);
        EntityType entityType = JwtUtils.getEntityType(claims);

        request.setAttribute("entityId", entityId);
        request.setAttribute("entityType", entityType);

        if(isAdminRoute(request) && entityType != EntityType.ADMIN){
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not enough rights for this request");
            return false;
        }

        return true;
    }

    private boolean isAdminRoute(HttpServletRequest request){
        String path = request.getRequestURI();
        return (path.startsWith("/admin"));
    }
}
