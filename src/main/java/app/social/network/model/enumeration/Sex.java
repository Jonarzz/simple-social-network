package app.social.network.model.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import app.social.network.controller.exception.IllegalUserSexValueException;

public enum Sex {

    MALE("male"),
    FEMALE("female"),
    OTHER("other");

    private String value;

    private Sex(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Sex fromString(String value) {
        try {
            return value == null ? null : Sex.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalUserSexValueException();
        }
    }

    @JsonValue
    private String getValue() {
        return this.value;
    }

}
