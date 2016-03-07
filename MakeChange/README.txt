Ben King
bking11@u.rochester.edu

Brainstorming:

To Solve this problem we will have to try all combinations, which can be done recursively. If you have x change left, for each denomination c less than x, recurse with input of (x - c), and increment the change count. The base case is when you can no longer make valid change or you make the correct change. Dynamic programming can be used to shortcut the recursion so that the algorithm does not take exponential time. 

In this Lab I created a program to make change with arbitrary sets of coin denominations using dynamic programming. 

A naive approach to this problem gives exponential running time, because the recursion branches d number of times where d is the number of different coins, and has a depth of n where n is the target amount. By using dynamic programming you short circuit the recursion so you are only calculating one more case than for n-1, giving linear time, which would be less depending on the number of coins. 