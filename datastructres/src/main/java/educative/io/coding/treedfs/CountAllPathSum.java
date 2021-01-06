package main.java.educative.io.coding.treedfs;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CountAllPathSum {
    public static int countPaths(TreeNode root, int S) {
        List<Integer> currentPath = new ArrayList<>();

        return countPathsRecursive(root,S,currentPath);
    }

    private static int countPathsRecursive(TreeNode currentNode, int S, List<Integer> currentPath){
        if (currentNode == null)
            return 0;
        // add the current node to the path
        currentPath.add(currentNode.val);
        int pathCount =0,pathSum = 0;
        // find the sum of all sub-path in the current path list
        ListIterator<Integer> pathIterator = currentPath.listIterator(currentPath.size());
        while (pathIterator.hasPrevious()){
            pathSum +=pathIterator.previous();
            // if the sum of any sub-path is equal to 'S' we increment our path count.
            if(pathSum == S){
                pathCount++;
            }
        }
        pathCount += countPathsRecursive(currentNode.left,S,currentPath);
        pathCount += countPathsRecursive(currentNode.right,S,currentPath);
        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
        return pathCount;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + CountAllPathSum.countPaths(root, 11));
    }
}
