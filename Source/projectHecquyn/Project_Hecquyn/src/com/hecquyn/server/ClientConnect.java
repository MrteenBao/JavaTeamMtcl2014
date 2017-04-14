package WebSocket;

import java.io.*;
import java.net.*;
import java.util.Date;

import org.bson.Document;


public class ClientConnect {

	public ClientConnect() {
		final String serverHost = "localhost";
		//final String _IP = "192.168.0.125";
		//int _PORT = 2345; 
		int portNumber = 2345;
		Socket socketOfClient = null;
		PrintWriter os = null;
	    BufferedReader is = null;
	    String query="gg";
	    
	    try{
	    	socketOfClient = new Socket(serverHost,portNumber);// Gửi yêu cầu kết nối tới port 8888 của localhost
	    	
	    	os = new PrintWriter(socketOfClient.getOutputStream(),true); // Mở Output trên Client 
	    	is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream())); //Mở Input trên Clinet 
	    }catch(UnknownHostException e){
	    	System.err.println("Can't connect to host : "+ serverHost);
	    	return;
	    }catch(IOException e){
	    	System.err.println("Can't get I/O with : "+ serverHost);
	    	
	    	return;
	    }
	    try{
	    	os.println("I'm client 0; Connected in Port: " + socketOfClient.getLocalPort() );
	    	os.flush();
	    	os.println(query);
	    	os.flush();
	    	String replyLine;
	    	while((replyLine = is.readLine() )!= null){
	    		System.out.println("Server reply : "+ replyLine);
	    		if(replyLine.indexOf("OK") != -1){
	    			break;
	    		}
	    	}
	    		os.close();
	    		is.close();
	    		socketOfClient.close();
	    }catch(UnknownHostException e){
	    	System.err.println("Trying to connect to unknown host " + e);
	    }catch(IOException e ){
	    	System.err.println(e);
	    }
	}

}
