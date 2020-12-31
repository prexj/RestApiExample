package com.monarch.UM.table.service;

import com.monarch.UM.table.model.TableConfiguration;

public interface TableConfigurationService {
	
	public TableConfiguration save(TableConfiguration tableConfig);

	public boolean delete(Long id);

}
