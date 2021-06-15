package com.system.datamigration.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class DataMigrationJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job dataMigrationJob(
            @Qualifier("personMigrationStep") Step personMigrationStep,
            @Qualifier("bankMigrationStep") Step bankMigrationStep
    ) {
        jobBuilderFactory
                .get("dataMigrationJob")
                .start(personMigrationStep)
                .next(bankMigrationStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
