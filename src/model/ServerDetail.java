/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * This class monitor host and port
 * @author Ho Zhen Hong
 */
public class ServerDetail {
    
    // declaration host and port
    //String host = "biocore-stag.utem.edu.my"; //server stagging
    //String host = "biocore-devp.utem.edu.my"; // server development
    private static String host;
    private static int port;
    
    public static String getHost(){
        return host;
    }
    
    public static int getPort(){
        return port;
    }

    public static void setHost(String host1) {
        host = host1;
    }

    public static void setPort(int port1) {
        port = port1;
    }
}
