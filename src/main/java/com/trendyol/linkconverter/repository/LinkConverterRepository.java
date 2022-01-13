package com.trendyol.linkconverter.repository;

import com.trendyol.linkconverter.dto.LinkDto;
import com.trendyol.linkconverter.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkConverterRepository extends JpaRepository<Link, Long> {
}
