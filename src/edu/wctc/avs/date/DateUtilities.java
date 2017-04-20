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
 * A simple utility class with miscellaneous methods for handling dates. The Java
 * 8 API is being used for this class.
 * 
 * @author Alex
 * @version 1.0
 * @since 1.8
 */
public class DateUtilities {

    /**
     * A <code>String</code> constant representing a date format where the year is first,
     * followed by the month and day, with hours and minutes at the end.
     * This constant is intended to be used with a date formatting object.
     */
    public static final String YEAR_FIRST = "yyyy-MM-dd HH:mm";
    
    /**
     * A <code>String</code> constant representing a date format where the first three 
     * letters of the month is first, followed by the day of month, then 
     * the year. Hours and minutes (non-military time) with AM or PM are at the end.
     * This constant is intended to be used with a date formatting object.
     */
    public static final String MONTH_FIRST_AM_PM = "MMM d yyyy  hh:mm a";
    
    /**
     * Takes a LocalDateTime object, as well as a String pattern used for the date,
     * then returns a formatted string representing the date based on those arguments.
     * @param ldt
     * @param pattern
     * @return A formatted string representing a date.
     * @throws IllegalArgumentException if either the LocalDateTime object or 
     * String pattern is not provided.
     */
    public String returnFormattedDateString(LocalDateTime ldt, String pattern)
            throws IllegalArgumentException {
        if(null == ldt || null == pattern){
            throw new IllegalArgumentException("Must provide a Local Date Time and a String"
                    + "pattern.");
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return ldt.format(fmt);
    }
    
    /**
     * Takes a string and a <code>DateTimeFormatter</code> object, then returns a
     * new <code>LocalDateTime</code> object based on the string and format provided.
     * @param fs 
     * @param fmt
     * @return A new LocalDateTime object based on the provided String and DateTimeFormatter 
     * object.
     * @throws IllegalArgumentException if either the DateTimeFormatter object,
     * or the String representing the returned date are not provided.
     */
    public LocalDateTime returnLocalDateTimeFromFormatString(String fs,
            DateTimeFormatter fmt) throws IllegalArgumentException {

        return LocalDateTime.parse(fs, fmt);
    }
    
    /**
     * Returns the difference between two dates, using a string representing the
     * corresponding Chrono Unit, such as hours or days, given as a String. 
     * (Ex: "days"). Certain values given for Chrono Units are disallowed,
     * such as MELLENIA and FOREVER, because they are not appropriate for the context 
     * in which this method is intended to be used. Another method will be implemented in 
     * the future to cover all possible cases.
     * The value returned is a long integer.
     * @param firstDt 
     * @param secondDt
     * @param timeString
     * @return
     * @throws IllegalArgumentException if the first, second date, or timeString is
     * not provided, or if the provided Time String is not a valid Chrono Unit for this method.
     */
    public long returnSimpleUnitsBetweenLocalDateTimes(LocalDateTime firstDt, LocalDateTime secondDt,
            String timeString) throws IllegalArgumentException {
        if(null == firstDt || null == secondDt || null == timeString){
            throw new IllegalArgumentException("First, second date, or String "
                    + "representing a valid Chrono unit not given.");
        } else if(!isStringValidSimpleChronoUnit(timeString)) {
            throw new IllegalArgumentException("Time string passed is not a valid "
                    + "chrono unit for this method.");
            
        }

        return ChronoUnit.valueOf(timeString.toUpperCase().trim()).between(firstDt, secondDt);
    }
    
    private boolean isStringValidSimpleChronoUnit(String s) throws IllegalArgumentException{
        if(null == s){
            throw new IllegalArgumentException("String to test for a valid Chrono "
                    + "Unit not given.");
        }
        String[] validChronoUnits = {
            "YEARS", "MONTHS", "WEEKS", "DAYS", "HOURS", "MINUTES", 
            "SECONDS"
        };
        for(String unit : validChronoUnits){
            if(s.toUpperCase().trim().equals(unit)){
                return true;
            }
        }
        
        
        return false;
    }
    
    
    /**
     * Returns a future date with an amount of whatever Simple Valid Chrono Unit is added to the 
     * current date time. 
     * @param value - A long indicating how many units to be added.
     * @param unit - A String representing a Chrono Unit within a limited scope for the purposes of 
     * this class. (ie YEARS, MONTHS, WEEKS, DAYS, HOURS, MINUTES, SECONDS)
     * @return A local DateTime with the number of given units added.
     * @throws IllegalArgumentException if the String unit is invalid. 
     */
    public LocalDateTime getFutureDateFromNow(long value, String unit)
            throws IllegalArgumentException {
        if(null == unit || !isStringValidSimpleChronoUnit(unit)){
            throw new IllegalArgumentException("The String unit value passed is "
                    + "invalid");
        }
        LocalDateTime today = LocalDateTime.now();
        unit = unit.toUpperCase().trim();

        return today.plus(value, ChronoUnit.valueOf(unit));

    }
    
    
    /**
     * Returns a LocalDateTime object with an amount of Chrono Units subtracted from the current
     * date and time.
     * @param value - A long indicating how many units to be subtracted.
     * @param unit -  A String representing a Chrono Unit within a limited scope for the purposes of 
     * this class. (ie YEARS, MONTHS, WEEKS, DAYS, HOURS, MINUTES, SECONDS)
     * @return A local DateTime with the number of given units subtracted.
     * @throws IllegalArgumentException if the string unit is invalid.
     */
    public LocalDateTime getPastDateFromNow(long value, String unit)
            throws IllegalArgumentException {
        if(null == unit || !isStringValidSimpleChronoUnit(unit)){
            throw new IllegalArgumentException("The String unit value passed is "
                    + "invalid");
        }
        LocalDateTime today = LocalDateTime.now();
        unit = unit.toUpperCase().trim();

        return today.minus(value, ChronoUnit.valueOf(unit));
    }

}
