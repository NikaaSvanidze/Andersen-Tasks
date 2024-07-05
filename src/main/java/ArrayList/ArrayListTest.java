package ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        MyArrayList arrayList = new MyArrayList(5);


        arrayList.put("Apple");
        arrayList.put("Banana");
        arrayList.put("Cherry");

        System.out.println("Size of ArrayList: " + arrayList.size() + " " + arrayList.get(2));
    }
}
