/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.service;

import java.util.List;
import org.dom4j.IllegalAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.casestudy.dal.BedDAO;
import com.philips.casestudy.dal.PatientDAO;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.dto.BedDTO;
import com.philips.casestudy.dto.PatientDTO;

@Service
public class PatientServiceImpl implements PatientService {


  PatientDAO patientdao;

  BedDAO beddao;



  @Autowired
  public void setPatientdao(PatientDAO patientdao) {
    this.patientdao = patientdao;
  }

  @Autowired
  public void setBeddao(BedDAO beddao) {
    this.beddao = beddao;
  }

  @Override
  public int addNewPatient(PatientDTO patient, int bedId) {
    final Bed bed = (beddao.findById(bedId));
    final BedDTO bedDto=bed.changeEntityToDto(bed);

    if(bedDto.isAvailable()){
      patient.setBed(bed);
      bedDto.setPatient(patient.changeDTOToEntity(patient));
      final Patient savedPatient = patientdao.save(patient, bedId);
      return savedPatient.getId();
    }
    else {
      throw new IllegalAddException("The bed is not empty to be assigned!");
    }
  }

  @Override
  public List<Patient> getAllPatients() {
    return patientdao.findAll();
  }

  @Override
  public Patient getPatient(int patientId) {
    return patientdao.findById(patientId);
  }

  @Override
  public void dischargePatient(int patientId) {
    patientdao.deletebyId(patientId);
  }

}