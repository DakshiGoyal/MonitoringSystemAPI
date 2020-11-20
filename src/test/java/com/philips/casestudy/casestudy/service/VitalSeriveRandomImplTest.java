/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.casestudy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.mockito.Mockito;
import com.philips.casestudy.dal.PulseRateDAO;
import com.philips.casestudy.dal.Spo2DAO;
import com.philips.casestudy.dal.TemperatureDAO;
import com.philips.casestudy.domain.MonitoringVitals;
import com.philips.casestudy.domain.PulseRate;
import com.philips.casestudy.domain.Spo2;
import com.philips.casestudy.domain.Temperature;
import com.philips.casestudy.service.VitalServiceRandomImpl;

public class VitalSeriveRandomImplTest {

  @Test
  public void generateAlertingStreamTest()
  {
    final PulseRate pulseRate=new PulseRate(60);
    final Spo2 spo2=new Spo2(90);
    final Temperature temperature=new Temperature(98);

    final MonitoringVitals vital=new MonitoringVitals(pulseRate,spo2,temperature);

    final PulseRateDAO pulseRateDummy=Mockito.mock(PulseRateDAO.class);
    final Spo2DAO spo2Dummy=Mockito.mock(Spo2DAO.class);
    final TemperatureDAO temperatureDummy=Mockito.mock(TemperatureDAO.class);

    final VitalServiceRandomImpl service=new VitalServiceRandomImpl();
    service.setPulserateDAO(pulseRateDummy);
    service.setSpo2Dao(spo2Dummy);
    service.setTempDAO(temperatureDummy);

    vital.getPulseRate().setResult("Normal");
    vital.getSpo2().setResult("Normal");
    vital.getTemperature().setResult("Normal");

    Mockito.when(pulseRateDummy.setVitalStatus(Mockito.any(MonitoringVitals.class))).thenReturn(vital);
    Mockito.when(spo2Dummy.setVitalStatus(Mockito.any(MonitoringVitals.class))).thenReturn(vital);
    Mockito.when(temperatureDummy.setVitalStatus(Mockito.any(MonitoringVitals.class))).thenReturn(vital);

    System.out.println(vital.getPulseRate().toString());

    final MonitoringVitals generatedVital=service.generateAlertingStream(vital);
    assertEquals(generatedVital, vital);

  }

  @Test
  public void generateRandomIntegerForVitalsIsSuccessful()
  {
    final VitalServiceRandomImpl service=new VitalServiceRandomImpl();

    final VitalServiceRandomImpl serviceDummy=Mockito.mock(VitalServiceRandomImpl.class);

    Mockito.when(serviceDummy.generateRandomIntegerForVitals(Mockito.anyInt(), Mockito.anyInt())).thenReturn(76);

    final int reading=service.generateRandomIntegerForVitals(74, 86);

    assertNotNull(reading);


  }

  @Test
  public void generateRandomDoubleForVitalsIsSuccessful()
  {
    final VitalServiceRandomImpl service=new VitalServiceRandomImpl();

    final VitalServiceRandomImpl serviceDummy=Mockito.mock(VitalServiceRandomImpl.class);

    Mockito.when(serviceDummy.generateRandomDoubleForVitals(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(76.00);

    final double reading=service.generateRandomDoubleForVitals(74.00, 86.00);

    assertNotNull(reading);

  }

}
