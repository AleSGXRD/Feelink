package com.sigith.feelink.clerk;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

import com.clerk.backend_api.helpers.security.AuthenticateRequest;
import com.clerk.backend_api.helpers.security.models.AuthenticateRequestOptions;
import com.clerk.backend_api.helpers.security.models.RequestState;

public class UserAuthentication {

    public static boolean isSignedIn(HttpRequest request) {
        Map<String, List<String>> headers = request.headers().map();
        RequestState requestState = AuthenticateRequest.authenticateRequest(headers, AuthenticateRequestOptions
                .secretKey(System.getenv("CLERK_SECRET_KEY"))
                .authorizedParty("https://example.com")
                .build());
        System.out.println(requestState.claims());
        return requestState.isSignedIn();
    }
}
