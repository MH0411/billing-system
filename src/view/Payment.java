/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Receipt;
import model.Month;
import model.ServerDetail;
import controller.EmailSender;
import controller.SMSService;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import main.RMIConnector;

/**
 *
 * @author Ho Zhen Hong
 */
public class Payment extends javax.swing.JFrame {

    //Call library
    RMIConnector rc = new RMIConnector();
    //Declaration host and port
    private String host = ServerDetail.getHost();
    private int port = ServerDetail.getPort();

    private DecimalFormat df = new DecimalFormat("0.00");
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private String txnDate = dateFormat.format(new Date());
    private Month month = new Month();

    private String custId;
    private String billNo;
    private double subtotal = 0;
    private double grandTotal;

    private String gstAmount = "0.00";
    private String serviceChargeAmount = "0.00";
    private String discountAmount = "0.00";
    private double cash = 0;
    private double change = 0;
    private double rounding = 0;
    private double amtPaid = 0;
    
    /**
     * Creates new form Payment
     */
    public Payment() {
        initComponents();
        super.pack();
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jtf_Subtotal.setEditable(false);
        jtf_GrandTotal.setEditable(false);
        jtf_Change.setEditable(false);
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the billNo
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * @param billNo the billNo to set
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        btn_MakePayment = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtf_Subtotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtf_GrandTotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jcb_PaymentMethod = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jtf_Amount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtf_Change = new javax.swing.JTextField();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btn_MakePayment.setText("Make Payment");
        btn_MakePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MakePaymentActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Payment Calculator");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setText("Subtotal :");

        jLabel6.setText("Grand Total :");

        jLabel4.setText("Payment Method :");

        jcb_PaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Credit Card", "Cheque" }));

        jLabel1.setText("Amount Received :");

        jtf_Amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtf_AmountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_AmountKeyTyped(evt);
            }
        });

        jLabel5.setText("Change :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtf_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcb_PaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_GrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_Change, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtf_GrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcb_PaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtf_Change, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jtf_Subtotal.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_MakePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btn_MakePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Make payment update the customer ledger
     *
     * @param evt
     */
    private void btn_MakePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MakePaymentActionPerformed
        // TODO add your handling code here:
        //cash = csh
        //credit card = crc
        //cheque = chq
        String amount = jtf_Amount.getText();
        String method = "Cash";
        method = jcb_PaymentMethod.getSelectedItem().toString();

        if (method != null) {
            switch (method) {
                case "Cash":
                    method = "csh";
                    break;
                case "Credit Card":
                    method = "crc";
                    break;
                case "Cheque":
                    method = "chq";
                    break;
            }
        }
        
        if (amount.equals("")) {
            String infoMessage = "Please insert an amount first.";
            JOptionPane.showMessageDialog(null, infoMessage, "Warning", JOptionPane.WARNING_MESSAGE);

        } else {

            try {
                month.setMonth(billNo.substring(10,12));
                
                //Get current credit from customer ledger
                String sql = "SELECT cl." + month.getCreditMonth() + " "
                        + "FROM far_customer_ledger cl, pms_patient_biodata pb "
                        + "WHERE cl.customer_id = '" + custId + "' "
                        + "AND pb.pmi_no = '" + custId + "'";
                ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);
                String creditMonth = data.get(0).get(0);
                
                if (creditMonth == null){
                    creditMonth = "0";
                }

                creditMonth = String.valueOf(Double.parseDouble(creditMonth) + Double.parseDouble(amount));
                
                //Update customer ledger credit
                String sql1 = "UPDATE far_customer_ledger "
                        + "SET pay_method = '" + method + "', " + month.getCreditMonth() + " = '" + creditMonth + "', txn_date = '" + txnDate + "' "
                        + "WHERE customer_id = '" + custId + "' ";
                rc.setQuerySQL(host, port, sql1);
                
                cash = Double.parseDouble(jtf_Amount.getText());
                String payment;
                if ((cash - grandTotal) >= 0){
                    amtPaid = grandTotal;
                    payment = "Paid";
                } else {
                    amtPaid = cash;
                    payment = "Unpaid";
                }
                
                //Get amt_paid from customer
                String sql2 = "SELECT amt_paid "
                        + "FROM far_customer_hdr "
                        + "WHERE bill_no = '"+ billNo +"'";
                ArrayList<ArrayList<String>> data1 = rc.getQuerySQL(host, port, sql2);
                double amountPaid = Double.parseDouble(data1.get(0).get(0));
                
                amtPaid = amountPaid + amtPaid;
                
                //Update customer hdr bill 
                String sql3 = "UPDATE far_customer_hdr "
                        + "SET payment = '"+ payment +"', txn_date = '" + txnDate + "', amt_paid = '"+ amtPaid +"' "
                        + "WHERE bill_no = '" + billNo + "'";
                rc.setQuerySQL(host, port, sql3);

                String infoMessage = "Payment success.";
                JOptionPane.showMessageDialog(null, infoMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
                
                String sql4 = "SELECT cd.item_desc, cd.item_amt "
                        + "FROM far_customer_dtl cd, pms_patient_biodata pb "
                        + "WHERE cd.item_cd LIKE 'BP%' "
                        + "AND cd.bill_no = '"+ billNo +"' "
                        + "AND cd.customer_id = '"+ custId +"' "
                        + "AND pb.pmi_no = '"+ custId +"'";
                ArrayList<ArrayList<String>> bp = rc.getQuerySQL(host, port, sql4);

                if (bp != null){
                    for (int i = 0 ; i < bp.size() ; i++){
                        if (bp.get(i).get(0).equalsIgnoreCase("gst")){
                            gstAmount = bp.get(i).get(1);
                        } else if (bp.get(i).get(0).equalsIgnoreCase("service charge")){
                            serviceChargeAmount = bp.get(i).get(1);
                        } else if (bp.get(i).get(0).equalsIgnoreCase("discount")){
                            discountAmount = bp.get(i).get(1);
                        } 
                        System.out.println(subtotal);
                        subtotal += Double.parseDouble(bp.get(i).get(1));
                    }
                }
                
                rounding = grandTotal - subtotal;
                String subtotalBeforeTax = jtf_Subtotal.getText();
                
                System.out.println("grand" + grandTotal);
                System.out.println("sub" + subtotal);
                System.out.println("round" + rounding);
                
                Receipt pdf = new Receipt(custId,
                        billNo,
                        String.valueOf(subtotalBeforeTax),
                        String.valueOf(df.format(grandTotal)),
                        String.valueOf(df.format(cash)),
                        String.valueOf(df.format(change)),
                        String.valueOf(gstAmount),
                        String.valueOf(serviceChargeAmount),
                        String.valueOf(discountAmount),
                        String.valueOf(df.format(rounding))
                );
                pdf.printPaidBill();
                Desktop.getDesktop().open(new File("Receipt.pdf"));
                
                String sql5 = "SELECT patient_name, email_address, mobile_phone "
                        + "FROM pms_patient_biodata "
                        + "WHERE pmi_no = '"+ custId +"'";
                ArrayList<ArrayList<String>> data2 = rc.getQuerySQL(host, port, sql5);
                String name = data2.get(0).get(0);
                String email = data2.get(0).get(1);
                String phone = data2.get(0).get(2);

                if(email != null){
                    EmailSender es = new EmailSender(email, "Receipt", "Thanks you have a nice day.", "Receipt.pdf");
                    es.sendEmail();
                }
                String message = "Hi " + name + ", Below is the receipt details: \n"
                        + "Subtotal : " + subtotal + "\n"
                        + "Service Charge : " + serviceChargeAmount + "\n"
                        + "GST : " + gstAmount + "\n"
                        + "Discount : " + discountAmount + "\n"
                        + "Rounding : " + df.format(rounding) + "\n"
                        + "Grand Total : " + grandTotal + "\n"
                        + "Cash : " + amount + "\n"
                        + "Change : " + change;
                SMSService service = new SMSService("+6" + phone, message, ServerDetail.getHost());
                
                dispose(); 
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btn_MakePaymentActionPerformed

    /**
     * Allow only numerical value and dot
     *
     * @param evt
     */
    private void jtf_AmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_AmountKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_PERIOD
                || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        } else if (jtf_Amount.getText().length() > 8) {
            evt.consume();
        }
    }//GEN-LAST:event_jtf_AmountKeyTyped

    /**
     * Calculate the change
     *
     * @param evt
     */
    private void jtf_AmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_AmountKeyReleased
        // TODO add your handling code here:
        try{
        if (jtf_Amount.getText().toString().equals(".")) {
            cash = 0;
        } else {
            cash = Double.parseDouble(jtf_Amount.getText().toString());
        }
        change = cash - grandTotal;
        jtf_Change.setText(String.valueOf(df.format(change)));
        } catch (Exception e){
            evt.consume();
        }
    }//GEN-LAST:event_jtf_AmountKeyReleased

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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment().setVisible(true);
            }
        });
    }

    /**
     * Display patient's bill details.
     */
    public void displayBillDetail() {
        
        String sql1 = "SELECT item_cd, item_desc, item_amt "
                + "FROM far_customer_dtl "
                + "WHERE bill_no = '"+ billNo +"' ";
        ArrayList<ArrayList<String>> data1 = rc.getQuerySQL(host, port, sql1);
        
        for(int i = 0 ; i < data1.size() ; i++){
            grandTotal += Double.parseDouble(data1.get(i).get(2));
            if (data1.get(i).get(0).contains("BP") == true){
            } else {
                subtotal += Double.parseDouble(data1.get(i).get(2));
            }
        }
        
        String sql2 = "SELECT item_amt, amt_paid "
                + "FROM far_customer_hdr "
                + "WHERE bill_no = '"+ billNo +"'";
        ArrayList<ArrayList<String>> data2 = rc.getQuerySQL(host, port, sql2);
        grandTotal = Double.parseDouble(data2.get(0).get(0));
        amtPaid = Double.parseDouble(data2.get(0).get(1));
        
        double outstandingBalance = grandTotal-amtPaid;
        
        if ((outstandingBalance != 0) && (amtPaid != 0)){
            subtotal = outstandingBalance;
            grandTotal = outstandingBalance;
        }
        
        grandTotal = Math.round(grandTotal * 20) / 20.0;
        jtf_Subtotal.setText(String.valueOf(df.format(subtotal)));
        jtf_GrandTotal.setText(String.valueOf(df.format(grandTotal)));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_MakePayment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JComboBox<String> jcb_PaymentMethod;
    private javax.swing.JTextField jtf_Amount;
    private javax.swing.JTextField jtf_Change;
    private javax.swing.JTextField jtf_GrandTotal;
    private javax.swing.JTextField jtf_Subtotal;
    // End of variables declaration//GEN-END:variables
}
