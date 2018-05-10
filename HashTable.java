import java.util.ArrayList;

public class HashTable<K,V>
{
	//create an arraylist of hashnodes
	ArrayList<HashNode<K,V>> array = new ArrayList<>();
	
	int size = 0;
	//constructor
	public HashTable(int initialCapacity)
	{
		//initialize the hashNodes in the array to null
		for (int i = 0; i < initialCapacity; i++)
		{
            array.add(null);
		}
	}

	//get hashindex
	private int getHashNodeIndex(K key)
	{
		int hashCode=key.hashCode();
		int index = hashCode%array.size();
		//if hashcode gives negative number, make it positive
		if (index<0)
		{
			index = index*(-1);
		}
		return index;
	}

	//get method
	public V get (K key)
	{
		int index=getHashNodeIndex(key);
		HashNode<K, V> head=array.get(index);
		//look through the hashnode linkedlist at the index we got to find and return the key
		while(head!=null)
		{
			if(head.key.equals(key))
			{
				return head.value;
			}
			else
			{
				head=head.next;
			}
		}
		//if key is not found, return null
		return null;
	}

	//put method
	public V put(K key, V value)
	{
		resize(key, value);
		int index=getHashNodeIndex(key);
		//find head of the linkedlist you are inserting key into
		HashNode<K, V> head=array.get(index);
		//check if key is already present in that linkedlist
		//if key is present, replace it with the new key,value
		while(head!=null)
		{
			if(head.key.equals(key))
			{
				head.value=value;
				return value;
			}
			head=head.next;
		}
		//else, insert key into the hashnode
		//create a new node with the key and value you want to insert, and insert it into the arraylist
        HashNode<K,V> newNode = new HashNode<K,V>();
        newNode.key = key;
        newNode.value = value;
        newNode.next = head;
        array.set(index, newNode);
        //increase size after inserting node
        size++;
        return value;
    }

		//method to double the size of the arraylist and rehash all nodes if loadfactor goes above default of .7
		public boolean resize(K key, V value)
		{
        if ((size/array.size())>=.7)
        {
        	//set a temp array to rehash all the keys with the new arraylist length
        	ArrayList<HashNode<K,V>> temp = new ArrayList();
        	//set temp to a copy of every hashnode in array
        	for (int x = 0; x<array.size(); x++)
        	{
        		temp.add(array.get(x));
        	}

        	//get double the length of the original arraylist
        	int newlength = array.size()*2;

        	//make array a new arraylist again so you can refill it
        	array = new ArrayList<>();
        	size = 0;

        	//initialize every node in arraylist to null
        	//make it the new length
        	for (int i = 0; i<newlength; i++)
        	{
        		array.add(null);
        	}

        	//take every node from temp and rehash into array now that it is double the size
        	for (HashNode<K,V> node : temp)
        	{
        		//since I set everything in the arraylist to null initially, I have to check if the node is null before I rehash and add it back
        		if (node!=null)
        		{
        			put(node.key, node.value);
        		}
        	}
        	return true;
        }
        return false;
	}

}