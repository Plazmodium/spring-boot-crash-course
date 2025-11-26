package udemy.experiments.spring.course.utils;

public interface IEntityMapper<T,U> {
    U mapToEntity(T model);
    T mapToModel(U entity);
    Iterable<U> mapToEntities(Iterable<T> models);
    Iterable<T> mapToModels(Iterable<U> entities);
}
