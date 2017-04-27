
package com.phuvg;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.bson.Document;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;

import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public abstract class ClientConnectB extends WebSocketClient {

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

    public abstract void get_News_Android(String source, List<String> result);

    @SuppressWarnings("unchecked")
    @Override
    public void onMessage( String message ) {

        JSONParser par =new JSONParser();
        JSONObject json = null;
        String source;
        List<String> result;
        try {
            json = (JSONObject) par.parse(message);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        source = (String) json.get("_Source");
        result =  ((List<String>) json.get("_Result"));
        this.get_News_Android(source, result);
//		System.out.println("**: "+message);
//		System.out.println("@@: "+json.get("_Result"));
//		System.out.println("##: "+source);


    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
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

    public String   SendtoServer(String Page,String Filter,String Query )throws URISyntaxException, IOException {
        //String uri = "ws://104.198.199.19:6969";
        //String uri = "ws://localhost:9999";
        Document CMD = new Document();
        CMD.append("_Page", Page)
                .append("_Filter", Filter)
                .append("_Query", Query);

        //ClientConnectB client = new ClientConnectB(new URI(uri), new Draft_10());
        //client.connect();
        //BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
        while (true) {
            //String in = sysin.readLine();
            if (Query.equals("exit")) {
                close();
                System.out.println("Closed connection");
                break;
            }
            else {
                try {
                    send(CMD.toJson());
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //sleep To receive data from Client
        while (true)
        {	     try {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }finally{

        }
            if(getMessage()!=null){
                //System.out.println(client.getMessage());

                return getMessage();
            }

        }
    }
}