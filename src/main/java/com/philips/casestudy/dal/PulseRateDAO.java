/*

 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.dal;

import org.springframework.stereotype.Repository;
import com.philips.casestudy.domain.MonitoringVitals;
import com.philips.casestudy.domain.PulseRate;

@Repository
public class PulseRateDAO implements MonitoringVitalsDAO{



  @Override
  public MonitoringVitals setVitalStatus(MonitoringVitals vitals) {


    vitals.getPulseRate().setResult(checkRange(vitals.getPulseRate().getReading()));
    return vitals;
  }

  @Override
  public String checkRange(double currentpulseReading)
  {
    for(final Double checkPointReading:PulseRate.getPulseMonitorStatus().keySet())
    {
      if(currentpulseReading<=checkPointReading)
      {
        return PulseRate.getPulseMonitorStatus().get(checkPointReading);
      }
    }
    return null;
  }
}
