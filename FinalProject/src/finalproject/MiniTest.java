package finalproject;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyHashTableADV<K, V> extends MyHashTable<K, V>{
    public MyHashTableADV() {super();}

    public MyHashTableADV(int c) {
        super(c);
    }

    public void setEle(MyPair<K, V> p) {
        this.getBuckets().get(hashFunction(p.getKey())).add(p);
    }
}

class MiniTest {

    @Test
    @Tag("score:1")
    @DisplayName("DefaultConstructorTest")
    public void DefaultConstructor() {
        MyHashTable h = new MyHashTable<>();

        assertEquals(0, h.size());
        assertEquals(16, h.numBuckets());
    }

    @Test
    @Tag("score:1")
    @DisplayName("CapacityConstructorTest")
    public void CapacityConstructor() {
        MyHashTable h = new MyHashTable<>(21);

        assertEquals(0, h.size());
        assertEquals(21, h.numBuckets());
    }
    @Test
    @Tag("score:1")
    @DisplayName("PutTest")
    public void PutTest() {
        MyHashTable h = new MyHashTable<>(2);

        assertEquals(0, h.size());
        assertEquals(2, h.numBuckets());

        h.put(1, "Hello");
        assertEquals(1, h.size());
        ArrayList<LinkedList<MyPair<String, Integer>>> L = h.getBuckets();

        assertEquals(0, L.get(0).size());
        assertEquals(1, L.get(1).size());
        assertEquals("Hello", L.get(1).get(0).getValue());
    }

    @Test
    @Tag("score:1")
    @DisplayName("GetTest")
    public void GetTest() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(6);

        h.setEle(new MyPair<Integer, String>(1, "Hello"));
        h.setEle(new MyPair<Integer, String>(2, "World"));
        h.setEle(new MyPair<Integer, String>(3, "Goodbye"));
        h.setEle(new MyPair<Integer, String>(4, "World"));

        assertEquals("Hello", h.get(1));
        assertEquals("Goodbye", h.get(3));
        assertNull(h.get(0));
    }

    @Test
    @Tag("score:1")
    @DisplayName("GetTest2")
    public void GetTest2() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(12);

        h.setEle(new MyPair<Integer, String>(1, "A"));
        h.setEle(new MyPair<Integer, String>(2, "B"));
        h.setEle(new MyPair<Integer, String>(3, "C"));
        h.setEle(new MyPair<Integer, String>(4, "D"));

        h.setEle(new MyPair<Integer, String>(13, "E"));
        h.setEle(new MyPair<Integer, String>(14, "F"));
        h.setEle(new MyPair<Integer, String>(15, "G"));
        h.setEle(new MyPair<Integer, String>(16, "H"));

        assertNull(h.get(5));
        assertEquals("B", h.get(2));
        assertEquals("D", h.get(4));
        assertEquals("F", h.get(14));
        assertEquals("H", h.get(16));
    }

    @Test
    @Tag("score:1")
    @DisplayName("RemoveTest1")
    public void RemoveTest1() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(6);

        h.setEle(new MyPair<Integer, String>(1, "Hello"));
        h.setEle(new MyPair<Integer, String>(2, "World"));
        h.setEle(new MyPair<Integer, String>(3, "Goodbye"));
        h.setEle(new MyPair<Integer, String>(4, "World"));

        assertNull(h.remove(5));
        assertEquals("World", h.remove(2));
        assertNull(h.remove(2));
    }

    @Test
    @Tag("score:1")
    @DisplayName("RemoveTest2")
    public void RemoveTest2() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(12);

        h.setEle(new MyPair<Integer, String>(1, "A"));
        h.setEle(new MyPair<Integer, String>(2, "B"));
        h.setEle(new MyPair<Integer, String>(3, "C"));
        h.setEle(new MyPair<Integer, String>(4, "D"));

        h.setEle(new MyPair<Integer, String>(13, "E"));
        h.setEle(new MyPair<Integer, String>(14, "F"));
        h.setEle(new MyPair<Integer, String>(15, "G"));
        h.setEle(new MyPair<Integer, String>(16, "H"));

        assertNull(h.remove(0));
        assertEquals("B", h.remove(2));
        assertNull(h.remove(2));
        assertEquals("F", h.remove(14));
        assertNull(h.remove(14));
    }

    @Test
    @Tag("score:2")
    @DisplayName("RehashTest")
    public void RehashTest1() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(6);

        h.setEle(new MyPair<Integer, String>(3, "Hello"));
        h.setEle(new MyPair<Integer, String>(9, "World"));
        h.setEle(new MyPair<Integer, String>(15, "Goodbye"));
        h.setEle(new MyPair<Integer, String>(21, "World"));

        h.rehash();

        ArrayList<LinkedList<MyPair<Integer, String>>> L = h.getBuckets();
        assertEquals(2, L.get(3).size());
        assertEquals(2, L.get(9).size());
    }

    @Test
    @Tag("score:3")
    @DisplayName("RehashTest2")
    public void RehashTest2() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(12);

        h.setEle(new MyPair<Integer, String>(1, "A"));
        h.setEle(new MyPair<Integer, String>(2, "B"));
        h.setEle(new MyPair<Integer, String>(3, "C"));
        h.setEle(new MyPair<Integer, String>(4, "D"));

        h.setEle(new MyPair<Integer, String>(13, "E"));
        h.setEle(new MyPair<Integer, String>(14, "F"));
        h.setEle(new MyPair<Integer, String>(15, "G"));
        h.setEle(new MyPair<Integer, String>(16, "H"));

        h.rehash();

        ArrayList<LinkedList<MyPair<Integer, String>>> L = h.getBuckets();
        assertEquals(1, L.get(1).size());
        assertEquals(1, L.get(2).size());
        assertEquals(1, L.get(3).size());
        assertEquals(1, L.get(4).size());
        assertEquals(1, L.get(13).size());
        assertEquals(1, L.get(14).size());
        assertEquals(1, L.get(15).size());
        assertEquals(1, L.get(16).size());
    }

    @Test
    @Tag("score:3")
    @DisplayName("getKeySetTest1")
    public void getKeySet1() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(12);

        h.setEle(new MyPair<Integer, String>(1, "A"));
        h.setEle(new MyPair<Integer, String>(2, "B"));
        h.setEle(new MyPair<Integer, String>(3, "C"));
        h.setEle(new MyPair<Integer, String>(4, "D"));

        h.setEle(new MyPair<Integer, String>(13, "E"));
        h.setEle(new MyPair<Integer, String>(14, "F"));
        h.setEle(new MyPair<Integer, String>(15, "G"));
        h.setEle(new MyPair<Integer, String>(16, "H"));

        List<Integer> expected = List.of(1, 2, 3, 4, 13, 14, 15, 16);
        ArrayList<Integer> received = h.getKeySet();
        received.sort(Comparator.naturalOrder());

        assertEquals(expected, received);
    }

    @Test
    @Tag("score:2")
    @DisplayName("getValueSetTest1")
    public void getValueSet1() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(10);

        h.setEle(new MyPair<Integer, String>(3, "Hello"));
        h.setEle(new MyPair<Integer, String>(9, "World"));
        h.setEle(new MyPair<Integer, String>(15, "Goodbye"));
        h.setEle(new MyPair<Integer, String>(21, "World"));

        List<String> expected = List.of("Goodbye", "Hello", "World");
        ArrayList<String> received = h.getValueSet();
        received.sort(Comparator.naturalOrder());

        assertEquals(expected, received);
    }

    @Test
    @Tag("score:3")
    @DisplayName("getValueSetTest2")
    public void getValueSet2() {
        MyHashTableADV<Integer, Character> h = new MyHashTableADV<Integer, Character>(12);

        h.setEle(new MyPair<Integer, Character>(1, 'A'));
        h.setEle(new MyPair<Integer, Character>(2, 'B'));
        h.setEle(new MyPair<Integer, Character>(3, 'C'));
        h.setEle(new MyPair<Integer, Character>(4, 'D'));

        h.setEle(new MyPair<Integer, Character>(13, 'E'));
        h.setEle(new MyPair<Integer, Character>(14, 'F'));
        h.setEle(new MyPair<Integer, Character>(15, 'G'));
        h.setEle(new MyPair<Integer, Character>(16, 'H'));

        ArrayList<Character> received = h.getValueSet();
        received.sort(Comparator.naturalOrder());
        for (int i = 0; i < 8; i++)
            assertEquals((char) ('A' + i), received.get(i));
    }

    @Test
    @Tag("score:2")
    @DisplayName("getEntriesTest1")
    public void getEntries1() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(10);
        List<MyPair<Integer, String>> expected = List.of(new MyPair<Integer, String>(3, "Hello"),
                new MyPair<Integer, String>(9, "World"),
                new MyPair<Integer, String>(15, "Goodbye"), new MyPair<Integer, String>(21, "World"));
        for (MyPair<Integer, String> p : expected)
            h.setEle(p);

        ArrayList<MyPair<Integer, String>> received = h.getEntries();
        received.sort(Comparator.comparing(MyPair<Integer, String>::getKey));

        assertEquals(expected, received);
    }

    @Test
    @Tag("score:3")
    @DisplayName("getEntriesTest2")
    public void getEntries2() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(12);

        List<MyPair<Integer, String>> expected = List.of(new MyPair<Integer, String>(1, "A"),
                new MyPair<Integer, String>(2, "B"),
                new MyPair<Integer, String>(3, "C"), new MyPair<Integer, String>(4, "D"),
                new MyPair<Integer, String>(13, "E"),
                new MyPair<Integer, String>(14, "F"), new MyPair<Integer, String>(15, "G"),
                new MyPair<Integer, String>(16, "H"));

        for (MyPair<Integer, String> p : expected)
            h.setEle(p);

        ArrayList<MyPair<Integer, String>> received = h.getEntries();
        received.sort(Comparator.comparing(MyPair<Integer, String>::getKey));

        assertEquals(expected, received);
    }

    @Test
    @Tag("score:2")
    @DisplayName("IteratorTest1")
    public void Iterator1() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(10);
        ArrayList<MyPair<Integer, String>> expected = new ArrayList<>(
                List.of(new MyPair<Integer, String>(3, "Hello"), new MyPair<Integer, String>(9, "World"),
                        new MyPair<Integer, String>(15, "Goodbye"), new MyPair<Integer, String>(21, "World")));
        for (MyPair<Integer, String> p : expected)
            h.setEle(p);

        for (MyPair<Integer, String> p : h) {
            assertTrue(expected.remove(p));
        }
        assertEquals(0, expected.size());
    }

    @Test
    @Tag("score:3")
    @DisplayName("IteratorTest2")
    public void Iterator2() {
        MyHashTableADV<Integer, String> h = new MyHashTableADV<Integer, String>(12);

        ArrayList<MyPair<Integer, String>> expected = new ArrayList<>(
                List.of(new MyPair<Integer, String>(1, "A"), new MyPair<Integer, String>(2, "B"),
                        new MyPair<Integer, String>(3, "C"), new MyPair<Integer, String>(4, "D"),
                        new MyPair<Integer, String>(13, "E"),
                        new MyPair<Integer, String>(14, "F"), new MyPair<Integer, String>(15, "G"),
                        new MyPair<Integer, String>(16, "H")));

        for (MyPair<Integer, String> p : expected)
            h.setEle(p);

        for (MyPair<Integer, String> p : h) {
            assertTrue(expected.remove(p));
        }
        assertEquals(0, expected.size());
    }

    @Test
    @Tag("score:2")
    @DisplayName("RatingDistributionByProfessor")
    public void RatingDistributionByProfessorTest() {
        Parser parser = new Parser("/src/finalproject/RateMyProf_Data_Gendered_Sample.csv");
        parser.read();
        RatingDistributionByProf ratingDistributionByProf = new RatingDistributionByProf(parser);
        MyHashTable<String, Integer> dist = ratingDistributionByProf.getDistByKeyword("soazig  le bihan");
        assertEquals(1, dist.get("1"));
        assertEquals(0, dist.get("2"));
        assertEquals(1, dist.get("3"));
        assertEquals(0, dist.get("4"));
        assertEquals(2, dist.get("5"));
    }

    @Test
    @Tag("score:2")
    @DisplayName("RatingDistributionBySchool")
    public void RatingDistributionBySchoolTest() {
        Parser parser = new Parser("/src/finalproject/RateMyProf_Data_Gendered_Sample.csv");
        parser.read();
        RatingDistributionBySchool ratingDistributionBySchool = new RatingDistributionBySchool(parser);
        MyHashTable<String, Integer> dist = ratingDistributionBySchool
                .getDistByKeyword("pennsylvania college of technology");
        String key2 = "david  burke" + "\n" + "4.33";

        assertEquals(6, dist.get(key2));
    }

    @Test
    @Tag("score:2")
    @DisplayName("GenderByKeyword")
    public void GenderByKeyword() {
        Parser parser = new Parser("/src/finalproject/RateMyProf_Data_Gendered_Sample.csv");
        parser.read();
        GenderByKeyword genderByKeyword = new GenderByKeyword(parser);
        MyHashTable<String, Integer> dist = genderByKeyword.getDistByKeyword("amazing");
        assertEquals(31, dist.get("F"));
        assertEquals(1, dist.get("X"));
        assertEquals(19, dist.get("M"));

    }

    @Test
    @Tag("score:2")
    @DisplayName("RatingByKeyword")
    public void RatingByKeyword() {
        Parser parser = new Parser("/src/finalproject/RateMyProf_Data_Gendered_Sample.csv");
        parser.read();
        RatingByKeyword ratingByKeyword = new RatingByKeyword(parser);
        MyHashTable<String, Integer> dist = ratingByKeyword.getDistByKeyword("terrible");
        for (String key : dist.getKeySet()) {
            System.out.println(key);
            System.out.println(dist.get(key));
        }

        assertEquals(12, dist.get("1"));
        assertEquals(6, dist.get("2"));
        assertEquals(3, dist.get("3"));
        assertEquals(0, dist.get("4"));
        assertEquals(0, dist.get("5"));
    }

    @Test
    @Tag("score:2")
    @DisplayName("RatingByGender")
    public void RatingByGender() {
        Parser parser = new Parser("/src/finalproject/RateMyProf_Data_Gendered_Sample.csv");
        parser.read();
        RatingByGender ratingByGender = new RatingByGender(parser);
        MyHashTable<String, Integer> dist = ratingByGender.getDistByKeyword("F,difficulty");

        assertEquals(83, dist.get("1"));
        assertEquals(90, dist.get("2"));
        assertEquals(126, dist.get("3"));
        assertEquals(73, dist.get("4"));
        assertEquals(57, dist.get("5"));
    }
}