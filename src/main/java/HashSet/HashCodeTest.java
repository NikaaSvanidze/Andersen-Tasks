package HashSet;
import java.util.Iterator;

public class HashCodeTest {
    public static void main(String[] args) {
        MyHashSet hashSet = new MyHashSet(5);

        hashSet.put("Apple");
        hashSet.put("Banana");
        hashSet.put("Cherry");

        System.out.println("Contains 'Banana': " + hashSet.contains("Banana")); // true
        System.out.println("Contains 'Grape': " + hashSet.contains("Grape"));   // false

        System.out.println("Elements of HashSet:");
        Iterator<Object> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        hashSet.delete("Banana");

        System.out.println("Updated Elements of HashSet:");
        iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
