package com.tec13.core.server.repository;

import com.tec13.core.server.domain.BaseDomain;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface BaseRepository<D extends BaseDomain, ID>  extends CrudRepository<D, ID>  {
}
