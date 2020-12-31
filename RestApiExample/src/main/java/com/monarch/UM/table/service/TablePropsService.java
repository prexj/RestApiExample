package com.monarch.UM.table.service;

import com.monarch.UM.table.model.TableProperties;

public interface TablePropsService {

	TableProperties findByName(String name);

	TableProperties save(TableProperties tableProps);

	TableProperties findById(Long id);

	boolean delete(Long id);

}
