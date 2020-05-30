package org.manu.gcp.idtoken;

import com.google.auth.oauth2.IdToken;
import com.google.auth.oauth2.ServiceAccountCredentials;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class IdTokenGeneratorSimple {

    private static final Path SCV_ACC_FILE = Paths.get(".", "src/main/resources/svc.json");
    private static final String CLIENT_ID = "#######.apps.googleusercontent.com";

    public static void main(String[] args) throws Exception {
        InputStream credentialsStream = Files.newInputStream(SCV_ACC_FILE);
        System.out.println("credentialsStream = " + credentialsStream);
        createToken(credentialsStream, CLIENT_ID);
    }

    private static void createToken(InputStream credentialsStream, String clientId) throws IOException {
        ServiceAccountCredentials sourceCredentials = ServiceAccountCredentials
                .fromStream(credentialsStream);
        IdToken idToken = sourceCredentials.idTokenWithAudience(clientId, new ArrayList<>());
        String idTokenStr = idToken.getTokenValue();
        System.out.println("ID Token :- \n" + idTokenStr);
    }
}
