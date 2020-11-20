/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.service;

import java.util.List;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.dto.PatientDTO;

public interface PatientService{
  int addNewPatient(PatientDTO patient, int bedId);
  List<Patient> getAllPatients();
  Patient getPatient(int patientId);
  void dischargePatient(int patientId);
}