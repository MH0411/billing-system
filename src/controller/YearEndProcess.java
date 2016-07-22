/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import main.RMIConnector;
import model.ServerDetail;

/**
 *
 * @author Ho Zhen Hong
 */
public class YearEndProcess {
    
    private RMIConnector rc = new RMIConnector();
    private String host = ServerDetail.getHost();
    private int port = ServerDetail.getPort();
    
    public void backup(){
        String sql1 = "DROP TABLE IF EXISTS far_customer_ledger_backup";
        rc.setQuerySQL(host, port, sql1);
        String sql2 = "CREATE TABLE far_customer_ledger_backup LIKE far_customer_ledger";
        rc.setQuerySQL(host, port, sql2);
        String sql3 = "INSERT far_customer_ledger_backup SELECT * FROM far_customer_ledger";
        rc.setQuerySQL(host, port, sql3);
    }
    
    public void startProcess(){
        
        String sql1 = "SET autocommit = 0";
        rc.setQuerySQL(host, port, sql1);
        
        String sql2 = 
                "SELECT "
                + "pb.pmi_no, "
                //total credit of a year
                + "IFNULL(cl.cr_amt_1, 0)+IFNULL(cl.cr_amt_2, 0)+IFNULL(cl.cr_amt_3, 0)+IFNULL(cl.cr_amt_4, 0)+"
                + "IFNULL(cl.cr_amt_5, 0)+IFNULL(cl.cr_amt_6, 0)+IFNULL(cl.cr_amt_7, 0)+IFNULL(cl.cr_amt_8, 0)+"
                + "IFNULL(cl.cr_amt_9, 0)+IFNULL(cl.cr_amt_10, 0)+IFNULL(cl.cr_amt_11, 0)+IFNULL(cl.cr_amt_12, 0)+"
                + "IFNULL(cl.cr_amt_13, 0), "
                //total debit of a year
                + "IFNULL(cl.dr_amt_1, 0)+IFNULL(cl.dr_amt_2, 0)+IFNULL(cl.dr_amt_3, 0)+IFNULL(cl.dr_amt_4, 0)+"
                + "IFNULL(cl.dr_amt_5, 0)+IFNULL(cl.dr_amt_6, 0)+IFNULL(cl.dr_amt_7, 0)+IFNULL(cl.dr_amt_8, 0)+"
                + "IFNULL(cl.dr_amt_9, 0)+IFNULL(cl.dr_amt_10, 0)+IFNULL(cl.dr_amt_11, 0)+IFNULL(cl.dr_amt_12, 0)+"
                + "IFNULL(cl.dr_amt_13, 0) "
                + "FROM far_customer_ledger cl, pms_patient_biodata pb "
                + "WHERE cl.customer_id = pb.pmi_no";
        ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql2);
        
        int flag = 0;
        for(int i = 0; i < data.size() && flag == 0; i++){
            String pmiNo = data.get(i).get(0);
            String totalYearCredit = data.get(i).get(1);
            String totalYearDebit = data.get(i).get(2);
            String sql3 = "UPDATE far_customer_ledger set "
                    + "cr_amt_1 = '0', cr_amt_2 = '0', cr_amt_3 = '0', cr_amt_4 = '0', "
                    + "cr_amt_5 = '0', cr_amt_6 = '0', cr_amt_7 = '0', cr_amt_8 = '0', "
                    + "cr_amt_9 = '0', cr_amt_10 = '0', cr_amt_11 = '0', cr_amt_12 = '0', cr_amt_13 = '"+ totalYearCredit +"', "
                    + "dr_amt_1 = '0', dr_amt_2 = '0', dr_amt_3 = '0', dr_amt_4 = '0', "
                    + "dr_amt_5 = '0', dr_amt_6 = '0', dr_amt_7 = '0', dr_amt_8 = '0', "
                    + "dr_amt_9 = '0', dr_amt_10 = '0', dr_amt_11 = '0', dr_amt_12 = '0', dr_amt_13 = '"+ totalYearDebit +"' "
                    + "WHERE customer_id = '"+ pmiNo +"'";
            boolean bool = rc.setQuerySQL(host, port, sql3);
            
            if(bool == false){
                flag = 1;
                String infoMessage = "There is an error during processing.\n"
                        + "Please restore the customer data and rerun the year end processing.";
                JOptionPane.showMessageDialog(null, infoMessage, "Error!", JOptionPane.INFORMATION_MESSAGE);
                String sql4 = "ROLLBACK";
                rc.setQuerySQL(host, port, sql4);
                
            } else {
                String sql5 = "COMMIT";
                rc.setQuerySQL(host, port, sql5);
            }
        }
        
        String sql6 = "SET autocommit = 1";
        rc.setQuerySQL(host, port, sql6);
    }
    
    public void restore(){
        String sql1 = "DROP TABLE IF EXISTS far_customer_ledger";
        rc.setQuerySQL(host, port, sql1);
        String sql2 = "CREATE TABLE far_customer_ledger LIKE far_customer_ledger_backup";
        rc.setQuerySQL(host, port, sql2);
        String sql3 = "INSERT far_customer_ledger SELECT * FROM far_customer_ledger_backup";
        rc.setQuerySQL(host, port, sql3);
    }
}
