package controller;

import controller.SMSService;
import model.ServerDetail;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.RMIConnector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ho Zhen Hong
 */
public class SendEmail {
    
    private RMIConnector rc = new RMIConnector();
    private String host = ServerDetail.getHost();
    private int port = ServerDetail.getPort();
    
    private DecimalFormat df = new DecimalFormat("0.00");
    
    private String receiptNo;
    private static String custId = "";
    private static String billNo = "";
    private static String subtotal = "";
    private static String grandTotal = "";
    private static String amount = "0.00";
    private static String change = "0.00";
    private static String gst = "0.00";
    private static String serviceCharge = "0.00";
    private static String discount = "0.00";
    private static String rounding = "0.00";
    private static String outstandingBalance = "0.00";
    
    private static String password = "B10core";
    private static String receiverEmail = "";
    private static String subject = "Receipt from BIOCORE";
    private static String message = "";
    
    public SendEmail(String custId, String billNo, String subtotal, String grandTotal, String amount,
            String change, String gst, String serviceCharge, String discount, String rounding){
        this.custId = custId;
        this.billNo = billNo;
        this.subtotal = subtotal;
        this.grandTotal = grandTotal;
        this.amount = amount;
        this.change = change;
        this.gst = gst;
        this.serviceCharge = serviceCharge;
        this.discount = discount;
        this.rounding = rounding;
    }
    
    public SendEmail(String custId, String billNo, String subtotal, String grandTotal,
            String gst, String serviceCharge, String discount, String rounding){
        this.custId = custId;
        this.billNo = billNo;
        this.subtotal = subtotal;
        this.grandTotal = grandTotal;
        this.gst = gst;
        this.serviceCharge = serviceCharge;
        this.discount = discount;
        this.rounding = rounding;
    }
    
    public void send(){
        // TODO code application logic here
        try {
            //Get patient data from database
            String sql = "SELECT "
                    + "pb.patient_name, "
                    + "pb.home_address, "
                    + "pb.id_no, "
                    + "pb.mobile_phone, "
                    + "ch.txn_date, "
                    + "cd.item_cd, "
                    + "cd.item_desc, "
                    + "cd.quantity, "
                    + "(cd.item_amt/cd.quantity), "
                    + "cd.item_amt, "
                    + "pb.email_address, "
                    + "ch.amt_paid "
                    + "FROM far_customer_hdr ch "
                    + "INNER JOIN far_customer_dtl cd "
                    + "ON ch.bill_no = cd.bill_no "
                    + "INNER JOIN pms_patient_biodata pb "
                    + "ON ch.customer_id = pb.pmi_no "
                    + "WHERE ch.customer_id = '"+ custId +"' "
                    + "AND ch.bill_no = '"+ billNo +"' ";
            ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);

            String company = "BIOCORE,\n"
                    + "Universiti Teknikal Malaysia Melaka,\n"
                    + "Hang Tuah Jaya,\n"
                    + "76100 Durian Tunggal,\n"
                    + "Melaka, Malaysia.\n";

            String name = data.get(0).get(0);
            String address = data.get(0).get(1);
            String otherId = data.get(0).get(2);
            String tel = data.get(0).get(3);
            String date = data.get(0).get(4);
            
            String custInfo =
//                    "CUSTOMER INFO\n"
                     "NAME : " + name + "\n"
//                    + "ADDRESS : " + address + "\n"
                    + "CUST ID : " + custId + "\n"
                    + "OTHER ID : " + otherId + "\n"
//                    + "NO. TEL : " + tel + "\n"
                    + "BILL NO : " + billNo + "\n"
                    + "DATE : " + date + "\n"
                    + "-----------\n";
            
            String itemsHeader =  "NO NAME                          QTY UNIT PRICE  TOTAL PRICE  \n"
                                            + "";
            String items = "";
            for(int i  = 0 ; i < data.size() ; i++){
                
                if (data.get(i).get(5).contains("BP")){
                }else{
                String itemCode = data.get(i).get(5);
                String itemName = data.get(i).get(6);
                String quantity = data.get(i).get(7);
                String unitPrice = df.format(Double.parseDouble(data.get(i).get(8)));
                String totalPrice = df.format(Double.parseDouble(data.get(i).get(9)));
                
                items = items + (i+1) + "  " + itemName;
                
                for(int space = itemName.length() ; space < 30 ; space++){
                    items = items + " ";
                }
                
                items = items + quantity + "    " + unitPrice + "        " + totalPrice + " \n";
                }
            }
             
            String amtPaid = data.get(0).get(11);
            
            if (Double.parseDouble(change) < 0){
                change = "0.00";
            }
            
            String summary = 
                      "-----------\n"
                    + "Subtotal : " + subtotal + "\n"
                    + "Service Charge : " + serviceCharge + "\n"
                    + "GST : " + gst + "\n"
                    + "Discount : " + discount + "\n"
                    + "Rounding : " + rounding + "\n"
                    + "Grand Total : " + grandTotal + "\n"
                    + "Cash : " + amount + "\n"
                    + "Change : " + change + "\n"
//                    + "Outstanding Balance : " + outstandingBalance + "\n"
                    ;
            
            message = custInfo + itemsHeader + items + summary;
            System.out.println(message);
            receiverEmail = "z.mike0411@gmail.com";
        
            String encodedSubject = URLEncoder.encode(subject, "UTF-8");
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String url = "http://tuffah.info/biocore/?password=" + password + "&to=" + receiverEmail 
                    + "&subject=" + encodedSubject + "&message=" + encodedMessage;
            System.out.println(url);
            
            // Send data    
            URL myUrl; 
            myUrl = new URL(url);
            URLConnection conn = myUrl.openConnection();
            conn.setDoOutput(true);
            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
            }
            rd.close();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
