/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.RMIConnector;
import model.Month;
import model.ServerDetail;

/**
 *
 * @author Ho Zhen Hong
 */
public class Report {
    
    private RMIConnector rc = new RMIConnector();
    private String host = ServerDetail.getHost();
    private int port = ServerDetail.getPort();
    private DecimalFormat df = new DecimalFormat("0.00");
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
    private DateFormat year = new SimpleDateFormat("yyyy");
    private Date date = new Date();
    private String strDate = dateFormat.format(date);
    private String strYear = year.format(date);
    
    public void generateYearlyStatement(String ic) {
        
        String sql = 
                "SELECT "
                + "pb.pmi_no, pb.patient_name, pb.new_ic_no, pb.id_no, pb.home_address, pb.mobile_phone, pb.email_address, "
                //credit of month
                + "IFNULL(cl.cr_amt_1, 0), IFNULL(cl.cr_amt_2, 0), IFNULL(cl.cr_amt_3, 0), IFNULL(cl.cr_amt_4, 0), "
                + "IFNULL(cl.cr_amt_5, 0), IFNULL(cl.cr_amt_6, 0), IFNULL(cl.cr_amt_7, 0), IFNULL(cl.cr_amt_8, 0), "
                + "IFNULL(cl.cr_amt_9, 0), IFNULL(cl.cr_amt_10, 0), IFNULL(cl.cr_amt_11, 0), IFNULL(cl.cr_amt_12, 0), "
                //debit of month
                + "IFNULL(cl.dr_amt_1, 0), IFNULL(cl.dr_amt_2, 0), IFNULL(cl.dr_amt_3, 0), IFNULL(cl.dr_amt_4, 0), "
                + "IFNULL(cl.dr_amt_5, 0), IFNULL(cl.dr_amt_6, 0), IFNULL(cl.dr_amt_7, 0), IFNULL(cl.dr_amt_8, 0), "
                + "IFNULL(cl.dr_amt_9, 0), IFNULL(cl.dr_amt_10, 0), IFNULL(cl.dr_amt_11, 0), IFNULL(cl.dr_amt_12, 0), "
                //total credit of a year
                + "IFNULL(cl.cr_amt_1, 0)+IFNULL(cl.cr_amt_2, 0)+IFNULL(cl.cr_amt_3, 0)+IFNULL(cl.cr_amt_4, 0)+"
                + "IFNULL(cl.cr_amt_5, 0)+IFNULL(cl.cr_amt_6, 0)+IFNULL(cl.cr_amt_7, 0)+IFNULL(cl.cr_amt_8, 0)+"
                + "IFNULL(cl.cr_amt_9, 0)+IFNULL(cl.cr_amt_10, 0)+IFNULL(cl.cr_amt_11, 0)+IFNULL(cl.cr_amt_12, 0), "
                //total debit of a year
                + "IFNULL(cl.dr_amt_1, 0)+IFNULL(cl.dr_amt_2, 0)+IFNULL(cl.dr_amt_3, 0)+IFNULL(cl.dr_amt_4, 0)+"
                + "IFNULL(cl.dr_amt_5, 0)+IFNULL(cl.dr_amt_6, 0)+IFNULL(cl.dr_amt_7, 0)+IFNULL(cl.dr_amt_8, 0)+"
                + "IFNULL(cl.dr_amt_9, 0)+IFNULL(cl.dr_amt_10, 0)+IFNULL(cl.dr_amt_11, 0)+IFNULL(cl.dr_amt_12, 0), "
                + "IFNULL(cl.cr_amt_13, 0), IFNULL(cl.dr_amt_13, 0) "
                + "FROM far_customer_ledger cl, pms_patient_biodata pb "
                + "WHERE cl.customer_id = pb.pmi_no "
                + "AND pb.new_ic_no = '"+ ic +"'";
        ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);
                
        for(int i = 0; i < data.size(); i++){
            String pmiNo = data.get(i).get(0);
            String name = data.get(i).get(1);
            String id = data.get(i).get(3);
            String address = data.get(i).get(4);
            String phone = data.get(i).get(5);
            String email = data.get(i).get(6);
            String cr1 = data.get(i).get(7);
            String cr2 = data.get(i).get(8);
            String cr3 = data.get(i).get(9);
            String cr4 = data.get(i).get(10);
            String cr5 = data.get(i).get(11);
            String cr6 = data.get(i).get(12);
            String cr7 = data.get(i).get(13);
            String cr8 = data.get(i).get(14);
            String cr9 = data.get(i).get(15);
            String cr10 = data.get(i).get(16);
            String cr11 = data.get(i).get(17);
            String cr12 = data.get(i).get(18);
            String dr1 = data.get(i).get(19);
            String dr2 = data.get(i).get(20);
            String dr3 = data.get(i).get(21);
            String dr4 = data.get(i).get(22);
            String dr5 = data.get(i).get(23);
            String dr6 = data.get(i).get(24);
            String dr7 = data.get(i).get(25);
            String dr8 = data.get(i).get(26);
            String dr9 = data.get(i).get(27);
            String dr10 = data.get(i).get(28);
            String dr11 = data.get(i).get(29);
            String dr12 = data.get(i).get(30);
            String yearCredit = data.get(i).get(31);
            String yearDebit = data.get(i).get(32);
            String lastYearCredit = data.get(i).get(33);
            String lastYearDebit = data.get(i).get(34);
            
            //create new document
            Document document = new Document(PageSize.A4, 36, 36, 64, 36) {}; 
            document.setMargins(40, 30, 50, 50); 

            double deficient = 0;
            double totalYearCredit = 0;
            double totalYearDebit = 0;
            
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("YearStatement.pdf"));
                document.open();
                
                //initialize pdf
                Font recno = new Font(Font.TIMES_ROMAN, 10);
                Font recti = new Font(Font.HELVETICA, 16, Font.BOLD);
                Font rectem = new Font(Font.HELVETICA, 12, Font.BOLD);
                Font rectemja = new Font(Font.COURIER, 12);
                Font rectemjaBold = new Font(Font.COURIER, 12, Font.BOLD);
                Font rectemjaBig = new Font(Font.COURIER, 16, Font.BOLD);
                
                //header
                PdfPTable table = new PdfPTable(6);
                table.setWidths(new float[]{0.5f, 1.5f, 4f, 1.5f, 1.5f, 1.5f});
                table.setLockedWidth(true);
                table.setTotalWidth(document.right() - document.left());

                PdfPTable tableHeader = new PdfPTable(4);
                tableHeader.setWidths(new float[]{3f, 4f, 3.5f, 4f});
                tableHeader.setLockedWidth(true);
                tableHeader.setTotalWidth(document.right() - document.left());

                Image logo = Image.getInstance("pic/logo.png");
                logo.scaleAbsolute(115, 50);

                PdfPCell cellLogo = new PdfPCell(logo);
                cellLogo.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellLogo.setBorder(Rectangle.NO_BORDER);
                cellLogo.setColspan(2);
                cellLogo.setLeading(15f, 0.3f);
                tableHeader.addCell(cellLogo);
                
                String addr = 
                        " Universiti Teknikal Malaysia Melaka, \n"
                        + " Hang Tuah Jaya, \n"
                        + " 76100 Durian Tunggal, \n"
                        + " Melaka, Malaysia.";

                PdfPCell cellAddress = new PdfPCell(new Phrase(addr, rectemja));
                cellAddress.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellAddress.setBorder(Rectangle.NO_BORDER);
                cellAddress.setColspan(2);
                tableHeader.addCell(cellAddress);
                
                PdfPCell cellAnnual = new PdfPCell(new Phrase("\nYearly Account Statement\n\n", recti));
                cellAnnual.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellAnnual.setBorder(Rectangle.NO_BORDER);
                cellAnnual.setColspan(4);
                tableHeader.addCell(cellAnnual);                
                
                //Customer Details
                PdfPTable tableCust = new PdfPTable(1);
                tableCust.setWidths(new float[]{10f});
                tableCust.setLockedWidth(true);
                tableCust.setTotalWidth(document.right() - document.left());
                
                PdfPCell cell11 = new PdfPCell(new Phrase(name, rectemja));
                cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell11.setBorder(Rectangle.NO_BORDER);
                cell11.setColspan(3);
                
                PdfPCell cell21 = new PdfPCell(new Phrase(address, rectemja));
                cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell21.setBorder(Rectangle.NO_BORDER);
                cell21.setColspan(3);
                
                PdfPCell cell31 = new PdfPCell(new Phrase("\n" + strDate + "\n\n", rectemja));
                cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell31.setBorder(Rectangle.NO_BORDER);
                cell31.setColspan(3);
                
                tableCust.addCell(cell11);
                tableCust.addCell(cell21);
                tableCust.addCell(cell31);
                
                PdfPTable tableStatement = new PdfPTable(3);
                tableStatement.setWidths(new float[]{10.5f, 2f, 2f});
                tableStatement.setLockedWidth(true);
                tableStatement.setTotalWidth(document.right() - document.left());
                
                PdfPCell cell41 = new PdfPCell(new Phrase("Month ("+ strYear +")", rectem));
                cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell cell42 = new PdfPCell(new Phrase("Debit (RM)", rectem));
                cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell cell43 = new PdfPCell(new Phrase("Credit (RM)", rectem));
                cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                tableStatement.addCell(cell41);
                tableStatement.addCell(cell42);
                tableStatement.addCell(cell43);
                
                PdfPCell cell51 = new PdfPCell(new Phrase("January", rectemja));
                cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell51.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell52 = new PdfPCell(new Phrase(dr1, rectemja));
                cell52.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell52.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell53 = new PdfPCell(new Phrase(cr1, rectemja));
                cell53.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell53.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                
                tableStatement.addCell(cell51);
                tableStatement.addCell(cell52);
                tableStatement.addCell(cell53);
                
                PdfPCell cell61 = new PdfPCell(new Phrase("February", rectemja));
                cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell61.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell62 = new PdfPCell(new Phrase(dr2, rectemja));
                cell62.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell62.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell63 = new PdfPCell(new Phrase(cr2, rectemja));
                cell63.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell63.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                
                tableStatement.addCell(cell61);
                tableStatement.addCell(cell62);
                tableStatement.addCell(cell63);                
                
                PdfPCell cell71 = new PdfPCell(new Phrase("March", rectemja));
                cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell71.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell72 = new PdfPCell(new Phrase(dr3, rectemja));
                cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell72.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell73 = new PdfPCell(new Phrase(cr3, rectemja));
                cell73.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell73.setBorder(Rectangle.LEFT | Rectangle.RIGHT);

                tableStatement.addCell(cell71);
                tableStatement.addCell(cell72);
                tableStatement.addCell(cell73);
                
                PdfPCell cell81 = new PdfPCell(new Phrase("April", rectemja));
                cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell81.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell82 = new PdfPCell(new Phrase(dr4, rectemja));
                cell82.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell82.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell83 = new PdfPCell(new Phrase(cr4, rectemja));
                cell83.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell83.setBorder(Rectangle.LEFT | Rectangle.RIGHT);

                tableStatement.addCell(cell81);
                tableStatement.addCell(cell82);
                tableStatement.addCell(cell83);
                
                PdfPCell cell91 = new PdfPCell(new Phrase("May", rectemja));
                cell91.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell91.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell92 = new PdfPCell(new Phrase(dr5, rectemja));
                cell92.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell92.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell93 = new PdfPCell(new Phrase(cr5, rectemja));
                cell93.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell93.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                
                tableStatement.addCell(cell91);
                tableStatement.addCell(cell92);
                tableStatement.addCell(cell93);
                
                PdfPCell cell101 = new PdfPCell(new Phrase("June", rectemja));
                cell101.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell101.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell102 = new PdfPCell(new Phrase(dr6, rectemja));
                cell102.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell102.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell103 = new PdfPCell(new Phrase(cr6, rectemja));
                cell103.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell103.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                
                tableStatement.addCell(cell101);
                tableStatement.addCell(cell102);
                tableStatement.addCell(cell103);
                
                PdfPCell cell111 = new PdfPCell(new Phrase("July", rectemja));
                cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell111.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell112 = new PdfPCell(new Phrase(dr7, rectemja));
                cell112.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell112.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell113 = new PdfPCell(new Phrase(cr7, rectemja));
                cell113.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell113.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                
                tableStatement.addCell(cell111);
                tableStatement.addCell(cell112);
                tableStatement.addCell(cell113);
                
                PdfPCell cell121 = new PdfPCell(new Phrase("August", rectemja));
                cell121.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell121.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell122 = new PdfPCell(new Phrase(dr8, rectemja));
                cell122.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell122.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell123 = new PdfPCell(new Phrase(cr8, rectemja));
                cell123.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell123.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                
                tableStatement.addCell(cell121);
                tableStatement.addCell(cell122);
                tableStatement.addCell(cell123);
                
                PdfPCell cell131 = new PdfPCell(new Phrase("September", rectemja));
                cell131.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell131.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell132 = new PdfPCell(new Phrase(dr9, rectemja));
                cell132.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell132.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell133 = new PdfPCell(new Phrase(cr9, rectemja));
                cell133.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell133.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                
                tableStatement.addCell(cell131);
                tableStatement.addCell(cell132);
                tableStatement.addCell(cell133);
                
                PdfPCell cell141 = new PdfPCell(new Phrase("October", rectemja));
                cell141.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell141.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell142 = new PdfPCell(new Phrase(dr10, rectemja));
                cell142.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell142.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell143 = new PdfPCell(new Phrase(cr10, rectemja));
                cell143.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell143.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                
                tableStatement.addCell(cell141);
                tableStatement.addCell(cell142);
                tableStatement.addCell(cell143);
                
                PdfPCell cell151 = new PdfPCell(new Phrase("November", rectemja));
                cell151.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell151.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell152 = new PdfPCell(new Phrase(dr11, rectemja));
                cell152.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell152.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell153 = new PdfPCell(new Phrase(cr11, rectemja));
                cell153.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell153.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                
                tableStatement.addCell(cell151);
                tableStatement.addCell(cell152);
                tableStatement.addCell(cell153);
                
                PdfPCell cell161 = new PdfPCell(new Phrase("December", rectemja));
                cell161.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell161.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell162 = new PdfPCell(new Phrase(dr12, rectemja));
                cell162.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell162.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                PdfPCell cell163 = new PdfPCell(new Phrase(cr12, rectemja));
                cell163.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell163.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                
                tableStatement.addCell(cell161);
                tableStatement.addCell(cell162);
                tableStatement.addCell(cell163);
                
                PdfPCell cell171 = new PdfPCell(new Phrase("Total", rectem));
                cell171.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell cell172 = new PdfPCell(new Phrase(yearDebit, rectemja));
                cell172.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell cell173 = new PdfPCell(new Phrase(yearCredit, rectemja));
                cell173.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                tableStatement.addCell(cell171);
                tableStatement.addCell(cell172);
                tableStatement.addCell(cell173);
                
                //Summary
                PdfPTable tableSummary = new PdfPTable(2);
                tableSummary.setWidths(new float[]{10.5f, 4f});
                tableSummary.setLockedWidth(true);
                tableSummary.setTotalWidth(document.right() - document.left());
                
                PdfPCell cell181 = new PdfPCell(new Phrase("\nDebit of last year (RM)", rectem));
                cell181.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell181.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell182 = new PdfPCell(new Phrase("\n" + lastYearDebit, rectemja));
                cell182.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell182.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell191 = new PdfPCell(new Phrase("Credit of last year (RM)", rectem));
                cell191.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell191.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell192 = new PdfPCell(new Phrase("" + lastYearCredit, rectemja));
                cell192.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell192.setBorder(Rectangle.NO_BORDER);
                
                tableSummary.addCell(cell181);
                tableSummary.addCell(cell182);
                tableSummary.addCell(cell191);
                tableSummary.addCell(cell192);
                
                totalYearCredit = Double.parseDouble(yearCredit) + Double.parseDouble(lastYearCredit);
                totalYearDebit = Double.parseDouble(yearDebit) + Double.parseDouble(lastYearDebit);
                deficient = totalYearDebit - totalYearCredit;
                
                PdfPCell cell201 = new PdfPCell(new Phrase("\nTotal Debit (RM)", rectem));
                cell201.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell201.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell202 = new PdfPCell(new Phrase("\n" + totalYearDebit, rectemja));
                cell202.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell202.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell211 = new PdfPCell(new Phrase("Total Credit (RM)", rectem));
                cell211.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell211.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell212 = new PdfPCell(new Phrase("" + totalYearCredit, rectemja));
                cell212.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell212.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell221 = new PdfPCell(new Phrase("Deficient (RM)", rectem));
                cell221.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell221.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell222 = new PdfPCell(new Phrase("" + deficient + "\n\n", rectemja));
                cell222.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell222.setBorder(Rectangle.NO_BORDER);
                
                tableSummary.addCell(cell201);
                tableSummary.addCell(cell202);
                tableSummary.addCell(cell211);
                tableSummary.addCell(cell212);
                tableSummary.addCell(cell221);
                tableSummary.addCell(cell222);
                
                //footer
                PdfPTable tableFooter = new PdfPTable(1);
                tableFooter.setWidths(new float[]{10.5f});
                tableFooter.setLockedWidth(true);
                tableFooter.setTotalWidth(document.right() - document.left());

                String message1 = "****Thank You****";
                String message2 = "Please Come Again";
                PdfPCell cellFooter1 = new PdfPCell(new Phrase(message1, rectemja));
                cellFooter1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellFooter1.setBorder(Rectangle.TOP);
                PdfPCell cellFooter2 = new PdfPCell(new Phrase(message2, rectemja));
                cellFooter2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellFooter2.setBorder(Rectangle.NO_BORDER);

                tableFooter.addCell(cellFooter1);
                tableFooter.addCell(cellFooter2);
                
                document.add(tableHeader);
                document.add(tableCust);
                document.add(tableStatement);
                document.add(tableSummary);
                document.add(tableFooter);
                document.close();
                writer.close();
                
            }catch(Exception e){
            }
        }
    }
    
    public void generateDetailsStatement(String ic){
        
    }
    
    public void generateMonthlyStatement(String ic, String month){
        String[] strMonth = Month.selectedMonth(month);
        
        if (strMonth != null){
            String dr = strMonth[0]; 
            String cr = strMonth[1];
            String period = strMonth[2];
            
            String sql1 = "SELECT "
                    + "pb.pmi_no, pb.patient_name, pb.id_no, pb.home_address, pb.mobile_phone, pb.email_address, "
                    + "cl."+ dr +", cl."+ cr +" "
                    + "FROM pms_patient_biodata pb, far_customer_ledger cl "
                    + "WHERE pb.pmi_no = cl.customer_id "
                    + "AND pb.new_ic_no = '"+ ic +"'";
            ArrayList<ArrayList<String>> dataPatient = rc.getQuerySQL(host, port, sql1);
            
            String pmiNo = dataPatient.get(0).get(0);
            String name = dataPatient.get(0).get(1);
            String id = dataPatient.get(0).get(2);
            String address = dataPatient.get(0).get(3);
            String phone = dataPatient.get(0).get(4);
            String email = dataPatient.get(0).get(5);
            String debit = dataPatient.get(0).get(6);
            String credit = dataPatient.get(0).get(7);
            
            String sql2 = "SELECT "
                    + "ch.txn_date, ch.bill_no, ch.item_amt "
                    + "FROM far_customer_hdr ch, pms_patient_biodata pb "
                    + "WHERE ch.customer_id = pb.pmi_no "
                    + "AND ch.customer_id = '"+ ic +"' "
                    + "AND ch.txn_date LIKE %'"+ period +"'% "
                    + "ORDER BY ch.txn_date, ch.bill_no";
            ArrayList<ArrayList<String>> dataBill = rc.getQuerySQL(host, port, sql2);
            
            //create new document
            Document document = new Document(PageSize.A4, 36, 36, 64, 36) {}; 
            document.setMargins(40, 30, 50, 50); 
            
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("MonthlyStatement.pdf"));
                document.open();
                
                //initialize pdf
                Font recno = new Font(Font.TIMES_ROMAN, 10);
                Font recti = new Font(Font.HELVETICA, 16, Font.BOLD);
                Font rectem = new Font(Font.HELVETICA, 12, Font.BOLD);
                Font rectemja = new Font(Font.COURIER, 12);
                Font rectemjaBold = new Font(Font.COURIER, 12, Font.BOLD);
                Font rectemjaBig = new Font(Font.COURIER, 16, Font.BOLD);
                
                //header
                PdfPTable table = new PdfPTable(6);
                table.setWidths(new float[]{0.5f, 1.5f, 4f, 1.5f, 1.5f, 1.5f});
                table.setLockedWidth(true);
                table.setTotalWidth(document.right() - document.left());

                PdfPTable tableHeader = new PdfPTable(4);
                tableHeader.setWidths(new float[]{3f, 4f, 3.5f, 4f});
                tableHeader.setLockedWidth(true);
                tableHeader.setTotalWidth(document.right() - document.left());

                Image logo = Image.getInstance("pic/logo.png");
                logo.scaleAbsolute(115, 50);

                PdfPCell cellLogo = new PdfPCell(logo);
                cellLogo.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellLogo.setBorder(Rectangle.NO_BORDER);
                cellLogo.setColspan(2);
                cellLogo.setLeading(15f, 0.3f);
                tableHeader.addCell(cellLogo);
                
                String addr = 
                        " Universiti Teknikal Malaysia Melaka, \n"
                        + " Hang Tuah Jaya, \n"
                        + " 76100 Durian Tunggal, \n"
                        + " Melaka, Malaysia.";

                PdfPCell cellAddress = new PdfPCell(new Phrase(addr, rectemja));
                cellAddress.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellAddress.setBorder(Rectangle.NO_BORDER);
                cellAddress.setColspan(2);
                tableHeader.addCell(cellAddress);
                
                PdfPCell cellAnnual = new PdfPCell(new Phrase("\n"+ month +" Account Statement\n\n", recti));
                cellAnnual.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellAnnual.setBorder(Rectangle.NO_BORDER);
                cellAnnual.setColspan(4);
                tableHeader.addCell(cellAnnual);                
                
                //Customer Details
                PdfPTable tableCust = new PdfPTable(1);
                tableCust.setWidths(new float[]{10f});
                tableCust.setLockedWidth(true);
                tableCust.setTotalWidth(document.right() - document.left());
                
                PdfPCell cell11 = new PdfPCell(new Phrase(name, rectemja));
                cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell11.setBorder(Rectangle.NO_BORDER);
                cell11.setColspan(3);
                
                PdfPCell cell21 = new PdfPCell(new Phrase(address, rectemja));
                cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell21.setBorder(Rectangle.NO_BORDER);
                cell21.setColspan(3);
                
                PdfPCell cell31 = new PdfPCell(new Phrase("\n" + strDate + "\n\n", rectemja));
                cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell31.setBorder(Rectangle.NO_BORDER);
                cell31.setColspan(3);
                
                tableCust.addCell(cell11);
                tableCust.addCell(cell21);
                tableCust.addCell(cell31);
                
                //Summary
                PdfPTable tableSummary = new PdfPTable(2);
                tableSummary.setWidths(new float[]{10.5f, 4f});
                tableSummary.setLockedWidth(true);
                tableSummary.setTotalWidth(document.right() - document.left());
                
                PdfPCell cell41 = new PdfPCell(new Phrase("Total Debit of "+ month +" (RM)", rectem));
                cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell41.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell42 = new PdfPCell(new Phrase(debit, rectemja));
                cell42.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell42.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell51 = new PdfPCell(new Phrase("Total Credit of "+ month +" (RM)", rectem));
                cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell51.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell52 = new PdfPCell(new Phrase(credit + "\n\n", rectemja));
                cell52.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell52.setBorder(Rectangle.NO_BORDER);
                
                tableSummary.addCell(cell41);
                tableSummary.addCell(cell42);
                tableSummary.addCell(cell51);
                tableSummary.addCell(cell52);
                
                PdfPTable tableStatement = new PdfPTable(3);
                tableStatement.setWidths(new float[]{2f, 10f, 2f});
                tableStatement.setLockedWidth(true);
                tableStatement.setTotalWidth(document.right() - document.left());
                
                PdfPCell cell61 = new PdfPCell(new Phrase("Transaction Date", rectem));
                cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell cell62 = new PdfPCell(new Phrase("Description", rectem));
                cell62.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell cell63 = new PdfPCell(new Phrase("Amount (RM)", rectem));
                cell63.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                tableStatement.addCell(cell61);
                tableStatement.addCell(cell62);
                tableStatement.addCell(cell63);
                
                for(int i = 0; i < dataBill.size(); i++){
                    String dateHdr = dataBill.get(i).get(0);
                    String billNo = dataBill.get(i).get(1);
                    String billAmt = dataBill.get(i).get(2);

                    String sql3 = "SELECT "
                            + "txn_date, item_desc, quantity, item_amt "
                            + "FROM far_customer_dtl "
                            + "WHERE bill_no = '"+ billNo +"'";
                    ArrayList<ArrayList<String>> dataItem = rc.getQuerySQL(host, port, sql3);

                    PdfPCell cell71 = new PdfPCell(new Phrase(dateHdr, rectem));
                    cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
                    PdfPCell cell72 = new PdfPCell(new Phrase(billNo + "(Bill No)", rectem));
                    cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
                    PdfPCell cell73 = new PdfPCell(new Phrase(billAmt, rectem));
                    cell73.setHorizontalAlignment(Element.ALIGN_CENTER);

                    tableStatement.addCell(cell71);
                    tableStatement.addCell(cell72);
                    tableStatement.addCell(cell73);
                    
                    for(int j = 0; j < dataItem.size(); j++){
                        String dateDtl = dataItem.get(j).get(0);
                        String itemName = dataItem.get(j).get(1);
                        String itemQty = dataItem.get(j).get(2);
                        String itemAmt = dataItem.get(j).get(3);
                        
                        PdfPTable tableItems = new PdfPTable(4);
                        tableItems.setWidths(new float[]{2f, 8f, 2f, 2f});
                        tableItems.setLockedWidth(true);
                        tableItems.setTotalWidth(document.right() - document.left());
                        
                        PdfPCell cell81 = new PdfPCell(new Phrase(dateDtl, rectem));
                        cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
                        PdfPCell cell82 = new PdfPCell(new Phrase(itemName, rectem));
                        cell82.setHorizontalAlignment(Element.ALIGN_CENTER);
                        PdfPCell cell83 = new PdfPCell(new Phrase(itemQty, rectem));
                        cell83.setHorizontalAlignment(Element.ALIGN_CENTER);
                        PdfPCell cell84 = new PdfPCell(new Phrase(itemAmt, rectem));
                        cell84.setHorizontalAlignment(Element.ALIGN_CENTER);

                        tableItems.addCell(cell81);
                        tableItems.addCell(cell82);
                        tableItems.addCell(cell83);
                        tableItems.addCell(cell84);
                    }
                    
                    PdfPTable tableFooter = new PdfPTable(1);
                    tableFooter.setWidths(new float[]{10.5f});
                    tableFooter.setLockedWidth(true);
                    tableFooter.setTotalWidth(document.right() - document.left());

                    String message1 = "****Thank You****";
                    String message2 = "Please Come Again";
                    PdfPCell cellFooter1 = new PdfPCell(new Phrase(message1, rectemja));
                    cellFooter1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellFooter1.setBorder(Rectangle.TOP);
                    PdfPCell cellFooter2 = new PdfPCell(new Phrase(message2, rectemja));
                    cellFooter2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellFooter2.setBorder(Rectangle.NO_BORDER);

                    tableFooter.addCell(cellFooter1);
                    tableFooter.addCell(cellFooter2);
                    
                    document.add(tableHeader);
                    document.add(tableCust);
                    document.add(tableSummary);
                    document.add(tableStatement);
                    document.add(tableFooter);
                    document.close();
                    writer.close();
               }   
            } catch (Exception e) {
            }
        } else {
            //Print all month
            //create new document
            Document document = new Document(PageSize.A4, 36, 36, 64, 36) {}; 
            document.setMargins(40, 30, 50, 50); 
            
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("MonthlyStatement.pdf"));
                document.open();
                
                strMonth = new String[]{"January", "February", "March", "April", "May", "June", 
                                    "July", "August", "September", "October", "November", "December"};
                
                for(int k = 0; k < strMonth.length; k++){
                    String[] monthDetails = Month.selectedMonth(strMonth[k]);
                    String dr = monthDetails[0]; 
                    String cr = monthDetails[1];
                    String period = monthDetails[2];
                    
                    String sql1 = "SELECT "
                            + "pb.pmi_no, pb.patient_name, pb.id_no, pb.home_address, pb.mobile_phone, pb.email_address, "
                            + "cl."+ dr +", cl."+ cr +" "
                            + "FROM pms_patient_biodata pb, far_customer_ledger cl "
                            + "WHERE pb.pmi_no = cl.customer_id "
                            + "AND pb.new_ic_no = '"+ ic +"'";
                    ArrayList<ArrayList<String>> dataPatient = rc.getQuerySQL(host, port, sql1);

                    String pmiNo = dataPatient.get(0).get(0);
                    String name = dataPatient.get(0).get(1);
                    String id = dataPatient.get(0).get(2);
                    String address = dataPatient.get(0).get(3);
                    String phone = dataPatient.get(0).get(4);
                    String email = dataPatient.get(0).get(5);
                    String debit = dataPatient.get(0).get(6);
                    String credit = dataPatient.get(0).get(7);

                    String sql2 = "SELECT "
                            + "ch.txn_date, ch.bill_no, ch.item_amt "
                            + "FROM far_customer_hdr ch, pms_patient_biodata pb "
                            + "WHERE ch.customer_id = pb.pmi_no "
                            + "AND ch.customer_id = '"+ ic +"' "
                            + "AND ch.txn_date LIKE %'"+ period +"'% "
                            + "ORDER BY ch.txn_date, ch.bill_no";
                    ArrayList<ArrayList<String>> dataBill = rc.getQuerySQL(host, port, sql2);
                    
                    //initialize pdf
                    Font recno = new Font(Font.TIMES_ROMAN, 10);
                    Font recti = new Font(Font.HELVETICA, 16, Font.BOLD);
                    Font rectem = new Font(Font.HELVETICA, 12, Font.BOLD);
                    Font rectemja = new Font(Font.COURIER, 12);
                    Font rectemjaBold = new Font(Font.COURIER, 12, Font.BOLD);
                    Font rectemjaBig = new Font(Font.COURIER, 16, Font.BOLD);

                    //header
                    PdfPTable table = new PdfPTable(6);
                    table.setWidths(new float[]{0.5f, 1.5f, 4f, 1.5f, 1.5f, 1.5f});
                    table.setLockedWidth(true);
                    table.setTotalWidth(document.right() - document.left());

                    PdfPTable tableHeader = new PdfPTable(4);
                    tableHeader.setWidths(new float[]{3f, 4f, 3.5f, 4f});
                    tableHeader.setLockedWidth(true);
                    tableHeader.setTotalWidth(document.right() - document.left());

                    Image logo = Image.getInstance("pic/logo.png");
                    logo.scaleAbsolute(115, 50);

                    PdfPCell cellLogo = new PdfPCell(logo);
                    cellLogo.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cellLogo.setBorder(Rectangle.NO_BORDER);
                    cellLogo.setColspan(2);
                    cellLogo.setLeading(15f, 0.3f);
                    tableHeader.addCell(cellLogo);

                    String addr = 
                            " Universiti Teknikal Malaysia Melaka, \n"
                            + " Hang Tuah Jaya, \n"
                            + " 76100 Durian Tunggal, \n"
                            + " Melaka, Malaysia.";

                    PdfPCell cellAddress = new PdfPCell(new Phrase(addr, rectemja));
                    cellAddress.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cellAddress.setBorder(Rectangle.NO_BORDER);
                    cellAddress.setColspan(2);
                    tableHeader.addCell(cellAddress);

                    PdfPCell cellAnnual = new PdfPCell(new Phrase("\n"+ strMonth[k] +" Account Statement\n\n", recti));
                    cellAnnual.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellAnnual.setBorder(Rectangle.NO_BORDER);
                    cellAnnual.setColspan(4);
                    tableHeader.addCell(cellAnnual);                

                    //Customer Details
                    PdfPTable tableCust = new PdfPTable(1);
                    tableCust.setWidths(new float[]{10f});
                    tableCust.setLockedWidth(true);
                    tableCust.setTotalWidth(document.right() - document.left());

                    PdfPCell cell11 = new PdfPCell(new Phrase(name, rectemja));
                    cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell11.setBorder(Rectangle.NO_BORDER);
                    cell11.setColspan(3);

                    PdfPCell cell21 = new PdfPCell(new Phrase(address, rectemja));
                    cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell21.setBorder(Rectangle.NO_BORDER);
                    cell21.setColspan(3);

                    PdfPCell cell31 = new PdfPCell(new Phrase("\n" + strDate + "\n\n", rectemja));
                    cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell31.setBorder(Rectangle.NO_BORDER);
                    cell31.setColspan(3);

                    tableCust.addCell(cell11);
                    tableCust.addCell(cell21);
                    tableCust.addCell(cell31);

                    //Summary
                    PdfPTable tableSummary = new PdfPTable(2);
                    tableSummary.setWidths(new float[]{10.5f, 4f});
                    tableSummary.setLockedWidth(true);
                    tableSummary.setTotalWidth(document.right() - document.left());

                    PdfPCell cell41 = new PdfPCell(new Phrase("Total Debit of "+ month +" (RM)", rectem));
                    cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell41.setBorder(Rectangle.NO_BORDER);
                    PdfPCell cell42 = new PdfPCell(new Phrase(debit, rectemja));
                    cell42.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell42.setBorder(Rectangle.NO_BORDER);
                    PdfPCell cell51 = new PdfPCell(new Phrase("Total Credit of "+ month +" (RM)", rectem));
                    cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell51.setBorder(Rectangle.NO_BORDER);
                    PdfPCell cell52 = new PdfPCell(new Phrase(credit + "\n\n", rectemja));
                    cell52.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell52.setBorder(Rectangle.NO_BORDER);

                    tableSummary.addCell(cell41);
                    tableSummary.addCell(cell42);
                    tableSummary.addCell(cell51);
                    tableSummary.addCell(cell52);

                    PdfPTable tableStatement = new PdfPTable(3);
                    tableStatement.setWidths(new float[]{2f, 10f, 2f});
                    tableStatement.setLockedWidth(true);
                    tableStatement.setTotalWidth(document.right() - document.left());

                    PdfPCell cell61 = new PdfPCell(new Phrase("Transaction Date", rectem));
                    cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
                    PdfPCell cell62 = new PdfPCell(new Phrase("Description", rectem));
                    cell62.setHorizontalAlignment(Element.ALIGN_CENTER);
                    PdfPCell cell63 = new PdfPCell(new Phrase("Amount (RM)", rectem));
                    cell63.setHorizontalAlignment(Element.ALIGN_CENTER);

                    tableStatement.addCell(cell61);
                    tableStatement.addCell(cell62);
                    tableStatement.addCell(cell63);

                    for(int i = 0; i < dataBill.size(); i++){
                        String dateHdr = dataBill.get(i).get(0);
                        String billNo = dataBill.get(i).get(1);
                        String billAmt = dataBill.get(i).get(2);

                        String sql3 = "SELECT "
                                + "txn_date, item_desc, quantity, item_amt "
                                + "FROM far_customer_dtl "
                                + "WHERE bill_no = '"+ billNo +"'";
                        ArrayList<ArrayList<String>> dataItem = rc.getQuerySQL(host, port, sql3);

                        PdfPCell cell71 = new PdfPCell(new Phrase(dateHdr, rectem));
                        cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
                        PdfPCell cell72 = new PdfPCell(new Phrase(billNo + "(Bill No)", rectem));
                        cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
                        PdfPCell cell73 = new PdfPCell(new Phrase(billAmt, rectem));
                        cell73.setHorizontalAlignment(Element.ALIGN_CENTER);

                        tableStatement.addCell(cell71);
                        tableStatement.addCell(cell72);
                        tableStatement.addCell(cell73);

                        for(int j = 0; j < dataItem.size(); j++){
                            String dateDtl = dataItem.get(j).get(0);
                            String itemName = dataItem.get(j).get(1);
                            String itemQty = dataItem.get(j).get(2);
                            String itemAmt = dataItem.get(j).get(3);

                            PdfPTable tableItems = new PdfPTable(4);
                            tableItems.setWidths(new float[]{2f, 8f, 2f, 2f});
                            tableItems.setLockedWidth(true);
                            tableItems.setTotalWidth(document.right() - document.left());

                            PdfPCell cell81 = new PdfPCell(new Phrase(dateDtl, rectem));
                            cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
                            PdfPCell cell82 = new PdfPCell(new Phrase(itemName, rectem));
                            cell82.setHorizontalAlignment(Element.ALIGN_CENTER);
                            PdfPCell cell83 = new PdfPCell(new Phrase(itemQty, rectem));
                            cell83.setHorizontalAlignment(Element.ALIGN_CENTER);
                            PdfPCell cell84 = new PdfPCell(new Phrase(itemAmt, rectem));
                            cell84.setHorizontalAlignment(Element.ALIGN_CENTER);

                            tableItems.addCell(cell81);
                            tableItems.addCell(cell82);
                            tableItems.addCell(cell83);
                            tableItems.addCell(cell84);
                        }

                        PdfPTable tableFooter = new PdfPTable(1);
                        tableFooter.setWidths(new float[]{10.5f});
                        tableFooter.setLockedWidth(true);
                        tableFooter.setTotalWidth(document.right() - document.left());

                        String message1 = "****Thank You****";
                        String message2 = "Please Come Again";
                        PdfPCell cellFooter1 = new PdfPCell(new Phrase(message1, rectemja));
                        cellFooter1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cellFooter1.setBorder(Rectangle.TOP);
                        PdfPCell cellFooter2 = new PdfPCell(new Phrase(message2, rectemja));
                        cellFooter2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cellFooter2.setBorder(Rectangle.NO_BORDER);

                        tableFooter.addCell(cellFooter1);
                        tableFooter.addCell(cellFooter2);

                        document.add(tableHeader);
                        document.add(tableCust);
                        document.add(tableSummary);
                        document.add(tableStatement);
                        document.add(tableFooter);
                        document.newPage();
                    }
                 }
                
                //close document
                document.close();
                writer.close();
                
            }catch (Exception e){
            }
        }
    }
}
