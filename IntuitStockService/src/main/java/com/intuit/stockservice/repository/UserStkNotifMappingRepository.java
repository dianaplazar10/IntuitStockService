package com.intuit.stockservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.stockservice.model.UserStkNotifMapping;

@Repository
@Transactional
public interface UserStkNotifMappingRepository extends JpaRepository<UserStkNotifMapping, Long>{
	
	List<UserStkNotifMapping> findByUserId(long userId);
}
