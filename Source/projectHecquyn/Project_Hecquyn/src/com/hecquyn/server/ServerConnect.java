

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.NotYetConnectedException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class ServerConnect extends WebSocketServer {
	public ServerConnect(int port) {
		super(new InetSocketAddress(port));
	}

	public ServerConnect(InetSocketAddress address) {
		super(address);	
	}
	
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		log("New connection from " + conn.getRemoteSocketAddress());
	}
	
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		log("Connection from " + conn.getRemoteSocketAddress() + " is closed");
	}
	
	@Override
	public void onMessage(WebSocket connect, String mess) {
		log(mess + " from " + connect.getRemoteSocketAddress());
		try{
			JSONParser par = new JSONParser();
			JSONObject json = (JSONObject) par.parse(mess);
			 
			// Page = { "ZING" , "VNEXPRESS" , "BONGDA" , "ALL"} 
			String page = json.get("_Page").toString();  
			// Filter = {"HASHTAG" , "IMAGE" , "TITLE"}
			String filter = json.get("_Filter").toString();
			String query = json.get("_Query").toString();
			
			//-------ZING NEWS-------
			if(page.equals("ZING") | page.equals("VNEXPRESS") | page.equals("BONGDA")){
					
				//------HASHTAG Search-----
				if(filter.equals("HASHTAG")){
					try {
						connect.send(getNews_with_Hashtag(page,query).toJson());
					} catch (NotYetConnectedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}
					
				//------IMAGE Search-----
				if(filter.equals("IMAGE")){
					try {
						connect.send(getNews_with_Image(page,query).toJson());
					} catch (NotYetConnectedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
				//-----TITLE Search-----
				if(filter.equals("TITLE")){
					try {
						connect.send(getNews_with_Title(page,query).toJson());
					} catch (NotYetConnectedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(page.equals("ALL")){

				String[] Page = {"ZING" , "VNEXPRESS" , "BONGDA"}; 
				
				//-------HASHTAG Search ALL-------
				if(filter.equals("HASHTAG")){
					for(int i = 0;i<Page.length;i++){
						try {
							connect.send(getNews_with_Hashtag(Page[i],query).toJson());
						} catch (NotYetConnectedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				//--------IMAGE Search ALL-------
				if(filter.equals("IMAGE")){
					for(int i = 0;i<Page.length;i++){
						try {
							connect.send(getNews_with_Image(Page[i],query).toJson());
						} catch (NotYetConnectedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				//---------TITLE Search ALL-------
				if(filter.equals("TITLE")){
					for(int i = 0;i<Page.length;i++){
						try {
							connect.send(getNews_with_Title(Page[i],query).toJson());
						} catch (NotYetConnectedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				//-------ALL NEWS----------
				if(filter.equals("ALL") && query.equals("ALL")){
					for(int i=0;i<Page.length;i++){
						try{
							connect.send(getNews_All(Page[i]).toJson());
						} catch (NotYetConnectedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
			

	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
		log("error " + ex.getMessage());
	}

	public static void main(String[] args) throws InterruptedException , IOException {
		String _HOST = "localhost";
		int _PORT = 9999;
		boolean connecting = true;
		//Get host 
		if (args.length > 0) {
			_HOST= args[0];
			_PORT = Integer.parseInt(args[1]);
		}
		//start server 
		WebSocketServer server = new ServerConnect(new InetSocketAddress(_HOST, _PORT));
		server.start();
		
		log("Server is running on port " + server.getPort());
		
		BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
		while (connecting) {
			String in = sysin.readLine();
			if(in.equals("crawl")){
				//Goi ham crawl
			}
			if(in.equals("exit"))
			{
				server.stop();
				log("Server is stop");break;
			}
		}
	}
	//-----------HASHTAG SEARCH---------
	public static Document getNews_with_Hashtag(String Collection,String hashtag) throws UnknownHostException{
		
		Document result = new Document();
		
		//Data.Collection.drop();
		Database data =new Database(Collection);
		data.Collection.createIndex(new Document("_Hashtag","text"));
		
		if(data.Search(hashtag, false, false)==null){
			return result.append("_Source",Collection)
						 .append("_Result", null);
		}
		else{
			result.append("_Source", Collection)
				  .append("_Result",data.Search(hashtag, false, false));
			
		}
		data.Collection.dropIndex(new Document("_Hashtag","text"));
		return result;
	}
	
	//-----------IMAGE SERACH--------------
	public static Document getNews_with_Image(String Collection,String image) throws UnknownHostException{
		
		Document result = new Document();
		
		//Data.Collection.drop();
		Database data =new Database(Collection);
		data.Collection.createIndex(new Document("_Image","text"));

		if(data.Search(image, false, false)==null){
			return result.append("_Source",Collection)
					 .append("_Result", null);
		}
		else{
			result.append("_Source", Collection)
				  .append("_Result",data.Search(image,false,false));
			
		}
		data.Collection.dropIndex(new Document("_Image","text"));
		return result;
	}
	
	//---------------TITLE SEARCH---------------
	public static Document getNews_with_Title(String Collection,String title) throws UnknownHostException{
		
		Document result = new Document();
		
		//Data.Collection.drop();
		Database data =new Database(Collection);
		data.Collection.createIndex(new Document("_Title","text"));

		if(data.Search(title, false, false)==null){
			return result.append("_Source",Collection)
					 .append("_Result", null);
		}
		else{
			result.append("_Source", Collection)
				  .append("_Result",data.Search(title, false, false));
		}
		data.Collection.dropIndex(new Document("_Title","text"));
		return result;
	}
	
	//--------------ALL NEWS-------------------
	public static Document getNews_All(String Collection) throws UnknownHostException {
		Document result =new Document();
		Database data = new Database(Collection);
		data.Collection.createIndex(new Document("_Hashtag","text"));
		result.append("Source", Collection)
			  .append("Result", data.getNews());
		//result = data.getNews_All();
		data.Collection.dropIndex(new Document("_Hashtag","text"));
		return result;
	}
	
	private static void log(String message) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date) + ": " + message);
   }
}

