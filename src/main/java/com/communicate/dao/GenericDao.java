package com.communicate.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenericDao<T,ID extends Serializable>
extends CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>{
	
	T findByName( String name );
	
}
