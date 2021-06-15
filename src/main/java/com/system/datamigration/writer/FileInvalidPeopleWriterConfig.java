package com.system.datamigration.writer;

import com.system.datamigration.model.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class FileInvalidPeopleWriterConfig {

    @Bean
    public FlatFileItemWriter<Person> fileInvalidPeopleWriter(
            @Value("file:${spring-batch-learning.output-folder}invalid-people.csv") Resource output
    ) throws IOException {
        return new FlatFileItemWriterBuilder<Person>()
                .name("fileInvalidPeopleWriter")
                .resource(new FileSystemResource(output.getFile()))
                .delimited()
                .names("id")
                .build();
    }

}
