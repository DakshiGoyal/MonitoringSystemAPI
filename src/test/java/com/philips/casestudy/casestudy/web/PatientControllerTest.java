/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.casestudy.web;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.dto.PatientDTO;
import com.philips.casestudy.service.PatientService;
import com.philips.casestudy.web.PatientController;

public class PatientControllerTest {

  @Test
  public void addPatient_Valid() throws Exception {

    final PatientController patientcontroller = new PatientController();
    final PatientService patientService = Mockito.mock(PatientService.class);
    patientcontroller.setPatientService(patientService);

    final Bed bed = new Bed(true);
    bed.setBedId(10);

    final PatientDTO patient = new PatientDTO("Aman Gill", 35,"9897969592");
    patient.setId(1);
    patient.setBed(bed);

    Mockito.when(patientService.addNewPatient(patient, 10)).thenReturn(1);

    final ResponseEntity<Patient> response = patientcontroller.addPatient(patient);

    assertEquals(HttpStatus.CREATED,response.getStatusCode());

  }

  @Test
  public void test_getPatientByID_validID() throws Exception {

    final PatientController patientcontroller = new PatientController();
    final PatientService patientService = Mockito.mock(PatientService.class);
    patientcontroller.setPatientService(patientService);

    final Patient patient = new Patient("Aman Gill", 35,"9897969592");
    patient.setId(1);

    Mockito.when(patientService.getPatient(1)).thenReturn(patient);

    final ResponseEntity<Patient> response = patientcontroller.getPatientById(1);

    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertEquals(response.getBody(), patient);

  }

  @Test
  public void getPatientById_invalidID() throws Exception {
    final PatientController patientcontroller = new PatientController();
    final PatientService patientService = Mockito.mock(PatientService.class);
    patientcontroller.setPatientService(patientService);

    final Patient patient = new Patient("Aman Gill", 35,"9897969592");
    patient.setId(1);

    Mockito.when(patientService.getPatient(1)).thenReturn(patient);
    final Patient actual = null;

    final ResponseEntity<Patient> response = patientcontroller.getPatientById(11);

    assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    assertEquals(null, actual);

  }

  @Test
  public void getAllPatients_ContainingBeds() throws Exception {
    final PatientController patientcontroller = new PatientController();
    final PatientService patientService = Mockito.mock(PatientService.class);
    patientcontroller.setPatientService(patientService);

    final Patient patient = new Patient("Aman Gill", 35,"9897969592");
    patient.setId(1);

    final List<Patient> patientList = new ArrayList<>();
    patientList.add(patient);

    Mockito.when(patientService.getAllPatients()).thenReturn(patientList);

    final List<Patient> responseList = patientcontroller.getAllPatients();

    assertEquals(responseList, patientList);

  }

  @Test
  public void getAllPatients_EmptyList() throws Exception {

    final PatientController patientcontroller = new PatientController();
    final PatientService patientService = Mockito.mock(PatientService.class);
    patientcontroller.setPatientService(patientService);

    final List<Patient> patientList = new ArrayList<>();
    Mockito.when(patientService.getAllPatients()).thenReturn(patientList);

    final List<Patient> responseList = patientcontroller.getAllPatients();
    assertEquals(responseList, patientList);
  }

  @Test
  public void dischargePatient_ValidId() throws Exception {
    final PatientController patientcontroller = new PatientController();
    final PatientService patientService = Mockito.mock(PatientService.class);
    patientcontroller.setPatientService(patientService);

    final Patient patient = new Patient("Aman Gill", 35,"9897969592");
    patient.setId(1);

    Mockito.when(patientService.getPatient(1)).thenReturn(patient);

    final ResponseEntity<Patient> response = patientcontroller.dischargePatient(1);

    assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());

    assertEquals(null,response.getBody());


  }

  @Test
  public void dischargePatient_InvalidId() throws Exception {
    final PatientController patientcontroller = new PatientController();
    final PatientService patientService = Mockito.mock(PatientService.class);
    patientcontroller.setPatientService(patientService);

    final Patient patient = new Patient("Aman Gill", 35,"9897969592");
    patient.setId(1);

    Mockito.when(patientService.getPatient(1)).thenReturn(patient);

    final ResponseEntity<Patient> response = patientcontroller.dischargePatient(2);

    assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    assertEquals(null,response.getBody());

  }

}