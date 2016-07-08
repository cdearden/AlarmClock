/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.craigdearden.time;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author C1
 */
public class AlarmClockDriver
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Alarm a = new Alarm().setName("Wake up!")
                             .setOn(true)
                             .setRepeat(true)
                             .setRepeatEvery(Duration.of(24, ChronoUnit.HOURS))
                             .setTime(LocalTime.parse("5:30", DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(a);
        System.out.println(LocalTime.now());
        System.out.println(a.getTime());
            
        a.start();
        
        System.out.println("end");
    }
    
}
