package org.codevscovid19.threedprintingservice.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class FirebasePreAuthenticationBearerTokenFilter extends AbstractPreAuthenticatedProcessingFilter {

    public static final String BEARER_HEADER_NAME = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public FirebasePreAuthenticationBearerTokenFilter() {
        this.setAuthenticationManager(authentication -> {
            authentication.setAuthenticated(true);
            return authentication;
        });
    }

    private Optional<String> extractBearerToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(BEARER_HEADER_NAME)).map(bearer -> bearer.replaceFirst(BEARER_PREFIX, ""));
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        Optional<String> decodedToken = this.extractBearerToken(request);
        if (decodedToken.isEmpty() || decodedToken.get().isBlank()) {
            throw new BadCredentialsException("No token provided");
        }
        try {
            return FirebaseAuth.getInstance().verifyIdToken(decodedToken.get());
        } catch (FirebaseAuthException e) {
            throw new BadCredentialsException(e.getMessage(), e);
        }
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null; // Not required for our auth flow
    }
}