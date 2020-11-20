/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.casestudy.web;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mockito.Mockito;
import com.philips.casestudy.domain.MonitoringVitals;
import com.philips.casestudy.domain.PulseRate;
import com.philips.casestudy.domain.Spo2;
import com.philips.casestudy.domain.Temperature;
import com.philips.casestudy.service.VitalServiceRandomImpl;
import com.philips.casestudy.web.VitalController;

public class VitalControllerTest {

  @Test
  public void getVitalsGeneratingVital()
  {
    final VitalController vital=new VitalController();

    final VitalServiceRandomImpl service=Mockito.mock(VitalServiceRandomImpl.class);

    vital.setVitalService(service);

    final PulseRate pulseRate=new PulseRate(72);
    pulseRate.setVitalName("Spo2");
    pulseRate.setResult("Normal");


    final Spo2 spo2=new Spo2(72);
    spo2.setVitalName("Spo2");
    spo2.setResult("Normal");


    final Temperature temp=new Temperature(72);
    temp.setVitalName("Temperature");
    temp.setResult("Normal");

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temp);

    Mockito.when(service.initialiseVitals()).thenReturn(vitals);

    final MonitoringVitals obtainedVital=vital.getVitals(1);

    assertEquals(vitals,obtainedVital);





  }

}
