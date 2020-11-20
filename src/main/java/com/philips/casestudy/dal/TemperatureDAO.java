/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.dal;

import org.springframework.stereotype.Repository;
import com.philips.casestudy.domain.MonitoringVitals;
import com.philips.casestudy.domain.Temperature;

@Repository
public class TemperatureDAO implements MonitoringVitalsDAO{

  @Override
  public MonitoringVitals setVitalStatus(MonitoringVitals vitals) {

    vitals.getTemperature().setResult(checkRange(vitals.getTemperature().getReading()));

    return vitals;
  }

  @Override
  public String checkRange(double currentpulseReading)
  {
    for(final Double checkPointReading:Temperature.getTemperatureMonitorStatus().keySet())
    {
      if(currentpulseReading<=checkPointReading)
      {
        return Temperature.getTemperatureMonitorStatus().get(checkPointReading);
      }
    }
    return null;
  }
}
