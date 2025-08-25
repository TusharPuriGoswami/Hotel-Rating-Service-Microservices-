    package com.user.service.services;

    import com.netflix.discovery.converters.Auto;
    import com.user.service.entities.Hotel;
    import com.user.service.entities.Rating;
    import com.user.service.entities.User;
    import com.user.service.exceptions.ResourceNotFoundException;
    import com.user.service.external.services.HotelService;
    import com.user.service.repositories.UserRepository;
    import org.slf4j.ILoggerFactory;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.slf4j.LoggerFactoryFriend;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Component;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.UUID;
    import java.util.stream.Collectors;

    @Service
    public class UserService {


        private final HotelService hotelService;

        private final UserRepository userRepository;

        private final RestTemplate restTemplate;

        private Logger logger = LoggerFactory.getLogger(UserService.class);

        public UserService(UserRepository userRepository , RestTemplate restTemplate , HotelService hotelService) {
            this.userRepository = userRepository;
            this.restTemplate = restTemplate;
            this.hotelService = hotelService;
        }

        ///  creating user
         public User createUser(User user){
             ///  generate unique UUId
             String randomUserId = UUID.randomUUID().toString();
             user.setUserId(randomUserId);
             return userRepository.save(user);
        }

        ///  get all users
         public List<User> getAllUser(){
            return userRepository.findAll();

        }

    ///         get single user by userId
         public User getUserById(String userId){
    ///         get single user from the database help of userRepository
             User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not found with the given id : " + userId));
    ///         Fetch rating of the above user from Rating Service
    ///         call the Rating Service with the help of this URL
    ///         localhost:8083/ratings/users/00d70309-aab4-4609-b895-91aa0aa668bb
             ///  this is the use of RestTemplate Client

             Rating[] ratingsForUsers = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
             logger.info("{} " , ratingsForUsers);
             List<Rating> ratings = Arrays.stream(ratingsForUsers).toList();
    //
    //       user.setRating(List.of(ratingsForUsers));

             List<Rating> ratingList = ratings.stream().map(rating -> {
                 ///  api call to hotel service to get the hotel
             ///  localhost:8082/hotels/d786183f-8a7a-45af-870c-2afcc2c02f1f
                 System.out.println(rating.getHotelId());
//                 ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                 Hotel hotel = hotelService.getHotel(rating.getHotelId());
//                 logger.info("response status code : {}",forEntity.getStatusCode());
                 ///  Set the hotel to rating
             rating.setHotel(hotel);
    //           return the rating
                 return rating;
             }).toList();
             user.setRating(ratingList);


             return user;
         }

        ///  Optional for you

        ///  delete user by userId
         public void deleteUser(String userId){

             User user = userRepository.findById(userId)
                     .orElseThrow(() -> new ResourceNotFoundException("User is not found with the given id : " + userId));

    //         user.setDeleted(true);
              userRepository.delete(user);

        }


        ///  update user by userId
         public User updateUser(String userId , User updateUser){
             User existingUser = userRepository.findById(userId)
                     .orElseThrow(() -> new ResourceNotFoundException("User is not found with the given id : " + userId));
             existingUser.setName(updateUser.getName());
             existingUser.setEmail(updateUser.getEmail());
             existingUser.setAbout(updateUser.getAbout());

             return userRepository.save(existingUser);
         }



    }
