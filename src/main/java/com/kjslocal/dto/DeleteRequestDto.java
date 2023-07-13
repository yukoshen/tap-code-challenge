package com.kjslocal.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "client")
public class DeleteRequestDto {
	
	@NotNull(message = "clientId is required.")
	private Long clientId;
	
	@NotNull(message = "ipId is required.")
	private Long ipId;

}
