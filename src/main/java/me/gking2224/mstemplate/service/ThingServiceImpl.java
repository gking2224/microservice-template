package me.gking2224.mstemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.mstemplate.db.dao.ThingDao;
import me.gking2224.mstemplate.model.Thing;

@Component
@Transactional(readOnly=true)
public class ThingServiceImpl implements ThingService {


    @Autowired
    private ThingDao dao;

    public ThingServiceImpl() {
    }

    @Override
    @Transactional(readOnly=false)
    public Thing createThing(Thing thing) {
        return dao.createThing(thing);
    }

    @Override
    public List<Thing> findAllThings() {
        return dao.findAllThings();
    }

    @Override
    @Transactional(readOnly=false)
    public Thing updateThing(Thing thing) {
        return dao.updateThing(thing);
    }

    @Override
    @Transactional(readOnly=false)
    public void deleteThing(Long id) {
        dao.deleteThing(id);
    }

    @Override
    public Thing findThingById(Long id) {
        return dao.findThingById(id);
    }

}
