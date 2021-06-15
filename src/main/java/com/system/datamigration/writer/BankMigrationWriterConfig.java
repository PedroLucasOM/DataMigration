package com.system.datamigration.writer;

import com.system.datamigration.model.Bank;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BankMigrationWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Bank> bankMigrationWriter(
            @Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Bank>()
                .dataSource(dataSource)
                .sql("INSERT INTO bank (id, person_id, agency, account, bank) VALUES (:id, :personId, :agency, :account, :bank)")
                .beanMapped()
                .build();
    }

}
