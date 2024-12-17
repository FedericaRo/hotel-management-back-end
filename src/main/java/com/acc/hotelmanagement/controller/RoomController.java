package com.acc.hotelmanagement.controller;

import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.dto.RoomFilterDTO;
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

    /**
     * Retrieves all rooms
     *
     * @return ResponseEntity containing a list of RoomDTOs with HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> roomDTOs = roomService.getAllRooms();
        return new ResponseEntity<>(roomDTOs, HttpStatus.OK);
    }

    /**
     * Retrieves room details for the specified room ID.
     *
     * @param roomId The ID of the room to retrieve.
     * @return ResponseEntity containing the RoomDTO for the specified ID with HTTP status OK.
     */
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable long roomId) {
        RoomDTO roomDTOs = roomService.getRoomDTO(roomId);
        return new ResponseEntity<>(roomDTOs, HttpStatus.OK);
    }


    /**
     * Adds a new room.
     *
     * @param roomDTO The RoomDTO object containing details of the room to be added.
     * @return ResponseEntity containing the added RoomDTO with HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<RoomDTO> addRoom(@Valid @RequestBody RoomDTO roomDTO) {
        RoomDTO createdRoom = roomService.createRoom(roomDTO);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    /**
     * Retrieves rooms based on provided filters
     *
     * @param roomFilterDTO The filterDTO containing the filters and their values
     * @return ResponseEntity containing a list of RoomDTOs with HTTP status OK
     */
    @PostMapping("/filtered")
    public ResponseEntity<List<RoomDTO>> getRoomsWithFilters(@RequestBody RoomFilterDTO roomFilterDTO) {
        List<RoomDTO> roomDTOs = roomService.getFilteredRooms(roomFilterDTO);
        return new ResponseEntity<>(roomDTOs, HttpStatus.OK);
    }


}
