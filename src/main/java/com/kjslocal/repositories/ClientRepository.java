package com.kjslocal.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kjslocal.entities.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

	List<ClientEntity> findByEnvAndApp(String env, String app);
	
	List<ClientEntity> findByEnvAndAppAndName(String env, String app, String name);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO CLIENT_IP_TBL (CLIENT_ID, IP_ID) VALUES (:clientId, :ipId)", nativeQuery = true)
	int saveToClientIpTbl(@Param("clientId") Long clientId, @Param("ipId")Long ipId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM CLIENT_IP_TBL WHERE CLIENT_ID = :clientId AND IP_ID = :ipId", nativeQuery = true)
	int deleteFromClientIpTbl(@Param("clientId") Long clientId, @Param("ipId")Long ipId);

}
