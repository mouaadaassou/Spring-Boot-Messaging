package io.nodom.redis.repository;

import io.nodom.redis.domain.Rate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository  extends JpaRepository<Rate,String>{
		List<Rate> findByDate(Date date);
		Rate findByDateAndCode(Date date,String code);
}
