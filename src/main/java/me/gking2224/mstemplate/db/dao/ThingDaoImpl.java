package me.gking2224.mstemplate.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.mstemplate.db.jpa.ThingRepository;
import me.gking2224.mstemplate.model.Thing;

@Component
@Transactional
public class ThingDaoImpl extends AbstractDaoImpl<Thing> implements ThingDao {

    @Autowired
    protected ThingRepository thingRepository;
    
    
    public ThingDaoImpl() {
    }

    @Override
    public Thing createThing(Thing thing) {
        Thing saved = thingRepository.save(thing);
        return saved;
    }

    @Override
    public List<Thing> findAllThings() {
        List<Thing> things = thingRepository.findAll();
        return things;
    }

    @Override
    public Thing updateThing(Thing thing) {
        Thing saved = thingRepository.save(thing);
        return saved;
    }
    
    @Override
    public void deleteThing(Long id) {
        thingRepository.delete(id);
    }

    @Override
    public Thing findThingById(Long id) {
        Thing thing = thingRepository.findOne(id);
        return thing;
    }
}
