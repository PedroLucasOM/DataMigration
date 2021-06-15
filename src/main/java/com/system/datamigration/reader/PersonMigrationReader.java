package com.system.datamigration.reader;

import com.system.datamigration.model.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import java.util.Date;

@Configuration
public class PersonMigrationReader {

    @Bean
    public FlatFileItemReader<Person> personMigrationReader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personMigrationReader")
                .resource(new FileSystemResource("files/people.csv"))
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
                person.setBirthDate(new Date(String.valueOf(fieldSet.readDate("birthDate", "yyyy-MM-dd HH-mm-ss"))));
                person.setAge(fieldSet.readInt("age"));
                person.setId(fieldSet.readInt("id"));
                return person;
            }
        };
    }

}
