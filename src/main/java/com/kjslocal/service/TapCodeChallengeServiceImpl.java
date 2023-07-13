package com.kjslocal.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.kjslocal.dto.ClientResponseDto;
import com.kjslocal.dto.DeleteRequestDto;
import com.kjslocal.dto.IpResponseDto;
import com.kjslocal.dto.RegisterRequestDto;
import com.kjslocal.dto.SearchRequestDto;
import com.kjslocal.entities.ClientEntity;
import com.kjslocal.entities.IpEntity;
import com.kjslocal.exceptions.TapCodeChallengeException;
import com.kjslocal.repositories.ClientRepository;
import com.kjslocal.repositories.IpTableRepository;

@Service
public class TapCodeChallengeServiceImpl implements TapCodeChallengeService {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private IpTableRepository ipRepo;

	@Override
	public void addClient(RegisterRequestDto req) throws TapCodeChallengeException {

		try {
			Set<Long> ipIds = new HashSet<>();
			Set<IpEntity> ipList = new HashSet<>();

			if (!CollectionUtils.isEmpty(req.getIpAddresses())) {
				req.getIpAddresses().forEach(ips -> {
					Optional<IpEntity> ip = ipRepo.findByAddress(ips.getIpAddress());

					if (ip.isPresent()) {
						ipIds.add(ip.get().getId());
					} else {
						ipList.add(IpEntity.builder().address(ips.getIpAddress()).build());
					}

				});
			}

			ClientEntity entity = ClientEntity.builder().name(req.getClientName()).env(req.getEnvironment())
					.app(req.getApplication()).ips(ipList).build();

			entity = clientRepo.save(entity);
			Long clientId = entity.getId();

			ipIds.forEach(id -> {
				clientRepo.saveToClientIpTbl(clientId, id);
			});

		} catch (DataAccessException ex) {
			throw new TapCodeChallengeException("Error during adding client");
		}
	}

	@Override
	public Set<ClientResponseDto> getClient(SearchRequestDto req) throws TapCodeChallengeException {

		Set<ClientResponseDto> responseList = new HashSet<>();
		List<ClientEntity> clientList = new ArrayList<>();

		try {
			if (req.getClientName().isBlank()) {
				clientList = clientRepo.findByEnvAndApp(req.getEnvironment(), req.getApplication());
			} else {
				clientList = clientRepo.findByEnvAndAppAndName(req.getEnvironment(), req.getApplication(),
						req.getClientName());
			}

			if (!CollectionUtils.isEmpty(clientList)) {
				clientList.forEach(data -> {
					ClientResponseDto response = ClientResponseDto.builder().clientId(data.getId())
							.clientName(data.getName()).environment(data.getEnv()).application(data.getApp())
							.ipAddresses(mapIps(data.getIps())).build();
					responseList.add(response);
				});

			} else {
				throw new TapCodeChallengeException("Data not found");
			}
		} catch (DataAccessException ex) {
			throw new TapCodeChallengeException("Error during searching client");
		}

		return responseList;

	}

	@Override
	public void deleteClient(DeleteRequestDto req) throws TapCodeChallengeException {

		try {
			int status = clientRepo.deleteFromClientIpTbl(req.getClientId(), req.getIpId());
			
			if(status == 0) {
				throw new TapCodeChallengeException("Data not found");
			}

		} catch (DataAccessException ex) {
			throw new TapCodeChallengeException("Error during deleting client");
		}

	}

	public Set<IpResponseDto> mapIps(Set<IpEntity> ips) {

		Set<IpResponseDto> ipList = new HashSet<>();

		ips.forEach(data -> {
			IpResponseDto ip = IpResponseDto.builder().ipId(data.getId()).ipAddress(data.getAddress()).build();
			ipList.add(ip);
		});

		return ipList;
	}

}
