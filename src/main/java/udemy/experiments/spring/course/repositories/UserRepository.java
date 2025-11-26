package udemy.experiments.spring.course.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import udemy.experiments.spring.course.data.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {  }
