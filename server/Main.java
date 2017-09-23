/*
 * REFERENCES
 * http://sparkjava.com/documentation#getting-started
 * http://www.programcreek.com/2014/01/compile-and-run-java-in-command-line-with-external-jars/
 */

package server;
 
import static spark.Spark.*;
import org.json.simple.JSONObject;

import java.lang.*;
import java.text.*;
import java.io.*;
import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import java.util.concurrent.*;
import java.util.concurrent.CompletableFuture.*;
import java.util.concurrent.ExecutorService.*;
import java.util.concurrent.Executors.*;

/*
 * INSTRUCTIONS TO START SERVER ON CLOUD9
 * Replace text in {} with appropriate value
 * >> javac -cp ".:lib/*" Server.java
 * >> java -cp ".:lib/*" Server {workspacename}-{username}
 * Open: http://{workspacename}-{username}.c9users.io/{route}
 */

public class Main {
    
    public static final String MAIN_URL = "http://{workspacename}-{username}.c9users.io/{route}";

    public static void main(String[] args){
        
        // Cloud9 environments serve localhost to port 8080
        port(8080);
        // Enable Cross-Origin Requests
        enableCORS("*", "*", "*");
        
        before((req, res) -> {
            String path = req.pathInfo();
            if(path.endsWith("/") && path.length() > 1){
                res.redirect(path.substring(0, path.length() - 1));
            }
        });
        
        System.out.println("\nInitializing Server.\n");
        
            
        get("/", (req, res) -> {
           return "Spark Server"; 
        });
        
        get("/hello", (req, res) -> "Hello World!");
        
        /*
         * Example of reflecting input parameters in response
         */
        get("/self/:name", (req, res) -> {
            JSONObject obj = new JSONObject();
            // Fetch a parameter from the URL
            obj.put("name", req.params(":name"));
            // Fetch a parameter from the query
            obj.put("data", req.queryParams("data"));
            return obj;
        });
        
        /*
         * Example of using local classes to generate response
         */
        get("/person", (req, res) -> {
            String name = req.queryParams("name");
            if(name == null){
                name = "Sam Person";
            }
            Person person = new Person(name); 
            JSONObject obj = new JSONObject();
            obj.put("name", person.getName());
            return obj;
        });
        
        System.out.println("\nServer up and running.\n");
        System.out.println("\nNavigate to: " + MAIN_URL + "\n");
        System.out.println("\nOr Navigate to: localhost:8080\n");
    
    }
    
    /*
     * Enables CORS on requests. This method is an initialization method and should be called once.
     */
    private static void enableCORS(final String origin, final String methods, final String headers) {
    
        options("/*", (request, response) -> {
    
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
    
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
    
            return "OK";
        });
    
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }
    
}