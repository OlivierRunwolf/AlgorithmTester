import java.util.ArrayList;
import java.util.Random;
/*
this is the concrete class for the hashtable
 */
public class MyHashMap extends AbstractHashMap {
    private ArrayList<ArrayList<Entry>> table;
    long startTime;
    long endTime;
    long timeElapsedInsert;

    public MyHashMap(int cap) {
        super(cap);
    }// this constructor initializes the variable capacity of its extended class

    public void createTable() {//this method actually initializes the array table and fills it with empty buckets
        table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(new ArrayList<Entry>());
        }
    }

    /**
     * this method returns the index of a hashed compressed key
     * @param key the key that will be used to lookup
     * @return the index of the table
     */
    public int getIndex(int key) {
        return compressHash(key);
    }

    @Override
    public int size() {
        return capacity;
    }//method that returns the capacity variable

    @Override
    public boolean isEmpty() {
        return n == 0;
    }//method that checks wether or not the table has elements

    @Override//method that searches for an element with key @k in the table and returns its value if fouind
    public int get(int k) {
        startTime = System.currentTimeMillis();
        int index = getIndex(k);
        ArrayList<Entry> top = table.get(index);
        //when the bucket is found it also gets the first element within the bucket since they all have the same index
        for (int i = 0; i < top.size(); i++) {
            if (top.get(i).getKey() == (int) k) {
                endTime = System.currentTimeMillis();
                timeElapsedInsert = endTime - startTime;
                System.out.println("Time take for get(k): "+timeElapsedInsert);
                return top.get(i).getValue();
            }
        }
        endTime = System.currentTimeMillis();
        timeElapsedInsert = endTime - startTime;
        System.out.println("Time take for get(k): "+timeElapsedInsert);
        return 0;
    }

    /**
     * //element that insert an element with key @k and value @v into the table, if an entry in the table has the same key it replaces that entry with the
     * new entry
     * @param k a key
     * @param v a value
     * @return the entry that is being inserted
     */
    @Override
    public int put(int k, int v) {
        startTime = System.currentTimeMillis();
        int indexBucket = getIndex(k);
        ArrayList<Entry> tempList = table.get(indexBucket);
        Entry newEntry;
        int collissions = 0;
        if (tempList != null) {
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i).getKey() == k) {
                    tempList.get(i).setValue(v);
                    return tempList.get(i).getValue();
                }
            }
        }

        n++;//If the key doesnt exist already, it created a new entry with the variable k and v, compresses its key and inserts it in the table
        newEntry = new Entry(k, v);
        table.get(indexBucket).add(newEntry);
        collissions = tempList.size();
        System.out.println("put(k,V):Element inserted, Size of the table "+capacity+", Number of elements in the table "+n+", Collisions encontered: "+(collissions-1)+" Number of items in bucket storing "+newEntry.getValue()+" : "+collissions);
        endTime = System.currentTimeMillis();
        timeElapsedInsert = endTime - startTime;
        System.out.println("Time take for put(k,v): "+timeElapsedInsert);
        return newEntry.getValue();
    }
    /*
    this method removes an entry with the key @k in the table if it exists
     */
    @Override
    public int remove(int k) {
        startTime = System.currentTimeMillis();
        int indexBucket = getIndex(k);
        ArrayList<Entry> tempList = table.get(indexBucket);
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).getKey() == k) {
                n--;
                endTime = System.currentTimeMillis();
                timeElapsedInsert = endTime - startTime;
                System.out.println("Time take for remove(k): "+timeElapsedInsert);
                return tempList.remove(i).getValue();
            }
        }
        endTime = System.currentTimeMillis();
        timeElapsedInsert = endTime - startTime;
        System.out.println("Time take for remove(k): "+timeElapsedInsert);
        return 0;
    }
    /*
    this is a method that was asked to be put but the assignement
     */
    public void validate(){
        Random r = new Random();
        int low = 0;
        int high = 100;
        ArrayList<int[]> validateList = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            int[] temp = {r.nextInt(high-low)+low,r.nextInt(high-low)+low};
            validateList.add(temp);
        }

        MyHashMap validate = new MyHashMap(100);
        validate.createTable();
        for(int[] entry: validateList){
            validate.put(entry[0],entry[1]);
        }
        for(int[] entry: validateList){
            validate.get(entry[0]);
        }
        for(int i=0;i<25;i++){
            validate.remove(validateList.get(i)[0]);
        }
        for(int[] entry: validateList){
            validate.get(entry[0]);
        }

        validate.printList();
    }
    /*
    this is a method that was asked to be put but the assignement
     */
    public void experiment_interpret(){
        MyHashMap validate;
        System.out.println("Time take for remove(k): "+timeElapsedInsert);
        Random r = new Random();
        int low = 0;
        int high = 3000;
        ArrayList<int[]> validateList = new ArrayList<>(150);
        for (int i = 0; i < 150; i++) {
            int[] temp = {r.nextInt(high-low)+low,r.nextInt(high-low)+low};
            validateList.add(temp);
        }

        int[] loads = {25,50,75,100,125,150};

        for(int i=0;i<loads.length;i++){
            System.out.println("Now procesesing "+loads[i]+"....elements please stand-by");
            validate = new MyHashMap(100);
            validate.createTable();
            startTime = System.currentTimeMillis();
            for(int j=0;j<loads[i];j++){
                validate.put(validateList.get(j)[0],validateList.get(j)[1]);
            }
            endTime = System.currentTimeMillis();
            timeElapsedInsert = endTime - startTime;
            System.out.println("Time to run for "+loads[i]+" entries is "+timeElapsedInsert+"....load factor is...."+((double) validate.n/(double) validate.capacity));
         //   validate.printList();
        }

    }
    public void printList() {
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).size() > 0) {
                System.out.print("Bucket[" + i + "] -> ");
                for (int j = 0; j < table.get(i).size(); j++) {
                    System.out.print("|{key:" + table.get(i).get(j).getKey() + ", value:" + table.get(i).get(j).getValue() + "}");
                }
                System.out.println('\n');
            }
        }
    }
}
