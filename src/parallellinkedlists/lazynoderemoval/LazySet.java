package parallellinkedlists.lazynoderemoval;

/*
+ Validation in constant time
+ membership checking does not require any locking;
  it's even wait free (traverses list once, without locking)
+ physical removal od logically removed nodes could be batched and performed when convenient,
  thus reducing the number of times the physical chain is changed,
  and in turn the expensive propagation of information among threads.
- Operations add and remove still require locking (as in OptimisticSet)
  which may reduce the amount of parallelism.

Validation only needs to check the mark 'valid'

'remove' marks a node invalid before removing it
'contains' is lock-free
'add' works as in OptimisticSet

Validation becomes a O(constant) operation:
* pre is reachable from the head iff it has not been removed
  i.e. iff it is marked valid
* post follows pre in the list if pre.next() == post
  AND is marked valid

After finding the position, removal consists of 2 steps:
1. logical removal: mark the node to be removed as invalid
2. physical removal: rewire the list to skip over the node

The removal is lazy because logical and physical removal may be done at different times:
after a node has been logically removed, every thread is aware that
it should not be considered part of the list.
 */
public class LazySet<T> {



}
