# Project 4
Here is our last project! It is due by **Thursday, May 10th**. This time, you will need to do an implementation and also some measurements.
> **Both** Part 1 and Part 2 are due on Thursday, May 10th and all files must be uploaded to GitHub BEFORE the deadline.
 

## What to submit:

1. Your own Hashtable class

2. Code that measures the time insertions and lookups take using 1) and using Java's Hashtable

3. Two charts based on the measurements from 2

4. **README** explaining your Hashtable class, esp. how your Hashtable rehashes when the table is too full.
>This README can either be a new README, README.txt, or add onto the beginning of this README.md. This is in addition to comments in your code.

 

## Part 1: Implement your own Hashtable class.

You may want to read [Java API on Hashtable class](https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html 'Hash Table Doc'). Your Hashtable class must include the following constructor and methods. You can change the initial capacity and the load factor. You may add variables, methods, or constructors.

### Constructor  
| Hashtable(int initialCapacity) |
| :------------------------------ |
|Constructs a new, empty hashtable with the specified initial capacity and default load factor (0.75).|

### Methods  
| V 	get(Object key)  |
|:----------------------|
|Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.|

|V 	put(K key, V value)  |
|:----------------------|
|Maps the specified key to the specified value in this hashtable.|

Note that K and V in the description can be any type/class in Java. You may use Object as the type of key and value.


## Part 2: Compare the performance of your Hashtable and Java's Hashtable.

The goal is for you to produce charts like those in [this website](https://github.com/fredrikwidlund/hash-table-benchmark 'Benchmark'). Insert all the data from the provided files for String and Integer and look up all the keys. For String insertion and lookup, use 10millioncombos.txt (You can get this file from Canvas) of 10,000,000 passwords (look for yours!) from [this website](https://xato.net/today-i-am-releasing-ten-million-passwords-b6278bbe7495). Each row of this file contains a username and a password. Use the username as key and the password as value. For Integer insertion and lookup, generate a random number between `Integer.MAX_VALUE` and `Integer.MIN_VALUE` use it as key and value.

Here is the code to measure the time it takes for one look-up.

		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		...
		long start = System.currentTimeMillis();
		String v = ht.get(k);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
