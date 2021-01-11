package com.qiangqiang.tree;

public class BinaryTree<Key extends Comparable<Key>, Value> {


    public static void main(String[] args) {
        BinaryTree<Integer ,String> binaryTree = new BinaryTree<>();
        binaryTree.put(1,"张三");
        binaryTree.put(2,"李四");
        binaryTree.put(3,"王五");
        System.out.println(binaryTree.size);

        System.out.println(binaryTree.get(2));

        binaryTree.delete(1);

        System.out.println();


    }

    //记录根节点
    public Node root;
    //记录树中元素的个数
    public int size;


    //获取树中元素的个数
    public int size() {
        return this.size;
    }

    //向树中添加元素key-value
    public void put(Key key, Value value) {
        //直接调用重载的put方法,root可以代表整个树,插入完毕后替换root
        root = put(root, key, value);
    }

    //向指定的树中添加元素key-value
    public Node put(Node x, Key key, Value value) {
        //如果x子树为空
        if (x == null) {
            return new Node(key, value, null, null);
        }
        //新添加的key与当前key比较的结果
        int cmp = key.compareTo(x.key);
        //如果x子树不为空
        if (cmp > 0) {
            //如果key大于当前节点的key
            //再递归去右子树去找
            //添加成功后,返回的树再赋值给原变量,这个地方去理解一层树的高度,如果下一层成功插入了,这个子树就得更新
            x.right = put(x.right, key, value);
        } else if (cmp < 0) {
            //如果key小于当前节点的key
            //再递归去左子树去找
            //添加成功后,返回的树再赋值给原变量,这个地方去理解一层树的高度,如果下一层成功插入了,这个子树就得更新
            x.left = put(x.left, key, value);
        } else {
            //如果key等于当前节点的key
            //替换当前节点的value
            x.value = value;
        }
        //树的元素个数加1
        size++;

        //还是返回内容更新过后的x子树结点
        return x;
    }

    //查询树中指定的key对应的value
    public Value get(Key key) {
        Value value = get(root, key);
        return value;
    }

    //从指定的树中,查找key对应的值
    public Value get(Node x, Key key) {
        //如果x子树为null
        if (x == null) {
            return null;
        }
        //如果x子树不为null
        //比较key和x结点的key的大小
        //新添加的key与当前key比较的结果
        int cmp = key.compareTo(x.key);
        //如果x子树不为空
        if (cmp > 0) {
            //如果key大于当前节点的key
            //再递归去右子树去找
            //添加成功后,返回的树再赋值给原变量,这个地方去理解一层树的高度,如果下一层成功插入了,这个子树就得更新
            return get(x.right, key);

        } else if (cmp < 0) {
            //如果key小于当前节点的key
            //再递归去左子树去找
            //添加成功后,返回的树再赋值给原变量,这个地方去理解一层树的高度,如果下一层成功插入了,这个子树就得更新
            return get(x.left, key);
        } else {
            //如果key等于当前节点的key

            return x.value;
        }
    }

    //删除树中key对应的value
    public void delete(Key key) {
        delete(root, key);
    }

    //删除的重载方法
    //返回删除后的新树,因为删掉一个结点后,有其他结点顶上来的可能,树结构更新了
    public Node delete(Node x, Key key) {
        //x树为null
        if (x == null) {
            return null;
        }

        //x树不为null
//比较key和x结点的key的大小
        //新添加的key与当前key比较的结果
        int cmp = key.compareTo(x.key);
        //如果x子树不为空
        if (cmp > 0) {
            //如果key大于当前节点的key
            //再递归去右子树去找
            //删除成功后,返回的树再赋值给原变量,这个地方去理解一层树的高度,如果下一层成功删除了,这个子树就得更新
            x.right = delete(x.right, key);


        } else if (cmp < 0) {
            //如果key小于当前节点的key
            //再递归去左子树去找
            //删除成功后,返回的树再赋值给原变量,这个地方去理解一层树的高度,如果下一层成功删除了,这个子树就得更新
            x.left = delete(x.left, key);

        } else {
            //如果key等于当前节点的key
            //这个地方就是真正的删除操作,要删除的节点就是x
            //得去找到一个结点去顶上去


            //如果右子树为null,直接把左子树顶上来就行
            if (x.right == null) {
                return x.left;
            }

            //如果左子树为null,更简单了,直接把右子树顶上去
            if (x.left == null) {
                return x.right;
            }

            //去找右子树中最小的结点
            //定义一个结点去接收右子树的最小节点
            Node minNode = x.right;
            while (minNode.left != null) {
                //不停的循环赋值,直到没有左子树了
                minNode = minNode.left;
            }

            //找到这个右子树中的最下结点后,删除这个最小结点
            Node n = x.right;
            while (n.left != null) {
                if (n.left.left != null) {
                    n.left = null;
                } else {
                    //替换结点,继续下一层
                    n = n.left;
                }
            }
            //让x结点的左子树成为minNode的左子树
            minNode.left = x.left;
            //让x结点的右子树成为minNode的右子树
            minNode.right = x.right;
            //让x结点的父节点指向minNode
            x = minNode;


        }
        size--;
        return x;
    }

    public class Node {
        //存储键
        public Key key;
        //存储值
        public Value value;
        //记录左子结点
        public Node left;
        //记录右子结点
        public Node right;

        //构造方法
        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
