/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.casestudy.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import com.philips.casestudy.dal.MonitoringVitalsDAO;
import com.philips.casestudy.dal.PulseRateDAO;
import com.philips.casestudy.domain.MonitoringVitals;
import com.philips.casestudy.domain.PulseRate;
import com.philips.casestudy.domain.Spo2;
import com.philips.casestudy.domain.Temperature;

public class PulseRateDAOTest {


  @Test
  public void pulseRateVitalResultSetAsUndetectablelowReading()
  {

    final MonitoringVitalsDAO dao=new PulseRateDAO();
    final PulseRate pulseRate=new PulseRate(27);
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature();
    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);
    assertEquals("undetectably low reading",dao.setVitalStatus(vitals).getPulseRate().getResult());

  }

  @Test
  public void pulseRateVitalResultSetAslowreadingwithcareNeeded()
  {

    final MonitoringVitalsDAO dao=new PulseRateDAO();

    final PulseRate pulseRate=new PulseRate(39);
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature();


    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertEquals("low reading - Care needed",dao.setVitalStatus(vitals).getPulseRate().getResult());

  }


  @Test
  public void pulseRateVitalResultSetAsNormal()
  {

    final MonitoringVitalsDAO dao=new PulseRateDAO();

    final PulseRate pulseRate=new PulseRate(59);
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature();

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertEquals("Normal",dao.setVitalStatus(vitals).getPulseRate().getResult());

  }


  @Test
  public void pulseRateVitalResultSetAsHighreadingCareneeded()
  {

    final MonitoringVitalsDAO dao=new PulseRateDAO();

    final PulseRate pulseRate=new PulseRate(253);
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature();

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertEquals("High reading - Care needed",dao.setVitalStatus(vitals).getPulseRate().getResult());

  }


  @Test
  public void pulseRateVitalResultSetAsDeviceNotAbleToMeasure()
  {

    final MonitoringVitalsDAO dao=new PulseRateDAO();

    final PulseRate pulseRate=new PulseRate(257);
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature();

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertEquals("Device not calibrated to measure such high values", dao.setVitalStatus(vitals).getPulseRate().getResult());

  }


  @Test
  public void pulseRateVitalResultSetAsNull()
  {

    final MonitoringVitalsDAO dao=new PulseRateDAO();

    final PulseRate pulseRate=new PulseRate(258);
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature();

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertNull(dao.setVitalStatus(vitals).getPulseRate().getResult());

  }

}
