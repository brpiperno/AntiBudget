import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    ITransaction t;
    ArrayList<LocalDateTime> l;

    @BeforeEach
    void setUp() {
        t = new Transaction("Test", "Test Description", 100.54f, LocalDateTime.MAX);
        l = new ArrayList<>();
        l.add(LocalDateTime.MAX);
    }

    @AfterEach
    void tearDown() {
        l.clear();
    }

    @Test
    void getValue() {
        //TODO: write test
    }

    @Test
    void testGetValue() {
        //TODO: write test
    }

    @Test
    void getAnnualValue() {
        //TODO: write test
    }

    @Test
    @DisplayName("Transactions within a range")
    void testGetTransactionsWithDates() {
        assertEquals(new ArrayList<LocalDateTime>(), t.getTransactions(LocalDateTime.MIN, LocalDateTime.MIN));
        l.add(0, LocalDateTime.MIN);
        t.addInstance(LocalDateTime.MIN);
        assertEquals(l, t.getTransactions(LocalDateTime.MIN, LocalDateTime.MAX));
        l.remove(0);
        assertEquals(l, t.getTransactions(LocalDateTime.MAX, LocalDateTime.MAX));
    }

    @Test
    @DisplayName("Get all instances of a transaction")
    void testGetTransactions() {
        assertEquals(l, t.getTransactions());
        l.add(0, LocalDateTime.MIN);
        t.addInstance(LocalDateTime.MIN);
        assertEquals(l, t.getTransactions());
    }

    @Test
    @DisplayName("Remove an instance by date")
    void removeInstance() {
        assertThrows(IllegalArgumentException.class, () -> t.removeInstance(LocalDateTime.MIN));
        t.removeInstance(LocalDateTime.MAX);
        assertEquals(new ArrayList<LocalDateTime>(), t.getTransactions());
    }

    @Test
    @DisplayName("Remove an instance by index")
    void testRemoveInstance() {
        assertThrows(IllegalArgumentException.class, () -> t.removeInstance(2));
        assertThrows(IllegalArgumentException.class, () -> t.removeInstance(1));
        assertThrows(IllegalArgumentException.class, () -> t.removeInstance(-1));
        t.removeInstance(0);
        t.addInstance(LocalDateTime.MAX);
        t.addInstance(LocalDateTime.MIN);
        t.removeInstance(1);
    }

    @Test
    @DisplayName("Add instance by date")
    void addInstance() {
        assertThrows(IllegalArgumentException.class, () -> t.addInstance(LocalDateTime.MAX));
        t.addInstance(LocalDateTime.MIN);
        l.add(0, LocalDateTime.MIN);
        assertEquals(l, t.getTransactions());
    }

    @Test
    @DisplayName("See if it has an instance on a specific date")
    void hasInstanceDate() {
        assertEquals(true, t.hasInstance(LocalDateTime.MAX));
        assertEquals(false, t.hasInstance(LocalDateTime.MIN));
        assertThrows(IllegalArgumentException.class, () -> t.hasInstance(null));
    }

    @Test
    @DisplayName("Has an instance within a range")
    void testHasInstanceRange(){
        assertThrows(IllegalArgumentException.class, () -> t.hasInstance(null, LocalDateTime.MAX));
        assertThrows(IllegalArgumentException.class, () -> t.hasInstance(LocalDateTime.MAX, null));
        assertThrows(IllegalArgumentException.class, () -> t.hasInstance(null, null));
        assertEquals(true, t.hasInstance(LocalDateTime.MAX, LocalDateTime.MAX));
        assertEquals(true, t.hasInstance(LocalDateTime.MIN, LocalDateTime.MAX));
        assertEquals(false, t.hasInstance(LocalDateTime.MIN, LocalDateTime.MIN));
    }

    @Test
    void numInstances() {
        assertEquals(1, t.numInstances());
        t.addInstance(LocalDateTime.MIN);
        assertEquals(2, t.numInstances());
    }

    @Test
    void startDate() {
        assertEquals(LocalDateTime.MAX, t.startDate());
        t.addInstance(LocalDateTime.MIN);
        assertEquals(LocalDateTime.MIN, t.startDate());
    }

    @Test
    void endDate() {
        assertEquals(LocalDateTime.MAX, t.endDate());
    }

    @Test
    void getDescription() {
        assertEquals("Test Description", t.getDescription());
    }

    @Test
    void changeDescription() {
        t.changeDescription("Test");
        assertEquals("Test", t.getDescription());
    }


}