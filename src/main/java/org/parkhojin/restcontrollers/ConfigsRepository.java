package org.parkhojin.restcontrollers;

import org.parkhojin.entities.Configs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigsRepository extends JpaRepository<Configs,String> {
}
