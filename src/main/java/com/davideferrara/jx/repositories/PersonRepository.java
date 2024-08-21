package com.davideferrara.jx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.davideferrara.jx.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
