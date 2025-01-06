package com.example.food_delivering_system.controller;

import com.example.food_delivering_system.entities.DriverLocation;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LiveLocationController {

    @MessageMapping("/driver/{driverId}/updateLocation") // Matches destination `/app/driver/{driverId}/updateLocation`
    @SendTo("/topic/driver/{driverId}/location") // Broadcasts to `/topic/driver/{driverId}/location`
    public DriverLocation updateLocation(@DestinationVariable String driverId, DriverLocation location) {
        System.out.println("Received location for driver " + driverId + ": " + location);
        return location; // The returned object is sent to the subscribers
    }
}