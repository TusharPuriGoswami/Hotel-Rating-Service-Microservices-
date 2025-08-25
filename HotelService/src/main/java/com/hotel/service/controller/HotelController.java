package com.hotel.service.controller;

import com.hotel.service.entities.Hotel;
import com.hotel.service.payload.ApiResponse;
import com.hotel.service.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController (HotelService hotelService){
        this.hotelService = hotelService;
    }

    /// create hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel create = hotelService.createHotel(hotel);
       return ResponseEntity.status(HttpStatus.CREATED).body(create);

    }

    ///  get Hotel by Hotel ID
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
        Hotel hotelById = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotelById);

    }
    /// Get All Hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> allHotel = hotelService.getAllHotel();
        return ResponseEntity.ok(allHotel);

    }

    ///  Delete hotel by Hotel ID
    @DeleteMapping("/{hotelId}")
    public  ResponseEntity<String> deleteHotel(@PathVariable String hotelId){
         hotelService.deleteHotelById(hotelId);
        return ResponseEntity.ok("Hotel deleted successfully");
    }

    ///  Update Hotel By Hotel ID
    @PutMapping("/{hotelId}")
    public ResponseEntity<String> updateHotel(@PathVariable String hotelId , @RequestBody Hotel updateHotel){
        Hotel update = hotelService.updateHotelById(hotelId, updateHotel);
        return ResponseEntity.ok( "Hotel update Successfully");


    }


}
