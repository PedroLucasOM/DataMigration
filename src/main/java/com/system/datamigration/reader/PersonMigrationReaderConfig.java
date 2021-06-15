package com.system.datamigration.reader;

import com.system.datamigration.model.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

import java.io.IOException;
import java.util.Date;

@Configuration
public class PersonMigrationReaderConfig {

    @Bean
    public FlatFileItemReader<Person> personMigrationReader(
            @Value("file:${spring-batch-learning.input-folder}people.csv") Resource input
    ) throws IOException {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personMigrationReader")
                .resource(new FileSystemResource(input.getFile()))
                .delimited()
                .names("name", "email", "birthDate", "age", "id")
                .addComment("--")
                .fieldSetMapper(fieldSetMapper())
                .build();
    }

    private FieldSetMapper<Person> fieldSetMapper() {
        return new FieldSetMapper<Person>() {
            @Override
            public Person mapFieldSet(FieldSet fieldSet) throws BindException {
                Person person = new Person();
                person.setName(fieldSet.readString("name"));
                person.setEmail(fieldSet.readString("email"));
                person.setBirthDate(new Date(fieldSet.readDate("birthDate", "yyyy-MM-dd HH:mm:ss").getTime()));
                person.setAge(fieldSet.readInt("age"));
                person.setId(fieldSet.readInt("id"));
                return person;
            }
        };
    }

}
