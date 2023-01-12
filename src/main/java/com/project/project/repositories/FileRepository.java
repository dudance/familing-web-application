package com.project.project.repositories;

import com.project.project.models.File;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {


    @Query(value = "Select nextval(pg_get_serial_sequence('file', 'id')) as new_id;", nativeQuery = true)
    public Long getNextId();
}
