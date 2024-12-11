package com.acc.hotelmanagement;

import com.acc.hotelmanagement.model.Room;
import com.acc.hotelmanagement.model.RoomType;
import com.acc.hotelmanagement.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HotelmanagementApplicationTests {

	@Autowired
	private RoomRepository roomRepository;


    @Test
	void contextLoads() {
	}

	// Problema iniziale con il test -> non avevo messo il corpo del costruttore, quindi le stanze venivano creeate con guest 0, price 0 e roomtype null
	// perchè le proprietà non venivano assegnate
	@Test
	public void createInitialRooms()
	{
		roomRepository.saveAll(List.of(
				new Room(RoomType.SUITE, 4, 86.99),
				new Room(RoomType.CLASSIC, 2, 45.10),
				new Room(RoomType.MODERN, 5, 50.89),
				new Room(RoomType.BASIC, 2, 29.99),
				new Room(RoomType.SUITE, 4, 78.11),
				new Room(RoomType.CLASSIC, 2, 41.22),
				new Room(RoomType.MODERN, 3, 64.50),
				new Room(RoomType.BASIC, 5, 35.70),
				new Room(RoomType.SUITE, 4, 74.10),
				new Room(RoomType.CLASSIC, 2, 58.50)
		));
	}

	/*@Test
	public void createInitialRoom() {
		Room room = new Room(RoomType.SUITE, 4, 86.99);
		System.out.println("Room Type: " + room.getType()); // Debugging: Check type here
		roomRepository.save(room);
	}*/

}
