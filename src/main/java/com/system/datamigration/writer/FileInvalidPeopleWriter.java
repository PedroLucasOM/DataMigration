package com.system.datamigration.writer;

import com.system.datamigration.model.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class FileInvalidPeopleWriter {

    @Bean
    public FlatFileItemWriter<Person> fileInvalidPeopleWriter() {
        return new FlatFileItemWriterBuilder<Person>()
                .name("fileInvalidPeopleWriter")
                .resource(new FileSystemResource("files/invalid-people.csv"))
                .delimited()
                .names("id")
                .build();
    }

}
