/* Author:      Craig Dearden
 * Date:        Jun 21, 2016
 * Name:        AlarmClock.java
 * Description: Implements functionality characteristic of an alarm clock
 */


package com.craigdearden.time;

import java.sql.Date;
import java.util.ArrayList;


public class AlarmClock {
    private ArrayList<Alarm> alarms = new ArrayList<>();
    
    
    
    public void turnOn(String name) {
        getAlarmByName(name).setOn(true);
    }
    
    public void turnOff(String name) {
        getAlarmByName(name).setOn(false);
    }
    
    public void newAlarm(String name, long time, Date repeat, boolean on) {
        Alarm alarm = new Alarm(name, time, repeat, on);
        alarms.add(alarm);
    }
    
    private Alarm getAlarmByName(String name) {
        int i = 0;        
        Alarm alarm = alarms.get(i); 
        while(alarm.getName() != name && i < alarms.size()){
            alarm = alarms.get(i);
            i++;
        }
        return alarm;
    }
    
    public void displayAlarms() {
        int i = 0;
        while(i < alarms.size()) {
            System.out.println(alarms.get(i).toString() + "\n");
        }
    }

}
