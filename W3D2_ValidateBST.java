import javax.swing.tree.TreeNode;

public class W3D2_ValidateBST {

    /**
     * Checks if a binary tree is a valid Binary Search Tree (BST).
     * @param root The root of the binary tree.
     * @return true if the tree is a valid BST, false otherwise.
     */
     public long findMin(TreeNode root){

            if(root == null){
                return Integer.MAX_VALUE;
            }
            long leftMin = findMin(root.left);
            long rightMin = findMin(root.right);
            return Math.min(root.val, Math.min(leftMin,rightMin));
      }
      public long findMax(TreeNode root){

            if(root == null){
                return Integer.MAX_VALUE;
            }
            long leftMax = findMax(root.left);
            long rightMax = findMax(root.right);
            return Math.max(root.val, Math.max(leftMax,rightMax));
      }
    
    
    public boolean isValidBST(TreeNode root) {

        long min = findMin(root);
        long max = findMax(root);
        return isValid(root, min, max);
        }

    }

    /**
     * Recursive helper to validate the BST property for a node and its subtrees.
     * @param node The current node to validate.
     * @param min The minimum allowed 
     * @param value for this node (exclusive).
     * @param max The maximum allowed value for this node (exclusive).
     * @return true if the subtree rooted at 'node' is a valid BST, false otherwise.
     */
    private boolean isValid(TreeNode node, long min, long max) {

        if(node.val > max || node.val < min){
            return false;
        }
        while(node != null){
            TreeNode right = node.right;
            TreeNode left = node.left;

            isValidBST(right);
            isValidBST(left);
        }
        return true;
    }

    // Main method to test the validation
    public static void main(String[] args) {
        W3D2_ValidateBST validator = new W3D2_ValidateBST();

        System.out.println("=== Testing BST Validation ===\n");

        // Test Case 1: A valid BST
        //      2
        //     / \
        //    1   3
        System.out.println("Test Case 1: Valid BST");
        TreeNode validRoot = new TreeNode(2);
        validRoot.left = new TreeNode(1);
        validRoot.right = new TreeNode(3);
        System.out.println("Tree structure: 2(1,3)");
        System.out.println("Result: " + validator.isValidBST(validRoot)); // Expected: true
        System.out.println();

        // Test Case 2: An invalid BST
        //      5
        //     / \
        //    1   4
        //       / \
        //      3   6
        // This is invalid because 3 is in the right subtree of 5 but 3 < 5
        System.out.println("Test Case 2: Invalid BST (3 in wrong position)");
        TreeNode invalidRoot = new TreeNode(5);
        invalidRoot.left = new TreeNode(1);
        invalidRoot.right = new TreeNode(4);
        invalidRoot.right.left = new TreeNode(3);
        invalidRoot.right.right = new TreeNode(6);
        System.out.println("Tree structure: 5(1,4(3,6))");
        System.out.println("Why invalid: 3 is in right subtree of 5, but 3 < 5");
        System.out.println("Result: " + validator.isValidBST(invalidRoot)); // Expected: false
        System.out.println();
        
        // Test Case 3: Edge case with single node
        System.out.println("Test Case 3: Single node tree");
        TreeNode singleNode = new TreeNode(1);
        System.out.println("Tree structure: 1");
        System.out.println("Result: " + validator.isValidBST(singleNode)); // Expected: true
        System.out.println();
        
        // Test Case 4: Empty tree
        System.out.println("Test Case 4: Empty tree");
        System.out.println("Tree structure: null");
        System.out.println("Result: " + validator.isValidBST(null)); // Expected: true
        System.out.println();

        // Test Case 5: Another invalid case - duplicate values
        //      10
        //     /  \
        //    5   15
        //       /  \
        //      6   20
        //           /
        //          15  (duplicate)
        System.out.println("Test Case 5: Invalid BST with duplicate values");
        TreeNode complexInvalid = new TreeNode(10);
        complexInvalid.left = new TreeNode(5);
        complexInvalid.right = new TreeNode(15);
        complexInvalid.right.left = new TreeNode(6);
        complexInvalid.right.right = new TreeNode(20);
        complexInvalid.right.right.left = new TreeNode(15);
        System.out.println("Tree structure: 10(5,15(6,20(15,null)))");
        System.out.println("Why invalid: duplicate values of 15");
        System.out.println("Result: " + validator.isValidBST(complexInvalid)); // Expected: false
        System.out.println();

        // Test Case 6: Another valid BST
        //      10
        //     /  \
        //    5   15
        //   /   /  \
        //  1   12  20
        //     /
        //    11   (This is valid - 11 is between 10 and 12)
        System.out.println("Test Case 6: Another valid BST");
        TreeNode trickyValid = new TreeNode(10);
        trickyValid.left = new TreeNode(5);
        trickyValid.left.left = new TreeNode(1);
        trickyValid.right = new TreeNode(15);
        trickyValid.right.left = new TreeNode(12);
        trickyValid.right.right = new TreeNode(20);
        trickyValid.right.left.left = new TreeNode(11);
        System.out.println("Tree structure: 10(5(1,null),15(12(11,null),20))");
        System.out.println("Result: " + validator.isValidBST(trickyValid)); // Expected: true
        System.out.println();
    }
}