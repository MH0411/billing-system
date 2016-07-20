/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import main.RMIConnector;
import model.ServerDetail;

/**
 *
 * @author Ho Zhen Hong
 */
public class Search {
    
    private RMIConnector rc = new RMIConnector();
    private SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
    private Date date = new Date();
    private String strDate = df1.format(date);
    private String strDate1 = df2.format(date);
    private String host = ServerDetail.getHost();
    private int port = ServerDetail.getPort();
    
    public ArrayList<ArrayList<String>> searchPatientByName(String name){
        String sql = 
                "SELECT distinct "
                    + "pe.episode_date, pom.order_no, pe.PMI_NO, pb.NEW_IC_NO, pb.ID_NO, "
                    + "pb.PATIENT_NAME, pb.HOME_ADDRESS, "
                    + "pb.MOBILE_PHONE "
                    + "FROM pms_episode pe "
                    + "INNER JOIN pis_order_master pom "
                    + "ON pe.PMI_NO = pom.PMI_NO "
                    + "INNER JOIN ehr_central ec "
                    + "ON pe.PMI_NO = ec.PMI_NO "
                    + "INNER JOIN pms_patient_biodata pb "
                    + "ON ec.PMI_NO = pb.PMI_NO "
                    + "WHERE (ec.status = 1 OR ec.status = 3) "
                    + "AND pe.STATUS ='Discharge' "
                    + "AND pom.episode_code like '"+ strDate1 +" %' " 
                    + "AND pe.episode_date = '"+ strDate +"' "
                    + "AND pb.patient_name = '"+ name +"' "
                    + "AND NOT EXISTS ("
                    + "SELECT ch.order_no FROM far_customer_hdr ch "
                    + "WHERE ch.order_no =  pom.order_no) "
                    + "GROUP BY pom.order_no";
        System.out.println(sql);
        ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);
        return data;
    }
    
    public ArrayList<ArrayList<String>> searchPatientByIC(String ic){
        String sql = 
                "SELECT distinct "
                    + "pe.episode_date, pom.order_no, pe.PMI_NO, pb.NEW_IC_NO, pb.ID_NO, "
                    + "pb.PATIENT_NAME, pb.HOME_ADDRESS, "
                    + "pb.MOBILE_PHONE "
                    + "FROM pms_episode pe "
                    + "INNER JOIN pis_order_master pom "
                    + "ON pe.PMI_NO = pom.PMI_NO "
                    + "INNER JOIN ehr_central ec "
                    + "ON pe.PMI_NO = ec.PMI_NO "
                    + "INNER JOIN pms_patient_biodata pb "
                    + "ON ec.PMI_NO = pb.PMI_NO "
                    + "WHERE (ec.status = 1 OR ec.status = 3) "
                    + "AND pe.STATUS ='Discharge' "
                    + "AND pom.episode_code like '"+ strDate1 +" %' " 
                    + "AND pe.episode_date = '"+ strDate +"' "
                    + "AND pb.new_ic_no = '"+ ic +"' "
                    + "AND NOT EXISTS ("
                    + "SELECT ch.order_no FROM far_customer_hdr ch "
                    + "WHERE ch.order_no =  pom.order_no) "
                    + "GROUP BY pom.order_no";
        ArrayList<ArrayList<String>> data = rc.getQuerySQL(ServerDetail.getHost(), ServerDetail.getPort(), sql);
        return data;
    }
    
    public ArrayList<ArrayList<String>> searchPatientByID(String id){
        String sql = 
                "SELECT distinct "
                    + "pe.episode_date, pom.order_no, pe.PMI_NO, pb.NEW_IC_NO, pb.ID_NO, "
                    + "pb.PATIENT_NAME, pb.HOME_ADDRESS, "
                    + "pb.MOBILE_PHONE "
                    + "FROM pms_episode pe "
                    + "INNER JOIN pis_order_master pom "
                    + "ON pe.PMI_NO = pom.PMI_NO "
                    + "INNER JOIN ehr_central ec "
                    + "ON pe.PMI_NO = ec.PMI_NO "
                    + "INNER JOIN pms_patient_biodata pb "
                    + "ON ec.PMI_NO = pb.PMI_NO "
                    + "WHERE (ec.status = 1 OR ec.status = 3) "
                    + "AND pe.STATUS ='Discharge' "
                    + "AND pom.episode_code like '"+ strDate1 +" %' " 
                    + "AND pe.episode_date = '"+ strDate +"' "
                    + "AND pb.id_no = '"+ id +"' "
                    + "AND NOT EXISTS ("
                    + "SELECT ch.order_no FROM far_customer_hdr ch "
                    + "WHERE ch.order_no =  pom.order_no) "
                    + "GROUP BY pom.order_no";
        ArrayList<ArrayList<String>> data = rc.getQuerySQL(ServerDetail.getHost(), ServerDetail.getPort(), sql);
        return data;
    }
    
    public ArrayList<ArrayList<String>> searchBillByName(String name, String status){
        String sql = 
                "SELECT ch.bill_no, ch.customer_id, pb.patient_name, pb.new_ic_no, pb.id_no, "
                + "pb.home_address, pb.mobile_phone, ch.quantity, (ch.item_amt-ch.amt_paid) "
                + "FROM far_customer_hdr ch, pms_patient_biodata pb "
                + "WHERE ch.payment = '"+ status +"' "
                + "AND pb.pmi_no = ch.customer_id "
                + "AND pb.patient_name = '"+ name +"'";
        ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);
        return data;
    }
    
    public ArrayList<ArrayList<String>> searchBillByIC(String ic, String status){
        String sql = 
                "SELECT ch.bill_no, ch.customer_id, pb.patient_name, pb.new_ic_no, pb.id_no, "
                + "pb.home_address, pb.mobile_phone, ch.quantity, (ch.item_amt-ch.amt_paid) "
                + "FROM far_customer_hdr ch, pms_patient_biodata pb "
                + "WHERE ch.payment = '"+ status +"' "
                + "AND pb.pmi_no = ch.customer_id "
                + "AND pb.new_ic_no = '"+ ic +"'";
        ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);
        return data;
    }
    
    public ArrayList<ArrayList<String>> searchBillByID(String id, String status){
        String sql = 
                "SELECT ch.bill_no, ch.customer_id, pb.patient_name, pb.new_ic_no, pb.id_no, "
                + "pb.home_address, pb.mobile_phone, ch.quantity, (ch.item_amt-ch.amt_paid) "
                + "FROM far_customer_hdr ch, pms_patient_biodata pb "
                + "WHERE ch.payment = '"+ status +"' "
                + "AND pb.pmi_no = ch.customer_id "
                + "AND pb.id_no = '"+ id +"'";
        ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);
        return data;
    }
}
