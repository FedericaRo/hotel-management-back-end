package com.acc.hotelmanagement.controller;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.model.Room;
import com.acc.hotelmanagement.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms()
    {
        List<RoomDTO> roomDTOs = roomService.getAllRooms();
        return new ResponseEntity<>(roomDTOs, HttpStatus.OK);
    }


}
