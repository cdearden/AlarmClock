/* Author:      Craig Dearden
 * Date:        Jun 21, 2016
 * Name:        Alarm.java
 * Description: Implements the methods to create and modify a simple alarm.
 */
package com.craigdearden.time;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Alarm implements Runnable {

  /**
   * The thread in which to run this alarm.
   */
  private Thread thread;

  /**
   * The name given to the alarm by the user.
   */
  private String name;

  /**
   * The time the alarm was set for.
   */
  private LocalTime time;

  /**
   * true if repeat is turned on.
   */
  private boolean repeat;

  /**
   * The interval of time to wait between alarm soundings.
   */
  private Duration repeatInterval;

  /**
   * true if the alarm is on.
   */
  private boolean on;

  Alarm() {
    name = "Default";
    time = LocalTime.parse("12:00");
    repeat = false;
    repeatInterval = Duration.of(1, ChronoUnit.DAYS);
    on = false;
  }

  Alarm(String name, LocalTime time) {
    this.name = name;
    this.time = time;
    repeat = false;
    repeatInterval = Duration.of(1, ChronoUnit.DAYS);
    on = true;
  }

  public void start() {
    if (getThread() == null) {
      thread = new Thread(this, name);
      getThread().start();
    }
  }

  /**
   * The time from now until the next alarm is determined. The thread is put to sleep
   * until that time. When the thread awakes it sounds the alarm. The time until the
   * next alarm is then set as the repeat time.
   */
  @Override
  public void run() {
    Duration tillAlarm = Duration.between(LocalTime.now(), time);
    if (tillAlarm.isNegative()) {
      tillAlarm = tillAlarm.plus(24, ChronoUnit.HOURS);
    }

    while (isOn()) {
      try {
        Thread.sleep(tillAlarm.toMillis());
      } catch (InterruptedException ex) {
        System.out.println("Thread: " + getThread().getName() + " interrupted!");
      }
      soundAlarm();
      tillAlarm = repeatInterval;
    }
  }

  public void soundAlarm() {
    System.out.println("*****Sounding Alarm: " + name + "*****");
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   * @return this <code>Alarm</code> instance
   */
  public Alarm setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * @return the time
   */
  public LocalTime getTime() {
    return time;
  }

  /**
   * @param time the time to set
   * @return this <code>Alarm</code> instance
   */
  public Alarm setTime(LocalTime time) {
    this.time = time;
    return this;
  }

  /**
   * @return the repeat
   */
  public boolean getRepeat() {
    return repeat;
  }

  /**
   * @param repeat the repeat to set
   * @return this <code>Alarm</code> instance
   */
  public Alarm setRepeat(Boolean repeat) {
    this.repeat = repeat;
    return this;
  }

  /**
   * @return the on
   */
  public boolean isOn() {
    return on;
  }

  /**
   * @param on the on to set
   * @return this <code>Alarm</code> instance
   */
  public Alarm setOn(boolean on) {
    this.on = on;
    return this;
  }

  /**
   * @return the repeatInterval
   */
  public Duration getRepeatEvery() {
    return repeatInterval;
  }

  /**
   * @param repeatInterval the repeatInterval to set
   * @return this <code>Alarm</code> instance
   */
  public Alarm setRepeatEvery(Duration repeatInterval) {
    this.repeatInterval = repeatInterval;
    return this;
  }

  /**
   * @return a String displaying the alarm information.
   */
  @Override
  public String toString() {
    String status = isOn() ? "ON" : "OFF";
    String alarm = String.format(
        "%1$-10s %2$-10s Repeat every: %3$-10s %4$-4s", name,
        time.toString(), repeatInterval.toString(), "Alarm: " + status);
    return alarm;
  }

  /**
   * @return the thread
   */
  public Thread getThread() {
    return thread;
  }

}
