package udemy.experiments.spring.course.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "timeapi")
public class TimeApiConfig {
    public String TimeZoneURL = "http://worldtimeapi.org/api/timezone/";
    public String TimeZone = "Etc/UCT";

    public String getTimeZoneURL() {
        return TimeZoneURL;
    }

    public void setTimeZoneURL(String timeZoneURL) {
        TimeZoneURL = timeZoneURL;
    }

    public String getTimeZone() {
        return TimeZone;
    }

    public void setTimeZone(String timeZone) {
        TimeZone = timeZone;
    }
}
