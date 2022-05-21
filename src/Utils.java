import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Various static utility methods for use
 */
public class Utils {

    /**
     * Checks if any value in the list return true from the Predicate
     * @param list list of objects of type E.
     * @param p predicate that checks if an object in the list is true.
     * @param <E> type of object to check.
     * @return True if at least one value in the list is true. If the list is empty, return false.
     */
    public static <E> boolean orMap(List<E> list, Predicate<E> p){
        for (E item : list){
            if (p.test(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if all values in the list return true from the Predicate.
     * @param list list of objects of type E.
     * @param p predicate that checks if an object in the list is true.
     * @param <E> type of object to check.
     * @return True if all values in the list are true. If the list is empty, return false.
     */
    public static <E> boolean andMap(List<E> list, Predicate<E> p){
        if (list.isEmpty()) {
            return false;
        }
        for (E item : list){
            if (!p.test(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Filter the give list according to the predicate.
     * @param list the list of objects to filter.
     * @param p predicate that checks if an object is true.
     * @param <E> the type of object to check.
     * @return The subset of objects that return true from the predicate.
     */
    public static <E> List<E> filter(List<E> list, Predicate<E> p){
        List<E> filtered = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (p.test(list.get(i))) {
                filtered.add(list.get(i));
            }
        }
        return filtered;
    }

    /**
     * Map a list of objects of E to objects of T
     * @param list the list of objects of E
     * @param func a function that converts from E to T
     * @param <E> a class of the original list
     * @param <T> a class of the new list
     * @return a list of T objects
     */
    public static <E,T> List<T> map(List<E> list, Function<E, T> func){
        List<T> output = new ArrayList<>(list.size());
        for (E item : list) {
            output.add(func.apply(item));
        }
        return output;
    }

    public static void checkNull(Object o, String s){
        if (o == null) {
            throw new NullPointerException(s + " is null.");
        }
    }

    public static boolean dateWithinRange(LocalDateTime d, LocalDateTime s, LocalDateTime e){
        return (!d.isAfter(e) && !d.isBefore(s));
    }

    public static Float interpolate(LocalDateTime second, Float aFloat2,
                                    LocalDateTime first, Float aFloat1, LocalDateTime current) {

    }
}
