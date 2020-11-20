/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.dal;

import com.philips.casestudy.domain.MonitoringVitals;

public interface MonitoringVitalsDAO  {

  public MonitoringVitals setVitalStatus(MonitoringVitals vitals);

  public String checkRange(double currentReading);

}