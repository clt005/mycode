/*
 * Name: Clayton Hammen Tan
 * PID:  A17819097
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.ScatteringByteChannel;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Clayton Hammen Tan
 * @since  05-13-2024
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @return false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String[] cast = scanner.nextLine().split(" ");
                String[] studios = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                for (String actor : cast) {
                    String actorKey = actor.toLowerCase();
                    insertHelper(movieTree, actorKey, movie);
                    insertHelper(ratingTree, actorKey, rating);
                }
                for (String studio : studios) {
                    String studioKey = studio.toLowerCase();
                    insertHelper(studioTree, studioKey, movie);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Helper function to insert data into an existing BSTree Node Linked list
     * or create a new one and then insert the data
     *
     * @param tree BSTree of the node
     * @param key key of the node
     * @param value data to be added
     */
    private static void insertHelper(BSTree<String> tree, String key, String value) {
        if (!tree.findKey(key)) {
            tree.insert(key);
        }
        LinkedList<String> treeLinkedList = tree.findDataList(key);
        if (!treeLinkedList.contains(value)) {
            tree.insertData(key, value);
        }
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        // process query
        String[] keys = query.toLowerCase().split(" ");
        LinkedList<LinkedList<String>> documentListsList = new LinkedList<>();

        // search and output intersection results
        for (String key : keys) {
            try {
                LinkedList<String> documentsList = searchTree.findDataList(key);
                documentListsList.add(documentsList);
            }
            catch (IllegalArgumentException e) {
                documentListsList.add(new LinkedList<>());
            }
        }

        LinkedList<String> intersectionList = new LinkedList<>(documentListsList.getFirst());
        if (intersectionList.isEmpty()) {
            print(query, intersectionList);
        } else {
            for (LinkedList<String> dList : documentListsList) {
                intersectionList.retainAll(dList);
            }
            print(query, intersectionList);
        }
        // hint: list's addAll() and retainAll() methods could be helpful

        // search and output individual results
        for (int i = 0; i < keys.length; i++) {
            LinkedList<String> keyUniqueDocumentsList = new LinkedList<>(documentListsList.get(i));
            keyUniqueDocumentsList.removeAll(intersectionList);
            if (!keyUniqueDocumentsList.isEmpty()) {
                print(keys[i], keyUniqueDocumentsList);
            }
        }

        // hint: list's addAll() and removeAll() methods could be helpful
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // initialize search trees
        BSTree<String> movieTree = new BSTree<>();
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();
        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        BSTree<String> searchTree = new BSTree<>();
        if (searchKind == 0) {
            searchTree = movieTree;
        } else if (searchKind == 1) {
            searchTree = studioTree;
        } else if (searchKind == 2) {
            searchTree = ratingTree;
        }
        // populate search trees
        boolean populate = populateSearchTrees(movieTree, studioTree, ratingTree, fileName);
        // choose the right tree to query
        StringBuilder queryB = new StringBuilder();
        for (int i = 2; i < args.length; i++ ) {
            queryB.append(args[i]);
            if (i != args.length - 1) {
                queryB.append(" ");
            }
        }
        String query = queryB.toString();
        searchMyQuery(searchTree,query);
    }
}
