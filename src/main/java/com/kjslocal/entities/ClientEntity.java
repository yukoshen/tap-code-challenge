package com.kjslocal.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CLIENT_TBL")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENT_ID", nullable = false)
	private Long id;

	@Column(name = "CLIENT_NAME", length = 50, nullable = false)
	private String name;
	
	@Column(name = "ENVIRONMENT", length = 5, nullable = false)
	private String env;
	
	@Column(name = "APPLICATION", length = 4, nullable = false)
	private String app;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "CLIENT_IP_TBL", joinColumns = {
			@JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "IP_ID", referencedColumnName = "IP_ID") })
	@JsonManagedReference
	private Set<IpEntity> ips = new HashSet<>();

}
