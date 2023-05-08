package com.navigation.error;

import java.util.UUID;

public class MobileStationNotFoundException extends RuntimeException {
    public MobileStationNotFoundException(UUID uuid) {
        super(String.format("Could not find mobile station for: %s", uuid));
    }
}
