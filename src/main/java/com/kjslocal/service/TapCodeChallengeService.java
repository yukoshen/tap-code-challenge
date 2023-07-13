package com.kjslocal.service;

import java.util.Set;

import com.kjslocal.dto.ClientResponseDto;
import com.kjslocal.dto.DeleteRequestDto;
import com.kjslocal.dto.RegisterRequestDto;
import com.kjslocal.dto.SearchRequestDto;
import com.kjslocal.exceptions.TapCodeChallengeException;

public interface TapCodeChallengeService {
	
	void addClient(RegisterRequestDto req) throws TapCodeChallengeException ;
	
	Set<ClientResponseDto> getClient(SearchRequestDto req) throws TapCodeChallengeException ;
	
	void deleteClient(DeleteRequestDto req) throws TapCodeChallengeException ;

}
