package sp_example.repositories;

// CRUD - Create, Read, Update, Delete
public interface CrudRepository<T> {
    void save(T model);
}
