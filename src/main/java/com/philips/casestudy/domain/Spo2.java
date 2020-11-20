/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.domain;

import java.util.Map;
import java.util.TreeMap;

public class Spo2{

  String result;
  private double reading;
  static Map<Double, String> spo2MonitorStatus= new TreeMap<>();
  String vitalName = "Spo2";

  public Spo2() {
  }

  public Spo2(double reading) {
    this.reading = reading;
    this.result = null;

    spo2MonitorStatus.put((double)65,"undetectably low reading");
    spo2MonitorStatus.put((double)70,"low reading - Care Needed");
    spo2MonitorStatus.put((double)90,"Normal");
    spo2MonitorStatus.put((double)100,"Device Not Calibrated to measure such high values");

  }


  public double getReading() {
    return reading;
  }

  @Override
  public String toString() {
    return "Spo2 [reading=" + reading + ", result=" + result + "]";
  }

  public String getResult() {
    return result;
  }

  public String getVitalName() {
    return vitalName;
  }



  public static Map<Double, String> getSpo2MonitorStatus() {
    return spo2MonitorStatus;
  }

  public static void setSpo2MonitorStatus(Map<Double, String> spo2MonitorStatus) {
    Spo2.spo2MonitorStatus = spo2MonitorStatus;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void setReading(double reading) {
    this.reading = reading;
  }

  public void setVitalName(String vitalName) {
    this.vitalName = vitalName;
  }



}