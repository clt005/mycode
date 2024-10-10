import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyHashTableTest {
    private MyHashTable hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new MyHashTable();
        Assertions.assertNotNull(hashTable);
        Assertions.assertEquals(0, hashTable.size());
        Assertions.assertEquals(19, hashTable.capacity());
    }

    @Test
    void constructorTest() {
        MyHashTable hashTable2 = new MyHashTable(100);
        Assertions.assertEquals(0, hashTable2.size());
        Assertions.assertEquals(100, hashTable2.capacity());
        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {new MyHashTable(1); });
    }

    @Test
    void insertTest() {
        Assertions.assertTrue(hashTable.insert("string1"));
        Assertions.assertEquals(1, hashTable.size());
        Assertions.assertTrue(hashTable.insert("string2"));
        Assertions.assertTrue(hashTable.insert("string3"));
        Assertions.assertFalse(hashTable.insert("string1"));
        Assertions.assertEquals(3, hashTable.size());
        Assertions.assertThrows(NullPointerException.class, () ->
        {hashTable.insert(null);});
    }

    @Test
    void removeTest() {
        hashTable.insert("string1");
        hashTable.insert("string2");
        hashTable.insert("string3");
        Assertions.assertEquals(3, hashTable.size());
        Assertions.assertTrue(hashTable.delete("string1"));
        Assertions.assertEquals(2, hashTable.size());
        Assertions.assertTrue(hashTable.delete("string2"));
        Assertions.assertEquals(1, hashTable.size());
        Assertions.assertFalse(hashTable.delete("string4"));
        Assertions.assertEquals(1, hashTable.size());
        Assertions.assertThrows(NullPointerException.class, () ->
        {hashTable.delete(null);});
    }

    @Test
    void lookupTest() {
        hashTable.insert("string1");
        hashTable.insert("string2");
        Assertions.assertEquals(2, hashTable.size());
        Assertions.assertTrue(hashTable.lookup("string1"));
        Assertions.assertTrue(hashTable.lookup("string2"));
        Assertions.assertFalse(hashTable.lookup("string3"));
        Assertions.assertThrows(NullPointerException.class, () ->
        {hashTable.lookup(null);});
    }

    @Test
    void returnAllTest() {
        for (int i = 1; i <= 10; i++) {
            hashTable.insert("str" + i);
        }
        Assertions.assertEquals(10, hashTable.size());
        String[] output = hashTable.returnAll();
        Assertions.assertEquals(10, output.length);
        Assertions.assertArrayEquals(new String[]
                {"str1","str2","str3","str4","str5","str6","str7","str8","str9","str10"}, output);
        Assertions.assertEquals(10, hashTable.size());
        for (int i = 11; i <= 100; i++) {
            hashTable.insert("str" + i);
        }
        Assertions.assertEquals(100, hashTable.size());
        String[] output2 = hashTable.returnAll();
        Assertions.assertEquals(100, output2.length);
    }

    @Test
    void insertRehashTest() {
        for (int i = 1; i <= hashTable.capacity(); i++) {
            hashTable.insert("str" + i);
        }
        Assertions.assertEquals(19, hashTable.size());
        Assertions.assertEquals(19, hashTable.capacity());
        for (int i = 1; i <= 10; i++) {
            hashTable.insert("string" + i);
        }
        Assertions.assertEquals(29, hashTable.size());
        Assertions.assertEquals(38, hashTable.capacity());
        for (int i = 1; i <= 20; i++) {
            hashTable.insert("s" + i);
        }
        Assertions.assertEquals(49, hashTable.size());
        Assertions.assertEquals(76, hashTable.capacity());
    }

    @Test
    void getStatsLogTest() {
        MyHashTable specialHashTable = new MyHashTable(8);
        specialHashTable.insert("string1");
        specialHashTable.insert("string2");
        specialHashTable.insert("string3");
        specialHashTable.insert("string4");
        specialHashTable.insert("string5");
        specialHashTable.insert("string6");
        specialHashTable.insert("string7");
        specialHashTable.insert("string8");
        specialHashTable.insert("string9");
        String statsLog = specialHashTable.getStatsLog();
        String[] statsLogArray = statsLog.split("\n");
        Assertions.assertEquals(1, statsLogArray.length);
        String expectedLogEntry = "Before rehash # 1: load factor 1.13, 0 collision(s).";
        Assertions.assertEquals(expectedLogEntry, statsLogArray[0]);
    }
}
