/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.casestudy.dal.BedDAO;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.MyException;
import com.philips.casestudy.dto.BedDTO;

@Service
public class BedServiceImpl implements BedService {


  BedDAO bedDao;


  @Autowired
  public void setBedDao(BedDAO bedDao) {
    this.bedDao = bedDao;
  }


  @Override
  public int addNewBed(BedDTO bed) {

    return  bedDao.save(bed).getBedId();


  }


  @Override
  public List<Bed> getAllBeds(){
    return bedDao.findAll();
  }

  @Override
  public Bed findBed(int bedId) {
    return bedDao.findById(bedId);
  }

  @Override
  public void deleteExistingBed(int bedId) {

    final Bed bed = bedDao.findById(bedId);
    if(bed.getisAvailable()) {
      bedDao.deletebyId(bedId);
    } else {
      try {
        throw new MyException("Bed can't be deleted");
      } catch (final MyException e) {
        e.getExceptionMessage();
      }
    }
  }

  @Override
  public boolean changeBedAvailability(int bedId) {
    final Bed bed=bedDao.findById(bedId);
    final boolean initialAvailability=bed.getisAvailable();
    bedDao.changeBedAvailabilityById(bedId, !bed.getisAvailable());
    bed.setAvailable(!bed.getisAvailable());
    return (bedDao.findById(bedId).getisAvailable()!=initialAvailability);

  }

}