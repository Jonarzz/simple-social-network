package app.social.network.model.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserSex {

    MALE("male"),
    FEMALE("female"),
    OTHER("other");

    private String value;

    private UserSex(String value) {
        this.value = value != null ? value.toLowerCase() : null;
    }

    @JsonCreator
    public static UserSex fromString(String value) {
        return value != null ? UserSex.valueOf(value.toUpperCase()) : null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
