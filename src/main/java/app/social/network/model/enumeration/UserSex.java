package app.social.network.model.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserSex {

    MALE("male"),
    FEMALE("female"),
    OTHER("other");

    private String key;

    private UserSex(String key) {
        this.key = key != null ? key.toLowerCase() : null;
    }

    @JsonCreator
    public static UserSex fromString(String key) {
        return key != null ? UserSex.valueOf(key.toUpperCase()) : null;
    }

    @JsonValue
    public String getKey() {
        return key;
    }

}
