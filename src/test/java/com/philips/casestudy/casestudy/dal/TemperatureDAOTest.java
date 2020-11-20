/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.casestudy.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import com.philips.casestudy.dal.MonitoringVitalsDAO;
import com.philips.casestudy.dal.PulseRateDAO;
import com.philips.casestudy.dal.TemperatureDAO;
import com.philips.casestudy.domain.MonitoringVitals;
import com.philips.casestudy.domain.PulseRate;
import com.philips.casestudy.domain.Spo2;
import com.philips.casestudy.domain.Temperature;

public class TemperatureDAOTest {


  @Test
  public void temperaturevitalResultSetUndetectablelowReading()
  {

    final MonitoringVitalsDAO dao=new TemperatureDAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature(29);

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertEquals("undetectably low reading",dao.setVitalStatus(vitals).getTemperature().getResult());

  }


  @Test
  public void temperaturevitalResultSetlowreadingwithcareNeeded()
  {

    final MonitoringVitalsDAO dao=new TemperatureDAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature(93);

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);


    assertEquals("low reading - Care needed",dao.setVitalStatus(vitals).getTemperature().getResult());

  }


  @Test
  public void temperaturevitalResultSetNormal()
  {

    final MonitoringVitalsDAO dao=new TemperatureDAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature(98);

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertEquals("Normal",dao.setVitalStatus(vitals).getTemperature().getResult());

  }



  @Test
  public void temperaturevitalResultSetHighReadingCareNeeded()
  {

    final MonitoringVitalsDAO dao=new TemperatureDAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature(100);


    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertEquals("High reading - Care needed",dao.setVitalStatus(vitals).getTemperature().getResult());

  }

  @Test
  public void temperaturevitalResultSetHighReadingExtremeCareNeeded()
  {

    final MonitoringVitalsDAO dao=new TemperatureDAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature(103);

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertEquals("High reading - Extreme Care needed",dao.setVitalStatus(vitals).getTemperature().getResult());

  }


  @Test
  public void temperaturevitalResultSetHighReadingCriticalCareNeeded()
  {

    final MonitoringVitalsDAO dao=new TemperatureDAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature(107);

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertEquals("High reading - Critical Care needed",dao.setVitalStatus(vitals).getTemperature().getResult());

  }

  @Test
  public void temperaturevitalResultSetDeviceNotAbleToMeasure()
  {

    final MonitoringVitalsDAO dao=new TemperatureDAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature(109);

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertEquals("Device Not Calibrated to measure such high values",dao.setVitalStatus(vitals).getTemperature().getResult());

  }


  @Test
  public void temperaturevitalResultSetNull()
  {

    final MonitoringVitalsDAO dao=new PulseRateDAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2();
    final Temperature temperature=new Temperature(110);

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertNull(dao.setVitalStatus(vitals).getTemperature().getResult());

  }


}
