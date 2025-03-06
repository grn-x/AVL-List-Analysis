# Title 
A Java project containing multiple test setups to compare the performance of a binary (search) tree with an arbitrary
list using a binary search algorithm directly

![Comparison](./docs/drawing_dark.svg#gh-dark-mode-only)
![Comparison](./docs/drawing_light.svg#gh-light-mode-only)

## About
Part of this years computer science curriculum was to implement a binary tree 
and then develop this further to a binary search tree and finally a balanced binary search /(AVL) tree.

At the end of the project I bet my teacher that all of these hours could have been avoided and a binary search algorithm on a sorted list would be faster than a binary search tree.

To prove my arrogance he wanted me to present a comparison of the two approaches.


## Technical Details
The projects Entry-Point is the `Main` class in the `src/main/java` directory.

<details>
<summary>The project is then divided into multiple packages, each containing a different data-structure</summary>


In school we are developing on the [online-ide.de](https://online-ide.de) platform, a WebApp which imitates the Java compilation and bytecode execution, by interpreting the Java code and running it in a sandboxed JavaScript environment.
As far as I know this is all done by a [single person](https://github.com/martin-pabst?tab=repositories), a teacher from Ingolstadt. This is insane, so nothing but my deepest respect, but I still prefer the local development environment for extension, debugging and feature-richness purposes. 
Hence I chose to develop this project in real Java.

#### Packages
- `interpreted`     - Contains a bare (=unbalanced) Binary Search Tree implementation, we developed in school
- `interpretedAVL` - Contains a balanced Binary Search Tree implementation, we developed in school
- `compiled`        - Contains a Wrapper Class called `BinarySearchFramework` which features sorted insertion, and binary search algorithms that work on any `List` implementation
  - To work with the various sorting mechanisms the items inserted are required to implement the `Comparable` interface
  - The `BSF` uses reflection to instantiate the `List` implementation passed to it, if the overloaded constructor is used, the list will be instantiated with an initial capacity, if possible

Due to this workspace's packaged nature, and the `interpreted`'s code's origin, each package is acting like standalone project, could be run completly indendently, but also has their own data interface.
This chaos becomes visible when viewing `ContentDTO` and `PopulateTree` helper functions.

To make sure the same random dataset is used for each test, the `PopulateTree` class is used to generate a random dataset and populate the trees in their three respective Data-Exchange-Objects.

</details>

## Results


<details>
<summary> Test Results from my Ryzen 7 5800X3D 32gb dualboot (Linux tests will follow, I promise) Windows 11 workstation </summary>

[I will follow](https://www.youtube.com/watch?v=BTa71Y5Dtxo)

</details>


<details>
<summary> (Deprecated) Test Results from my Ryzen 5 8640HS 16gb Windows 11 Notebook</summary>

```
Example Output: 10 chars each over 10,000 entries


            BSF linear search:
            Num Entries List (Linear Search): 10000
            Insertion time: 11,329ms
                Search time (avg): 161,005ms
                Search time (max): 191,982ms
                Search time (min): 153,032ms

            BSF (ArrayList (RandomAccess)):
            Num Entries List: 10000
            Insertion time: 7,075ms
                Search time (avg): 2,759ms
                Search time (max): 4,530ms
                Search time (min): 2,407ms

            Binary Tree (Non balanced, dataset randomized):
            Num Entries Tree: 10000
            Insertion time: 16,314ms
                Search time (avg): 4,349ms
                Search time (max): 11,210ms
                Search time (min): 3,332ms


            Binary Tree (AVL, balanced, dataset randomized):
            Num Entries Tree: 10000
            Insertion time: 878,072ms
                Search time (avg): 3,328ms
                Search time (max): 7,265ms
                Search time (min): 2,825ms

            Binary Tree (Non balanced, dataset presorted, worst-case):
            Num Entries Tree: 10000
            Insertion time: 774,718ms
                Search time (avg): 640,298ms
                Search time (max): 823,111ms
                Search time (min): 597,168ms

            Warning: java.util.LinkedList is not RandomAccess

            BSF (LinkedList (Non RandomAccess!)):
            Num Entries List: 10000
            Insertion time: 996,273ms
                Search time (avg): 2012,533ms
                Search time (max): 2213,094ms
                Search time (min): 1788,400ms
```

```
Example Output: 1,000 chars each over 100,000 entries


            BSF (ArrayList (RandomAccess)):
            Num Entries List: 100000
            Insertion time: 316,564ms
                Search time (avg): 93,573ms
                Search time (max): 202,000ms
                Search time (min): 76,785ms

            Binary Tree (Non balanced, dataset randomized):
            Num Entries Tree: 100000
            Insertion time: 154,320ms
                Search time (avg): 117,216ms
                Search time (max): 191,574ms
                Search time (min): 97,148ms


            Binary Tree (AVL, balanced, dataset randomized):
            Num Entries Tree: 100000
            Insertion time: 212823,719ms
                Search time (avg): 94,495ms
                Search time (max): 118,619ms
                Search time (min): 83,614ms
```
</details>

## Roadmap
- [ ] Refractor to use an universal data-exchange interface for each package
- [ ] Convert the test cases to make use of the JMH Java Microbenchmark Harness library