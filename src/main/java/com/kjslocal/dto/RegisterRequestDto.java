package com.kjslocal.dto;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "client")
public class RegisterRequestDto {

	@NotBlank(message = "clientName is required")
	private String clientName;

	@Pattern(regexp = "^$|DEV|STAGE|PROD", message = "environment is invalid.")
	@NotBlank(message = "environment is required")
	private String environment;

	@Pattern(regexp = "^$|app1|app2", message = "application is invalid.")
	@NotBlank(message = "application is required")
	private String application;

	private Set<@Valid IpDto> ipAddresses;

}
