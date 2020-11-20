/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.dal;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.dto.PatientDTO;

@Transactional
@Repository
public class JpaPatientDAO implements PatientDAO {

  @PersistenceContext
  EntityManager em;

  @Override
  public Patient save(PatientDTO patient, int bedId) {
    final Patient pat=patient.changeDTOToEntity(patient);
    em.persist(pat);
    em.createQuery("update Bed set isAvailable=false where bedId=:bedParam")
    .setParameter("bedParam", bedId).executeUpdate();
    return pat;
  }

  @Override
  public List<Patient> findAll() {
    return em.createQuery("select p from Patient p").getResultList();
  }

  @Override
  public Patient findById(int id) {
    return em.find(Patient.class, id);
  }

  @Override
  public void deletebyId(int id) {
    final Patient patient = findById(id);
    final Bed bed = patient.getBed();
    bed.setPatient(null);
    bed.setisAvailable();

    em.createQuery("update Bed set isAvailable=true where bedId=:bedParam")
    .setParameter("bedParam", bed.getBedId()).executeUpdate();
    em.createQuery("delete from Patient p where p.id = :paramId").setParameter("paramId", id).executeUpdate();

  }

}