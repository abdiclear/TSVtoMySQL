package habibstuff.TSVtoSQL.batch;

import habibstuff.TSVtoSQL.model.Row;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchDeployment {

    /**
     *
     * Here is the automation part of the process.
     * This basically executes the methods in BatchStep sequentially through the file provided.
     * What we are saying with chunk is that the job will execute the read and process methods on 100 lines of data, and then write
     * those 100 rows into the database. It'll repeat that for the next 100 lines and so on.
     */
    @Bean
    Job job(JobBuilderFactory jbf,
            StepBuilderFactory sbf,
            BatchStep step) throws Exception {

        Step s1 = sbf.get("file-db")
                .<Row, Row>chunk(100)
                .reader(step.fileReader(null))
                .processor(step.processor())
                .writer(step.jdcbWriter(null))
                .build();

        return jbf.get("etl")
                .incrementer(new RunIdIncrementer())
                .start(s1)
                .build();

    }

}
