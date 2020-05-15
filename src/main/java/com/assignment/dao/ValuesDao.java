package com.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entities.Value;

@Repository
public interface ValuesDao extends JpaRepository<Value, Long>{

}
