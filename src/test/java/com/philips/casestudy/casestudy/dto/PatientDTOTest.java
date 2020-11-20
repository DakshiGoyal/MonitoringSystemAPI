/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.casestudy.dto;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.dto.PatientDTO;

public class PatientDTOTest {

  @Test
  public void patientDtoChangeTopatientEntity()
  {
    final PatientDTO patientDto=new PatientDTO("Anil",43,"9999999999");
    final Patient pat=patientDto.changeDTOToEntity(patientDto);
    assertEquals(pat.getName(),patientDto.getName());
  }

}
