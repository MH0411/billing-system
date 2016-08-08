/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ho Zhen Hong
 */
public class Month {
    
    private DateFormat monthFormat = new SimpleDateFormat("MM");
    private Date date = new Date();
    private String creditMonth = null;
    private String debitMonth = null;
    private String month = monthFormat.format(date);
    private String monthName = null;
    private static Date d = new Date();
    private static DateFormat yearFormat = new SimpleDateFormat("yyyy");
    private static String year = yearFormat.format(d);
    
    public Month(){
        determineCreditMonth();
        determineDebitMonth();
        determineMonthName();
    }
    
    public String getCreditMonth(){
        return creditMonth;
    }
    
    public String getDebitMonth(){
        return debitMonth;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }
    
    /**
     * @return the month name
     */
    public String getMonthName(){
        return monthName;
    }

    /**
     * @return the year
     */
    public static String getYear() {
        return year;
    }
    
    /**
     * Find month credit
     */
    public void determineCreditMonth(){
        
        //Check current month
        if (null != getMonth()) {
            switch (getMonth()) {
                case "01":
                    creditMonth = "cr_amt_1";
                    break;
                case "02":
                    creditMonth = "cr_amt_2";
                    break;
                case "03":
                    creditMonth = "cr_amt_3";
                    break;
                case "04":
                    creditMonth = "cr_amt_4";
                    break;
                case "05":
                    creditMonth = "cr_amt_5";
                    break;
                case "06":
                    creditMonth = "cr_amt_6";
                    break;
                case "07":
                    creditMonth = "cr_amt_7";
                    break;
                case "08":
                    creditMonth = "cr_amt_8";
                    break;
                case "09":
                    creditMonth = "cr_amt_9";
                    break;
                case "10":
                    creditMonth = "cr_amt_10";
                    break;
                case "11":
                    creditMonth = "cr_amt_11";
                    break;
                case "12":
                    creditMonth = "cr_amt_12";
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * Find month debit
     */
    public void determineDebitMonth(){
        
        //Check current month
        if (null != getMonth()) {
            switch (getMonth()) {
                case "01":
                    debitMonth = "dr_amt_1";
                    break;
                case "02":
                    debitMonth = "dr_amt_2";
                    break;
                case "03":
                    debitMonth = "dr_amt_3";
                    break;
                case "04":
                    debitMonth = "dr_amt_4";
                    break;
                case "05":
                    debitMonth = "dr_amt_5";
                    break;
                case "06":
                    debitMonth = "dr_amt_6";
                    break;
                case "07":
                    debitMonth = "dr_amt_7";
                    break;
                case "08":
                    debitMonth = "dr_amt_8";
                    break;
                case "09":
                    debitMonth = "dr_amt_9";
                    break;
                case "10":
                    debitMonth = "dr_amt_10";
                    break;
                case "11":
                    debitMonth = "dr_amt_11";
                    break;
                case "12":
                    debitMonth = "dr_amt_12";
                    break;
                default:
                    break;
            }
        }
    }
    
    public static String selectedMonth(String month){
        if (month != null || month.equalsIgnoreCase("all")) {
            switch (month) {
                case "January":
                    return "-01-";
                case "February":
                   return "-02-";
                case "March":
                    return "-03-";
                case "April":
                    return "-04-";
                case "May":
                    return "-05-";
                case "June":
                    return  "-06-";
                case "July":
                    return "-07-";
                case "August":
                    return "-08-";
                case "September":
                    return "-09-";
                case "October":
                    return "-10-";
                case "November":
                    return "-11-";
                case "December":
                    return "-12-";
                default:
                    return null;
            }
        }
        return null;
    }
    
    public void determineMonthName(){
        
        //Check current month
        if (null != getMonth()) {
            switch (getMonth()) {
                case "01":
                    monthName = "January";
                    break;
                case "02":
                    monthName = "February";
                    break;
                case "03":
                    monthName = "March";
                    break;
                case "04":
                    monthName = "April";
                    break;
                case "05":
                    monthName = "May";
                    break;
                case "06":
                    monthName = "June";
                    break;
                case "07":
                    monthName = "July";
                    break;
                case "08":
                    monthName = "August";
                    break;
                case "09":
                    monthName = "September";
                    break;
                case "10":
                    monthName = "October";
                    break;
                case "11":
                    monthName = "November";
                    break;
                case "12":
                    monthName = "December";
                    break;
                default:
                    break;
            }
        }
    }
}
