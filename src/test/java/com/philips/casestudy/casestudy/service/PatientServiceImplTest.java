/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.casestudy.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mockito.Mockito;
import com.philips.casestudy.dal.JpaBedDAO;
import com.philips.casestudy.dal.JpaPatientDAO;
import com.philips.casestudy.dal.PatientDAO;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.dto.BedDTO;
import com.philips.casestudy.dto.PatientDTO;
import com.philips.casestudy.service.PatientServiceImpl;

public class PatientServiceImplTest {



  @Test
  public void addPatientIfBedIsAvailable()
  {

    final PatientServiceImpl service=new PatientServiceImpl();

    final PatientDAO mockPatientDao=Mockito.mock(JpaPatientDAO.class);
    final JpaBedDAO mockBedDao=Mockito.mock(JpaBedDAO.class);

    service.setBeddao(mockBedDao);
    service.setPatientdao(mockPatientDao);

    final PatientDTO patientToBeAddedDTO=Mockito.mock(PatientDTO.class);
    patientToBeAddedDTO.setName("Anil");
    patientToBeAddedDTO.setAge(42);
    patientToBeAddedDTO.setPhoneNumber("8980704657");
    final Patient patientToBeAdded=new Patient("Anil",42,"8980704657");
    final Patient savedPatient=new Patient("Anil",42,"8980704657");

    final Bed bedAvailable=Mockito.mock(Bed.class);
    bedAvailable.setAvailable(true);
    bedAvailable.setBedId(2);

    final BedDTO bedtoBeAllocatedDTO=Mockito.mock(BedDTO.class);
    bedtoBeAllocatedDTO.setAvailable(true);

    Mockito.when(mockBedDao.findById(Mockito.anyInt())).thenReturn(bedAvailable);
    Mockito.when(bedAvailable.changeEntityToDto(bedAvailable)).thenReturn(bedtoBeAllocatedDTO);
    Mockito.when(bedtoBeAllocatedDTO.isAvailable()).thenReturn(true);
    Mockito.when(patientToBeAddedDTO.changeDTOToEntity(patientToBeAddedDTO)).thenReturn(patientToBeAdded);

    final PatientDTO patientDTO=new PatientDTO("Anil",42,"8980704657");

    Mockito.when(mockPatientDao.save(patientDTO, bedAvailable.getBedId())).thenReturn(savedPatient);

    final int id=service.addNewPatient(patientDTO, bedAvailable.getBedId());
    assertEquals(0, id);

  }







}
