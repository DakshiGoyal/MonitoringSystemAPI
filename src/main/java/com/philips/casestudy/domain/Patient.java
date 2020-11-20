/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.philips.casestudy.dto.PatientDTO;

@Entity
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  String name;
  int age;
  String phoneNumber;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name ="bed_id")
  Bed bed;

  public Patient() {
  }
  public Patient(PatientDTO patient) {
    this(patient.getName(),patient.getAge(),patient.getPhoneNumber());
  }
  public Patient(String name, int age, String phoneNumber) {
    this.name = name;
    this.age = age;
    this.phoneNumber = phoneNumber;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Bed getBed() {
    return bed;
  }

  public void setBed(Bed bed) {
    this.bed = bed;
  }
}
