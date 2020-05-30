package org.manu.gcp.idtoken;

import com.google.auth.oauth2.IdToken;
import com.google.auth.oauth2.ServiceAccountCredentials;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static com.google.auth.oauth2.IdTokenProvider.Option;

public class IdTokenGenerator {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            exitWithError("Usage : java -jar IdTokenGenerator.jar <path-to-json> <client-id>");
        } else {
            Path svcAccFile = Paths.get(args[0]);
            String clientId = args[1];
            validateInput(svcAccFile, clientId);
            System.out.println("File path = " + svcAccFile);
            InputStream credentialsStream = Files.newInputStream(svcAccFile);
//            System.out.println("credentialsStream = " + credentialsStream);
            createToken(credentialsStream, clientId);
        }
    }

    private static void createToken(InputStream credentialsStream, String clientId) throws IOException {
        ServiceAccountCredentials sourceCredentials = ServiceAccountCredentials
                .fromStream(credentialsStream);
        IdToken idToken = sourceCredentials.idTokenWithAudience(clientId, new ArrayList<>());
        String idTokenStr = idToken.getTokenValue();
        System.out.println("ID Token :- \n" + idTokenStr);
    }


    private static void validateInput(Path scvAccFile, String clientId) {
        if (!Files.exists(scvAccFile)) {
            exitWithError("Provide a valid file path :- " + scvAccFile);
        }
        if (!clientId.endsWith("apps.googleusercontent.com")) {
            exitWithError("Provide a valid client id");
        }
    }

    private static void exitWithError(String error) {
        System.err.println(error);
        System.exit(1);
    }
}
