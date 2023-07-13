package com.kjslocal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kjslocal.entities.IpEntity;

@Repository
public interface IpTableRepository extends JpaRepository<IpEntity, Long>{
	
	Optional<IpEntity> findByAddress(String address);

}
