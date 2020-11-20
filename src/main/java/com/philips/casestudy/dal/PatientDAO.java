/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.dal;

import java.util.List;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.dto.PatientDTO;

public interface PatientDAO{
  Patient save(PatientDTO patient,int bedId);
  List<Patient> findAll();
  Patient findById(int id);
  void deletebyId(int id);
}