import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BinarySearchTree {
    TreeNode root;
    Comparator<String> comparator;

    public BinarySearchTree(Comparator<String> comparator) {
        this.comparator = comparator;
    }

    public void insert(String value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, String value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        int comparisonResult = comparator.compare(value, root.value);
        if (comparisonResult <= 0) {
            root.left = insertRec(root.left, value);
        } else {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    public List<String> toSortedList() {
        List<String> sortedList = new ArrayList<>();
        inOrderTraversal(root, sortedList);
        return sortedList;
    }

    private void inOrderTraversal(TreeNode root, List<String> sortedList) {
        if (root != null) {
            inOrderTraversal(root.left, sortedList);
            sortedList.add(root.value);
            inOrderTraversal(root.right, sortedList);
        }
    }

    public static BinarySearchTree fromList(List<String> list, Comparator<String> comparator) {
        BinarySearchTree bst = new BinarySearchTree(comparator);
        for (String value : list) {
            bst.insert(value);
        }
        return bst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinarySearchTree that = (BinarySearchTree) o;
        return Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}
