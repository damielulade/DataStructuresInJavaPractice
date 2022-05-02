package trees.linkednodesbased;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import trees.BST;

public class LinkedNodesBST<T extends Comparable<T>> implements BST<T> {

  protected LightNode<T> root;

  public LinkedNodesBST() {
    this.root = null;
  }

  public LinkedNodesBST(T element) {
    this.root = new LightNode<>(element);
  }

  public static void main(String[] args) {
    LinkedNodesBST<Integer> tree = new LinkedNodesBST<>();

    tree.add(1);
    tree.add(2);
    tree.add(3);
    tree.add(4);
    tree.add(5);
    tree.add(6);

    System.out.println(tree);

    tree.remove(14);
    System.out.println(tree);
  }


  public int height() {
    return height(root);
  }

  private int height(LightNode<T> node) {
    /*
    If the node is null, the height is 0
    Else, the height is 1 plus the max height between the children
     */
    if (node == null) {
      return 0;
    } else {
      return 1 + Math.max(height(node.left), height(node.right));
    }
  }

  @Override
  public boolean add(T element) {
    if (root == null) {
      root = new LightNode<>(element);
      return true;
    } else {
      return addRecursive(root, element);
    }
  }

  private boolean addRecursive(LightNode<T> subtree, T newElement) {
    /*
    If the element at this node is equal to the new element:
    - it is already in the tree;
    Else, if the element at this node is greater than the new element,
    You need to check the left child:
    - if it is null, you add the element as the left child
    - else, you check the left child's subtree
    Otherwise... (you repeat the process for the right subtree)
     */
    if (subtree.element.compareTo(newElement) == 0) {
      return false;
    } else if (subtree.element.compareTo(newElement) > 0) {
      if (subtree.left == null) {
        subtree.left = new LightNode<>(newElement);
        return true;
      } else {
        return addRecursive(subtree.left, newElement);
      }
    } else {
      if (subtree.right == null) {
        subtree.right = new LightNode<>(newElement);
        return true;
      } else {
        return addRecursive(subtree.right, newElement);
      }
    }
  }

  @Override
  public boolean remove(T element) {
    Set<LightNode<T>> deletedNode = new HashSet<>();
    root = remove(root, element, deletedNode);
    return !deletedNode.isEmpty();
  }

  private LightNode<T> remove(LightNode<T> subtree, T element, Set<LightNode<T>> deletedNode) {
    /*
    If the node is not in the subtree at all;
    Else, if the subtree's root is smaller than the element we want to remove:
    - perform the remove function on the right subtree;
    Else, if the subtree's root is bigger:
    - perform the remove function on the left subtree;
    Otherwise:
    - add the subtree node/root to the deletedNode list
    - perform the deleteNode function on the subtree to correct the BST invariants
    At the end, return the subtree.
     */
    if (subtree == null) {
      return null;
    } else if (subtree.element.compareTo(element) < 0) {
      subtree.right = remove(subtree.right, element, deletedNode);
    } else if (subtree.element.compareTo(element) > 0) {
      subtree.left = remove(subtree.left, element, deletedNode);
    } else {
      deletedNode.add(subtree);
      subtree = deleteNode(subtree);
    }
    return subtree;
  }

  private LightNode<T> deleteNode(LightNode<T> node) {
    /*
    If the node has no children:
    - return null
    Else, if the node only has a left child:
    - return the left node, thus removing its parent
    Else, if the node only has a right child:
    - return the right node, removing its parent
    Otherwise...
     */
    if (node.left == null && node.right == null) {
      return null;
    } else if ((node.left != null) && (node.right == null)) {
      return node.left;
    } else if ((node.left == null) && (node.right != null)) {
      return node.right;
    } else {
      /*
      Otherwise:
      - find the minimum node in the right child (leftmost of right)
        this will be the replacement at the root of the subtree
      - set its right child as a node to be carried from the right child
        this function will get rid of the minimum node we found prior
      - set its left child as whatever the subtree's left child was before
      ** some may decide to find the maximum on the left (rightmost of left)
       */
      LightNode<T> replacement = findMinNode(node.right);
      replacement.right = removeMinNode(node.right);
      replacement.left = node.left;
      return replacement;
    }
  }

  private LightNode<T> findMinNode(LightNode<T> subtree) {
    /*
    If the left child is null, return its parent as the minimum node
    Else, search in that left subtree for the minimum node
     */
    if (subtree.left == null) {
      return subtree;
    } else {
      return findMinNode(subtree.left);
    }
  }

  private LightNode<T> removeMinNode(LightNode<T> subtree) {
    /*
    If the left child is null, make the subtree the right node
    Else:
    - search in the left child
    - replace the left child with whatever node is found
    */
    if (subtree.left == null) {
      return subtree.right;
    } else {
      subtree.left = removeMinNode(subtree.left);
      return subtree;
    }
  }

  private LightNode<T> findMaxNode(LightNode<T> subtree) {
    if (subtree.right == null) {
      return subtree;
    } else {
      return findMinNode(subtree.right);
    }
  }

  @Override
  public boolean contains(T element) {
    return contains(root, element);
  }

  private boolean contains(LightNode<T> subtree, T element) {
    if (subtree == null) {
      return false;
    } else if (subtree.element.compareTo(element) == 0) {
      return true;
    } else if (subtree.element.compareTo(element) > 0) {
      return contains(subtree.left, element);
    } else {
      return contains(subtree.right, element);
    }
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    toString(root, 0, stringBuilder);
    return stringBuilder.toString() + '\n';
  }

  private void toString(LightNode<T> node, int indentation, StringBuilder stringBuilder) {
    if (node == null) {
      return;
    }
    IntStream.range(0, indentation).forEach(i -> stringBuilder.append("  "));
    stringBuilder.append(node.element + "\n");
    toString(node.left, indentation + 1, stringBuilder);
    toString(node.right, indentation + 1, stringBuilder);
  }


}
