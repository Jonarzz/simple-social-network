package app.social.network.model.Enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sex {

    MALE("male"), FEMALE("female"), OTHER("other");

    private String value;

    Sex(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Sex fromString(String value) {
        return value == null ? null : Sex.valueOf(value.toUpperCase());
    }

    @JsonValue
    private String getValue() {
        return this.value;
    }

}
