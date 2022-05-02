package trees.linkednodesbased;

import java.util.List;
import trees.BST;

/*
AVL notes;
invariant:
4 every node, dif btw heights of left & right child subtrees
is at most 1.

insertion and removal must preserve both BST and AVL invariants

after each inset/remove:
- check if the tree became unbalanced
- if needed, re-balance the tree
  * if the AVL inv. holds before the operation,
    there are only 4 possible cases:



A BST of height h is balanced if:
- all nodes at levels <= (h - 2) have 2 children
- nodes at level (h - 1) have 0, 1 or 2 children
- nodes at level h have 0 children (i.e. leaves)

CERTIFIABLY MORE RELIABLE THAN THE RECURSIVE CLASS
(I could test this one properly).

-- Complexity of BST operations --
These are only true if the tree is balanced:
contains -> O(log n)
add -> O(log n)
remove -> O(log n)

The worst case is if you try to add SORTED DATA into a BST
All operations will cost linear time O(n)
Which is as bad as an ordered list.

In terms of making this class thread-safe:
'contains' is similar to 'find' in the linked list
'add' is similar to 'append' in the linked list
'remove' is quite a nightmare...

BST is a non-linear ADT,
So there is no 'unique' traversal order (iterator):
(you can traverse lists FORWARDS, BACKWARDS, ODDS THEN EVENS etc.)
(this is why there is caution around the word 'unique')

Some common traversal orders (for any tree) are:
preorder, inorder, postorder, depth-first, breadth-first
 */
public class NonRecursiveBST<T extends Comparable<T>> implements BST<T> {

  protected MintNode<T> root;

  public NonRecursiveBST() {
    this.root = null;
  }

  public NonRecursiveBST(T element) {
    this.root = new MintNode<>(element);
  }

  public static void main(String[] args) {
    NonRecursiveBST<Integer> tree = new NonRecursiveBST<>();
    tree.add(3);
    System.out.println("\n" + tree);
    tree.add(1);
    System.out.println("\n" + tree);
    tree.add(9);
    System.out.println("\n" + tree);
    tree.add(7);
    System.out.println("\n" + tree);
    tree.add(10);
    System.out.println("\n" + tree);
    tree.add(6);
    System.out.println("\n" + tree);

    tree.remove(9);
    System.out.println("\n" + tree);
    tree.remove(3);
    System.out.println("\n" + tree);
  }

  @Override
  public String toString() {
    return root.toString();
  }

  @Override
  public boolean add(T element) {
    // if the tree is empty, add the element as the root
    if (root == null) {
      root = new MintNode<>(element);
      return true;
    }
    // set a current node as root, with its parent as null
    MintNode<T> curr = root;
    MintNode<T> parent = null;

    // keep going until you find a null current node
    // where its parent would be a leaf
    while (curr != null) {

      // set the parent as the current node
      // i.e. move the parent down
      parent = curr;

      /*
      If the current node is the same as the element to add
      - return false
      Else, if current is greater than the node to add
      - set current as the left child (move it down and left)
      Else, set current as the right child
       */
      if (curr.element.compareTo(element) == 0) {
        return false;
      } else if (curr.element.compareTo(element) > 0) {
        curr = curr.left;
      } else {
        curr = curr.right;
      }
    }

    /*
    If the parent is greater than the element
    - add the element as the parent's left child
    Else
    - add the element as the parent's right child
    Return true
     */
    if (parent.element.compareTo(element) > 0) {
      parent.left = new MintNode<>(element);
    } else {
      parent.right = new MintNode<>(element);
    }
    return true;
  }

  @Override
  public boolean remove(T element) {
    // set current as root, and its parent as null
    MintNode<T> curr = root;
    MintNode<T> parent = null;

    /*
    Keep going until:
    A. current becomes null, so its parent is a node
    -- OR --
    B. the current node is equal to the element you want to remove
       i.e. you found the node to check for removal
     */
    while (curr != null && curr.element.compareTo(element) != 0) {

      // set parent as the current node (move parent pointer down)
      parent = curr;

      // pick left child if the current is greater than the element
      // otherwise, pick the right child
      if (curr.element.compareTo(element) > 0) {
        curr = curr.left;
      } else {
        curr = curr.right;
      }
    }

    // You did not find a node to remove as it is not in the tree
    if (curr == null) {
      return false;
    }

    /*
    If the parent is null:
    - the current never changed from root
    - remove the root by performing deleteNode on root
    Else, if the parent is greater than the element to remove:
    - remove the left child using deleteRoot to change the tree
    Else:
    - remove the right child
    Return true
     */
    if (parent == null) {
      root = deleteNode(root);
    } else if (parent.element.compareTo(element) > 0) {
      parent.left = deleteNode(parent.left);
    } else {
      parent.right = deleteNode(parent.right);
    }
    return true;
  }

  private MintNode<T> deleteNode(MintNode<T> node) {
    /*
    If the right child of the node to remove is null:
    - find its replacement
      i.e. the minimum/leftmost node in the right subtree
    - set the replacement's right child as
      the right subtree, altered after removeMinNode
    - set the replacement's left child as
      the original left subtree, since this is unaffected
    - return the replacement as the new node

    Else if the left child of the node is null,
    Carry out the same steps but inverted:
    - replace with rightmost of left
    - set right child as original right subtree
    - set left as left subtree altered by removeMaxNode
    - return replacement

    Else, the node to remove has no children, so:
    - return null
      i.e. replace the node with a fresh leaf node
     */
    if (node.right != null) {
      MintNode<T> replacement = findMinNode(node.right);
      replacement.right = removeMinNode(node.right);
      replacement.left = node.left;
      return replacement;
    } else if (node.left != null) {
      MintNode<T> replacement = findMaxNode(node.left);
      replacement.right = node.right;
      replacement.left = removeMaxNode(node.left);
      return replacement;
    }
    return null;
  }

  private MintNode<T> findMinNode(MintNode<T> subtree) {
    MintNode<T> curr = subtree;
    while (curr.left != null) {
      curr = curr.left;
    }
    return curr;
  }

  private MintNode<T> findMaxNode(MintNode<T> subtree) {
    MintNode<T> curr = subtree;
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr;
  }

  private MintNode<T> removeMinNode(MintNode<T> subtree) {
    // make sure the subtree is not null
    // it won't be due to the IF statement in deleteNode
    assert subtree != null;

    // set the current node as the subtree root
    // set it s parent as null
    MintNode<T> curr = subtree;
    MintNode<T> parent = null;

    // until you find the current has no left child:
    // set the parent as current (the parent's left child)
    // set current as its left child
    while (curr.left != null) {
      parent = curr;
      curr = curr.left;
    }

    /*
    If the parent is null, current had no left child, so:
    - return the right child as the new head of the subtree
    Else:
    - set the parent's left child as the right of current
      this gets rid of current as the left child and
      brings in current's right so that it is not lost
    - return the edited subtree
     */
    if (parent == null) {
      return curr.right;
    } else {
      parent.left = curr.right;
      return subtree;
    }
  }

  /*
  Same as removeMinNode, but decisions are inverted
  and left/right actions are inverted.
   */
  private MintNode<T> removeMaxNode(MintNode<T> subtree) {
    assert subtree != null;

    MintNode<T> curr = subtree;
    MintNode<T> parent = null;

    while (curr.right != null) {
      parent = curr;
      curr = curr.right;
    }
    if (parent == null) {
      return curr.left;
    } else {
      parent.right = curr.left;
      return subtree;
    }
  }

  @Override
  public boolean contains(T element) {
    MintNode<T> curr = root;
    while (curr != null) {
      if (curr.element.compareTo(element) == 0) {
        return true;
      } else if (curr.element.compareTo(element) > 0) {
        curr = curr.left;
      } else {
        curr = curr.right;
      }
    }
    return false;
  }

  /*
  ALL TREE TRAVERSAL FUNCTIONS IN THIS SECTION WILL BE
  RECURSIVE AS THIS IS HOW THEY WERE TAUGHT :/

  Recursive implementation:
  + elegant
  - if the tree is too high, you may get StackOverflowError
  Solution: create your own stack (list of visited nodes)
  + better than stack/recursive calls which stores the entire
    context, with variables and everything
  + more memory is available on the heap than in stack frames

  PREORDER:  node, left,  right
  INORDER:   left, node,  right
  POSTORDER: left, right, node

  DEPTH-FIRST:
  Go as far down as possible, visit, backtrack one level up
  Inorder and Postorder are depth-first traversals

  BREADTH-FIRST:
  Traverse the tree level by level, starting from the root
  You can use a queue to implement this easily

  How to traverse depends on the need for it:
  Return elements in their natural order: inorder
  Evaluating an expression: postorder
  Shortest path to the exit: breadth-first
   */
  private void traversePreorder(MintNode<T> subtree, List<T> traversal) {
    if (subtree == null) {
      return;
    }
    traversal.add(subtree.element);
    traversePreorder(subtree.left, traversal);
    traversePreorder(subtree.right, traversal);
  }

  private void traverseInorder(MintNode<T> subtree, List<T> traversal) {
    if (subtree == null) {
      return;
    }
    traverseInorder(subtree.left, traversal);
    traversal.add(subtree.element);
    traverseInorder(subtree.right, traversal);
  }

  private void traversePostorder(MintNode<T> subtree, List<T> traversal) {
    if (subtree == null) {
      return;
    }
    traversePostorder(subtree.left, traversal);
    traversePostorder(subtree.right, traversal);
    traversal.add(subtree.element);
  }

  private class MintNode<T> {

    protected T element;
    protected MintNode<T> left;
    protected MintNode<T> right;

    public MintNode(T element) {
      this.element = element;
    }


    @Override
    public String toString() {
      return "{ N[" + element + "] L -> " + left + " R -> " + right + " }";
    }
  }
}
