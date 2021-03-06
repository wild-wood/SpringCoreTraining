package by.epam.maksim.movietheater.repository.inmemory;

import by.epam.maksim.movietheater.entity.IdentifiedEntity;
import by.epam.maksim.movietheater.repository.GenericRepository;
import org.apache.commons.lang3.SerializationUtils;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

abstract class IMGenericRepository<E extends IdentifiedEntity> implements GenericRepository<E> {

    final AtomicLong idCounter = new AtomicLong(1);
    final Map<Long, E> storage = new ConcurrentHashMap<>();

    @Override
    public E save(E entity) {
        if (entity.getId() == null) {
            entity.setId(idCounter.getAndIncrement());
        }
        storage.put(entity.getId(), SerializationUtils.clone(entity));
        return entity;
    }

    @Override
    public void remove(E entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Entity can't be deleted, id is 'null'.");
        }
        storage.remove(entity.getId());
    }

    @Override
    public E getById(Long id) {
        return SerializationUtils.clone(storage.get(id));
    }

    @Override
    public Collection<E> getAll() {
        return storage.values().stream().map(SerializationUtils::clone).collect(Collectors.toList());
    }

}