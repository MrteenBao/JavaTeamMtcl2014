package WebSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import DATABASE_HECQUIN.Database;

public class ServerConnect {
	
	private MongoClient mongoClient = null; 
	private MongoDatabase db = null;
	static Database Data;
	public ServerConnect() throws IOException {
		int _portNumber = 2345;
		ServerSocket listener = null;
		int clientNumber = 0;
		boolean listening = true;
		try {
			listener = new ServerSocket(_portNumber); // mở cổng giao tiếp 8888
														// ,note: Người dùng ko
														// thể chọn cổng <1023
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		try {
			while (listening) {
				System.out.println("Server is waiting to accept client in Port: " + listener.getLocalPort());
				// cấp phát 1 socket cho  mỗi client 
				Socket socketOfServer = listener.accept();
				listener.setSoTimeout(10000);
				
				new ServiceThread(socketOfServer, clientNumber++).start(); 
			}
		} finally {
			listener.close();
		}

	}

	private static void log(String message) {
		System.out.println(message);
	}
	public String getNews(){
		return "o";
	}
	private static class ServiceThread extends Thread {

		private int clientNumber;
		private Socket socketOfServer;

		public ServiceThread(Socket socketOfServer, int clientNumber) {
			this.clientNumber = clientNumber;
			this.socketOfServer = socketOfServer;

			// Log
			log("Connect with Client  : " + this.clientNumber + " at " + socketOfServer);
		}
		
		public void run() {
			BufferedReader is = null;
			PrintWriter os = null;
			boolean transing = true;
			Document x = null;
			try {
				is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
				os = new PrintWriter(socketOfServer.getOutputStream(), true);

				while (transing) {
					String line = is.readLine(); // Đọc dữ liệu đc gửi tới
					Data = new Database("Collection");
					
					// Server
					// GỌI HÀM XỬ LÝ CHUỖI LINE
					// GỌI HÀM TRUY VẤN DATABASE
					// TRẢ VỀ DOCUMENT CHO CLINET
					os.println(x);
					os.flush();
					os.println("OK");
					os.flush();
					break;
					/*
					 * if(line.equals("QUIT")){ os.println("OK"); os.flush();
					 * break; }
					 */
				}
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		
	}
	
}
