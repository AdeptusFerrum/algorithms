import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BinarySearchTreeBalanced {
    private TreeNode root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private TreeNode insertRec(TreeNode node, int key) {
        if (node == null) {
            return new TreeNode(key);
        }

        if (key < node.val) {
            node.left = insertRec(node.left, key);
        } else if (key > node.val) {
            node.right = insertRec(node.right, key);
        }

        return node;
    }

    public boolean contains(int key) {
        return containsRec(root, key);
    }

    private boolean containsRec(TreeNode node, int key) {
        if (node == null) {
            return false;
        }

        if (key == node.val) {
            return true;
        } else if (key < node.val) {
            return containsRec(node.left, key);
        } else {
            return containsRec(node.right, key);
        }
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private TreeNode deleteRec(TreeNode node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.val) {
            node.left = deleteRec(node.left, key);
        } else if (key > node.val) {
            node.right = deleteRec(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.val = minValue(node.right);
            node.right = deleteRec(node.right, node.val);
        }

        return node;
    }

    private int minValue(TreeNode node) {
        int minValue = node.val;
        while (node.left != null) {
            minValue = node.left.val;
            node = node.left;
        }
        return minValue;
    }

    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    private void inorderRec(TreeNode node, List<Integer> result) {
        if (node != null) {
            inorderRec(node.left, result);
            result.add(node.val);
            inorderRec(node.right, result);
        }
    }

    public boolean isBalanced() {
        return checkBalance(root) != -1;
    }

    private int checkBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(1);
        System.out.println(bst.inorderTraversal());

        System.out.println(bst.contains(3));
        bst.delete(3);
        System.out.println(bst.contains(3));
        System.out.println(bst.inorderTraversal());
        System.out.println("Is balanced: " + bst.isBalanced());
    }
}
