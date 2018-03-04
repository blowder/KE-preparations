var Color = Object.freeze({
    RED: 0,
    BLACK: 1
});

function Node(value) {
    this.parent = null;
    this.value = value;
    this.left = null;
    this.right = null;
    this.color = Color.RED
}

Node.prototype.isRoot = function () {
    return this.parent == null;
}

Node.prototype.getUnckleColor = function () {
    if (this.isRoot() || this.parent.isRoot()) {
        return null;
    }
    var grandParent = this.parent.parent;
    return grandParent.value > this.parent.value
        ? grandParent.right.color
        : grandParent.left.color;
}

function insert(root, value) {
    if (root == null) {
        var result = new Node(value);
        result.color = Color.BLACK;
        return result;
    }
    if (root.value > value) {
        root.left = updateChild(root, root.left, value);
    } else if (root.value < value) {
        root.right = updateChild(root, root.right, value);
    }
    //specific to red black tree logick
    if (!root.isRoot()) {
        root.color = Color.BLACK;
    }
    return root;
}

function updateChild(parent, child, value) {
    var result = child;
    if (result == null) {
        result = new Node(value);
        result.color = Color.RED;
        result.parent = parent;
    } else {
        result = insert(result, value);
    }
    return result;
}

function getBlackHeight(node) {
    if (node == null) { //all null nodes are black
        return 1;
    } else {
        return (node.color== Color.RED ? 0 : 1) + Math.max(getBlackHeight(node.left), getBlackHeight(node.right))
    }
}

var root = insert(null, 5);
root = insert(root, 2);
root = insert(root, 1);
root = insert(root, 0);

console.log(getBlackHeight(root));