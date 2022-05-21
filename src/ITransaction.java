import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A representation of a transaction within a budget model. It can either be positive (income) or negative (expense).
 * A transaction can be onetime or recurring. A transaction can have a static amount, or a scaling one
 */
public interface ITransaction {

    /**
     * Calculate the value of the transaction according to a specific date and interest rate
     * @param date the date of reference to calculate value
     * @param interest the interest rate for the transaction
     * @return the value of the transaction
     */
    float getValue(LocalDateTime date, float interest);

    /**
     * Get the value of the transaction on a given day.
     * @param date the date to filter to.
     * @return the value of the instance on the given date.
     * If there is no instance of the transaction on the given date, return 0.
     */
    float getValue(LocalDateTime date);

    /**
     * Calculate the annual value of the transaction
     * @param interest the interest rate for the calculation
     * @return the annual value of the transaction
     */
    float getAnnualValue(float interest);

    /**
     * Create a list of all dates that the transaction takes place between two dates
     * @param start the starting date
     * @param end the endign date
     * @return the list of dates that the transaction falls between. Returns an empty list if there are none.
     */
    ArrayList<LocalDateTime> getTransactions(LocalDateTime start, LocalDateTime end);

    /**
     * Create a list of all dates that the transaction is active for
     * @return the list of dates that the transaction falls upon. Returns an empty list if there are none.
     */
    ArrayList<LocalDateTime> getTransactions();

    /**
     * Remove an instance of an expense.
     * @param date the date of the transaction to be removed.
     * @throws IllegalArgumentException if there is no transaction on that date.
     */
    public void removeInstance(LocalDateTime date);

    /**
     * Remove the kth instance of the transaction.
     * @param index the index of the transaction.
     */
    public void removeInstance(int index);

    /**
     * Add a new instance of the transaction. Multiple instances of the same transaction cannot exist on the same date.
     * Dates continue to be stored in chronological order.
     * @param date the date the instance is added on.
     * @throws IllegalArgumentException if there is already an instance of the transaction on the given date.
     */
    public void addInstance(LocalDateTime date);

    /**
     * Check if the transaction has an instnace on the given date.
     * @param date the date to check.
     * @return True if there is an instance of the transaction on the given date, false otherwise.
     */
    public boolean hasInstance(LocalDateTime date);

    /**
     * Check if the transaction has an instance in the provided range.
     * @param start the starting date to check
     * @param end the ending date to check
     * @return true if there is at least one instance that falls within the range, false otherwise.
     * @throws IllegalArgumentException if the end date is before the start date.
     * @throws IllegalArgumentException if null values are provided.
     */
    public boolean hasInstance(LocalDateTime start, LocalDateTime end);

    /**
     * Return the number of instances the transaction has.
     * @return the number of instances.
     */
    public int numInstances();

    /**
     * Return the earliest date of the transaction.
     * @return the first date of the transaction.
     */
    public LocalDateTime startDate();

    /**
     * Return the latest date of the transaction.
     * @return the last date of the transaction.
     */
    public LocalDateTime endDate();

    /**
     * Return the description of the transaction.
     * @return the description of the transaction.
     */
    public String getDescription();

    /**
     * Change the description of the transaction.
     * @param description the new description of the transaction
     */
    public void changeDescription(String description);

    /**
     * Return the name of the transaction.
     * @return the name of the transaction.
     */
    public String getName();

    /**
     * Change the name of the transaction.
     * @param name the new name of the transaction.
     */
    public void setName(String name);

    float getAmount();

    void setAmount(float amount);
}
