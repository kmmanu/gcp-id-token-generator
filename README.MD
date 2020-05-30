# Id Token Generator from GCP service account JSON key

## 1. Running from IDE :-
* Put the service account json in the file `src/main/resources/svc.json`
* Open [IdTokenGeneratorSimple.java](src/main/java/org/manu/gcp/idtoken/IdTokenGeneratorSimple.java) and update the constant `CLIENT_ID` to proper value.
* Right click and run.

## 2. Running from jar :-
* checkout code and run `mvn clean package`
* java -jar target/IdTokenGenerator.jar &lt;path-to-svc-json-file&gt; &lt;client-id&gt;
    * Eg. `java -jar target/IdTokenGenerator.jar d:\svc.json 123456789-rtr546jgd343ssd.apps.googleusercontent.com`


Copy the generated token and use it as the Bearer Token for the APIs.