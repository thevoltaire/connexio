package com.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entities.Division;

@Repository
public interface DivisionDao extends JpaRepository<Division, Long> {

}
