package com.qiangqiang.tree;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
    //红黑树
    public static void main(String[] args) {
        //创建红黑树
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        //往树中插入元素
        tree.put("1", "张三");
        tree.put("2", "李四");
        tree.put("3", "王五");


        //从树中获取元素
        String s = tree.get("1");
        System.out.println(s);

        String s1 = tree.get("2");
        System.out.println(s1);
        String s2 = tree.get("3");
        System.out.println(s2);

    }


    //根节点
    private Node root;
    //记录树中的元素个数
    private int N;
    //红色链接
    private static final boolean RED = true;
    //黑色链接
    private static final boolean BLACK = false;

    //获取树中的所有个数
    public int size() {
        return this.N;
    }

    //判断当前节点的父指向链接是否为红色
    public boolean isRed(Node x) {
        //判断当前x是否为空
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    //左旋转
    public Node rotateLeft(Node h) {
        //获取h节点的右子结点,表示为x
        Node x = h.right;
        //让x结点的左子结点成为h结点的右子结点
        h.right = x.left;
        //让h结点成为x结点的左子结点
        x.left = h;
        //让x结点的color等于h节点的color,因为x结点现在变成向上连接的结点了,需要颜色来同上一层链接
        x.color = h.color;
        //让h节点的color变为红色
        h.color = RED;
        //现在x节点变成顶了,要返回x
        return x;
    }

    //右旋转
    public Node rotateRight(Node h) {
        //获取h结点的左子结点,标识为x
        Node x = h.left;
        //让x结点的右子结点成为h结点的左子结点
        h.left = x.right;
        //让h结点成为x结点的右子结点
        x.right = h;
        //让x结点的color等于h结点的color
        x.color = h.color;
        //让h结点的color为红色
        h.color = RED;
        //现在x节点变成顶了,要返回x
        return x;
    }

    //颜色反转
    public void flipColors(Node h) {
        //当前结点变为红色
        h.color = RED;
        //左子结点和右子结点变为黑色
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    //在整个树上完成插入操作
    public void put(Key key, Value value) {
        //调用重载的put方法,向root中去插入新元素
        root = put(root, key, value);
        //根节点的颜色总是黑的
        root.color = BLACK;
    }

    //在指定树中,完成插入操作,并返回添加元素后新的树
    public Node put(Node h, Key key, Value value) {
        //判断h是否为空,如果为空则直接返回一个红色的结点
        if (h == null) {
            N++;
            return new Node(key, value, null, null, RED);
        }
        //比较h结点的键和key的大小
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            //继续往左
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            //继续往右
            h.right = put(h.right, key, value);

        } else {
            //发生值的替换
            h.value = value;
        }

        //进行左旋
        //当当前节点h的左子结点为黑色,右子结点为红色,需要左旋
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }

        //进行右旋
        //当当前节点h的左子结点和左子结点的左子结点,都为红色时,需要右旋
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        //颜色反转
        //当当前节点h的左子结点和右子结点都为红色时,需要颜色反转
        //把左子结点和右子结点变为黑色,把当前节点变为红色
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }


        return h;
    }

    //根据key,从树中找出对应的值
    public Value get(Key key) {
        return get(root, key);
    }

    //根据key,从指定树中获取key对应的值
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


    public class Node {
        //存储键
        public Key key;
        //存储值
        public Value value;
        //记录左子结点
        public Node left;
        //记录右子结点
        public Node right;
        //由其父节点指向它的链接的颜色
        public boolean color;

        public Node(Key key, Value value, Node left, Node right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }
}
