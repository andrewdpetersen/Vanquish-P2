package Application.filters;

import Application.DTOs.PrincipalDTO;
import Application.exceptions.AuthenticationException;
import Application.services.LoggerService;
import Application.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Authentication Filter
 * Chains with other filters on every endpoint call to make sure the current request is from a valid source
 * @author Kollier Martin
 * @date 11/8/2021
 */

@Component
public class AuthenticationFilter implements Filter {
    private LoggerService loggerService;
    private JWTUtil jwtUtil;

    @Override
    public void init(FilterConfig config) {
        ApplicationContext container = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        this.jwtUtil = container.getBean(JWTUtil.class);
        this.loggerService = container.getBean(LoggerService.class);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (!request.getRequestURI().equals("4TheMusic/login")
                || !request.getRequestURI().equals("4TheMusic/register/basic")
                || !request.getRequestURI().equals("4TheMusic/register/premium")) {
            parseToken(request);
        }
        System.out.println(request.getRequestURI());

        chain.doFilter(request, response);
    }
    private void parseToken(HttpServletRequest request) {
        String errMessage = "Unauthorized user tried to barge their way in here! Don't worry, I caught this transgression. Error: %s";

        try {
            String header = request.getHeader(jwtUtil.getHeader());

            if (header == null || !header.startsWith(jwtUtil.getPrefix())) {
                loggerService.writeLog(errMessage, 3);
                return;
            }

            String token = header.replaceAll(jwtUtil.getPrefix(), "");

            Claims jwtClaims = jwtUtil.parseJWT(token);
            PrincipalDTO principalDTO = new PrincipalDTO(jwtClaims);
            request.setAttribute("principal", principalDTO);

        } catch (AuthenticationException e) {
            loggerService.writeLog(("Uh oh... Authentication Filter has a message: " + e.getMessage()), 3);
        }
    }
}
