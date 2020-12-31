package com.monarch.UM.table.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.monarch.UM.table.model.TableConfiguration;

@Repository
public interface TableConfigurationRepository extends JpaRepository<TableConfiguration, Long> {
	
	@Modifying
	@Query("delete from TableConfiguration where id = :id")
	@Transactional
	void deleteTableConfig(long id);

}
