
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.bson.Document;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

	
public class ClientConnectB extends WebSocketClient {
	
	//private static final String URI = "ws://localhost:9999";
	private String message;
	public ClientConnectB(URI serverUri, Draft draft) {
		super(serverUri, draft); 
	}

	public ClientConnectB(URI serverURI) {
		super(serverURI);
	}

	@Override
	public void onOpen( ServerHandshake handshakedata ) {
		log( "Opened connection");
		// if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
	}

	@Override
	public void onMessage( String message ) {
		System.out.println( "Result of search:  "  + message );
	}

	@Override
	public void onClose( int code, String reason, boolean remote ) {
		// The codecodes are documented in class org.java_websocket.framing.CloseFrame
		System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) );
	}

	@Override
	public void onError( Exception ex ) {
		ex.printStackTrace();
		// if the error is fatal then onClose will be called additionally
	}
	
	private static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("VST"));
		Date date = new Date();
		return dateFormat.format(date);
	}
	public String getMessage(){
		return this.message;
	}
	public static void log(String message) {
		System.out.println(getCurrentDateTime() + ": " + message);
	}
	public  static String   SendtoServer(String Page,String Filter,String Query )throws URISyntaxException, IOException {
		//	String uri = "ws://104.198.199.19:9999";
			String uri = "ws://localhost:9999";
			Document CMD = new Document();
			CMD.append("_Page", Page)
				.append("_Filter", Filter)
				.append("_Query", Query);
		
			ClientConnectB client = new ClientConnectB(new URI(uri), new Draft_10());
			client.connect();
			//BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
			while (true) {
			//	String in = sysin.readLine();
				if (CMD==null) {
					client.close();
					System.out.println("Closed connection");
					break;
				}
				else {
					try {
						 client.send(CMD.toJson());
						 break;
					} catch (Exception e) {
						
					}
				}
			}    
			//sleep To receive data from Client
		while (true)
		{	     try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				if(client.getMessage()!=null){
				//System.out.println(client.getMessage());
				return client.getMessage();
				}
				
		}
	}
}