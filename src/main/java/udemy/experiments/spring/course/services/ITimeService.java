package udemy.experiments.spring.course.services;

import udemy.experiments.spring.course.models.TimeResponse;

public interface ITimeService {
    TimeResponse GetCurrentTime(String timeZone);

}
