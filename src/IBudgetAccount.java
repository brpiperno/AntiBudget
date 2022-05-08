import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Stores historical data and expected transactions for a single bank account.
 * Allows viewing of historical data, future projections, as well as current performance.
 */
public interface IBudgetAccount {

    String getName();

    /**
     *
     * @param name
     */
    void setName(String name);

    /**
     * Get the description of the account.
     * @return the description of the account.
     */
    String getDescription();

    /**
     * set a new description for the budget account.
     * @param description the new description.
     */
    void setDescription(String description);

    /**
     * Adds an income item to an IAntibudget model.
     * @param transaction the transaction to add.
     */
    void addTransaction(ITransaction transaction);

    /**
     * Remove the Income from the model
     * @param transaction the transaction to remove.
     * @throws IllegalArgumentException if the transaction does not exist in the model.
     */
    void removeTransaction(ITransaction transaction);

    /**
     * Get the transaction of a particular name
     * @param name the name of the transaction
     * @return The transaction object
     * @throws IllegalArgumentException if there is no transaction of the given name.
     */
    ITransaction getTransaction(String name);

    /**
     * See if the model has a transaction of a given name.
     * @param name the name of the transaction
     * @return true if the model has a transaction of the given name, false otherwise.
     */
    boolean hasTransaction(String name);

    /**
     * Generate a list of transaction names that are in the model.
     * @return A list of strings of transaction names in the model.
     */
    List<String> getTransactions();

    /**
     * Sets the expected interest rate for the account. 1 = 100%.
     * @param interest the interest rate to set all transactions within that category to calculate with.
     */
    void setInterestRate(float interest);

    /**
     * Get the expected interest rate for the account.
     * @return the estiamted interest rate for the account. Default is 0%. 1 = 100%.
     */
    float getInterestRate();

    /**
     * enters in an actual value the account
     * @param date the date of the value.
     * @param value the value of the category.
     */
    void enterValue(LocalDateTime date, float value);

    /**
     * Checks if there budget account has a value already entered on the given date.
     * @param date The date to check
     * @return true if there is an entered value, false otherwise.
     */
    boolean hasValue(LocalDateTime date);

    /**
     * Remove the actual value on a given date.
     * @param date the date of the value to remove.
     * @throws IllegalArgumentException if the account does not have a value on the given date.
     */
    void removeValue(LocalDateTime date);

    /**
     * Return the nth value entered.
     * @param index the index of the value.
     * @throws IllegalArgumentException if the model does not have a value entered of the given index.
     */
    float getValue(int index);

    /**
     * Get the actual entered value on a particular day.
     * @param date the date to get the value for.
     * @param interpolate whether the method should interpolate values, or only look for values entered into the model.
     * @return The actualized value of the account on the given date.
     * @throws IllegalArgumentException if there isn't an actualized date if interpolate is false.
     */
    float getValue(LocalDateTime date, boolean interpolate);

    /**
     * Get the estimated net growth/loss on the given day for the budgetaccount
     * @param date the day to look on.
     * @return the amount the account will increase or decrease by on the given day.
     */
    float getNet(LocalDateTime date);

    /**
     * Create a map of all actualized entered values organized by date, within the provided range.
     * @param start the starting date to filter with, inclusive.
     * @param end the ending date to filter with, inclusive.
     * @return a map of date/values for a category.
     * @throws IllegalArgumentException if the starting value is after the ending date.
     */
    Map<LocalDateTime, Float> getActualValues(LocalDateTime start, LocalDateTime end);

    /**
     * Create an approximation of actualized values in a given time frame and level of detail.
     * Uses the account's interest rate to interpolate between actualized dates.
     * @param start the starting date to filter with, inclusive.
     * @param end the ending date to filter with, inclusive.
     * @param unit the level of detail of the values.
     *            For values other than daily, provides values for the first day of the unit (week starting on Sunday)
     * @return a map of date/values for a category.
     * @throws IllegalArgumentException if the starting value is after the ending date.
     * @throws IllegalStateException if the model does not have enough values to interpolate (if it needs to)
     */
    Map<LocalDateTime, Float> interpolate(LocalDateTime start, LocalDateTime end, ChronoUnit unit);

    /**
     * Construct a map of estimated account balances in a given time frame and level of detail.
     * Uses the account's interest rate to estimate growth/loss
     * @param start the starting date to filter with, inclusive.
     * @param end the ending date to filter with, inclusive.
     * @param unit the level of detail of the values.
     *             For values other than daily, provides values for the first day of the unit (week starting on Sunday)
     * @return a map of date/values for the account, representing estimated values.
     * @throws IllegalArgumentException if the starting value is after the ending date.
     */
    Map<LocalDateTime, Float> extrapolateBalance(LocalDateTime start, LocalDateTime end, ChronoUnit unit);

    /**
     * Construct a map of estimated net gains/losses for the account in a given time frame and level of detail.
     * uses the account's interest rate to estimate growth/loss
     * @param start the starting date to filter with, inclusive.
     * @param end the ending date to filter with, inclusive.
     * @param unit the level of detail of the values.
     *             For values other than daily, provides values for the first day of the unit (week starting on Sunday)
     * @return a map of date/values for the account, representing the net gain or loss since the last element.
     * @throws IllegalArgumentException if the starting value is after the ending date.
     */
    Map<LocalDateTime, Float> extrapolateNet(LocalDateTime start, LocalDateTime end, ChronoUnit unit);

    /**
     * Calculate the Rate of Return of the account in a given time frame.
     * @param start the starting date to calculate with, inclusive.
     * @param end the ending date to calculate with, inclusive.
     * @param includeInflation Whether to calculate the real rate of return, or the nominal.
     *                         If true, provides real rate of return.
     * @return the Nominal or Real rate of return for the account in the given time period.
     * @throws IllegalArgumentException if the starting value is after the ending date.
     */
    float calculateRateOfReturn(LocalDateTime start, LocalDateTime end, boolean includeInflation);

    /**
     * See if if
     * @param d
     * @param direction
     * @return
     */
    boolean hasNeighborValue(LocalDateTime d, boolean direction);
}
