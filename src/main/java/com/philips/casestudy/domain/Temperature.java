/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.domain;

import java.util.Map;
import java.util.TreeMap;

public class Temperature {

  private double reading;

  String result;
  String vitalName = "Temperature";
  static Map<Double, String> temperatureMonitorStatus= new TreeMap<>();

  public Temperature(double reading) {
    this.reading = reading;
    this.result = null;

    temperatureMonitorStatus.put((double)92,"undetectably low reading");
    temperatureMonitorStatus.put((double)97,"low reading - Care needed");
    temperatureMonitorStatus.put(98.9,"Normal");
    temperatureMonitorStatus.put(100.5,"High reading - Care needed");
    temperatureMonitorStatus.put(103.1,"High reading - Extreme Care needed");
    temperatureMonitorStatus.put((double)108,"High reading - Critical Care needed");
    temperatureMonitorStatus.put((double)109,"Device Not Calibrated to measure such high values");


  }


  public static Map<Double, String> getTemperatureMonitorStatus() {
    return temperatureMonitorStatus;
  }


  public static void setTemperatureMonitorStatus(Map<Double, String> temperatureMonitorStatus) {
    Temperature.temperatureMonitorStatus = temperatureMonitorStatus;
  }


  public Temperature() {

  }

  public double getReading() {
    return reading;
  }

  @Override
  public String toString() {
    return "Temperature [reading=" + reading + ", result=" + result + "]";
  }
  public String getResult() {
    return result;
  }
  public String getVitalName() {
    return vitalName;
  }


  public void setReading(double reading) {
    this.reading = reading;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void setVitalName(String vitalName) {
    this.vitalName = vitalName;
  }



}