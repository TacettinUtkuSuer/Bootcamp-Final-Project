package com.example.emlakburada.model.enums;

import java.util.Arrays;

public enum AdvertStatus {
    IN_REVIEW("inreview"),
    ACTIVE("active"),
    PASSIVE("passive");

    public String displayName;

    AdvertStatus(String name) {
        this.displayName = name;
    }

    public static AdvertStatus value(String value) {
        try {
            var nameValue = Arrays.stream(AdvertStatus.values()).filter(x -> x.displayName.equals(value)).findFirst();
            if (nameValue.isPresent()) {
                return nameValue.get();
            }
            return AdvertStatus.valueOf(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String fromString(){
        return displayName;
    }
}

