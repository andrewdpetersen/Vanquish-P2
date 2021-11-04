package VanquishP2.Application.Beans.Configs.Filters;

import VanquishP2.Application.Beans.Service.JWTUtil;
import VanquishP2.Application.Beans.Service.Logger;
import VanquishP2.Exceptions.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@Component*/
public class AuthenticationFilter implements Filter {
    private JWTUtil jwtUtil;
    private String errMessage = "Unauthorized user tried to barge their way in here! Don't worry, I caught this transgression. Error: %s";


    @Override
    public void init(FilterConfig cfg) {
        ApplicationContext container = WebApplicationContextUtils.getRequiredWebApplicationContext(cfg.getServletContext());
        this.jwtUtil = container.getBean(JWTUtil.class);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        parseToken(request);
        chain.doFilter(request, response);
    }

    private void parseToken(HttpServletRequest req) {

        try {
            String header = req.getHeader(jwtUtil.getHeader());
            if (header == null || !header.startsWith(jwtUtil.getPrefix())) {
                Logger.getFileLogger().writeLog("Request originates from an unauthenticated source.", 3);
                return;
            }

            String token = header.replaceAll(jwtUtil.getPrefix(), "");

            Jws<Claims> jwtClaims = jwtUtil.parseJWT(token);

        } catch (AuthenticationException e) {
            Logger.getFileLogger().writeLog(String.format(errMessage, e.getMessage()), 3);
        }
    }
}
