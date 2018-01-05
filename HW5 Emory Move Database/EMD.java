/*
THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS.  ___Yicheng Wang(2190066)____
*/
import java.util.List;
import java.util.ArrayList;
import java.io.Console;
// NOTE: No other Java libraries allowed (automatic 0)

class EMD<K extends Comparable<K>, V> implements RangeMap<K,V> {
    class Node {
        Node left;
        Node right;
        KVPair<K,V> kv;
    }

    private Node root;

    // Add the key and a value to your RangeMap. (For EMD, this would be the
    // name of the movie (key) and its description (value), respectively.) If
    // there is a duplicate key, the old entry should be overwritten with the
    // new value.
    public void add(K key, V value) {
        // TODO: Implement me(basic score)
        Node newNode = new Node();
        newNode.kv = new KVPair<K,V>(key, value);
        
        if(root == null){
            root = newNode;
        }else{
            Node focusNode = root;
            Node parent;
            
            while(focusNode != null){
                parent = focusNode;
                if (key.compareTo(focusNode.kv.key) == 0){
                    focusNode.kv.value = value;
                    break;
                }
                
                if (key.compareTo(focusNode.kv.key) < 0){
                    focusNode = focusNode.left;
                    if(focusNode == null){
                        parent.left = newNode;
                    }
                }else{
                    focusNode = focusNode.right;
                    if(focusNode == null){
                        parent.right = newNode;
                    }
                }           
            }
            
        }
    
    }

    // Retrieve the value corresponding to key, or return null if the key is
    // not in your RangeMap. The comparison between keys should be exact.
    // (For EMD, this would correspond to the lower-case name of the movie
    // (key).)
    public V get(K key) {
        // TODO: Implement me(basic score)
        Node focusNode = root;
        
        while(focusNode != null){
            
            if (key.compareTo(focusNode.kv.key) == 0)
                return focusNode.kv.value;
            
            if (key.compareTo(focusNode.kv.key) < 0){
                focusNode = focusNode.left;
            }else{
                focusNode = focusNode.right;
            }
        
        }
        return null;
    }

    // Return the key in the RangeMap that's lexicographically next after
    // 'key', or return null otherwise. (For EMD, this would correspond to
    // the name of the movie that's next after the one specified. 
    // If key is exactly the name of a movie, next should still return
    // the following movie in the database.)
    // Note that key does not have to exist in the database.
    
    public K next(K key) {
        if (key == null || root == null) 
            return null;
        
        Node x = helper(root, key);
        
        if (x == null) 
            return null;
        else 
            return x.kv.key;
    }

    private Node helper(Node x, K key) {
        
        if (x == null) 
            return null;
        
        int num = key.compareTo(x.kv.key);
        
        if (num < 0) { 
            Node t = helper(x.left, key); 
            
            if (t != null) 
                return t;
            else 
                return x; 
        } 
        
        return helper(x.right, key); 
    } 
    // Return a list of key-value pairs in the RangeMap that are between the
    // strings start and end, both inclusive. The list should be in
    // lexicographic order. If no keys match, the method should return the empty list.
    // (For EMD, range would return an alphabetic list of movies titles that
    // are between the two parameter strings). Note that neither start nor
    // end have to exist in the database.
    List<KVPair<K,V>> sort = new ArrayList<KVPair<K,V>>();
    List<KVPair<K,V>> result = new ArrayList<KVPair<K,V>>();

    public List<KVPair<K,V>> range(K start, K end) {
        // TODO: Implement me(EC for full score)
        sort.clear();
        result.clear();
        helperForRange(root);
        for (int i = 0; i < sort.size(); i++){
            if(sort.get(i).key.compareTo(start)>=0 
                && sort.get(i).key.compareTo(end)<=0)
                result.add(sort.get(i));
        }
        return result;
    }

    public void helperForRange(Node focusNode){
        
        if(focusNode != null){
            helperForRange(focusNode.left);
            sort.add(focusNode.kv);
            helperForRange(focusNode.right);
        }
    }
    // Removes the key-value pair with key specified by the parameter from
    // the RangeMap. Does nothing if the key does not exist. 
    // Extra Credit beyond 100%
    public void remove(K key) {
        // TODO: Implement me(EC beyond full score)
        Node focusNode = root;
        Node parent = root;
        boolean isItALeftChild = true;

        while(focusNode != null && focusNode.kv.key != key){
            parent = focusNode;

            if (key.compareTo(focusNode.kv.key) < 0){
                    
                    isItALeftChild = true;
                    focusNode = focusNode.left;
                      
                }else{
                    isItALeftChild = false;
                    focusNode = focusNode.right;

                }
        }

        if (focusNode == null)
            return;

        if (focusNode.left == null && focusNode.right == null){
            if (focusNode == root){
                root = null;
            }else if(isItALeftChild){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }else if(focusNode.right == null){
            if (focusNode == root)
                root = focusNode.left;
            else if (isItALeftChild)
                parent.left = focusNode.left;
            else
                parent.right = focusNode.left;
        }else if(focusNode.left == null){
            if (focusNode == root)
                root = focusNode.right;
            else if (isItALeftChild)
                parent.left = focusNode.right;
            else
                parent.right = focusNode.right;

        }else{
            Node replace = getReplaceNode(focusNode);
            
            if(isItALeftChild)
                parent.left = replace;
            else
                parent.right = replace;

            replace.left = focusNode.left;
        }

    }

    private Node getReplaceNode(Node replacedNode){
           
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;

        Node focusNode = replacedNode.right;

        while (focusNode != null){
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.left;
        }

        if(replacement != replacedNode.right){

            replacementParent.left = replacement.right;
            replacement.right = replacedNode.right;

        }

        return replacement;
        
    }

    /////////////////////////////////////////////////
    // You shouldn't have to change anything below //
    /////////////////////////////////////////////////
    public static void main(String[] args) {
        EMD<String, String> emd = new EMD<String, String>();
        In in;
        In inputFile = null;

        // read from a given input file instead?
        if(args.length > 0) {
            inputFile = new In(args[0]);
        }

        while(true) {
            if(inputFile != null ? !inputFile.hasNextLine() : !StdIn.hasNextLine()) {
                break;
            }
            String input =(inputFile != null ? inputFile.readLine() : StdIn.readLine());

            // process commands from the user
            String[] line = input.split("/");
            switch(line[0].charAt(0)) {
                // Open and read a file with "Movie/Information..." lines
                case 'o':                // e.g. "open/movies.txt"
                    in = new In(line[1]);
                    // clean out the old movies
                    emd = new EMD<String, String>();
                    while(in.hasNextLine()) {
                        String[] arr = in.readLine().split("/");
                        // Test only lower case strings for simplicity
                        emd.add(arr[0].toLowerCase(), arr[1]);
                    }
                    break;

                    // Add a new movie
                case 'a':                // e.g. "add/Shredder/Foot Clan Ninja"
                    System.out.println("Adding '" + line[1] + "' ...");
                    emd.add(line[1].toLowerCase(), line[2]);
                    break;

                    // Look up a movie
                case 'g':                // e.g. "get/shredder"
                    System.out.println("Looking up '" + line[1] + "' ...");
                    System.out.println(emd.get(line[1].toLowerCase()));
                    break;

                    // Find next movie after a string(like auto-complete)
                case 'n':                // e.g. "next/shred" would return "shredder"
                    System.out.println("Looking up next movie after '" + line[1] + "' ...");
                    System.out.println(emd.next(line[1].toLowerCase()));
                    break;

                    // Remove a movie
                case 'r':                // e.g. "remove/Shredder"
                    System.out.println("Removing '" + line[1] + "' ...");
                    emd.remove(line[1].toLowerCase());
                    break;

                    // Find movies in a range
                case 'f':                // e.g. "find/shed/shre/"
                    // might print "sherlock" and "shrek",
                    // but not "shredder" since it's outside
                    // the range
                    List<KVPair<String, String>> list;
                    System.out.println("Searching range of '" + line[1] + "'-'" + line[2] + "' ...");
                    list = emd.range(line[1].toLowerCase(), line[2].toLowerCase());
                    if(list == null)
                    {
                        System.out.println("Not found.\n");
                    } else {
                        // print out all movies in this range
                        for(KVPair<String, String>kv : list)
                            System.out.println(kv);
                    }
                    break;
                default:
                    System.out.println("Unknown command. ");
                    break;
            }
        }
    }
};
