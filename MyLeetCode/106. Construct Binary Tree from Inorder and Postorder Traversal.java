/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    HashMap<Integer, Integer> inMap = new HashMap<>();
    int[] inorder;
    int[] postorder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        int postIndex = postorder.length - 1;
        
        for(int i = 0 ; i < inorder.length ; i++)
            inMap.put(inorder[i], i);
        
        return build(0, inorder.length - 1, postIndex);
    }
    public TreeNode build(int start, int end, int postIndex){
        if(start > end)return null;
        int index = postIndex;
        TreeNode root = new TreeNode(postorder[postIndex]);
        postIndex--;
        
        root.right = build(inMap.get(postorder[index]) + 1, end, postIndex);
        root.left = build(start, inMap.get(postorder[index]) - 1, postIndex - (end - inMap.get(postorder[index])));
        
        return root;
    }
}