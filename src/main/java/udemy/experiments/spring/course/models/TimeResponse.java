package udemy.experiments.spring.course.models;

//public record TimeResponse (
//        String utcOffset,
//        String timezone,
//        int dayOfWeek,
//        int dayOfYear,
//        String datetime,
//        String utcDatetime,
//        long unixtime,
//        int rawOffset,
//        int weekNumber,
//        boolean dst,
//        String abbreviation,
//        int dstOffset,
//        String dstFrom,
//        String dstUntil,
//        String clientIp
//) {}

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class TimeResponse {
    private String datetime;

    public TimeResponse(
            @JsonProperty("datetime") String datetime
    ) {
        this.datetime = datetime;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}


