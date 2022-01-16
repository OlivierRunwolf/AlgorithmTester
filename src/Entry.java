import java.util.Objects;
/*
Class containing the main elements of the hash map
 */
public class Entry{
    int k;
    int v;

    /**
     * constructor for the Entry class
     * @param k this is where the key is initialized
     * @param v this is where the value is initialized
     */
    public Entry(int k,int v){
        this.k=k;
        this.v=v;
    }

    /**
     * getter method for the keys
     * @return the key variable
     */
    public int getKey() {
        return k;
    }

    /**
     * getter method for the value
     * @return the value variable
     */
    public int getValue() {
        return v;
    }

    /**
     * setter method for the value
     * @param v the value variable
     */
    public void setValue(int v) {
        this.v = v;
    }

    /**
     * Overriden equals method to make sure both the key and value are the same, are from the same class, and not from the same memory refernce
     * @param o the object being compared
     * @return a boolean wether or not two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        //i mod N
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return k == entry.k && v == entry.v;
    }

    /**
     * Hashcode function for hashing the keys before inserting them into the hash table
     * @return the hash value
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.k;
        return result;
    }
}
