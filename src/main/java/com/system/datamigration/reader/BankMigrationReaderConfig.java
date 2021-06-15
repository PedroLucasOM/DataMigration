package com.system.datamigration.reader;

import com.system.datamigration.model.Bank;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class BankMigrationReaderConfig {

    @Bean
    public FlatFileItemReader<Bank> bankDataMigrationReader(
            @Value("file:${spring-batch-learning.input-folder}bank.csv") Resource input
    ) throws IOException {
        return new FlatFileItemReaderBuilder<Bank>()
                .name("bankMigrationReader")
                .resource(new FileSystemResource(input.getFile()))
                .delimited()
                .names("personId", "agency", "account", "bank", "id")
                .addComment("--")
                .targetType(Bank.class)
                .build();
    }

}
