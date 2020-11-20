/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.web;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.dto.BedDTO;
import com.philips.casestudy.service.BedService;
@RestController
public class BedController {

  BedService bedService;

  @Autowired
  public void setBedService(BedService bedService) {
    this.bedService = bedService;
  }

  int bedLimit=10;
  @PostMapping(value = "/beds")
  public ResponseEntity<Bed> addBed(){

    final BedDTO bed = new BedDTO(true);
    final int size = getAllBeds().size();
    if(size==bedLimit) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    try{
      final int id = bedService.addNewBed(bed);
      final HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/beds/"+id));
      return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    catch(final Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @CrossOrigin
  @GetMapping(value = "/beds")
  public List<Bed> getAllBeds()
  {
    return bedService.getAllBeds();
  }

  @GetMapping(value = "/beds/{bid}")
  public ResponseEntity<Bed> getBedById(@PathVariable("bid")int id){

    final Bed bed = bedService.findBed(id);

    if(bed!=null){
      return new ResponseEntity<>(bed, HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin
  @GetMapping(value = "/bedStatus/{bid}")
  public ResponseEntity<String> changeBedAvailability(@PathVariable("bid") int bedId){

    final boolean changedSuccessfully=bedService.changeBedAvailability(bedId);

    if(changedSuccessfully)
    {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    else
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}