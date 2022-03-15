public class BST {
    // Вершина дерева
    private class Node {
        //Значение вершины
        int key;

        // Дети вершины
        Node left, right;

        public Node(int x) {
            key = x;
            left = right = null;
        }
    }

    // Создание вершины дерева
    Node root;

    // Изначальное создание пустого дерева
    BST() {
        root = null;
    }

    // Удаление вершины по значению
    void deleteKey(int key) {
        root = delete_Recursive(root, key);
    }

    // Функция
    public Node delete_Recursive(Node root, int key) {
        // Вершина нулевая
        if (root == null) {
            return root;
        }

        // Узнаем
        if (key < root.key) {
            //  Нужно взять самый правый элемент из левого поддерева,
            //  т.к. он будет меньше корня, но самый большой из них
            root.left = delete_Recursive(root.left, key);
        } else if (key > root.key) {
            // Нужно взять самый левый элемент из правого поддерева,
            // т.к. он будет больше корня, но самый маленький из них
            root.right = delete_Recursive(root.right, key);
        } else {
            // Если корень имеет только одного ребенка, то просто берем второго
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Если вершина имеет два ребенка
            // Нужно взять самый правый элемент из левого поддерева,
            // т.к. он будет меньше корня, но самый большой из них
            root.key = minValue(root.right);

            // Сдвигаем все правые вершины поддерева
            root.right = delete_Recursive(root.right, root.key);
        }
        return root;
    }

    public int minValue(Node root) {
        // Поиск наименьшего элемента
        int minval = root.key;
        while (root.left != null) {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }

    // Добавляем вершину в дерево
    public void insert(int key) {
        root = insert_Recursive(root, key);
    }

    // Функция
    private Node insert_Recursive(Node root, int key) {
        // Если дерево нулевое
        if (root == null) {
            root = new Node(key);
            return root;
        }
        // Обход дерева для поиска места для новой ноды
        if (key < root.key) {
            //Добавлять в левое поддерево
            root.left = insert_Recursive(root.left, key);
        } else if (key > root.key) {
            //Добавлять в правое поддерево
            root.right = insert_Recursive(root.right, key);
        }
        return root;
    }

    // вывести BST
    public void inorder() {
        inorder_Recursive(root);
    }

    // вывести BST
    private void inorder_Recursive(Node root) {
        if (root != null) {
            inorder_Recursive(root.left);
            System.out.print(root.key + " ");
            inorder_Recursive(root.right);
        }
    }

    public boolean search(int key) {
        root = search_Recursive(root, key);
        if (root != null) {
            return true;
        } else {
            return false;
        }
    }

    //Поиск вершины в дереве
    private Node search_Recursive(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        if (root.key > key) {
            return search_Recursive(root.left, key);
        }
        return search_Recursive(root.right, key);
    }
}

class Main {
    public static void main(String[] args) {
        //CСоздаем BST
        BST bst = new BST();
        /*
              30
           /     \
          10      65
         /  \    /
        5   15  50   */
        //insert data into BST
        bst.insert(30);
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(65);
        bst.insert(50);
        //print the BST
        System.out.println("Создаем BST");
        bst.inorder();

        System.out.println("\nУдалить Ноду без детей:");
        bst.deleteKey(15);
        bst.inorder();

        System.out.println("\nУдалить Ноду с ребенком (одним):");
        bst.deleteKey(65);
        bst.inorder();

        System.out.println("\nУдалить Ноду с двумя детьми:");
        bst.deleteKey(10);
        bst.inorder();
        //Есть ли значение в BST?
        boolean ret_val = bst.search(30);
        System.out.println("\nЕсть ли в BST:" + ret_val);
        ret_val = bst.search(12);
        System.out.println("\nЕсть ли в BST:" + ret_val);
    }
}