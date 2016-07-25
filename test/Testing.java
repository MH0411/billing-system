
import model.Month;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ho Zhen Hong
 */
public class Testing {
    public static void main(String[] args) {
        String month = null;
        String[] strMonth = Month.selectedMonth("January");
        if (strMonth != null){
            String dr = strMonth[0]; 
            String cr = strMonth[1];
            String period = strMonth[2];
            
            System.out.println(dr);
            System.out.println(cr);
            System.out.println(period);
        }
    }
}
