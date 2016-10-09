package me.gking2224.mstemplate.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import me.gking2224.mstemplate.model.Thing;

public interface ThingRepository extends JpaRepository<Thing, Long>{

}
