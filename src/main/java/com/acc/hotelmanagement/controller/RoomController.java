package com.acc.hotelmanagement.controller;

import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> roomDTOs = roomService.getAllRooms();
        return new ResponseEntity<>(roomDTOs, HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable long roomId) {
        RoomDTO roomDTOs = roomService.getRoomDTO(roomId);
        return new ResponseEntity<>(roomDTOs, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RoomDTO> addRoom(@Valid @RequestBody RoomDTO roomDTO) {
        RoomDTO roomDTOs = roomService.createRoom(roomDTO);
        return new ResponseEntity<>(roomDTOs, HttpStatus.CREATED);
    }


}
