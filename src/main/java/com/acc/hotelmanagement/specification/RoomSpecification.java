package com.acc.hotelmanagement.specification;

import com.acc.hotelmanagement.model.Booking;
import com.acc.hotelmanagement.model.Room;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

//https://docs.spring.io/spring-data/jpa/reference/jpa/specifications.html#page-title
// https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl
// https://github.com/AhmetAksunger/Jpa-Specifications-Example
//https://vladmihalcea.com/exists-subqueries-jpa-hibernate/
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

            // Create a subquery to check for overlapping bookings
            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<Booking> bookingRoot = subquery.from(Booking.class); //represents the bookings table

            // Selects a literal value to indicate the existence of matching rows, without returning specific rows from bookings
            // if it returns at least one row it means that there is an overlapping booking
            subquery.select(criteriaBuilder.literal(1))
            //  subquery.select(bookingRoot.get("id"))
                    .where(
                            // links the subquery bookings to the main root room by matching room.id to the booking.room_id
                            criteriaBuilder.equal(bookingRoot.get("room").get("id"), root.get("id")),
                            // check if booking overlaps
                            criteriaBuilder.lessThan(bookingRoot.get("checkInDate"), checkOut), // checkin before requested checkout
                            criteriaBuilder.greaterThan(bookingRoot.get("checkOutDate"), checkIn) // and checkout after requested checkin
                    );
            // exists checks if the subquery returns at least one row
            // not -> ensures to get rooms with no overlapping bookings
            return criteriaBuilder.not(criteriaBuilder.exists(subquery));

        };
    }
// SQL query
//    select
//    r1_0.id,
//    r1_0.number_of_guests,
//    r1_0.price,
//    r1_0.type
//            from
//    rooms r1_0
//    where
//    r1_0.number_of_guests=?
//    and r1_0.type=?
//    and not exists(select
//            1
//                    from
//                    bookings b1_0
//                    where
//                    b1_0.room_id=r1_0.id
//                    and b1_0.check_in_date<? (checkout)
//                    and b1_0.check_out_date>? (checkin) )


    // // questa versione crea problemi quando ci sono più di un booking per stanza alcuni che si sovrappongono alcuni no, la stanza viene ecomunque inserita
// //  left join valuta ogni prenotazione independentemente , quindi se c'è una prenotazione per quella stanza che non si sovrappone viene inserita, anche se ce n'è una che si sovrapone
// // versione con moltissimi commenti esplicativi sul flow delle condizioni booleane
//    public static Specification<Room> isAvailableBetween2(LocalDate checkIn, LocalDate checkOut) {
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

//    select
//    r1_0.id,
//    r1_0.number_of_guests,
//    r1_0.price,
//    r1_0.type
//            from
//    rooms r1_0
//    left join
//    bookings b1_0
//    on r1_0.id=b1_0.room_id
//            where
//    r1_0.number_of_guests=?
//    and r1_0.type=?
//    and (
//            b1_0.id is null
//                    or not(b1_0.check_in_date<?
//                    and b1_0.check_out_date>?)
//        )
}

