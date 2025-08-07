package com.rating.service.service;

import com.rating.service.entities.Rating;
import com.rating.service.exception.ResourceNotFoundException;
import com.rating.service.repository.RatingRepository;
import org.springframework.lang.Contract;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

  public RatingService(RatingRepository ratingRepository){
      this.ratingRepository = ratingRepository;
  }

    // create rating
    public Rating createRating(Rating rating){
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepository.save(rating);
    }

    // Get All Rating
    public List<Rating> getAllRating(){
     return ratingRepository.findAll();
    }

    // Get rating by Rating Id
    public Rating getRatingById(String ratingId){
      return ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating is Not found with the ID : " + ratingId));
    }

    // Get All By User ID
    public List<Rating> getRatingByUserId(String userId){
      return ratingRepository.findByUserId(userId);

    }


    // Get All by Hotel ID
    public List<Rating> getRatingByHotelId(String hotelId){
      return ratingRepository.findByHotelId(hotelId);
    }

   /* // Delete Rating
    public void deleteRating(String ratingId){
        Rating delete = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating is Not found with the ID : " + ratingId));
        ratingRepository.delete(delete);

    }
    // Update Rating
    public Rating updateRating(String ratingId){
        Rating update = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating is Not found with the ID : " + ratingId));
        //update fields
        update.setRating(update.getRating());
        update.setFeedback(update.getFeedback());
        return ratingRepository.save(update);
    }*/
}
