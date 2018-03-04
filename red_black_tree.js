const RED = true;
const BLACK = false;

function Node(parent, value, color) {
    this.parent = parent;
    this.value = value;
    this.color = color;
    this.left = null;
    this.right = null;
}


Node.prototype.displayInOrder = function () {
    if (this.left != null) {
        this.left.displayInOrder()
    }
    console.log(this.value + ":" + (this.color ? "Red" : "Black"))
    if (this.right != null) {
        this.right.displayInOrder()
    }
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
    //RB fixup tree
    if (!isRed(root.left) && isRed(root.right))
        root = rotateLeft(root);
    if (isRed(root.left) && isRed(root.left.left))
        root = rotateRight(root);
    if (isRed(root.left) && isRed(root.right))
        root = recolorTree(root);
    return root;
}

function isRed(node) {
    return node != null && node.color == RED;
}

function rotateLeft(node) {
    var result = node.right;
    node.right = result.left;
    result.left = node;
    //inherit parent color
    result.color = node.color;
    node.color = RED;
    //swap parents
    result.parent = node.parent;
    node.parent = result;
    return result;
}
function rotateRight(node) {
    var result = node.left;
    node.left = result.right;
    result.right = node;
    //inherit parent color
    result.color = node.color;
    node.color = RED;
    //swap parents
    result.parent = node.parent;
    node.parent = result;
    return result;
}

function recolorTree(node) {
    if (node == null) {
        return null;
    }
    if (node.parent != null) {
        node.color = RED;
    }
    node.left.color = BLACK;
    node.right.color = BLACK;
    return node;
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
function printLevels(level) {
    var nextLevel = [];
    var levelPrint = ""
    while (level.length != 0) {
        var node = level.shift();
        if (node.left != null) {
            nextLevel.push(node.left);
        }
        if (node.right != null) {
            nextLevel.push(node.right);
        }
        levelPrint += "[" + node.value + ":" + (node.color ? "Red" : "Black") + "]";
    }
    console.log(levelPrint);
    if(nextLevel.length !=0)
        printLevels(nextLevel);
}

var root = insert(null, 5);
for (var i = 0; i < 10; i++) {
    var nodeValue = Math.floor(Math.random() * 100)
    root = insert(root, nodeValue);
}
//root = insert(root, 0.5);
printLevels([root]);
root.displayInOrder();