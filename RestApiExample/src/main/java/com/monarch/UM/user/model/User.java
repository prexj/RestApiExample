package com.monarch.UM.user.model;

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
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends Auditable<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "rf_id")
	private String rfId;

	@Column(name = "finger_print_id")
	private String fingerPrintId;

	@Column(name = "isadmin", columnDefinition = "boolean default false")
	private boolean isAdmin;

	@Column(name = "contactno")
	private String contactNo;

	@Column(name = "age")
	private int age;

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private List<UserProfile> userProfile;

}
