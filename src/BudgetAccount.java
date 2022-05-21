import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class BudgetAccount implements IBudgetAccount{
    String name;
    String description;
    float estInterest = 0;
    SortedMap<LocalDateTime, Float> actualValues;
    ArrayList<ITransaction> transactions;

    public BudgetAccount(String name, String description, float interest) {
        this.name = name;
        this.description = description;
        this.estInterest = interest;
        this.actualValues = new TreeMap<>();
        this.transactions = new ArrayList<>();
    }

    @Override
    public String getName() {
        Utils.checkNull(name, "Current name");
        return name;
    }

    @Override
    public void setName(String name) {
        Utils.checkNull(name, "Provided name");
        this.name = name;
    }

    @Override
    public String getDescription() {
        Utils.checkNull(description, "Current Description");
        return description;
    }

    @Override
    public void setDescription(String description) {
        Utils.checkNull(description, "Provided Description");
        this.description = description;
    }

    @Override
    public void addTransaction(ITransaction transaction) {
        Utils.checkNull(transaction, "Provided ITransaction");
        if (hasTransaction(transaction.getName())){
            throw new IllegalArgumentException("Transaction of name: " + transaction.getName() + "already in model.");
        }
        this.transactions.add(transaction);
    }

    @Override
    public void removeTransaction(ITransaction transaction) {
        Utils.checkNull(transaction, "Provided ITransaction");
        if (!hasTransaction(transaction.getName())){
            throw new IllegalArgumentException("Transaction of name: " + transaction.getName() + "not in model.");
        }
        this.transactions.remove(transaction);
    }

    @Override
    public ITransaction getTransaction(String name) {
        List<ITransaction> l = Utils.filter(this.transactions, (p) -> Objects.equals(p.getName(), name));
        if (l.isEmpty()){
            throw new IllegalArgumentException("No Transaction of name: " + name);
        }
        return l.get(0);
    }

    @Override
    public boolean hasTransaction(String name) {
        return Utils.andMap(transactions, (p) -> Objects.equals(p.getName(), name));
    }

    @Override
    public List<String> getTransactions() {
        return Utils.map(this.transactions, ITransaction::getName);
    }

    @Override
    public void setInterestRate(float interest) {
        this.estInterest = interest;
    }

    @Override
    public float getInterestRate() {
        return this.estInterest;
    }

    @Override
    public void enterValue(LocalDateTime date, float value) {
        Utils.checkNull(date, "Entered Date");
        if (hasValue(date)) {
            this.actualValues.replace(date, value);
        }
        else {
            this.actualValues.put(date, value);
        }
    }

    @Override
    public boolean hasValue(LocalDateTime date) {
        return this.actualValues.containsKey(date);
    }

    @Override
    public void removeValue(LocalDateTime date) {
        Utils.checkNull(date, "Entered Date");
        if (hasValue(date)) {
            this.actualValues.remove(date);
        }
        else {
            throw new IllegalArgumentException("Model does not have a value on the given date: " + date);
        }
    }

    @Override
    public float getValue(int index) {
        if (index < 0 || index >= this.actualValues.size()) {
            throw new IllegalArgumentException("Index out of bounds. Requested index " + index +
                    " of size " + this.actualValues.size());
        }
        int i = 0;
        for ( Float f : this.actualValues.values()) {
            i++;
            if (i == index) {
                return f;
            }
        }
        throw new IllegalStateException("Could not find value at index: " + index);
    }

    @Override
    public float getValue(LocalDateTime date, boolean interpolate) {
        return 0;
    }

    @Override
    public float getNet(LocalDateTime date) {
        return 0;
    }

    @Override
    public Map<LocalDateTime, Float> getActualValues(LocalDateTime start, LocalDateTime end) {
        Map<LocalDateTime, Float> m = new HashMap<>(this.actualValues);
        m.keySet().removeIf(s -> Utils.dateWithinRange(s, start, end));
        return m;
    }

    @Override
    public Map<LocalDateTime, Float> interpolate(LocalDateTime start, LocalDateTime end, ChronoUnit unit) {
        //construct an ordered list of actualized dates within the range (pad one additional value on each side)

        //construct a list of values (nulls included) of non-interpolated actualized values.

        //for each null in the list, see if it has enough values on each side to interpolate, or extrapolate
    }

    @Override
    public Map<LocalDateTime, Float> extrapolateBalance(LocalDateTime start, LocalDateTime end, ChronoUnit unit) {
        return null;
    }

    @Override
    public Map<LocalDateTime, Float> extrapolateNet(LocalDateTime start, LocalDateTime end, ChronoUnit unit) {
        return null;
    }

    @Override
    public float calculateRateOfReturn(LocalDateTime start, LocalDateTime end, boolean includeInflation) {
        return 0;
    }

}
