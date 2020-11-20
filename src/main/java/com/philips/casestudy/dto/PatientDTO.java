/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.dto;

import org.modelmapper.ModelMapper;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;

public class PatientDTO {

  int patientId;
  String patientName;
  int patientAge;
  String patientPhoneNumber;
  Bed bedAllocate;


  public PatientDTO() {
    super();

  }

  public PatientDTO(Patient pat)
  {
    this(pat.getName(),pat.getAge(),pat.getPhoneNumber());
  }

  public PatientDTO(String name,int age,String phoneNumber)
  {
    this.patientAge=age;
    this.patientName=name;
    this.patientPhoneNumber=phoneNumber;
  }
  public int getId() {
    return patientId;
  }
  public void setId(int id) {
    this.patientId = id;
  }
  public String getName() {
    return patientName;
  }
  public void setName(String name) {
    this.patientName = name;
  }
  public int getAge() {
    return patientAge;
  }
  public void setAge(int age) {
    this.patientAge = age;
  }
  public String getPhoneNumber() {
    return patientPhoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.patientPhoneNumber = phoneNumber;
  }
  public Bed getBed() {
    return bedAllocate;
  }
  public void setBed(Bed bed) {
    this.bedAllocate = bed;
  }

  public Patient changeDTOToEntity(PatientDTO pat)
  {
    final ModelMapper model=new ModelMapper();
    return model.map(pat,Patient.class);

  }

}
