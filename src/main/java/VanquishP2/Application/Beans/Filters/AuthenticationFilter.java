package VanquishP2.Application.Beans.Filters;

import VanquishP2.Application.Beans.Service.JWTUtil;
import VanquishP2.DTOs.PrincipalDTO;
import VanquishP2.Exceptions.AuthenticationException;
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
    private JWTUtil jwtUtil;

    @Override
    public void init(FilterConfig config) {
        ApplicationContext container = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        this.jwtUtil = container.getBean(JWTUtil.class);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (!request.getRequestURI().equals("/4TheMusic/login")
                && !request.getRequestURI().equals("/4TheMusic/register/basic")
                && !request.getRequestURI().equals("/4TheMusic/register/premium")
                && !request.getRequestURI().equals("/4TheMusic/user/all")) {
            parseToken(request);
        }

        chain.doFilter(request, response);
    }

    private void parseToken(HttpServletRequest request) throws AuthenticationException {
        String errMessage = "Unauthorized user tried to barge their way in here! Don't worry, I caught this transgression. Error: %s";

        String header = request.getHeader(jwtUtil.getHeader());

        if (header == null || !header.startsWith(jwtUtil.getPrefix())) {
            throw new AuthenticationException(errMessage);
        } else {
            String token = header.replaceAll(jwtUtil.getPrefix(), "");

            Claims jwtClaims = jwtUtil.parseJWT(token);
            PrincipalDTO principalDTO = new PrincipalDTO(jwtClaims);
            request.setAttribute("principal", principalDTO);
        }
    }
}
