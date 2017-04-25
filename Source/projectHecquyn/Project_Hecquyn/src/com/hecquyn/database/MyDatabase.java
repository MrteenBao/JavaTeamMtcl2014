package DATABASE_HECQUIN;





import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

import com.mongodb.*;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Database {
	public MongoCollection<Document> Collection;
	public static MongoDatabase database;

	public Database(String Collection) throws UnknownHostException {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);
		// Kết nối tới MongoDB.
		MongoClient mongoClient = getMongoClient();
		this.Collection = database.getCollection(Collection);
		// Kết nối tới Database
		//database.getCollection(Collection).createIndex(new Document("_Image","text").append("_Title", "text").append("_Hashtag", "text"));
	}

	// Kết nối authorized 
	private static MongoClient getMongoClient() throws UnknownHostException {
		MongoClientURI uri = new MongoClientURI(
				"mongodb://Administrator:dangthienbao1412@ds161159.mlab.com:61159/javamtcl2014");
		MongoClient mongoClient = new MongoClient(uri);
		database = mongoClient.getDatabase(uri.getDatabase());
		return mongoClient;
	}

	public List<Document> Search_Query(Document query, Document OrderBy) {
		List<Document> documents = new ArrayList<>();
		try {
			MongoCursor<Document> cur = this.Collection.find(query).sort(OrderBy).iterator();
			if (cur.hasNext()) {
				while (cur.hasNext()) {
					documents.add(cur.next());
				}
			} else {
				System.out.println("No Result !!");
			}
			cur.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documents;
	}

	public String FindOneString(Document query, String key) {
		MongoCursor<Document> cursor = this.Collection.find(query).iterator();
		if (cursor.hasNext()) {
			return cursor.next().getString(key);
		}
		return null;
	}

	public int FindOneInt(Document query, String key) {
		MongoCursor<Document> cursor = this.Collection.find(query).iterator();
		if (cursor.hasNext()) {
			return cursor.next().getInteger(key);
		}
		return 0;
	}

	public boolean CheckExist(Document query) {
		MongoCursor<Document> cursor = this.Collection.find(query).iterator();
		return cursor.hasNext();
	}

	public long CountRecords(Document query) {
		return this.Collection.count(query);
	}

	public void RemoveRecord(Document query) {
		this.Collection.deleteOne(query);
	}

	public List<String> getNews(){
		List<String> list = new ArrayList<>();
		try{
			MongoCursor<Document> cur = null;
			cur =  this.Collection.find().iterator();
			if(cur.hasNext()){
				while(cur.hasNext()){
					list.add(cur.next().toJson());
				}
			}
			else{
				list.add("NULL");
			}
			cur.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public List<String> Search_Image(String query){
		List<String> list = new ArrayList<>();
		try{
			MongoCursor<Document> cur = null;
			cur = this.Collection.find(new Document("_Image",query)).iterator();
			if(cur.hasNext()){
				while(cur.hasNext()){
					list.add(cur.next().toJson());
				}
			}
		}catch(Exception e ){
			e.printStackTrace();
		}return list;
	}
	public  List<Document> Search(String query, boolean caseSensitive, boolean diacriticSensitive) {
		List<Document> list = new ArrayList<>();
		try {
			MongoCursor<Document> cursor = null;
			//int i =0;
			cursor = this.Collection
					.find(new Document("$text",
							new Document("$search", query).append("$caseSensitive", new Boolean(caseSensitive))
									.append("$diacriticSensitive", new Boolean(diacriticSensitive)))).sort(new Document("_Date",1)).iterator();
			if(cursor.hasNext()){
				while (cursor.hasNext()) {
					list.add(cursor.next());
				}
			}else {
				return null;
			}
			cursor.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}return list;
	}
}                                              
