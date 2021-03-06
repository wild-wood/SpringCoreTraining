package by.epam.maksim.movietheater.repository;

import by.epam.maksim.movietheater.entity.Counter;

import java.util.Collection;

public interface CounterRepository extends GenericRepository<Counter> {
    Collection<Counter> getByType(Class<?> type);
    Collection<Counter> getByName(String name);
    Collection<Counter> getByDomain(String domain);
    Counter get(Class<?> type, String name, String domain);
    Counter save(Class<?> type, String name, String domain);
}