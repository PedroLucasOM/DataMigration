package com.system.datamigration.step;

import com.system.datamigration.model.Bank;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankMigrationStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step bankMigrationStep(
            ItemReader<Bank> bankMigrationReader,
            ItemWriter<Bank> bankMigrationWriter
    ) {
        return stepBuilderFactory
                .get("bankMigrationStep")
                .<Bank, Bank>chunk(1)
                .reader(bankMigrationReader)
                .writer(bankMigrationWriter)
                .build();
    }
}
