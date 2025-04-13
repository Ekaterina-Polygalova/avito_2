package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private Result result;
    private String status;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Result {
        private String message;
        private Messages messages;

        @Data
        public static class Messages {
        }
    }
}