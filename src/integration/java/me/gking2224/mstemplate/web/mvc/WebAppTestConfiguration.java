package me.gking2224.mstemplate.web.mvc;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import me.gking2224.common.test.TestConfiguration;
import me.gking2224.common.test.WebAppTestConfigurer;
import me.gking2224.mstemplate.db.DatabaseConfiguration;
import me.gking2224.mstemplate.db.EmbeddedDatabaseConfiguration;
import me.gking2224.mstemplate.web.WebAppConfiguration;

@ComponentScan({"me.gking2224.mstemplate.model", "me.gking2224.mstemplate.service"})
@Import({WebAppConfiguration.class, DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class, TestConfiguration.class})
public class WebAppTestConfiguration extends WebAppTestConfigurer {
}
