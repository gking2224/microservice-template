package me.gking2224.mstemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.dao.CrudDao;
import me.gking2224.common.service.AbstractCrudServiceImpl;
import me.gking2224.mstemplate.db.dao.ThingDao;
import me.gking2224.mstemplate.model.Thing;

@Component
@Transactional(readOnly=true)
public class ThingServiceImpl extends AbstractCrudServiceImpl<Thing, Long> implements ThingService {

    @Autowired
    private ThingDao dao;

    public ThingServiceImpl() {
    }

    @Override
    protected CrudDao<Thing, Long> getDao() {
        return dao;
    }
}
