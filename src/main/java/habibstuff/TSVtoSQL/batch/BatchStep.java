package habibstuff.TSVtoSQL.batch;

import habibstuff.TSVtoSQL.model.Row;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;


//We call this BatchStep because there can be more than one of these.
//Here is where we define a file reader, a row processor, and a database table writer
@Configuration
public class BatchStep {

    @Bean
    public FlatFileItemReader<Row> fileReader(@Value("${input}") Resource in) throws Exception{

        /**
         *
         * This is where we can customize our file reader.
         * Note that we set linesToSkip to 1 to skip the column headers row.
         * If you want to read a CSV file instead of a TSV file, simply replace DELIMITER_TAB with DELIMITER_COMMA
         * Within setNames, place in all variable names from Row/ column headers of your file
         *
          */
        return new FlatFileItemReaderBuilder<Row>()
                .name("file-reader")
                .resource(in)
                .linesToSkip(1)
                .lineTokenizer(new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_TAB) {{
                    setNames(new String[]{"incomeTax"});
                }})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Row>() {{
                    setTargetType(Row.class);
                }})
                .build();

    }


    //Here is where we call on our data-manipulation magic and/or logger
    @Bean
    public SingleRowProcessor processor(){
        return new SingleRowProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Row> jdcbWriter(DataSource ds){

        //Here is where we write to the MySQL table.
        return new JdbcBatchItemWriterBuilder<Row>()
                .dataSource(ds)
                .sql("INSERT INTO exampleTaxTable (id, incomeTax) VALUES (0, :incomeTax)")
                .beanMapped()
                .build();


    }


}
