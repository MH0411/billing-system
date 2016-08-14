/**
 * Edited by SyasyaAzizan
 * May 2016
 */

package controller;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMSService {
    
    private static String textToSend, receiver, host;
    private int port = 9500;
    private String username = "admin";
    private String password = "biocore";
    
    public SMSService(String phone, String message, String hostAdd) {
        receiver = phone;
        textToSend = message;
        host = hostAdd;
        
        sending();
        System.out.println("--- End SMSService ---");
    }

    public void sending()  {
        try {
            System.out.println("--- Start SMSService ---");
            
            SMSClient smsClient = new SMSClient(host, port);
            smsClient.login(username, password);
            System.out.println("SMS message: ");
            if(smsClient.isLoggedIn())
            {
                System.out.println("SMSService login to Ozeki" + "\n");
                
                smsClient.sendMessage(receiver, textToSend);
                System.out.println("Send to : " + receiver + "\n" + "Message: " + textToSend);
                smsClient.logout();
            }
        } catch (IOException ex) {
            Logger.getLogger(SMSService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SMSService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
