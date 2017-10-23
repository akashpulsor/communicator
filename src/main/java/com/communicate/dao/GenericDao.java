package com.communicate.dao;

import java.util.List;

import org.springframework.ui.Model;

public interface GenericDao<T> {
	
	public T  get ( Long id ) ;
	
	public List<T>  getall (  ) ;
	
	public void save( T t );

	public void delete ( T t );

}
