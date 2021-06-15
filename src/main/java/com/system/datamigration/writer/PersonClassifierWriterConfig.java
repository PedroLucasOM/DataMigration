package com.system.datamigration.writer;

import com.system.datamigration.model.Person;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonClassifierWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<Person> personClassifierWriter(
            JdbcBatchItemWriter<Person> personBankWriter,
            FlatFileItemWriter<Person> fileInvalidPeopleWriter) {
        return new ClassifierCompositeItemWriterBuilder<Person>()
                .classifier(classifier(personBankWriter, fileInvalidPeopleWriter))
                .build();
    }

    @SuppressWarnings("serial")
    private Classifier<Person, ItemWriter<? super Person>> classifier(JdbcBatchItemWriter<Person> bancoPessoaWriter,
                                                                      FlatFileItemWriter<Person> arquivoPessoasInvalidasWriter) {
        return new Classifier<Person, ItemWriter<? super Person>>() {

            @Override
            public ItemWriter<? super Person> classify(Person person) {
                if (person.isValid())
                    return bancoPessoaWriter;
                else
                    return arquivoPessoasInvalidasWriter;
            }

        };
    }

}
