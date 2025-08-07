package com.hotel.service.service;

import com.hotel.service.entities.Hotel;
import com.hotel.service.exceptions.ResourceNotFoundException;
import com.hotel.service.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class HotelService{

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /// create hotel
    public Hotel createHotel(Hotel hotel){
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    /// get Hotel by Hotel ID
    public Hotel getHotelById(String hotelId){
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel is not found With the ID : " + hotelId));

    }

    /// Get All Hotels
    public List<Hotel> getAllHotel(){
        return hotelRepository.findAll();
    }

    /// Delete Hotel By Hotel ID
    public void deleteHotelById(String hotelId){
        Hotel delete = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel is not found with this hotel id : " + hotelId));
        hotelRepository.delete(delete);

    }

    ///  Update Hotel By Hotel ID
    public Hotel updateHotelById(String hotelId , Hotel updateHotel){
        Hotel update = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel is not found with this hotel id : " + hotelId));
        // update fields
        update.setName(updateHotel.getName());
        update.setAbout(updateHotel.getAbout());
        update.setLocation(updateHotel.getLocation());

        return hotelRepository.save(update);

    }


}
