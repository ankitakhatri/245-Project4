README for Ankita Khatri -- Project 4

Classes:
HashNode
HashTable
Driver

HashNode:
My HashNode class is a class with objects of K and V to represent keys and values. This class can work with any type of object-- String, Integer, etc.
The HashNode stores the key, value, and pointer to the next node.

HashTable:
My actual HashTable is an arraylist of HashNodes, and I set each node to null at the beginning, in regards to the initial capacity. Each HashNode contains a linkedlist because I used an open hashing method. The constructor takes the initial capacity as the parameter. My getHashCode function uses the java built in HashCode function, and then I do that number%size of the arraylist, which is whatever my initial capacity is, or the new size if I have it resized. In my put method, which takes key and value as parameter, I use my getHashCode function to find an index to insert the new HashNode. I first check if the key is already present in that chain, and if it is, I just replace the value. If it is not present, I add it to the end of the linkedlist. If the loadfactor>.7, which is the default value for loadfactor, I double the size of the arraylist by creating a temp copy of the original, doubling the length and reinitializing the original, and then taking all the values from the temp and rehashing them into the array with the new doubled size. For my get function, I just rehashed the key to find the index we are looking for, and go through the chain at that index to find the HashNode I am looking for. 

Driver:
In the driver, I read the first million lines from the 10millioncombos file and hashed the username and password values using both the java built-in hashmap, and my own hashtable. I set my initial capacity for my HashTable to 1000. I also generated 1 million random integers and hashed those values both ways. While hashing, I found the lookup times for both the built-in and my own get(key) methods and stored the 0, 250000, 500000, and 1000000 time benchmarks in an arraylist. After hashing and calculating total lookup time for strings and integers, using both hashtables, I wrote the benchmark values into a .csv file to open in excel, which will turn into a line graph.