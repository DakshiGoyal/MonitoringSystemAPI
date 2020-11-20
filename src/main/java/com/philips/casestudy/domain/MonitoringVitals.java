/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.domain;

public class MonitoringVitals {
  PulseRate pulseRate;
  Spo2 spo2;
  Temperature temperature;

  public MonitoringVitals() {
  }

  public PulseRate getPulseRate() {
    return pulseRate;
  }

  public void setPulseRate(PulseRate pulseRate) {
    this.pulseRate = pulseRate;
  }

  public Spo2 getSpo2() {
    return spo2;
  }

  public void setSpo2(Spo2 spo2) {
    this.spo2 = spo2;
  }

  public Temperature getTemperature() {
    return temperature;
  }

  public void setTemperature(Temperature temperature) {
    this.temperature = temperature;
  }

  public MonitoringVitals(PulseRate pulseRate, Spo2 spo2, Temperature temperature) {
    super();
    this.pulseRate = pulseRate;
    this.spo2 = spo2;
    this.temperature = temperature;
  }



}
