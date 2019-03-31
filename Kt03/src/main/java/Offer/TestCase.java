package Offer;



public class TestCase {
    public Solutions.TreeNode getSearchTreeCase(){
        Solutions solutions = new Solutions();
        Solutions.TreeNode root = solutions.new TreeNode(9);
        Solutions.TreeNode node1 = solutions.new TreeNode(4);
        Solutions.TreeNode node2 = solutions.new TreeNode(10);
        Solutions.TreeNode node3 = solutions.new TreeNode(3);
        Solutions.TreeNode node4 = solutions.new TreeNode(5);
        Solutions.TreeNode node5 = solutions.new TreeNode(2);
        Solutions.TreeNode node6 = solutions.new TreeNode(6);
        Solutions.TreeNode node7 = solutions.new TreeNode(11);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node3.left = node5;
        node4.right = node6;
//        node2.right = node7;

        return root;
    }
}
