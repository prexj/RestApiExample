package com.monarch.UM.table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.monarch.UM.table.model.TableProperties;

public interface TablePropsRepository
		extends JpaRepository<TableProperties, Long>, CrudRepository<TableProperties, Long> {

	TableProperties findByName(String name);

}
