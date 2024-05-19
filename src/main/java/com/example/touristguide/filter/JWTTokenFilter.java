package com.example.touristguide.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.touristguide.jwt.JWTCoder;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenFilter implements ContainerRequestFilter {

    public static Map<String, Set<String>> PROTECTED_PATHS = new HashMap<>();

    static {
        //to do zasada su sve zasticene
//        Set<String> methods = new HashSet<>();
//        methods.add("GET");
//        methods.add("DELETE");
//        methods.add("POST");
//        methods.add("PUT");
//        PROTECTED_PATHS.put("/api/destination", methods);
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getMethod();
        System.out.println("STARTED FILTERING: " + path + " : " + method);

        if (isProtectedPath(path, method)) {
            System.out.println("ROUTE IS PROTECTED");
            String authorizationHeader = requestContext.getHeaderString("Authorization");

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                System.out.println("HEADER PRAZAN ILI NEMAMO BEARER UNAUTHORIZED");
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                return;
            }

            String token = authorizationHeader.substring("Bearer".length()).trim();
            System.out.println("TOKEN: " + token);

            try {
                Algorithm algorithm = Algorithm.HMAC256(JWTCoder.SECRET_KEY);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT jwt = verifier.verify(token);
                requestContext.setProperty("jwt", jwt);
                System.out.println("JWT VERIFICATION SUCCESSFUL");
            } catch (JWTVerificationException exception) {
                System.out.println("JWT VERIFICATION FAILED: " + exception.getMessage());
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } else {
            System.out.println("ROUTE IS NOT PROTECTED");
        }
    }

    private static boolean isProtectedPath(String path, String method) {
        System.out.println("CHECKING PROTECTED PATH: " + path + " : " + method);
        Set<String> methods = PROTECTED_PATHS.get("/api/"+path);
        boolean isProtected = methods != null && methods.contains(method);
        System.out.println("IS PROTECTED: " + isProtected);
        return isProtected;
    }
}
