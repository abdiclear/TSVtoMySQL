package habibstuff.TSVtoSQL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class TsVtoSqlApplication {

	public static void main(String[] args) {

		//Enter the complete path to your TSV/CSV file within the empty quotes
		System.setProperty("input", "file:" + new File("").getAbsolutePath());

		SpringApplication.run(TsVtoSqlApplication.class, args);
	}

}
