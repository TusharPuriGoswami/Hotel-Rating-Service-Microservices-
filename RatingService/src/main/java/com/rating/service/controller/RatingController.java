package com.rating.service.controller;

import com.rating.service.entities.Rating;
import com.rating.service.service.RatingService;
import org.springframework.boot.reactor.ReactorEnvironmentPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }
    ///  Create Rating
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating create = ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }
    /// Get Rating By Rating ID
    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRatingByRatingId(@PathVariable String ratingId){
        Rating ratingById = ratingService.getRatingById(ratingId);
        return ResponseEntity.status(HttpStatus.OK).body(ratingById);
    }
    ///  Get All Rating
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating(){
        List<Rating> allRating = ratingService.getAllRating();
         return ResponseEntity.status(HttpStatus.OK).body(allRating);

    }
    ///  get Rating By UserID
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        List<Rating> ratingByUserId = ratingService.getRatingByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ratingByUserId);

    }

    ///  Get Rating By HotelID

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        List<Rating> ratingByHotelId = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(ratingByHotelId);

    }
}
