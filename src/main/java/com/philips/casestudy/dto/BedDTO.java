/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.dto;

import org.modelmapper.ModelMapper;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;

public class BedDTO {

  int bedId;
  boolean isAvailable;
  Patient patient;


  public BedDTO() {
    super();
  }

  public BedDTO(Bed bed)
  {
    this(bed.getisAvailable());
  }

  public BedDTO(boolean isAvaliable)
  {
    this.isAvailable = isAvaliable;
  }
  public int getBedId() {
    return bedId;
  }
  public void setBedId(int bedId) {
    this.bedId = bedId;
  }
  public boolean isAvailable() {
    return this.isAvailable;
  }
  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }
  public Patient getPatient() {
    return patient;
  }
  public void setPatient(Patient patient) {
    this.patient = patient;
    System.out.println("Assigning the patient to bed");
    this.isAvailable=false;
  }

  public boolean getisAvailable() {
    return isAvailable;
  }


  public void setisAvailable() {
    this.isAvailable = patientIsPresent();
  }

  @Override
  public String toString() {
    return "Bed [bedId=" + bedId + ", isAvailable=" + isAvailable + ", patient=" + patient + "]";
  }

  public boolean patientIsPresent()
  {
    System.out.println("This,getPatient for the bed" + this.getPatient());
    return (this.getPatient() == null);
  }

  public Bed changeDTOToEntity(BedDTO bedDTO)
  {
    final ModelMapper model=new ModelMapper();
    final Bed bed=model.map(bedDTO,Bed.class);
    bed.setAvailable(bedDTO.isAvailable);
    return bed;

  }
}
