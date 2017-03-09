/*
this's class will control database with mongodb
 */
package hecquyn;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author Vuong Gia Phu
 */
public class MyDatabase {
    private DB db;
    
    public MyDatabase() {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            db = mongoClient.getDB("hecquyn");
            System.out.println("Connect database successfully!");
        } catch(Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public DB getDB() {
        return db;
    }
    
    //-------------------------
    public void insert(String collection, String link, String text) {
        DBCollection dept = db.getCollection(collection);
        BasicDBObject doc = new BasicDBObject();
        doc.append("link", link);
        doc.append("text", text);
        dept.insert(doc);
    }
}
