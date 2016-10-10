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
    public Thing create(Thing thing) {
        return dao.create(thing);
    }

    @Override
    public List<Thing> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly=false)
    public Thing update(Thing thing) {
        return dao.update(thing);
    }

    @Override
    @Transactional(readOnly=false)
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Thing findById(Long id) {
        return dao.findById(id);
    }
}
