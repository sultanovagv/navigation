package com.navigation.error;

import java.util.UUID;

public class BaseStationNotFoundException extends RuntimeException {
    public BaseStationNotFoundException(UUID uuid) {
        super(String.format("Could not find base station for: %s", uuid));
    }
}
