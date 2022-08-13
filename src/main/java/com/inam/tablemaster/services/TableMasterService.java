package com.inam.tablemaster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inam.tablemaster.models.TableMaster;
import com.inam.tablemaster.repository.TableMasterRepository;

@Service
public class TableMasterService {
	
	private final TableMasterRepository nameRepo;
	
	// Constructor
	public TableMasterService(TableMasterRepository nameRepo) {
		super();
		this.nameRepo = nameRepo;
	}
	
	// List all the show names
		public List<TableMaster> allNames(){
			return nameRepo.findAll();
		}
		
	// Find one show by Id
	public TableMaster findOne(Long Id) {
		Optional<TableMaster> optionalName = nameRepo.findById(Id);
		if(optionalName.isPresent()) {
			return optionalName.get();
		}else {
			return null;
		}
	}
	
	// Create a new show
	public TableMaster create(TableMaster name) {
		return nameRepo.save(name);
	}
	
	// Update an existing show
	public TableMaster update(TableMaster name) {
		return nameRepo.save(name);
	}
	
	// Delete a show 
	public void delete(Long id) {
		nameRepo.deleteById(id);
	}
	
}
