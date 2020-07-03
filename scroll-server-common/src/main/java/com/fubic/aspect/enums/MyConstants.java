package com.fubic.aspect.enums;

public class MyConstants {
    public enum RedisKey{
        SCROLLS(0, "SCROLL:WEAPON_SCROLL:ID:");

        private final Integer code;
        private final String message;

        RedisKey(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
