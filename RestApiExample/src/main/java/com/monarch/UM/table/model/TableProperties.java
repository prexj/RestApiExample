package com.monarch.UM.table.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.monarch.UM.audit.Auditable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "table_props")
@EqualsAndHashCode(callSuper = true)
public class TableProperties extends Auditable<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "no")
	private Integer no;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table_props_id")
	private List<TableConfiguration> tableConfiguration;

}

