package me.gking2224.mstemplate.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.mstemplate.db.jpa.ThingRepository;
import me.gking2224.mstemplate.model.Thing;

@ManagedResource(objectName="dao:name=thingDaoImpl", description="ThingDao", log=true,
logFile="jmx.log", currencyTimeLimit=15, persistPolicy="Never")
@Component
@Transactional
public class ThingDaoImpl extends AbstractDaoImpl<Thing> implements ThingDao {

    @Autowired
    protected ThingRepository thingRepository;
    
    private int lastLoadCount = 0;
    
    public ThingDaoImpl() {
    }

    @Override
    public Thing create(Thing thing) {
        Thing saved = thingRepository.save(thing);
        return saved;
    }

    @Override
    public List<Thing> findAll() {
        List<Thing> things = thingRepository.findAll();
        this.lastLoadCount = things.size();
        return things;
    }

    @Override
    public Thing update(Thing thing) {
        Thing saved = thingRepository.save(thing);
        return saved;
    }
    
    @Override
    public void delete(Long id) {
        thingRepository.delete(id);
    }

    @Override
    public Thing findById(Long id) {
        Thing thing = thingRepository.findOne(id);
        return thing;
    }

    @ManagedAttribute
    public int getLastLoadCount() {
        return lastLoadCount;
    }
}
