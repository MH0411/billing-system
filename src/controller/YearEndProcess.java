/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import main.RMIConnector;
import model.Month;
import model.ServerDetail;

/**
 *
 * @author Ho Zhen Hong
 */
public class YearEndProcess {
    
    private RMIConnector rc = new RMIConnector();
    private String host = ServerDetail.getHost();
    private int port = ServerDetail.getPort();
    
    /**
     * Back up far_customer_ledger table
     * @return 
     */
    public int backup(){
        boolean bool = true;
        try{
            String sql1 = "DROP TABLE IF EXISTS far_customer_ledger_backup";
            bool = rc.setQuerySQL(host, port, sql1);
            if (bool == false)
                return 0;
            String sql2 = "CREATE TABLE far_customer_ledger_backup LIKE far_customer_ledger";
            bool = rc.setQuerySQL(host, port, sql2);
            if (bool == false)
                return 0;
            String sql3 = "INSERT far_customer_ledger_backup SELECT * FROM far_customer_ledger";
            bool = rc.setQuerySQL(host, port, sql3);
            if (bool == false)
                return 0;
            
            String sql4 = "UPDATE far_year_end_parameter "
                    + "SET process_status = '1' "
                    + "WHERE code = 'yep'";
            rc.getQuerySQL(host, port, sql4);
            
            return 100;
            
        } catch (Exception e){
            return 0;
        }
    }
    
    /**
     * Processing year end
     * @return 
     */
    public int startProcess(){
        try{
            String sql0 = "SELECT process_status, processed_year "
                    + "FROM far_year_end_parameter "
                    + "WHERE code = 'yep'";
            ArrayList<ArrayList<String>> yep= rc.getQuerySQL(host, port, sql0);
            String processStatus = yep.get(0).get(0);
            String year = yep.get(0).get(1);

            if(processStatus.equals("1") && year.equals(Month.getYear())){
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
                for (int i = 0; i < data.size() && flag == 0; i++){
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

                        String sql4 = "ROLLBACK";
                        rc.setQuerySQL(host, port, sql4);

                        String sql6 = "SET autocommit = 1";
                        rc.setQuerySQL(host, port, sql6);

                        return 50;
                    } else {
                        String sql5 = "COMMIT";
                        rc.setQuerySQL(host, port, sql5);

                        if(i == (data.size()-1)){
                            String sql6 = "SET autocommit = 1";
                            rc.setQuerySQL(host, port, sql6);
                            String sql7 = "UPDATE far_year_end_parameter "
                                    + "SET process_status = '0', processed_year = '"+ Month.getYear() +"' "
                                    + "WHERE code = 'yep'";
                            rc.getQuerySQL(host, port, sql7);

                            return 100;
                        }
                    }
                }
            } else {
                return 0;
            }
            return 100;
        }catch(Exception e){
            return 50;
        }
    }
    
    /**
     * Restore far_customer_ledger table
     * @return 
     */
    public int restore(){
        boolean bool = true;
        try{
            String sql1 = "DROP TABLE IF EXISTS far_customer_ledger";
            rc.setQuerySQL(host, port, sql1);
            if (bool == false)
                return 0;
            String sql2 = "CREATE TABLE far_customer_ledger LIKE far_customer_ledger_backup";
            rc.setQuerySQL(host, port, sql2);
            if (bool == false)
                return 0;
            String sql3 = "INSERT far_customer_ledger SELECT * FROM far_customer_ledger_backup";
            rc.setQuerySQL(host, port, sql3);
            if (bool == false)
                return 0;
           
            return 100;
            
        } catch (Exception e){
            return 0;
        }
    }
}
