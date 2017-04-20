/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.avs.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Alex
 */
public class TestRun {
    public static void main(String[] args) {
        DateUtilities dUtils = new DateUtilities();
        String dateString = "1998-12-22 10:30";
        String pattern = "yyyy-MM-dd HH:mm";
        String timeString = "seconds";
        
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.of(2017, Month.APRIL, 11, 2, 40);
       
        
        
      
        System.out.println(dUtils.returnFormattedDateString(dt2, 
                DateUtilities.MONTH_FIRST_AM_PM));

       
        
       
      
        
    }
}
