package me.gking2224.mstemplate.batch;


import java.util.Arrays;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import me.gking2224.mstemplate.batch.jobs.DummyJobBatchConfiguration;

@Configuration
@ComponentScan(basePackages={"me.gking2224.mstemplate.batch.jobs"})
@Import({
    InitMainBatchStep.class,
    DummyJobBatchConfiguration.class
})
public class MainBatch {
    
    private boolean async = true;
    
    @Autowired
    private JobBuilderFactory jobs;
    
    @Autowired
    private StepBuilderFactory steps;
    
    @Autowired @Qualifier("batchProperties")
    private Properties batchProperties;

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(MainBatch.class);
    
    @Bean("mainRefDataBatch")
    public Job mainBatch(
            @Qualifier("initMainBatch") Step initBatch,
            @Qualifier("jobOne") Flow jobOne
    ) {
        SimpleJobBuilder builder = jobs.get("mainBatchJob")
            .start(initBatch);
        
        Flow[] flows = new Flow[] { jobOne };
        if (async)
            async(builder, flows);
        else
            sequential(builder, flows);
        return builder.build();
    }

    private void async(final SimpleJobBuilder builder, final Flow... flows) {


        builder.split(new SimpleAsyncTaskExecutor())
            .add(flows);
    }

    private void sequential(final SimpleJobBuilder builder, final Flow... flows) {
        
        Arrays.asList(flows).forEach(f -> builder.next(flowStep(f)));
    }

    private Step flowStep(Flow f) {
        return steps.get(f.getName()+"Flow").flow(f).build();
    }
}
