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
    int preindex = 0;
    int[] preorder;
    int[] inorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        for(int i = 0 ; i < inorder.length ; i++){
            inMap.put(inorder[i], i);
        }
        return build(0, inorder.length - 1);
    }
    public TreeNode build(int start, int end){
        if(start > end)return null;
        int index = preindex;
        TreeNode root = new TreeNode(preorder[index]);
        preindex++;
        
        root.left = build(start, inMap.get(preorder[index]) - 1);
        root.right = build(inMap.get(preorder[index]) + 1, end);
        
        return root;
    }
}