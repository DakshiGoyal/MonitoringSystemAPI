/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.service;

import com.philips.casestudy.domain.MonitoringVitals;

public interface VitalServiceRandom {
  MonitoringVitals initialiseVitals();
  int generateRandomIntegerForVitals(int minValue, int maxValue);
  double generateRandomDoubleForVitals(double minValue, double maxValue);
  MonitoringVitals generateAlertingStream(MonitoringVitals vitals);
}