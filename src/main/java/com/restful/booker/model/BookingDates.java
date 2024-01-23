package com.restful.booker.model;

import java.util.HashMap;

public class BookingDates {
    public HashMap<String, Object> getbookingdates() {
        HashMap<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout", "2019-01-01");
        return bookingDates;
    }
}
