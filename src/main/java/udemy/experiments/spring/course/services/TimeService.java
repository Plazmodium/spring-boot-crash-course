package udemy.experiments.spring.course.services;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import udemy.experiments.spring.course.config.TimeApiConfig;
import udemy.experiments.spring.course.models.TimeResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class TimeService implements ITimeService {

    private static final int MAX_RETRIES = 5;
    private final TimeApiConfig timeApiConfig;

    public TimeService(TimeApiConfig timeApiConfig) {
        this.timeApiConfig = timeApiConfig;
    }

    @Override
    public TimeResponse GetCurrentTime(String timeZone) {
        int attempts = 0;
        Exception lastException = null;

        while (attempts < MAX_RETRIES) {
            try {

                HttpClient httpClient = HttpClient.newBuilder()
                        .version(HttpClient.Version.HTTP_2)
                        .followRedirects(HttpClient.Redirect.NORMAL)
                        .connectTimeout(java.time.Duration.ofSeconds(10))
                        .build();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(timeApiConfig.getTimeZoneURL() + timeZone))
                        .header("Content-Type", "application/json")
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    attempts++;
                    return null;
                }

                return parseResponse(response.body());

            } catch (Exception e) {
                lastException = e;
                attempts++;
            }
        }

        throw new RuntimeException("Failed to get current time after " + MAX_RETRIES + " attempts", lastException);
    }

    private TimeResponse parseResponse (String response) {
        return new Gson().fromJson(response, TimeResponse.class);
    }
}