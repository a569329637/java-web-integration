# 集合

## List
ArrayList：动态数组实现，内部元素可通过get和set方法进行访问，线程不安全
LinkedList：双向链表实现，在添加和删除内部元素比ArrayList性能更好，但是get和set方面不如ArrayList，线程不安全
Vector：线程安全的ArrayList
Stack：继承于Vector，一个先进后出的栈

## Set
HashSet：由哈希表实现，实际上是一个HashMap，key放元素，value放一个Object常量，内部元素不能重复
LinkedHashSet：继承于HashSet，保证了插入顺序有序，内部元素不能重复
TreeSet：保证内部元素有序，内部元素不能重复，底层实现是一个TreeMap，和HashSet一样，key放元素，value放一个Object常量

## Map
HashMap：基于哈希表实现，null能够作为key和value，key值唯一
LinkedHashMap：基于哈希表和链表实现，能够按照插入顺序遍历
TreeMap：基于红黑树实现，内部元素有序
HashTable：线程安全HashMap，null不能够作为key和value
