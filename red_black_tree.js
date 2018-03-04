const RED = true;
const BLACK = false;

function Node(parent, value, color) {
    this.parent = parent;
    this.value = value;
    this.color = color;
    this.left = null;
    this.right = null;
}

function insert(root, value) {
    if (root == null) {
        return new Node(null, value, BLACK);
    }
    if (root.value > value) {
        root.left = _updateChild(root, root.left, value);
    } else if (root.value < value) {
        root.right = _updateChild(root, root.right, value);
    }
    return root;
}

function _updateChild(parent, child, value) {
    return child == null
        ? new Node(parent, value, RED)
        : insert(child, value);
}

function getBlackHeight(node) {
    if (node == null) { //all null nodes are black
        return 1;
    } else {
        return (node.color == RED ? 0 : 1) + Math.max(getBlackHeight(node.left), getBlackHeight(node.right))
    }
}

var root = insert(null, 5);
root = insert(root, 2);
root = insert(root, 1);
root = insert(root, 0);

console.log(getBlackHeight(root));