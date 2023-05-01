public class MyHashTable<K, V> {
    private int CONST_SIZE = 11;
    private HashNode<K, V>[] table;
    private int size;

    public MyHashTable() {
        table = new HashNode[CONST_SIZE];
        size = 0;
    }

    public MyHashTable(int CONST_SIZE) {
        this.CONST_SIZE = CONST_SIZE;
        table = new HashNode[CONST_SIZE];
        size = 0;
    }

    // Hash function to determine index in table
    private int hash(K key) {
        return key.hashCode() % CONST_SIZE;
    }

    // Add or update a key-value pair in table
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            HashNode<K, V> currentNode = table[index];
            while (currentNode.getNext() != null) {
                if (currentNode.getKey().equals(key)) {
                    currentNode.setValue(value);
                    return;
                }
                currentNode = currentNode.getNext();
            }

            if (currentNode.getKey().equals(key)) {
                currentNode.setValue(value);
            } else {
                currentNode.setNext(newNode);
            }
        }

        size++;
    }
    // Getting the value associated with the key
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> currentNode = table[index];

        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }

        return null;
    }
    // Return an array with the size of each bucket
    public int[] getBucketSizes() {
        int[] sizes = new int[CONST_SIZE];
        for (int i = 0; i < CONST_SIZE; i++) {
            HashNode<K, V> node = table[i];
            int count = 0;
            while (node != null) {
                count++;
                node = node.getNext();
            }
            sizes[i] = count;
        }
        return sizes;
    }

    // Remove key-value pair from table and return its value
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> currentNode = table[index];
        HashNode<K, V> prevNode = null;

        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                if (prevNode == null) {
                    table[index] = currentNode.getNext();
                } else {
                    prevNode.setNext(currentNode.getNext());
                }
                size--;
                return currentNode.getValue();
            }
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }

        return null;
    }

    // Print all key-value pairs in a bucket
    public void printBucket(int index) {
        HashNode<K, V> node = table[index];
        while (node != null) {
            System.out.println(node.toString());
            node = node.getNext();
        }
    }

    // Print all key-value pairs in a bucket
    public boolean contains(V value) {
        for (int i = 0; i < CONST_SIZE; i++) {
            HashNode<K, V> currentNode = table[i];
            while (currentNode != null) {
                if (currentNode.getValue().equals(value)) {
                    return true;
                }
                currentNode = currentNode.getNext();
            }
        }
        return false;
    }
    // Retrieve the key associated with a value
    public K getKey(V value) {
        for (int i = 0; i < CONST_SIZE; i++) {
            HashNode<K, V> currentNode = table[i];
            while (currentNode != null) {
                if (currentNode.getValue().equals(value)) {
                    return currentNode.getKey();
                }
                currentNode = currentNode.getNext();
            }
        }
        return null;
    }
}