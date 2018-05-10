public class HashNode<K,V>
{
	//HashNode is a node of chains
	//initialize key and value as object K and V
	K key;
	V value;

	//holds a reference to the next node, linkedlist implementation
	HashNode<K, V> next; 

	//constructor
	public HashNode ()
	{
		this.key=key;
		this.value=value;
	}
}