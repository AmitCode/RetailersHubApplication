package com.intoThe.filter;

import com.intoThe.exceptions.SuppliersOprException.JwtTokenValidationException;
import com.intoThe.service.AuthUserDetailService;
import com.intoThe.service.impl.AuthServiceImpl;
import com.intoThe.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtApiFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;
    private final AuthUserDetailService authUserDetailService;

    public JwtApiFilter(JWTUtils utils, AuthUserDetailService userDetails){
        this.jwtUtils = utils;
        this.authUserDetailService =  userDetails;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException, JwtTokenValidationException {
        String authenticationHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if(null != authenticationHeader && authenticationHeader.startsWith("Bearer ")){
            token = authenticationHeader.substring(7);
            userName = jwtUtils.getUserNameFromToken(token);
        }
        //SecurityContextHolder.getContext().getAuthentication() == null this we are using to ensure
        // that is the user is already authenticated we do not trying to authenticate again for the same request
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = authUserDetailService.loadUserByUsername(userName);

            if(jwtUtils.validateToken(token, userName)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/userService/");
    }
}
