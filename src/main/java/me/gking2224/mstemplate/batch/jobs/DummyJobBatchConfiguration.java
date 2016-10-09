package me.gking2224.mstemplate.batch.jobs;

import java.util.Properties;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.gking2224.common.batch.generic.AbstractBatchConfiguration;

@Configuration
public class DummyJobBatchConfiguration extends AbstractBatchConfiguration {

    @Autowired @Qualifier("batchProperties")
    private Properties batchProperties;
    
    @Autowired
    private StepBuilderFactory steps;
    
    @Bean("job1")
    public Flow job1() {
        return new FlowBuilder<Flow>(getFlowName()).start(dummyStep()).build();
    }
    
    private Step dummyStep() {
        return steps.get("dummyStep").tasklet(dummyTasklet()).build();
    }

    private Tasklet dummyTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Override
    protected String getFlowName() {
        return "job1";
    }
}
