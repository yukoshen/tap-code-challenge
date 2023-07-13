package com.kjslocal.dto;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ipAddresses")
public class IpDto {

	@Pattern(regexp = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$", message = "ipAddress is invalid.")
	private String ipAddress;

}
