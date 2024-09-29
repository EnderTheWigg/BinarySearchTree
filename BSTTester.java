public class BSTTester 
{

    public static void main(String[] args) 
    {
    	BST<String> tree = new BST<String>();
    	tree.add("C");
    	tree.add("A");
    	System.out.println("Pre Order:\t" + tree.preorder());
    	System.out.println("In Order:\t" + tree.inorder());
    	System.out.println("Post Order:\t" + tree.postorder());
    }
    
    
}