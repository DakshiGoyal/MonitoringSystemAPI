/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.service;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.casestudy.dal.PulseRateDAO;
import com.philips.casestudy.dal.Spo2DAO;
import com.philips.casestudy.dal.TemperatureDAO;
import com.philips.casestudy.domain.MonitoringVitals;
import com.philips.casestudy.domain.PulseRate;
import com.philips.casestudy.domain.Spo2;
import com.philips.casestudy.domain.Temperature;

@Service
public class VitalServiceRandomImpl implements VitalServiceRandom {


  Spo2DAO spo2Dao;


  TemperatureDAO tempDAO;


  PulseRateDAO pulserateDAO;



  @Autowired
  public void setSpo2Dao(Spo2DAO spo2Dao) {
    this.spo2Dao = spo2Dao;
  }

  @Autowired
  public void setTempDAO(TemperatureDAO tempDAO) {
    this.tempDAO = tempDAO;
  }

  @Autowired
  public void setPulserateDAO(PulseRateDAO pulserateDAO) {
    this.pulserateDAO = pulserateDAO;
  }

  @Override
  public MonitoringVitals initialiseVitals() {
    final PulseRate pulseRate = new PulseRate(generateRandomIntegerForVitals(28, 257));
    final Spo2 spo2 = new Spo2(generateRandomDoubleForVitals(65, 100));
    final Temperature temperature = new Temperature(generateRandomDoubleForVitals(92, 109));

    final MonitoringVitals vitals=new MonitoringVitals(pulseRate,spo2,temperature);
    return  generateAlertingStream(vitals);

  }

  @Override
  public int generateRandomIntegerForVitals(int minValue, int maxValue) {
    return ThreadLocalRandom.current().nextInt(minValue, maxValue);
  }



  @Override
  public double generateRandomDoubleForVitals(double minValue, double maxValue) {
    return ThreadLocalRandom.current().nextDouble(minValue, maxValue);
  }


  @Override
  public MonitoringVitals generateAlertingStream(MonitoringVitals vital) {

    vital=pulserateDAO.setVitalStatus(vital);
    vital=spo2Dao.setVitalStatus(vital);
    vital=tempDAO.setVitalStatus(vital);

    return vital;
  }



}