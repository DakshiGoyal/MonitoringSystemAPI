/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.web;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.dto.PatientDTO;
import com.philips.casestudy.service.PatientService;

@RestController
public class PatientController{

  PatientService patientService;

  @Autowired
  public void setPatientService(PatientService patientService) {
    this.patientService = patientService;
  }

  @PostMapping(value = "/patients")
  public ResponseEntity<Patient> addPatient(@RequestBody PatientDTO patient){

    final Bed b = patient.getBed();
    final int bedId = b.getBedId();

    try{
      final int id = patientService.addNewPatient(patient,bedId);
      final HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/patients/"+id));
      return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    catch (final Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/patients")
  public List<Patient> getAllPatients(){
    return patientService.getAllPatients();
  }

  @GetMapping(value = "/patients/{pid}")
  public ResponseEntity<Patient> getPatientById(@PathVariable("pid")int id){

    final Patient patient = patientService.getPatient(id);

    if(patient!=null){
      return new ResponseEntity<>(patient, HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(value = "/patients/{pid}")
  public ResponseEntity<Patient> dischargePatient(@PathVariable("pid") int id){

    final Patient patient = patientService.getPatient(id);
    if(patient!=null){
      patientService.dischargePatient(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    else{
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}