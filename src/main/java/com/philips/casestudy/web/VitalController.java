/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy.domain.MonitoringVitals;
import com.philips.casestudy.service.PatientService;
import com.philips.casestudy.service.VitalServiceRandomImpl;

@RestController
public class VitalController{


  VitalServiceRandomImpl vitalService;

  @Autowired
  public void setVitalService(VitalServiceRandomImpl vitalService) {
    this.vitalService = vitalService;
  }



  @Autowired
  PatientService patientService;



  @CrossOrigin
  @GetMapping(value = "/monitor/{id}")
  public MonitoringVitals getVitals(@PathVariable("id") int bedId){

    return vitalService.initialiseVitals();

  }
}