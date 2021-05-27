package com.order.dao;

import java.util.List;

import com.order.entity.Entity;

public interface OrderMangementDAO {
	List<? extends Entity> getAll(String tableName);

	<T> Entity getById(Long id, Class<T> t);

	Long save(Entity data);

	Boolean merge(Entity data);

	Boolean delete(Entity data);
}
