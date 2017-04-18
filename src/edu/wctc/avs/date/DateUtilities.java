/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.avs.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
/**
 * A simple utility class with miscellaneous methods for handling dates.
 * 
 * @author Alex
 * @version 1.8
 * @since 1.8
 */
public class DateUtilities {

    /**
     * A <code>String</code> constant representing a date format where the year is first,
     * followed by the month, with hours and minutes at the end.
     */
    public static final String YEAR_FIRST = "yyyy-MM-dd HH:mm";
    
    /**
     * A <code>String</code> constant representing a date format where the month
     * with the first three letters is first, followed by the day of month, then 
     * the year. Hours and minutes (non-military time) with AM or PM at the end.
     */
    public static final String MONTH_FIRST_AM_PM = "MMM d yyyy  hh:mm a";
    
    /**
     * Takes a LocalDateTime object, as well as a String pattern used for the date,
     * then returns a formatted string based on those arguments.
     * @param ldt
     * @param pattern
     * @return
     * @throws IllegalArgumentException 
     */
    public String returnFormattedDateString(LocalDateTime ldt, String pattern)
            throws IllegalArgumentException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return ldt.format(fmt);
    }
    
    /**
     * Takes a string and a <code>DateTimeFormatter</code> object, then returns a
     * new <code>LocalDateTime</code> object based on the string and format.
     * @param fs
     * @param fmt
     * @return
     * @throws IllegalArgumentException 
     */
    public LocalDateTime returnLocalDateTimeFromFormatString(String fs,
            DateTimeFormatter fmt) throws IllegalArgumentException {

        return LocalDateTime.parse(fs, fmt);
    }
    
    /**
     * Returns the difference between two dates, using a string representing the
     * corresponding Chrono Unit.
     * @param firstDt
     * @param secondDt
     * @param timeString
     * @return
     * @throws IllegalArgumentException 
     */
    public long returnUnitBetweenTimesOrDates(LocalDateTime firstDt, LocalDateTime secondDt,
            String timeString) throws IllegalArgumentException {

        return ChronoUnit.valueOf(timeString.toUpperCase().trim()).between(firstDt, secondDt);
    }
    
    
    /**
     * Returns a future date with an amount of whatever Chrono unit is added to the 
     * original.
     * @param value
     * @param unit
     * @return
     * @throws IllegalArgumentException 
     */
    public LocalDateTime getFutureDateFromNow(long value, String unit)
            throws IllegalArgumentException {
        LocalDateTime today = LocalDateTime.now();
        unit = unit.toUpperCase().trim();

        return today.plus(value, ChronoUnit.valueOf(unit));

    }
    
    
    /**
     * Returns a LocalDateTime object with an amount of Chrono Units from the current
     * date and time.
     * @param value
     * @param unit
     * @return
     * @throws IllegalArgumentException 
     */
    public LocalDateTime getPastDateFromNow(int value, String unit)
            throws IllegalArgumentException {
        LocalDateTime today = LocalDateTime.now();
        unit = unit.toUpperCase().trim();

        return today.minus(value, ChronoUnit.valueOf(unit));
    }

}
