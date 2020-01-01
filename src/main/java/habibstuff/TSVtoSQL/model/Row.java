package habibstuff.TSVtoSQL.model;

//This class models what a row of data from the TSV file looks like. This translates to the SQL table too.
public class Row {


    private long id; //The primary key

    /**
     *
     * Insert all the relevant columns as private class variables here
     * An example column will be placed below
     *
     */

    private double netIncome;

    //A default constructor is necessary here for some file reading classes
    public Row(){

    }


    //This constructor should have all your defined variables as parameters, and should be set accordingly aside from the predefined id
    //Remove id from the parameter onc
    public Row(double netIncome) {
        this.id = 0;
        this.netIncome = netIncome;
    }

    //All your defined variables should also have getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(double netIncome) {
        this.netIncome = netIncome;
    }

    //This method should contain all your defined variables as well
    @Override
    public String toString() {
        return "Row{" +
                "id=" + id +
                ", netIncome=" + netIncome +
                '}';
    }
}
