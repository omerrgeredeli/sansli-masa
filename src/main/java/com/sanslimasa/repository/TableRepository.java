package com.sanslimasa.repository;

import com.sanslimasa.model.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

    TableEntity findByNumber(int number);

}
