
public class BinarySearchTreeVissicchio 
{
    NodeTreeVissicchio root = null;

    public void BSTInsert(BinarySearchTreeVissicchio tree, NodeTreeVissicchio newNode)
    {
        //trailing is a pointer that is always one step behind
        NodeTreeVissicchio trailing = null;
        //current is a pointer to where we currently are at in the tree
        NodeTreeVissicchio current = tree.root;

        //go through BST until we find a null node
        while (current != null)
        {
            trailing = current;
            int result = newNode.myItem.compareToIgnoreCase(current.myItem);
            if (result < 0)
            {
                //go left
                current = current.left;
                System.out.print("L");
                
            }//if
            else //must be >=
            {
                //go right
                current = current.right;
                System.out.print("R");
                
            }//else
        }//while

        //the parent node will be the node one step behind
        newNode.parent = trailing;
        //set the first node of the BST
        if (trailing == null)
        {
            tree.root = newNode;
            System.out.print("Root: " + tree.root.myItem);
            //System.out.print(root.myItem);
        }//if
        //start adding child nodes
        else
        {
            int result = newNode.myItem.compareToIgnoreCase(trailing.myItem);
            if(result < 0)
            {
                //go left since less than (sooner in alphabet)
                trailing.left = newNode;
                System.out.print(": " + trailing.left.myItem);
                
            }//if
            else
            {
                //go right since greater than (further in alphabet)
                trailing.right = newNode;
                System.out.print(": " + trailing.right.myItem);
                
            }//else
        }//else
        System.out.println("");
    }//BSTInsert

    public void inOrderTraversal(NodeTreeVissicchio myNode)
    {
        //Start at the left most node and make your way to the right most node
        //Should print out the items in alphabetical order
        if (myNode != null)
        {
            inOrderTraversal(myNode.left);
            System.out.println(myNode.myItem);
            inOrderTraversal(myNode.right);
        }
    }//InOrderTraversal

    int compCount = 0;
    boolean found = false;

    public void BSTSearch(NodeTreeVissicchio root, String target)
    {
        int result = root.myItem.compareToIgnoreCase(target);
        //target has been found
        if (result == 0)
        {
            compCount++;
            System.out.print(": The item, " + target + ", was found");
            found = true;
        }//if
        //continue searching
        else
        {
            if (result > 0 && root.left != null)
            {
                //go left to continue searching for the target
                compCount++;
                System.out.print("L");
                BSTSearch(root.left, target);
            }
            if (result < 0 && root.right != null)
            {
                //go right to continue searching for the target
                compCount++;
                System.out.print("R");
                BSTSearch(root.right, target);
                
            }
        }//else
        //System.out.println("");
        if (found == false)
        {
            //item was never found while going through the BST
            System.out.print(": The item, " + target + ", was not found");
            //set found back to true so it only prints once
            found = true;
        }
        //found = false;
    }//BSTSearch

    public int searchComp(NodeTreeVissicchio root, String target)
    {
        //start the count at 0
        compCount = 0;
        BSTSearch(root, target);
        System.out.println("");
        System.out.println("BST Search had " + compCount + " comparisons.");
        System.out.println("");
        //return the compCount to use for finding the average comparisons
        return compCount;
    }//searchComp

}
