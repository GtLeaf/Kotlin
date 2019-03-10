package interviewer.Tree

import java.util.*

class TreeNode(data:String, leftNode:TreeNode?, rightNode:TreeNode?) {
    var data:String = ""
    var leftNode:TreeNode? = null
    var rightNode:TreeNode? = null

    constructor(data: String):this(data, null, null)

    init {
        this.data = data
        this.leftNode = leftNode
        this.rightNode = rightNode
    }

    companion object {

        fun getTree():TreeNode{
            //root右子树
            val treeNode8 = TreeNode("I")
            val treeNode7 = TreeNode("H")
            val treeNode6 = TreeNode("G", treeNode7, treeNode8)
            val treeNode5 = TreeNode("F", treeNode6, null)
            val treeNode4 = TreeNode("E", null , treeNode5)
            //root左子树
            val treeNode3 = TreeNode("D")
            val treeNode2 = TreeNode("C",treeNode3, null)
            val treeNode1 = TreeNode("B", null, treeNode2)
            return TreeNode("A", treeNode1, treeNode4)
        }

        //前序遍历 中左右
        fun preOrderTraverse1(root:TreeNode?){
            if (null != root){
                print(root.data)
                preOrderTraverse1(root.leftNode)
                preOrderTraverse1(root.rightNode)
            }
        }

        //中序遍历 左中右
        fun inOrderTraverse1(root:TreeNode?){
            if (null != root){
                inOrderTraverse1(root.leftNode)
                print(root.data)
                inOrderTraverse1(root.rightNode)
            }
        }

        //后序遍历 左右中
        fun postOrderTraverse1(root:TreeNode?){
            if (null != root){
                postOrderTraverse1(root.leftNode)
                postOrderTraverse1(root.rightNode)
                print(root.data)
            }
        }

        //深度优先遍历，其实就是前中后序遍历，这里选前序遍历和另一种与广度优先的写法
        fun DFS(root: TreeNode?){
//            preOrderTraverse1(root)
            if (null == root) return
            val stack = LinkedList<TreeNode>()
            stack.add(root)
            while (!stack.isEmpty()){
                val node = stack.removeLast()
                print(node.data)
                //注意，右结点先进栈，左结点后进栈，这样才能左结点先出栈
                if (null != node.rightNode){
                    stack.offer(node.rightNode)
                }
                if (null != node.leftNode){
                    stack.offer(node.leftNode)
                }
            }

        }

        //广度优先：使用队列实现，先进先出
        fun BFS(root: TreeNode?){
            if (null == root){
                return
            }
            val queue = LinkedList<TreeNode>()
            queue.add(root)
            while (!queue.isEmpty()){
                val node = queue.poll()
                print(node.data)
                if (null != node.leftNode){
                    queue.offer(node.leftNode)
                }
                if (null != node.rightNode){
                    queue.offer(node.rightNode)
                }
            }
        }
    }
}

fun main(args:Array<String>){
//    TreeNode.preOrderTraverse1(TreeNode.getTree())
//    TreeNode.inOrderTraverse1(TreeNode.getTree())
//    TreeNode.postOrderTraverse1(TreeNode.getTree())
//    TreeNode.BFS(TreeNode.getTree())
    TreeNode.DFS(TreeNode.getTree())
}