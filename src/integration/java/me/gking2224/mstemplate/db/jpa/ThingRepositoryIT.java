package me.gking2224.mstemplate.db.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.mstemplate.TestConfiguration;
import me.gking2224.mstemplate.model.Thing;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"embedded"})
@ContextConfiguration(classes=TestConfiguration.class)
@Transactional
@Rollback
public class ThingRepositoryIT {

    @Autowired
    protected ThingRepository repository;
    
    @Test
    public void testSave() {
        String name = "thingName";
        
        Thing p = new Thing(name);
        Thing saved = repository.save(p);
        assertNotNull(saved);
        assertEquals(name, saved.getName());
    }
    
    @Test
    @Sql("../../SingleThing.sql")
    public void testFindOne() {
        
        Thing p = repository.findOne(1L);
        
        assertNotNull(p);
        assertEquals("Test Thing", p.getName());
    }
}
