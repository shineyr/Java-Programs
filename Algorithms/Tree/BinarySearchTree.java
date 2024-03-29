package com.ria.gradle.algorithms.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 二叉查找树实现
 */
public class BinarySearchTree<T extends Comparable> {
    TreeNode root;

    BinarySearchTree() {
        this.root = null;
    }

    /**
     * 从二叉搜索树查找等于给定元素的节点
     * @param data
     * @return
     */
    public TreeNode find(T data) {
        if (this.root == null) {
            return null;
        }

        TreeNode p = root;
        while (p != null) {
            if (p.value == data) {
                return p;
            } else if (p.value.compareTo(data) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    /**
     * 将指定元素插入二叉搜索树
     * @param data
     */
    public void insert(T data) {
        if (this.root == null) {
            this.root = new TreeNode(data);
            return;
        }

        TreeNode p = root;
        while (p != null) {
            if (data.compareTo(p.value) < 0) {
                if (p.left == null) {
                    p.left = new TreeNode(data);
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new TreeNode(data);
                    return;
                }
                p = p.right;
            }
        }
    }

    /**
     * 查找二叉搜索树中最小元素节点
     * @return
     */
    public TreeNode findMin() {
        if (this.root == null) {
            return null;
        }

        TreeNode p = root;
        while(p.left != null) {
            p = p.left;
        }

        return p;
    }

    /**
     * 查找二叉搜索树中最大元素节点
     * @return
     */
    public TreeNode findMax() {
        if (this.root == null) {
            return null;
        }

        TreeNode p = root;
        while(p.right != null) {
            p = p.right;
        }

        return p;
    }

    /**
     * 从二叉搜索树中删除指定元素
     * @param data
     */
    public void delete(T data) {
        TreeNode p = root, parent = null;

        while (p != null && p.value != data) {
            parent = p;
            if (p.value.compareTo(data) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        if (p == null) {
            return;
        }

        /**
         * 要删除节点左右子树非空
         */
        if (p.left != null && p.right != null) {
            TreeNode minRight = p.right, minRightParent = p;
            // 查找右子树中最左叶子节点
            while (minRight.left != null) {
                minRightParent = minRight;
                minRight = minRight.left;
            }

            // 交换，此时要删除节点转换为右子树最左节点
            p.value = minRight.value;
            p = minRight;
            parent = minRightParent;
        }

        // 记录要删除节点的子节点
        TreeNode child = null;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        // 要删除节点为root
        if (parent == null) {
            root = child;
        } else if (parent.left == p) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    /**
     * 前序遍历
     * @return
     */
    public List<T> preOrder(TreeNode node) {
        List<T> result = new ArrayList<>();
        if (node == null) {
            return result;
        }

        result.add((T) node.value);
        result.addAll(preOrder(node.left));
        result.addAll(preOrder(node.right));
        return result;
    }

    /**
     * 中序遍历
     * @param node
     * @return
     */
    public List<T> inOrder(TreeNode node) {
        List<T> result = new ArrayList<>();

        if (node == null) {
            return result;
        }

        result.addAll(inOrder(node.left));
        result.add((T) node.value);
        result.addAll(inOrder(node.right));

        return result;
    }

    /**
     * 后序遍历
     * @param node
     * @return
     */
    public List<T> postOrder(TreeNode node) {
        List<T> result = new ArrayList<>();

        if (node == null) {
            return result;
        }

        result.addAll(postOrder(node.left));
        result.addAll(postOrder(node.right));
        result.add((T) node.value);

        return result;
    }

    /**
     *  求二叉树的高度
     * @param node
     * @return
     */
    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 前序遍历非递归算法
     * @param node
     * @return
     */
    public List<T> preOrderNonRecursion(TreeNode node) {
        List<T> result = new ArrayList<>();

        if (node == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();

            result.add((T) current.value);

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return result;
    }

    /**
     * 非递归中序遍历
     * @param node
     * @return
     */
    public List<T> inOrderNonRecursion(TreeNode node) {
        List<T> result = new ArrayList<>();

        if (node == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = node;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();

                result.add((T) current.value);
                current = current.right;
            }
        }
        return result;
    }


    /**
     * 非递归后序遍历1
     * @param node
     */
    public List<T> postOrderNonRecursion1(TreeNode node) {
        List<T> result = new ArrayList<>();

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(node);
        while (!stack1.isEmpty()) {
            TreeNode current = stack1.pop();

            stack2.push(current);
            if (current.left != null) {
                stack1.push(current.left);
            }

            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        while (!stack2.isEmpty()) {
            result.add((T) stack2.pop().value);
        }
        return result;
    }

    /**
     * 非递归后序遍历2
     * @param node
     */
    public List<T> postOrderNonRecursion2(TreeNode node) {
        List<T> result = new ArrayList<>();


        Stack<TreeNode> stack = new Stack<>();

        TreeNode current = node;
        stack.push(node);
        while (!stack.isEmpty()) {

            TreeNode tmp = stack.peek();

            if (tmp.left != null && current != tmp.left && current != tmp.right) {
                stack.push(tmp.left);
            } else if (tmp.right != null && current != tmp.right) {
                stack.push(tmp.right);
            } else {
                result.add((T) tmp.value);
                current = tmp;
                stack.pop();
            }
        }

        return result;
    }


    public static class TreeNode<T extends Comparable<? super T>> implements Comparable<TreeNode<T>> {
        T value;
        TreeNode left;
        TreeNode right;

        TreeNode(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(TreeNode<T> o) {
            return this.value.compareTo(o.value);
        }

        @Override
        public String toString() {
            return "[" + value + "]";
        }
    }
}

class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        Arrays.asList(2,3,5,6,7,8,1,4,9,10).forEach(
                data -> {
                    tree.insert(data);
                }
        );

        System.out.println("Pre-order:" + tree.preOrder(tree.root).toString());
        System.out.println("Pre-Order:" + tree.preOrderNonRecursion(tree.root).toString());

        System.out.println("In-Order:" + tree.inOrder(tree.root).toString());
        System.out.println("In-Order:" + tree.inOrderNonRecursion(tree.root).toString());

        System.out.println("Post-Order:" + tree.postOrder(tree.root).toString());
        System.out.println("Post-Order:" + tree.postOrderNonRecursion1(tree.root).toString());
        System.out.println("Post-Order:" + tree.postOrderNonRecursion2(tree.root).toString());


        System.out.println("The height is: " + tree.height(tree.root));

        System.out.println(tree.findMax());
        System.out.println(tree.findMin());

        tree.delete(2);
        System.out.println(tree.preOrder(tree.root).toString());
        System.out.println(tree.inOrder(tree.root).toString());
        System.out.println(tree.postOrder(tree.root).toString());

        System.out.println("The height is: " + tree.height(tree.root));

    }
}
