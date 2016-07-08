/* Author:      Craig Dearden
 * Date:        Jun 21, 2016
 * Name:        AlarmClock.java
 * Description: Implements functionality characteristic of an alarm clock
 */

package com.craigdearden.time;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

public class AlarmClock {

  /**
   * Contains the alarms that have been created. The key corresponds to the alarm name.
   */
  private final Map<String, Alarm> alarms = new HashMap<>();

  public void editDialog() {
    displayAlarms();
    System.out.println("Edit alarm.%n" + "Please enter the alarm you would like to edit.");
    Scanner sc = new Scanner(System.in);
    String input = sc.next();
    while (!alarms.containsKey(input) && !input.equals("exit")) {
      System.out.println("Invalid input. Please enter the alarm you would like to edit.");
      input = sc.next();
    }
    Alarm alarm = alarms.get(input);

    System.out.println(
        "Please enter the field you would like to edit. (name, time, repeat, repeat_interval, status");
    input = sc.next();
    while (!input.toLowerCase().equals("exit")) {
      switch (sc.next()) {
        case "name":
          promptUsr(alarm, "name", alarm.getName());
          String name = sc.nextLine();
          alarm.setName(name);
          break;
        case "time":
          promptUsr(alarm, "time", alarm.getTime().toString());
          Pattern pattern = Pattern.compile("\\d\\d:\\d\\d");
          try {
            String time = sc.next(pattern);
            alarm.setTime(LocalTime.parse(time));
          } catch (NoSuchElementException e) {
            System.out.println("Invalid input! Must be in the form HH:MM.");
          }
          // Must restart thread
          break;
        case "repeat":
          promptUsr(alarm, "repeat", String.valueOf(alarm.getRepeat()));
          try {
            boolean repeat = sc.nextBoolean();
            alarm.setRepeat(repeat);
          } catch (InputMismatchException e) {
            System.out.println("Invalid input! Must be 'true' or 'false'.");
          }
          // Must restart thread
          break;
        case "repeat_interval":
          promptUsr(alarm, "repeat_interval", alarm.getRepeatEvery().toString());
          pattern = Pattern.compile("\\d\\d:\\d\\d");
          try {
            String time = sc.next(pattern);
            alarm.setTime(LocalTime.parse(time));
          } catch (NoSuchElementException e) {
            System.out.println("Invalid input! Must be in the form HH:MM.");
          }
          // Must restart thread
          break;
        case "status":
          promptUsr(alarm, "status", String.valueOf(alarm.isOn()));
          pattern = Pattern.compile("\\d\\d:\\d\\d");
          try {
            boolean status = sc.nextBoolean();
            alarm.setOn(status);
          } catch (InputMismatchException e) {
            System.out.println("Invalid input! Must be 'true' or 'false'.");
          }
          // Must kill or start thread
          break;
        default:
      }
    }
  }

 // Trying to take away some of the repeated code
 // Find a better way to do this.
  private void promptUsr(Alarm alarm, String field, String currentValue) {
    System.out.println("Edit alarm " + field + ": " + currentValue);
    System.out.print("Enter new " + field + "... ");
  }

  public void turnOn(String name) {
    Alarm alarm = alarms.get(name);
    alarm.setOn(true);
    alarm.getThread().start();
  }

  public void turnOff(String name) {
    alarms.get(name).setOn(false);
  }

  public void newAlarm(String name, LocalTime time) {
    Alarm alarm = new Alarm(name, time);
    alarms.put(name, alarm);
  }

  public void deleteAlarm(String name) {
    alarms.remove(name);
  }

  public void displayAlarms() {
    alarms.forEach((k, v) -> System.out.println(v + "\n"));
  }

  public void alert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setGraphic(null);
    alert.setHeaderText(null);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
