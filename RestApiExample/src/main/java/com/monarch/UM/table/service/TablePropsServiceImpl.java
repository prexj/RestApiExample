package com.monarch.UM.table.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monarch.UM.table.model.TableProperties;
import com.monarch.UM.table.repository.TablePropsRepository;

@Service
public class TablePropsServiceImpl implements TablePropsService {

	@Autowired
	TablePropsRepository tablePropsRepository;

	@Override
	public TableProperties findByName(String name) {
		return tablePropsRepository.findByName(name);
	}

	@Override
	public TableProperties save(TableProperties tableProps) {
		return tablePropsRepository.save(tableProps);
	}

	@Override
	public TableProperties findById(Long id) {
		return tablePropsRepository.findById(id).orElse(null);
	}

	@Override
	public boolean delete(Long id) {
		try {
			tablePropsRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
