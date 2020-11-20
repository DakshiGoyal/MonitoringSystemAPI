/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.dto;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.philips.casestudy.domain.Bed;

public class BedDTOTest {


  @Test
  public void BedDtoChangeToBedEntity()
  {
    final BedDTO bedDto=new BedDTO(true);
    final Bed bed=bedDto.changeDTOToEntity(bedDto);
    assertEquals(bed.getisAvailable(),bedDto.getisAvailable());
  }

}
