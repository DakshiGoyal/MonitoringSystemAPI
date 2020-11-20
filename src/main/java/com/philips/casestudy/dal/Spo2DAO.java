/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.dal;

import org.springframework.stereotype.Repository;
import com.philips.casestudy.domain.MonitoringVitals;
import com.philips.casestudy.domain.Spo2;

@Repository
public class Spo2DAO implements MonitoringVitalsDAO{

  @Override
  public MonitoringVitals setVitalStatus(MonitoringVitals vitals) {

    vitals.getSpo2().setResult(checkRange(vitals.getSpo2().getReading()));


    return vitals;
  }


  @Override
  public String checkRange(double currentpulseReading)
  {
    for(final Double checkPointReading:Spo2.getSpo2MonitorStatus().keySet())
    {
      if(currentpulseReading<=checkPointReading)
      {
        return Spo2.getSpo2MonitorStatus().get(checkPointReading);
      }
    }
    return null;
  }
}
