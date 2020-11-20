/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.domain;

import java.util.Map;
import java.util.TreeMap;

public class PulseRate {

  int reading;
  String result;

  static Map<Double, String> pulseMonitorStatus= new TreeMap<>();


  String vitalName = "PulseRate";



  public static Map<Double, String> getPulseMonitorStatus() {
    return pulseMonitorStatus;
  }

  public static void setPulseMonitorStatus(Map<Double, String> pulseMonitorStatus) {
    PulseRate.pulseMonitorStatus = pulseMonitorStatus;
  }



  public PulseRate(int reading) {
    this.reading = reading;
    this.result=null;
    pulseMonitorStatus.put((double)28,"undetectably low reading");
    pulseMonitorStatus.put((double)40,"low reading - Care needed");
    pulseMonitorStatus.put((double)220,"Normal");
    pulseMonitorStatus.put((double)254,"High reading - Care needed");
    pulseMonitorStatus.put((double)257,"Device not calibrated to measure such high values");



  }

  public PulseRate() {

  }

  public double getReading() {
    return reading;
  }

  @Override
  public String toString() {
    return "PulseRate [reading=" + reading + ", result=" + result + "]";
  }

  public void setReading(int reading) {
    this.reading = reading;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void setVitalName(String vitalName) {
    this.vitalName = vitalName;
  }

  public String getResult() {
    return result;
  }

  public String getVitalName() {
    return vitalName;
  }

}