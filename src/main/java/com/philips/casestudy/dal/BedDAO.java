/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.dal;

import java.util.List;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.dto.BedDTO;

public interface BedDAO {

  Bed save(BedDTO bed);
  List<Bed> findAll();
  Bed findById(int bedId);
  void deletebyId(int bedId);
  void changeBedAvailabilityById(int bedId, boolean availableStatus);

}
