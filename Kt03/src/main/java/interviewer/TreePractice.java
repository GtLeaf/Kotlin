package interviewer;


public class TreePractice {

    public static void main(String[] args) {
        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeConstruct construct = new TreeConstruct();
//        TreeNode node = construct.construct2(preOrder, inOrder);
//        System.out.println("结束");
        FindNextNode findNextNode = new FindNextNode();
        TreeNode tree = findNextNode.getTree();
        System.out.println(findNextNode.findNext(tree, tree.leftNode.rightNode.rightNode).str);
    }

}

class TreeNode {
    int data;
    String str;
    TreeNode leftNode;
    TreeNode rightNode;
    TreeNode parentNode;

    TreeNode(int data, TreeNode leftNode, TreeNode rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;

    }
    TreeNode(String str, TreeNode leftNode, TreeNode rightNode) {
        this.str = str;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }
}

class TreeConstruct {
    //------------------------1.根据先序中序遍历构建树--------------------------------------------------------
    //未完成，不想写了T_T
    public TreeNode construct(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd) {
        //边界输入处理
        if (null == preOrder || null == inOrder || 0 == preOrder.length || 0 == inOrder.length) {
            return null;
        }
        if (preOrder.length != inOrder.length) {
            return null;
        }
        //根据先遍历获取root
        TreeNode rootNode = new TreeNode(preOrder[preStart], null, null);
        //中序遍历根据root
        int rootInOrderIndex = 0;
        while (rootInOrderIndex < inOrder.length && inOrder[rootInOrderIndex] != rootNode.data) {
            rootInOrderIndex++;
        }

        if (rootInOrderIndex >= inOrder.length) {
            return null;
        }
        //区分左右子树
        int leftTreeLength = rootInOrderIndex - preStart;
        int rightTreeLength = inOrder.length - 1 - rootInOrderIndex;
        //递归调用获得左右结点
        if (leftTreeLength > 0) {
            rootNode.leftNode = construct(preOrder, inOrder, preStart + 1, preEnd, 0, leftTreeLength);
        }
        if (rightTreeLength > 0) {
            rootNode.rightNode = construct(preOrder, inOrder, preStart + 1, preEnd, leftTreeLength + 1, inOrder.length);
        }

        return rootNode;
    }

    public TreeNode construct2(int[] preOrder, int[] inOrder) {
        //边界输入处理
        if (null == preOrder || null == inOrder || 0 == preOrder.length || 0 == inOrder.length) {
            return null;
        }
        if (preOrder.length != inOrder.length) {
            return null;
        }
        //根据先遍历获取root
        TreeNode rootNode = new TreeNode(preOrder[0], null, null);
        //中序遍历根据root
        int rootInOrderIndex = 0;
        while (rootInOrderIndex < inOrder.length && inOrder[rootInOrderIndex] != rootNode.data) {
            rootInOrderIndex++;
        }

        if (rootInOrderIndex >= inOrder.length) {
            return null;
        }
        //区分左右子树
        int leftTreeLength = rootInOrderIndex;
        int rightTreeLength = inOrder.length - 1 - rootInOrderIndex;
        //递归调用获得左右结点
        int[] leftPreOrder = new int[leftTreeLength];
        int[] rightPreOrder = new int[rightTreeLength];
        System.arraycopy(preOrder, 1, leftPreOrder, 0, leftTreeLength);
        System.arraycopy(preOrder, leftTreeLength + 1, rightPreOrder, 0, rightTreeLength);
        int[] leftInOrder = new int[leftTreeLength];
        int[] rightInOrder = new int[rightTreeLength];
        System.arraycopy(inOrder, 0, leftInOrder, 0, leftTreeLength);
        System.arraycopy(inOrder, leftTreeLength + 1, rightInOrder, 0, rightTreeLength);
        if (leftTreeLength > 0) {
            rootNode.leftNode = construct2(leftPreOrder, leftInOrder);
        }
        if (rightTreeLength > 0) {
            rootNode.rightNode = construct2(rightPreOrder, rightInOrder);
        }
        return rootNode;
    }

    //后序遍历 左右中
    private void postOrderTraverse1(TreeNode root){
        if (null != root){
            postOrderTraverse1(root.leftNode);
            postOrderTraverse1(root.rightNode);
            System.out.print(root.data+" ");
        }
    }
    //-------------------------------------------------1.完成，耗时：1小时-----------------------------------------
}

//-------------给定一个节点，找出中序遍历的下一个节点-------------------
class FindNextNode{

    public TreeNode getTree(){
        TreeNode node6 = new TreeNode("f", null, null);
        TreeNode node7 = new TreeNode("g", null, null);
        TreeNode node3 = new TreeNode("c", node6, node7);

        TreeNode node8 = new TreeNode("h", null, null);
        TreeNode node9 = new TreeNode("i", null, null);
        TreeNode node5 = new TreeNode("e", node8, node9);
        TreeNode node4 = new TreeNode("d", null, null);
        TreeNode node2 = new TreeNode("b", node4, node5);
        TreeNode node1 = new TreeNode("a", node2, node3);

        node2.setParentNode(node1);
        node3.setParentNode(node1);
        node4.setParentNode(node2);
        node5.setParentNode(node2);
        node8.setParentNode(node5);
        node9.setParentNode(node5);
        node6.setParentNode(node3);
        node7.setParentNode(node3);

        return node1;
    }

    //----------------------16:45开始----------------------
    public TreeNode findNext(TreeNode treeNode, TreeNode node){
        //不规范输入处理
        if (null == treeNode || null == node){
            return null;
        }

        TreeNode nextNode;
        TreeNode tempNode;
        //是否有右子树，右子树的最左节点就是next
        if(null != node.rightNode){
            //有右子树，右子树没有左节点，右子树就是next
            nextNode = node.rightNode;
            //找最左节点
            while (null != nextNode.leftNode){
                nextNode = nextNode.leftNode;
            }
            return nextNode;
        }
        //没有右子树，判断是否为父节点左子节点
        //不是父节点左子节点，判断父节点是否是祖父的左子节点
        tempNode = node;
        while (null != (nextNode = tempNode.parentNode)){
            if (nextNode.leftNode == tempNode){
                return nextNode;
            }
            tempNode = nextNode;
        }

        //没有父节点，则没有下一个节点了
        return null;
    }
    //----------------------------17:10结束，耗时：25分钟----------------------------------
}