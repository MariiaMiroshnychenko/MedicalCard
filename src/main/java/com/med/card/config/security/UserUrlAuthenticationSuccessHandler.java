package com.med.card.config.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class UserUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {

        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    private void handle(HttpServletRequest request,
                        HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug(
                    "Response has already been committed. Unable to redirect to "
                            + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isPatient = false;
        boolean isDoctor = false;
        boolean isSpecDoctor = false;
        boolean isMedEmployee = false;
        boolean isAdmin = false;

        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
                case ("Пацієнт"):
                    isPatient = true;
                    break;
                case ("Сімейний лікар"):
                    isDoctor = true;
                    break;
                case ("Лікар за спеціальністю"):
                    isSpecDoctor = true;
                    break;
                case ("Медичний персонал"):
                    isMedEmployee = true;
                    break;
                case ("Адміністратор"):
                    isAdmin = true;
                    break;
            }
        }

        if (isPatient) {
            return "/patientPage";
        } else if (isDoctor) {
            return "/doctorPage";
        } else if (isSpecDoctor) {
            return "/specDoctorPage";
        } else if (isMedEmployee) {
            return "/medEmployeePage";
        } else if (isAdmin) {
            return "/adminPage";
        } else {
            throw new IllegalStateException();
        }
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
