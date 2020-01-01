package habibstuff.TSVtoSQL.batch;

import habibstuff.TSVtoSQL.model.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

//This class is where you perform any necessary magic on the row of data you read from the file.
//You can also just simply slap a Logger here to keep track of proceedings line by line.
public class SingleRowProcessor implements ItemProcessor<Row, Row> {

    //Your logger. It logs.
    private static final Logger Log = LoggerFactory.getLogger(SingleRowProcessor.class);


    @Override
    public Row process(final Row row) throws Exception {

        /**
         *
         * The magic happens here!
         *
         */

        //And here is where the logger logs.
        Log.info("Logging" + row);

        return row;
    }
}
