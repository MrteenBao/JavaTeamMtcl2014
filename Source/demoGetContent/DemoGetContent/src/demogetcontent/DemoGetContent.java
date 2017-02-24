/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demogetcontent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 *
 * @author P
 */
public class DemoGetContent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String link = "http://vnexpress.net/";
        URL url;
        String content = ""; 
        try {
            url = new URL(link);
            
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String text = br.readLine();
            
            while(text != null) {
                content += text += "\n";
                text = br.readLine();
            }
            br.close();
            
        } catch(IOException e) {
            System.out.println("error!");
        }
        
        System.out.print(content);
    }
    
}
