package com.kjslocal.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {
	
	private Long clientId;

	private String clientName;

	private String environment;

	private String application;
	
	private Set<IpResponseDto> ipAddresses;

}
