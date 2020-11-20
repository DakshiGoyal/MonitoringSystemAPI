/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.modelmapper.ModelMapper;
import com.philips.casestudy.dto.BedDTO;

@Entity
public class Bed {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int bedId;

  @Column(name = "availability")
  boolean isAvailable;


  @OneToOne(mappedBy = "bed")
  Patient patient;

  public Bed() {
  }

  public Bed(BedDTO bed)
  {
    this(bed.getisAvailable());
  }

  public Bed( boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public int getBedId() {
    return bedId;
  }

  public void setBedId(int bedId) {
    this.bedId = bedId;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
    setisAvailable();
  }

  public boolean getisAvailable() {
    return isAvailable;
  }


  public void setisAvailable() {
    this.isAvailable = !patientIsPresent();
  }

  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  @Override
  public String toString() {
    return "Bed [bedId=" + bedId + ", isAvailable=" + isAvailable + ", patient=" + patient + "]";
  }

  public boolean patientIsPresent()
  {
    return (this.getPatient() == null);
  }

  public BedDTO changeEntityToDto(Bed bed)
  {
    final ModelMapper model=new ModelMapper();
    final BedDTO beddto= model.map(bed,BedDTO.class);
    beddto.setAvailable(bed.isAvailable);
    return beddto;
  }
}
