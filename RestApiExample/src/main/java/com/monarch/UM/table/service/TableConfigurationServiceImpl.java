package com.monarch.UM.table.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monarch.UM.table.model.TableConfiguration;
import com.monarch.UM.table.model.TableProperties;
import com.monarch.UM.table.repository.TableConfigurationRepository;
import com.monarch.UM.table.repository.TablePropsRepository;

@Service
public class TableConfigurationServiceImpl implements TableConfigurationService {

	@Autowired
	private TablePropsRepository tablePropsRepository;

	@Autowired
	private TableConfigurationRepository tableConfigRepository;

	@Override
	public TableConfiguration save(TableConfiguration tableConfig) {
		return tableConfigRepository.save(tableConfig);
	}

	@Override
	public boolean delete(Long id) {
			try {
				TableProperties tableProps = tablePropsRepository.findById(id).orElse(null);
				List<TableConfiguration> profiles = tableProps.getTableConfiguration();
				for (TableConfiguration tc : profiles) {
					tableConfigRepository.deleteTableConfig(tc.getId());
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}

	

}
