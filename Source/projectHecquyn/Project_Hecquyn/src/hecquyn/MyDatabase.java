/*
this's class will control database with mongodb
 */
package hecquyn;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author Vuong Gia Phu
 */
public class MyDatabase {
    private static String host = "localhost";
    private static int port = 27017;
    
    //connect mongodb
    private static MongoClient getMongoClient() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(host, port);
        return mongoClient;
    }
    
    public static MongoClient connectMongoClient() throws UnknownHostException {
        return getMongoClient();
    }
    
    private static void ping() throws UnknownHostException {
        MongoClient mongoClient = getMongoClient();
        
        System.out.println("List all DB");
        
        List<String> dbNames = mongoClient.getDatabaseNames();
        for(String name : dbNames) {
            System.out.println(name);
        }
        
        System.out.println("Success connection");
    }
    
    //test
    public static void main(String[] args) throws UnknownHostException {
        ping();
    }
}
