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

    public enum CalculateResultCode{
        SUCCESS(200, "计算成功"), EMAIL(302, "全选卷轴，计算结果以Email形式发送给您"),FAIL(500, "计算失败");

        private final Integer code;
        private final String message;

        CalculateResultCode(Integer code, String message){
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
