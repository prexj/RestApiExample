package com.monarch.UM.table.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "table_config")
public class TableConfiguration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "device_type")
	private String deviceType;

	@Column(name = "device_no")
	private String deviceNo;

	@Column(name = "device_sequence")
	private int deviceSequence;

	@Column(name = "socket_no")
	private String socketNo;

	@Column(name = "power")
	private Boolean power;

	@Column(name = "level")
	private String level;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	@ManyToOne
	@JsonIgnore
	private TableProperties tableProps;

}
