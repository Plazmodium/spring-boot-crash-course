package udemy.experiments.spring.course.utils;

import org.springframework.stereotype.Component;
import udemy.experiments.spring.course.data.UserEntity;
import udemy.experiments.spring.course.models.User;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class UserEntityMapper implements IEntityMapper<User, UserEntity> {

    @Override
    public UserEntity mapToEntity(User model) {
        return new UserEntity(
                model.getId(),
                model.getFirstName(),
                model.getLastName(),
                model.getEmail(),
                model.getRole(),
                model.getCreatedOn(LocalDateTime.now(Clock.systemUTC()).toString())
        );
    }

    @Override
    public User mapToModel(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getRole(),
                entity.getCreatedOn()
        );
    }

    @Override
    public Iterable<UserEntity> mapToEntities(Iterable<User> models) {
        var entities = new ArrayList<UserEntity>();
        for (User model : models) {
            entities.add(mapToEntity(model));
        }
        return entities;
    }

    @Override
    public Iterable<User> mapToModels(Iterable<UserEntity> entities) {
        var models = new ArrayList<User>();
        for (UserEntity entity : entities) {
            models.add(mapToModel(entity));
        }
        return models;
    }
}
