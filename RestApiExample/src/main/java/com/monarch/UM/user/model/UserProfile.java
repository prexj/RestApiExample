package com.monarch.UM.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "userprofile")
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

//	@Column(name = "desk_height")
//	private int deskHeight;
//
//	@Column(name = "fan", columnDefinition = "boolean default false")
//	private boolean fan;
//
//	@Column(name = "fan_speed")
//	private int fanSpeed;
//
//	@Column(name = "heater", columnDefinition = "boolean default false")
//	private boolean heater;
//
//	@Column(name = "heater_level")
//	private int heaterLevel;
//
//	@Column(name = "task_light", columnDefinition = "boolean default false")
//	private boolean taskLight;
//
//	@Column(name = "task_light_level")
//	private int taskLightLevel;
//
//	@Column(name = "profile_name")
//	private String profileName;
//
//	@Column(name = "default_profile", columnDefinition = "boolean default false")
//	private boolean defaultProfile;
//
//	@Column(name = "admin_demo", columnDefinition = "boolean default false")
//	private boolean adminDemo;
	
	
	@Column(name = "device_type")
	private String deviceType;

	@Column(name = "device_sequence")
	private int deviceSequence;

	@Column(name = "power")
	private Boolean power;

	@Column(name = "level")
	private String level;

	@ManyToOne
	@JsonIgnore
	private User user;

}
