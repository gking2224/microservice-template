package me.gking2224.mstemplate.service;

import java.util.List;

import me.gking2224.mstemplate.model.Thing;

public interface ThingService {

    Thing create(Thing thing);

    List<Thing> findAll();

    Thing update(Thing thing);

    void delete(Long id);

    Thing findById(Long id);

}
