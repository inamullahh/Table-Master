package com.inam.tablemaster.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.inam.tablemaster.models.TableMaster;

public interface TableMasterRepository extends CrudRepository<TableMaster, Long> {
	
	List<TableMaster> findAll();
}
