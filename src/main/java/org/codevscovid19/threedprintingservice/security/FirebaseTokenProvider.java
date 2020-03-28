package org.codevscovid19.threedprintingservice.security;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FirebaseTokenProvider {
    public FirebaseToken getSessionPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (FirebaseToken) authentication.getPrincipal();
    }
}
