package com.acc.hotelmanagement.specification;

import com.acc.hotelmanagement.model.Booking;
import com.acc.hotelmanagement.model.Room;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

//https://docs.spring.io/spring-data/jpa/reference/jpa/specifications.html#page-title
// https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl
// https://github.com/AhmetAksunger/Jpa-Specifications-Example
public class RoomSpecification {

    public static Specification<Room> withNumberOfGuests(Integer numberOfGuests) {
        return (root, query, criteriaBuilder) ->
                // if numberOfGuests is null, return a conjunction (true) otherwise apply equal to the number of guests field of root (Room)
                numberOfGuests == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("numberOfGuests"), numberOfGuests);

    }

    public static Specification<Room> withRoomType(String type) {
        return ((root, query, criteriaBuilder) ->
                type == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("type"), type));
    }

    public static Specification<Room> isAvailableBetween(LocalDate checkIn, LocalDate checkOut) {
        return (root, query, criteriaBuilder) ->
        {
            if (checkIn == null || checkOut == null)
                return criteriaBuilder.conjunction(); // returns all rooms if check in or checkout filter is null

            Join<Room, Booking> roomJoin = root.join("bookings", JoinType.LEFT); // use left join to get all rooms even if they have no bookings
            return // true --> room is available and included, false --> room is not available and not included
                    criteriaBuilder.or(
                            roomJoin.isNull(),// if no booking exists, room is available
                            criteriaBuilder.not( // invert the boolean: is overlapping? no -> so put it in the result set
                                    criteriaBuilder.and( // is overlapping? FALSE
                                            criteriaBuilder.lessThan(roomJoin.get("checkInDate"), checkOut),
                                            criteriaBuilder.greaterThan(roomJoin.get("checkOutDate"), checkIn)
                                    )
                            )
                    );
        };
    }

    // versione con moltissimi commenti esplicativi sul flow delle condizioni booleane
//    public static Specification<Room> isAvailableBetween(LocalDate checkIn, LocalDate checkOut) {
//        return (root, query, criteriaBuilder) ->
//        {
//            if (checkIn == null || checkOut == null)
//                return criteriaBuilder.conjunction(); // returns all rooms if check in or checkout filter is null
//
//            Join<Room, Booking> roomJoin = root.join("bookings", JoinType.LEFT); // use left join to get all rooms even if they have no bookings
//            return // true --> room is available and included, false --> room is not available and not included
//                    criteriaBuilder.or( //B1 -> false OR true = true
//                            roomJoin.isNull(),// (A. if no booking exists, room is available returns true) B. if a booking exists, returns false
//                            criteriaBuilder.not( // B1. negates the boolean: false becomes true -> is overlapping? no -> so put it in the result set B1
//                                    criteriaBuilder.and( // is overlapping? B1. FALSE
//                                            // new : 2024-12-15 to 2024-12-18
//                                            // compare to : 2024-12-13 to 2024-12-15 NO CONFLICT gives false B1
//                                            // compare to  : 2024-12-14 to 2024-12-19 CONFLICT gives true B2
//                                            // 2024-12-13 < 2024-12-18 T B1
//                                            // 2024-12-14 < 2024-12-18 T B2
//                                            criteriaBuilder.lessThan(roomJoin.get("checkInDate"), checkOut),
//                                            // 2024-12-15 > 2024-12-15 F B1
//                                            // 2024-12-19 > 2024-12-18 T B2
//                                            criteriaBuilder.greaterThan(roomJoin.get("checkOutDate"), checkIn)
//                                    )
//                            )
//                    );
//        };
//    }
}

