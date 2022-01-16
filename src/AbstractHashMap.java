/**
 * abstract hash table class to implement abstract methods for other hash table class
 */
public abstract class AbstractHashMap {
    protected int n=0;// n is the number of elements in the hash table
    protected int capacity;// capacity is a variable to keep track of how big the table is when initialized

    /**
     * constructor for the hash table class
     * @param cap
     */
    public AbstractHashMap(int cap){
        capacity=cap;
    }
    /*
    abstract method that compresses a hashed key to ensure it fits in the table
     */
    public int compressHash(int key){
        return hashCode(key) % capacity;
    }
    public abstract int size();//method that returns the size of the table or the variable capacity
    public abstract boolean isEmpty();//method that checks whether or not a table has no elements in it
    public abstract int get(int k);// method that searched for an entry with certain entry @k in the table and then returns its value
    public abstract int put(int k,int v);// method to insert an element in the table with a key @k and a value @v
    public abstract int remove(int k);// method that remove an entry with the key @k from the hashtable
    /*
    compression function for hashing keys
     */
    public int hashCode(int k){
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + k;
        return result;
    }
}
