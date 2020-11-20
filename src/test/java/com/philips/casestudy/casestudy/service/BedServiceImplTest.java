/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.casestudy.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mockito.Mockito;
import com.philips.casestudy.dal.BedDAO;
import com.philips.casestudy.dal.JpaBedDAO;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.MyException;
import com.philips.casestudy.service.BedServiceImpl;

public class BedServiceImplTest {

  @Test(expected=MyException.class)
  public void deleteBedWhenPatientIsAlreadyAssigned() throws MyException
  {
    final BedServiceImpl service=new BedServiceImpl();
    final Bed toBeDeleted=new Bed(false);
    toBeDeleted.setBedId(1);

    final JpaBedDAO mockDao=Mockito.mock(JpaBedDAO.class);

    service.setBedDao(mockDao);

    Mockito.when(mockDao.findById(Mockito.anyInt())).thenReturn(toBeDeleted);

    service.deleteExistingBed(toBeDeleted.getBedId());

    throw new MyException("Bed can't be deleted");

  }



  @Test
  public void changeBedAvailabilityWhenSuccessfullyChanged()
  {
    final BedServiceImpl bedService=new BedServiceImpl();
    final BedDAO bedDAO=Mockito.mock(BedDAO.class);

    bedService.setBedDao(bedDAO);
    final Bed bed=new Bed();
    bed.setBedId(1);
    bed.setAvailable(true);

    Mockito.when(bedDAO.findById(Mockito.anyInt())).thenReturn(bed);

    assertEquals(true, bedService.changeBedAvailability(bed.getBedId()));
  }

}
