package com.communicate.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;


@Repository
public interface GenericDao<T,ID extends Serializable>
extends CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>{
	
	

}
