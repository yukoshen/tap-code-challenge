package com.kjslocal.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kjslocal.dto.IpDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IP_TBL")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IpEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IP_ID", nullable = false)
	private Long id;

	@Column(name = "IP_ADDRESS", length = 15, unique = true, nullable = false)
	private String address;

	@ManyToMany(mappedBy = "ips", fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<ClientEntity> clients = new HashSet<>();

	public IpEntity(IpDto dto) {
		this.address = dto.getIpAddress();
	}

}
