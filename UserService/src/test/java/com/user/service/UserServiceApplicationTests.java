// //package com.user.service;
// //
// //import com.user.service.entities.Hotel;
// //import com.user.service.entities.Rating;
// //import com.user.service.external.services.RatingService;
// //import org.junit.jupiter.api.Test;
// //import org.springframework.beans.factory.annotation.Autowired;
// //import org.springframework.boot.test.context.SpringBootTest;
// //
// //@SpringBootTest
// //class UserServiceApplicationTests {
// //
// //	@Test
// //	void contextLoads() {
// //	}
// //
// //
// //
// //	@Autowired
// //	private RatingService ratingService;
// //
// ////	public UserServiceApplicationTests(RatingService ratingService){
// ////		this.ratingService = ratingService;
// ////	}
// //
// //
// //
// //@Test
// //	void creatingRating(){
// //		Rating rating = Rating
// //				.builder()
// //				.rating(2)
// //				.userId("")
// //				.hotelId("")
// //				.feedback("This is created using feign client")
// //				.build();
// //		Rating saveRating = ratingService.createRating(rating);
// //		System.out.println("New Rating Created....." + saveRating);
// //
// //	}
// //
// //}




package com.user.service;

import com.user.service.entities.Rating;
import com.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {



	@Test
	void contextLoads() {
	}

//	@Autowired
//	private RatingService ratingService;
//
//	@Test
//	void creatingRating() {
//		Rating rating = Rating.builder()
//				.rating(2)
//				.userId("")
//				.hotelId("")
//				.feedback("This is created using feign client")
//				.build();
//
//		Rating saveRating = ratingService.createRating(rating);
//		System.out.println("New Rating Created....." + saveRating);
//	}
}

