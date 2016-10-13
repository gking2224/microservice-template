package me.gking2224.mstemplate;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import me.gking2224.common.test.CommonTestConfiguration;
import me.gking2224.mstemplate.db.DatabaseConfiguration;
import me.gking2224.mstemplate.db.EmbeddedDatabaseConfiguration;

@ComponentScan({"me.gking2224.mstemplate.model", "me.gking2224.mstemplate.service"})
@EnableJpaRepositories("me.gking2224.mstemplate.db.jpa")
@Import({DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class, CommonTestConfiguration.class})
public class TestConfiguration {

}
