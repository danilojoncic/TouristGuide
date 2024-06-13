package com.example.touristguide.requestFilters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.*;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenFilter implements ContainerRequestFilter {

    public static Map<String, List<String>> PROTECTED_PATHS = new HashMap<>();

    static {
        init();
    }
    private static void init() {
        List<String> normalProtection = new ArrayList<>();
        normalProtection.add("POST");
        normalProtection.add("PUT");
        normalProtection.add("DELETE");


        List<String> fullProtection = new ArrayList<>(normalProtection);
        fullProtection.add("GET");
        PROTECTED_PATHS.put("destination", normalProtection);
        PROTECTED_PATHS.put("article", normalProtection);
        PROTECTED_PATHS.put("user",fullProtection);
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getMethod();
        System.out.println("PATH " + path);
        System.out.println("METHOD " + method);

        if (isProtectedPath(path, method)) {
            System.out.println("Path is protected!");
            String authorizationHeader = requestContext.getHeaderString("Authorization");

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                return;
            }
            String token = authorizationHeader.substring("Bearer".length()).trim();

            try {
                Algorithm algorithm = Algorithm.HMAC256(JWTCoder.SECRET_KEY);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT jwt = verifier.verify(token);
                Claim claim = jwt.getClaim("tip");

                if(claim.isNull() || claim.asString().isEmpty()){
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    return;
                }

                if (path.equals("user") && (!claim.toString().equals("admin"))){
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    return;
                }

                requestContext.setProperty("jwt", jwt);
            } catch (JWTVerificationException exception) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }else{
            System.out.println("Path is not protected!");
        }
    }

    private boolean isProtectedPath(String path, String method) {
        for (Map.Entry<String, List<String>> entry : PROTECTED_PATHS.entrySet()) {
            String protectedPath = entry.getKey();
            List<String> methods = entry.getValue();

            if (path.matches(protectedPath) && methods.contains(method)) {
                return true;
            }
        }
        return false;
    }
}