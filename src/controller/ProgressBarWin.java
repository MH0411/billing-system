/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.Billing;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Ho Zhen Hong
 */
public class ProgressBarWin {
    public static void main(String[] args) {
        JFrame parentFrame = new JFrame();
        parentFrame.setSize(500, 150);

        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentFrame.pack();
        parentFrame.setLocationRelativeTo(null);
        parentFrame.setVisible(true);

        final JDialog dlg = new JDialog(parentFrame, "Progress Dialog", true);
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        dlg.add(BorderLayout.CENTER, progressBar);
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlg.setSize(300, 75);
        dlg.setLocationRelativeTo(parentFrame);

        Thread t = new Thread(() -> {
            dlg.setVisible(true);
            dlg.getContentPane().paintAll(progressBar.getGraphics());
        });
        t.start();
        //=========================================
        for (int i = 0; i <= 500; i++) {
            progressBar.setValue(i*100/500);
            
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        dlg.setVisible(false);
    }
}

