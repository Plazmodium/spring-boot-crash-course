package udemy.experiments.spring.course.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udemy.experiments.spring.course.config.TimeApiConfig;
import udemy.experiments.spring.course.data.UserEntity;
import udemy.experiments.spring.course.models.User;
import udemy.experiments.spring.course.repositories.UserRepository;
import udemy.experiments.spring.course.utils.IEntityMapper;

@Service
public class UserService implements IUserService {
    private final ITimeService timeService;
    private final TimeApiConfig timeApiConfig;
    private final UserRepository userRepository;
    private final IEntityMapper<User, UserEntity> entityMapper;

    public UserService(ITimeService timeService,
                       TimeApiConfig timeApiConfig,
                       UserRepository userRepository,
                       IEntityMapper<User, UserEntity> entityMapper) {
        this.timeService = timeService;
        this.userRepository = userRepository;
        this.timeApiConfig = timeApiConfig;
        this.entityMapper = entityMapper;
    }

    public User getUser(Long id) {
        if (!userRepository.existsById(Math.toIntExact(id))) {
            return null;
        }

        return entityMapper.mapToModel(userRepository.findById(Math.toIntExact(id)).orElseThrow());
    }

    public Iterable<User> getUsers() {
        return entityMapper.mapToModels(userRepository.findAll());
    }

    public void addUser(User user) {
        user.setCreatedOn(timeService.GetCurrentTime(timeApiConfig.TimeZone).getDatetime());
//        user.getCreatedOn(LocalDateTime.now(Clock.systemUTC()).toString());
        userRepository.save(entityMapper.mapToEntity(user));
    }

    public User updateUser(User user) {
        userRepository.save(entityMapper.mapToEntity(user));
        return user;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(Math.toIntExact(id));
    }

    public boolean userExists(Long id) {
        return userRepository.existsById(Math.toIntExact(id));
    }
}
