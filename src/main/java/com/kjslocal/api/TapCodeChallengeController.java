package com.kjslocal.api;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kjslocal.dto.ClientResponseDto;
import com.kjslocal.dto.DeleteRequestDto;
import com.kjslocal.dto.RegisterRequestDto;
import com.kjslocal.dto.SearchRequestDto;
import com.kjslocal.exceptions.TapCodeChallengeException;
import com.kjslocal.service.TapCodeChallengeService;

@RestController
@RequestMapping("/v1/")
@Validated
public class TapCodeChallengeController {

	@Autowired
	private TapCodeChallengeService service;

	@PostMapping(value = "/addClient", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> add(@Validated @RequestBody RegisterRequestDto req) throws TapCodeChallengeException {

		service.addClient(req);
		return new ResponseEntity<>("Client IP Registered Successfully!", HttpStatus.OK);
	}

	@GetMapping(value = "/getClient", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Set<ClientResponseDto>> get(@Validated @RequestBody SearchRequestDto req) throws TapCodeChallengeException {

		return new ResponseEntity<>(service.getClient(req), HttpStatus.OK);

	}

	@DeleteMapping(value = "/deleteClient", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> delete(@Validated @RequestBody DeleteRequestDto req) throws TapCodeChallengeException {

		service.deleteClient(req);
		return new ResponseEntity<>("Client IP Deleted Successfully!", HttpStatus.OK);
	}

}
