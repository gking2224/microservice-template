package me.gking2224.mstemplate.db.dao;

import java.util.List;

import me.gking2224.mstemplate.model.Thing;

public interface ThingDao {

    Thing createThing(Thing thing);

    List<Thing> findAllThings();

    Thing updateThing(Thing thing);

    void deleteThing(Long id);

    Thing findThingById(Long id);


}