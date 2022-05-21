import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Abstract representation of a transaction
 */
public class Transaction implements ITransaction {
    private String name; //the name of the transaction
    private String description; // a text description of the transaction
    private ArrayList<LocalDateTime> dates = new ArrayList<>(); // the dates of the transaction
    private float amount; // the amount of the transaction

    public Transaction(String name, String description, float amount, LocalDateTime date){
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.dates.add(date);
    }


    @Override
    public float getValue(LocalDateTime date, float interest) {
        //TODO include calculation for getValue
        return 0;
    }

    @Override
    public float getValue(LocalDateTime date) {
        //TODO
        return 0;
    }

    @Override
    public float getAnnualValue(float interest) {
        //TODO include calculation for getAnnualValue
        return 0;
    }

    @Override
    public ArrayList<LocalDateTime> getTransactions(LocalDateTime start, LocalDateTime end) {
        Utils.checkNull(start, "Start Date");
        Utils.checkNull(start, "End Date");
        ArrayList<LocalDateTime> output = new ArrayList<>(this.dates);
        output.removeIf(s -> s.isBefore(start) || s.isAfter(end));
        return output;
    }

    @Override
    public ArrayList<LocalDateTime> getTransactions() {
        return new ArrayList<LocalDateTime>(this.dates);
    }

    @Override
    public void removeInstance(LocalDateTime date) {
        Utils.checkNull(date, "Date");
        if (!this.hasInstance(date)) {
            throw new IllegalArgumentException("Transaction has no instance on given date: " + date);
        }
        this.dates.remove(date);
    }

    @Override
    public void removeInstance(int index) {
        if (index > this.dates.size()-1 || index < 0){
            throw new IllegalArgumentException("Provided index is out of bounds of this Transaction. Given index is " +
                    index + ". Transaction has " + this.dates.size() + " instances");
        }
        this.dates.remove(index);
    }

    @Override
    public void addInstance(LocalDateTime date) {
        Utils.checkNull(date, "Date");
        if (this.hasInstance(date)) {
            throw new IllegalArgumentException("Transaction already has an instance on " + date + ".");
        }
        this.dates.add(date);
        Collections.sort(this.dates);
        //TODO: implement quicksort
    }

    @Override
    public boolean hasInstance(LocalDateTime date) {
        Utils.checkNull(date, "Date");
        return this.dates.contains(date);
    }

    @Override
    public boolean hasInstance(LocalDateTime start, LocalDateTime end) {
        Utils.checkNull(start, "Start Date");
        Utils.checkNull(start, "End Date");
        return Utils.orMap(this.dates, (d) -> !(d.isAfter(end) || d.isBefore(start)));
    }

    @Override
    public int numInstances() {
        return this.dates.size();
    }

    @Override
    public LocalDateTime startDate() {
        return this.dates.get(0);
    }

    @Override
    public LocalDateTime endDate() {
        return this.dates.get(this.dates.size()-1);
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void changeDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
