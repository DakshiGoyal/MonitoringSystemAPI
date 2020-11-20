/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.casestudy.web;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.dto.BedDTO;
import com.philips.casestudy.service.BedService;
import com.philips.casestudy.web.BedController;

public class BedControllerTest {

  @Test
  public void addBedIfAvaliable() throws Exception {
    final BedController bedcontroller = new BedController();
    final BedService bedService = Mockito.mock(BedService.class);
    bedcontroller.setBedService(bedService);

    final BedDTO bed = new BedDTO(true);
    bed.setBedId(1);

    final List<Bed> beds = new ArrayList<>();

    Mockito.when(bedcontroller.getAllBeds()).thenReturn(beds);
    Mockito.when(bedService.addNewBed(bed)).thenReturn(1);

    final ResponseEntity<Bed> response = bedcontroller.addBed();

    assertEquals(HttpStatus.CREATED,response.getStatusCode());

  }

  @Test
  public void addBedBeyondCapacityOfBeds() throws Exception {

    final BedController bedcontroller = new BedController();
    final BedService bedService = Mockito.mock(BedService.class);
    bedcontroller.setBedService(bedService);

    final List<Bed> beds = new ArrayList<>();
    for(int i=0;i<10;i++){
      beds.add(new Bed(true));
    }

    final Bed bed = new Bed(true);
    bed.setBedId(11);

    Mockito.when(bedcontroller.getAllBeds()).thenReturn(beds);

    final ResponseEntity<Bed> response = bedcontroller.addBed();

    assertEquals( HttpStatus.BAD_REQUEST,response.getStatusCode());
  }



  @Test
  public void getBedByID_ValidID() throws Exception {

    final BedController bedcontroller = new BedController();
    final BedService bedService = Mockito.mock(BedService.class);
    bedcontroller.setBedService(bedService);

    final Bed bed = new Bed(true);
    bed.setBedId(1);

    Mockito.when(bedService.findBed(1)).thenReturn(bed);

    final ResponseEntity<Bed> response = bedcontroller.getBedById(1);

    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertEquals(response.getBody(), bed);

  }

  @Test
  public void getBedById_InvalidID() throws Exception {
    final BedController bedController = new BedController();
    final BedService bedService = Mockito.mock(BedService.class);
    bedController.setBedService(bedService);

    final Bed bed = new Bed(true);
    bed.setBedId(1);

    Mockito.when(bedService.findBed(1)).thenReturn(bed);
    final Bed actual = null;

    final ResponseEntity<Bed> response = bedController.getBedById(11);

    assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    assertEquals(null, actual);

  }

  @Test
  public void getAllBeds_ContainingBeds() throws Exception {
    final BedController bedController = new BedController();
    final BedService bedService = Mockito.mock(BedService.class);
    bedController.setBedService(bedService);

    final Bed bed = new Bed(true);
    bed.setBedId(1);

    final List<Bed> bedList = new ArrayList<>();
    bedList.add(bed);

    Mockito.when(bedService.getAllBeds()).thenReturn(bedList);

    final List<Bed> responseList = bedController.getAllBeds();

    assertEquals(responseList ,bedList);

  }

  @Test
  public void getAllBeds_EmptyList() throws Exception {
    final BedController bedController = new BedController();
    final BedService bedService = Mockito.mock(BedService.class);
    bedController.setBedService(bedService);

    final List<Bed> bedList = new ArrayList<>();
    Mockito.when(bedService.getAllBeds()).thenReturn(bedList);

    final List<Bed> responseList = bedController.getAllBeds();

    assertEquals(responseList,bedList);

  }

  @Test
  public void changeBedAvailabilityWhenSuccesfullyChanged()
  {
    final BedController bedController=new BedController();
    final BedService bedService=Mockito.mock(BedService.class);

    bedController.setBedService(bedService);
    Mockito.when(bedService.changeBedAvailability(Mockito.anyInt())).thenReturn(true);

    final ResponseEntity<String> response=bedController.changeBedAvailability(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void changeBedAvailabilityWhenNotChanged()
  {
    final BedController bedController=new BedController();
    final BedService bedService=Mockito.mock(BedService.class);

    bedController.setBedService(bedService);
    Mockito.when(bedService.changeBedAvailability(Mockito.anyInt())).thenReturn(false);

    final ResponseEntity<String> response=bedController.changeBedAvailability(1);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

}