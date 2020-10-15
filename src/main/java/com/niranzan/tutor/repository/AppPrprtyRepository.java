/**
 * 
 */
package com.niranzan.tutor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.niranzan.tutor.model.AppPrprty;

/**
 * @author Niranjan
 *
 */

@Repository
public interface AppPrprtyRepository extends JpaRepository<AppPrprty, Long> {
	public Optional<AppPrprty> findByPrprtyKey(String key);
	public Boolean existsByPrprtyKey(String prprtyKey);
	@Query("SELECT CASE WHEN COUNT(PRPRTY) > 0 THEN 'true' ELSE 'false' END FROM AppPrprty PRPRTY WHERE PRPRTY.prprtyKey = :prprtyKey and PRPRTY.id != :id")
    Boolean existsByPrprtyKeyExceptThisPrprty(@Param("id") Long id, @Param("prprtyKey") String prprtyKey);
}