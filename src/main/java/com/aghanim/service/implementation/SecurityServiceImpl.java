package com.aghanim.service.implementation;

import com.aghanim.service.abstraction.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public String findLoggedInLogin() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails){
            return (((UserDetails) userDetails).getUsername());
        }
        return null;
    }

    @Override
    public void autoLogin(String login, String password, HttpServletRequest req, HttpServletResponse resp) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        req.getSession();
        token.setDetails(new WebAuthenticationDetails(req));
        authenticationProvider.authenticate(token);
    }
}
