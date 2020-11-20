/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.casestudy.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import com.philips.casestudy.dal.MonitoringVitalsDAO;
import com.philips.casestudy.dal.PulseRateDAO;
import com.philips.casestudy.dal.Spo2DAO;
import com.philips.casestudy.domain.MonitoringVitals;
import com.philips.casestudy.domain.PulseRate;
import com.philips.casestudy.domain.Spo2;
import com.philips.casestudy.domain.Temperature;

public class Spo2DAOTest {


  @Test
  public void spo2vitalResultSetAsUndetectableLowReading()
  {

    final MonitoringVitalsDAO dao=new Spo2DAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2(63);
    final Temperature temperature=new Temperature();


    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);


    assertEquals("undetectably low reading",dao.setVitalStatus(vitals).getSpo2().getResult());

  }



  @Test
  public void spo2vitalResultSetNormal()
  {

    final MonitoringVitalsDAO dao=new Spo2DAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2(77);
    final Temperature temperature=new Temperature();

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);


    assertEquals("Normal",dao.setVitalStatus(vitals).getSpo2().getResult());

  }



  @Test
  public void spo2vitalResultSetLowReading()
  {

    final MonitoringVitalsDAO dao=new Spo2DAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2(69);
    final Temperature temperature=new Temperature();

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertEquals("low reading - Care Needed",dao.setVitalStatus(vitals).getSpo2().getResult());

  }


  @Test
  public void spo2vitalResultSetDeviceCannotCallibrate()
  {

    final MonitoringVitalsDAO dao=new Spo2DAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2(99);
    final Temperature temperature=new Temperature();

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);
    assertEquals("Device Not Calibrated to measure such high values",dao.setVitalStatus(vitals).getSpo2().getResult());

  }

  @Test
  public void spo2vitalResultSetNull()
  {

    final MonitoringVitalsDAO dao=new PulseRateDAO();

    final PulseRate pulseRate=new PulseRate();
    final Spo2 spo2=new Spo2(101);
    final Temperature temperature=new Temperature();

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);

    assertNull(dao.setVitalStatus(vitals).getSpo2().getResult());

  }


}
