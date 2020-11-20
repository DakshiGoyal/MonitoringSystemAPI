/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.service;

import java.util.List;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.dto.BedDTO;

public interface BedService {

  int addNewBed(BedDTO bed);
  List<Bed> getAllBeds();
  Bed findBed(int bedId);
  void deleteExistingBed(int bedId);
  boolean changeBedAvailability(int bedId);
}