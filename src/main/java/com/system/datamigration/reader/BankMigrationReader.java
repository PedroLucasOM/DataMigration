package com.system.datamigration.reader;

import com.system.datamigration.model.Bank;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class BankMigrationReader {

    @Bean
    public FlatFileItemReader<Bank> bankDataMigrationReader() {
        return new FlatFileItemReaderBuilder<Bank>()
                .name("bankMigrationReader")
                .resource(new FileSystemResource("files/bank.csv"))
                .delimited()
                .names("personId", "agency", "account", "bank", "id")
                .addComment("--")
                .targetType(Bank.class)
                .build();
    }

}
