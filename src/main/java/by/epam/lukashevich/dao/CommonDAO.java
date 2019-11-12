package by.epam.lukashevich.dao;

import by.epam.lukashevich.domain.entity.AbstractEntity;


public interface CommonDAO<E extends AbstractEntity> extends CrudRepository<E, Integer> {

}
