
function Node(value) {
    this._value = value;
    this._left = null;
    this._right = null;
}

function updateNode(oldNode, newNode) {
    return oldNode != null ? insert(oldNode, newNode) : newNode;
}

function insert(target, node) {
    var result = target;
    if (target._value > node._value) {
        target._left = updateNode(target._left, node)
    } else if (target._value < node._value) {
        target._right = updateNode(target._right, node)
    }

    if (target.getBalanceIndex() == 2) {
        if (target._left.getBalanceIndex() < 0) {
            target._left = rotateLeft(target._left)
        }
        result = rotateRight(target);
    } else if (target.getBalanceIndex() == -2) {
        if (target._right.getBalanceIndex() > 0) {
            target._right = rotateRight(target._right)
        }
        result = rotateLeft(target);
    }
    return result;
}

function rotateRight(node) {
    var result = node._left;
    var oldRight = result._right;
    result._right = node;
    node._left = oldRight;
    return result;
}

function rotateLeft(node) {
    var result = node._right;
    var oldLeft = result._left;
    result._left = node;
    node._right = oldLeft;
    return result;
}
/**
 * This function could be part of Node class. It
 *  was created only for proof posibility to swap nodes without external function. *
 * @param {Node} node 
 */
function rotateLeft2(node) {
    //swap node values
    var oldValue = node._value;
    node._value = node._right._value;
    node._right._value = oldValue;
    //now we swap childs of right child
    swapChilds(node._right);
    //now we will swap root childs.
    swapChilds(node);
    // now we swap root right and root.left left child
    var oldRootright = node._right;
    node._right = node._left._left;
    node._left._left = oldRootright;
    return node;
}

function swapChilds(node) {
    if (node != null) {
        var oldChild = node._left;
        node._left = node._right;
        node._right = oldChild;
    }
}

Node.prototype.getHeight = function () {
    return 1 + Math.max(
        this._left == null ? 0 : this._left.getHeight(),
        this._right == null ? 0 : this._right.getHeight()
    );
}

Node.prototype.getBalanceIndex = function () {
    var leftHight = this._left == null ? 0 : this._left.getHeight();
    var rightHight = this._right == null ? 0 : this._right.getHeight();
    return leftHight - rightHight;
}
Node.prototype.displayInOrder = function () {
    if (this._left != null) {
        this._left.displayInOrder()
    }
    console.log(this._value)
    if (this._right != null) {
        this._right.displayInOrder()
    }
}

var root = new Node(50);
for (var i = 0; i < 10; i++) {
    var nodeValue = Math.floor(Math.random() * 100)
    root = insert(root, new Node(nodeValue));
}


console.log("High is " + root.getHeight());
console.log("Balance index is " + root.getBalanceIndex());
root.displayInOrder();