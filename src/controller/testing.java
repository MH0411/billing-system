/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import main.RMIConnector;

/**
 *
 * @author Ho Zhen Hong
 */
public class testing {    
    public static void main(String[] args) {
        try{
            RMIConnector rc = new RMIConnector();
            RMIConnector rc1 = new RMIConnector();
            String host = "10.73.32.200";
            int port = 1099;
            String sql = "SELECT * FROM far_year_end_parameter";
            System.out.println("1");
            rc.getQuerySQL(host, port, sql);
            System.out.println("2");
            rc1.getQuerySQL(host, port, sql);
            System.out.println("3");
        
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
