import java.util.List;
import java.util.ArrayList;
public class BST<E extends Comparable>
{
    private BNode<E> root;

    public BST() 
    {
        root = null;
    }

    //add data to this BST
    public void add(E data)
    {
        addHelper(root, new BNode<E>(data));
    }

    //Recursive helper method for add
    private void addHelper(BNode<E> node, BNode<E> addMe)
    {
        if(root == null)
            root = addMe;
        else
        if(node.getData().compareTo(addMe.getData()) > 0){
            if(node.getLeft() != null)
                addHelper(node.getLeft(), addMe);
            else
                node.setLeft(addMe);
        }
        else{
            if(node.getRight() != null)
                addHelper(node.getRight(), addMe);
            else
                node.setRight(addMe);
        }
    }

    public void addAll(List<E> data)
    {
        for(E x: data)
            add(x);
    }

    //reutrn the size of this tree (how many nodes are in it?)
    public int size()
    {
        return sizeHelper(root);
    }

    //recursive helper method for size
    public int sizeHelper(BNode<E> node)
    {
        if(node == null)
            return 0;
        return 1 + sizeHelper(node.getLeft()) + sizeHelper(node.getRight());
    }

    //Return a string with the data of this BST in pre order
    public String preorder()
    {
        String x = preorderHelper(root, "");
        return "[" + x.substring(2, x.length()) + "]";
    }

    //Recursive helper method for preorder
    private String preorderHelper(BNode<E> node, String ret)
    {
        if(node == null)
            return ret;
        ret += ", " + node.getData();
        ret = preorderHelper(node.getLeft(), ret);
        ret = preorderHelper(node.getRight(), ret);
        return ret;
    }

    //Return a string with the data of this BST in order
    public String inorder()
    {
        String x = inorderHelper(root, "");
        return "[" + x.substring(2, x.length()) + "]";
    }

    //Recursive helper method for inorder
    private String inorderHelper(BNode<E> node, String ret)
    {
        if(node == null)
            return ret;
        ret = inorderHelper(node.getLeft(), ret);
        ret += ", " + node.getData();
        ret = inorderHelper(node.getRight(), ret);
        return ret;
    }

    //Return a string with the data of this BST in post order
    public String postorder()
    {
        String x = postorderHelper(root, "");
        return "[" + x.substring(2, x.length()) + "]";
    }

    //Recursive helper method for postorder
    private String postorderHelper(BNode<E> node, String ret)
    {
        if(node == null)
            return ret;
        ret = postorderHelper(node.getLeft(), ret);
        ret = postorderHelper(node.getRight(), ret);
        ret += ", " + node.getData();
        return ret;
    }

    //Check if this BST contains the specified data
    public boolean contains(E data)
    {
        return containsHelper(root, data);
    }

    //Recursive helper method for contains
    private boolean containsHelper(BNode<E> node, E data)
    {   
        boolean x = false;
        if(node!= null){
            if(data.equals(node.getData()))
                return true;
            x = containsHelper(node.getLeft(), data);
            if(x == true)
                return true;
            x = containsHelper(node.getRight(), data);
            if(x == true)
                return true;
        }
        return x;
    }

    public List<BNode<E>> get(E data)
    {
        return getHelper(root, data);
    }

    public List<BNode<E>> getHelper(BNode<E> node, E data)
    {
        List<BNode<E>> list = new ArrayList<BNode<E>>();
        if(node!= null){
            if(data.equals(node.getData())){
                return list;
            }
            list = getHelper(node.getLeft(), data);
            list.add(node);
            list.add(node.getLeft());
            if(list.get(1) != null && data.equals(list.get(1).getData()))
                return list;
            list = getHelper(node.getRight(), data);
            list.add(node);
            list.add(node.getRight());
            if(list.get(1) != null && data.equals(list.get(1).getData()))
                return list;
        }
        return list;
    }
    //Remove the first occurance of data from this BST tree.
    //If data is successfully removed return true, otherwise return false.
    public boolean remove(E data)
    {
        if(!(contains(data)))
            return false;
        if(data == root.getData())
        {
            BNode<E> x = root;
            root = combine(x.getLeft(), x.getRight());
            return true;
            
        }
        if(get(data).size() <= 0)
            return true;
        if(get(data).get(1).getNumChildren() <= 0){
            if((get(data).get(0) != null && get(data).get(0).getLeft() != null) && (get(data).get(0).getLeft().getData().equals(data))){
                get(data).get(0).setLeft(null);
                return true;
            }
            else if((get(data).get(0) != null && get(data).get(0).getRight() != null) && (get(data).get(0).getRight().getData().equals(data))){
                get(data).get(0).setRight(null);
                return true;
            }
        }
        else if(get(data).get(1).getNumChildren() == 1){
            if((get(data).get(0) != null && get(data).get(0).getLeft() != null) && (get(data).get(0).getLeft().getData().equals(data))){
                get(data).get(0).setLeft(get(data).get(1).getLeft() != null? get(data).get(1).getLeft(): null);
                return true;
            }
            else if((get(data).get(0) != null && get(data).get(0).getRight() != null) && (get(data).get(0).getRight().getData().equals(data))){
                get(data).get(0).setRight(get(data).get(1).getRight() != null? get(data).get(1).getRight(): null);
                return true;
            }  
        }
        else
        {
            if((get(data).get(0) != null && get(data).get(0).getLeft() != null) && (get(data).get(0).getLeft().getData().equals(data))){
                get(data).get(0).setLeft(combine(get(data).get(1).getLeft(), get(data).get(1).getRight()));
                return true;
            }
            else if((get(data).get(0) != null && get(data).get(0).getRight() != null) && (get(data).get(0).getRight().getData().equals(data))){
                get(data).get(0).setRight(combine(get(data).get(1).getLeft(), get(data).get(1).getRight()));
                return true;
            }
            return true;
        }
        return false;
    }

    //Recursive helper method for remove. Removes the smallest descendant from the specified node.
    public BNode<E> removeSmallestChild(BNode<E> node)
    {
        if(node.hasLeftChild())
        {
            BNode<E> x  = removeSmallestChild(node.getLeft());
            if(x.getData() == node.getLeft().getData())
                node.setLeft(x.getRight());
            return x;
        }
        return node;
    }

    //Helper method for remove. Reforms the left and right subtrees into a single
    //BST and returns its root node.
    public BNode<E> combine(BNode<E> left, BNode<E> right)
    {
        BNode<E> x = removeSmallestChild(right);
        if(x.getData() == right.getData())
            right = right.getRight();
        x.setLeft(left);
        x.setRight(right);
        return x;
    }

    ///////////////////
    //  Helper Class //
    ///////////////////
    class BNode<E extends Comparable>
    {
        private E data;
        private BNode<E> left, right;

        public BNode(E data)
        {this.data = data;}

        public E getData()
        {return data;}

        public BNode<E> getLeft()
        {return left;}

        public BNode<E> getRight()
        {return right;}

        public void setLeft(BNode<E> left)
        {this.left = left;}

        public void setRight(BNode<E> right)
        {this.right = right;}

        public boolean hasLeftChild()
        {return null != left;}

        public boolean hasRightChild()
        {return null != right;}

        public boolean hasChildren()
        {return getNumChildren() > 0;}

        public int getNumChildren()
        {
            int ret = 0;
            if(hasLeftChild())  ret++;
            if(hasRightChild()) ret++;
            return ret;
        }
    }
}