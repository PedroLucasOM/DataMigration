package com.system.datamigration.step;

import com.system.datamigration.model.Person;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonMigrationStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step personMigrationStep(
            ItemReader<Person> personMigrationReader,
            ItemWriter<Person> personMigrationWriter
    ) {
        return stepBuilderFactory
                .get("personMigrationStep")
                .<Person, Person>chunk(1)
                .reader(personMigrationReader)
                .writer(personMigrationWriter)
                .build();
    }

}
