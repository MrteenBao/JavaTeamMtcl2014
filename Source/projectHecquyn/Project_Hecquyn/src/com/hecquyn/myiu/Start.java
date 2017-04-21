/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecquyn.myiu;

import com.hecquyn.crawl.*;
import com.hecquyn.database.Data;
import com.hecquyn.database.MyDatabase;
import static com.hecquyn.database.MyDatabase.db;
import static com.hecquyn.database.MyDatabase.getMongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.bson.Document;

/**
 *
 * @author Vuong Gia Phu
 */
public class Start extends javax.swing.JFrame {

    /**
     * Creates new form Start
     */
    public Start() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taInformation = new javax.swing.JTextArea();
        btnStart = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hecules project");
        jLabel1.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        taInformation.setEditable(false);
        taInformation.setColumns(20);
        taInformation.setRows(5);
        taInformation.setText("Hecules project: crawl online newspageper and hashtag the news.\n- Đặng Thiên Bảo 14520047\n- Lâm Việt Trí 14520992\n- Từ Vĩnh Nguyên 14520615\n- Vương Gia Phú 14520688 ");
        taInformation.setName("taInformation_Start"); // NOI18N
        jScrollPane1.setViewportView(taInformation);

        btnStart.setBackground(new java.awt.Color(153, 255, 153));
        btnStart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnStart.setForeground(new java.awt.Color(0, 0, 204));
        btnStart.setText("Crawl");
        btnStart.setName("btnCrawl_Start"); // NOI18N
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    MyDatabase db;

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        try {
            dbConnection();
            vnexpress();
            //zingnews();
            //bongdavn();
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(rootPane, "Done!");
    }//GEN-LAST:event_btnStartActionPerformed

    private static void dbConnection() throws UnknownHostException {
        try {
            MongoClient mongoClient = getMongoClient();
            /*System.out.println("List all DB:");
            List<String> dbNames = mongoClient.getDatabaseNames();
            for (String dbName : dbNames) {
                System.out.println("+ DB Name: " + dbName);
            }

            System.out.println("Done!");*/

        } catch (IOException e) {
            System.out.println("ERROR DATABASE CONNECTION!");
        }
    }

    public void vnexpress() throws IOException {
        String url = "http://vnexpress.net/";
        db = new MyDatabase("vnexpress");
        
        ArrayList<Data> list = new ArrayList<Data>();
        Vnexpress vnexpress = new Vnexpress(url);
        list = vnexpress.processPage();
        for (int i = 0; i < list.size(); i++) {
            db.Collection.insertOne(new Document("_Link", list.get(i).getLink())
                                    .append("_Title", list.get(i).getTitle())
                                    .append("_Date", list.get(i).getDate())
                                    .append("_Hashtag", list.get(i).getTag()));
        }
    }

    public void zingnews() throws IOException {
        String url = "http://news.zing.vn/";
        ArrayList<Data> list = new ArrayList<Data>();
        Crawler zingnews = new Zingnews(url);
        list = zingnews.processPage();
        for (int i = 0; i < list.size(); i++) {
            //db.insert(url, list.get(i));
        }
    }

    public void bongdavn() throws IOException {
        String url = "http://bongda.vn/";
        ArrayList<Data> list = new ArrayList<Data>();
        Bongda bongda = new Bongda(url);
        list = bongda.processPage();
        for (int i = 0; i < list.size(); i++) {
            //db.insertOne(url, list.get(i));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Start().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taInformation;
    // End of variables declaration//GEN-END:variables
}
