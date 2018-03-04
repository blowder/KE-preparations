function Node(value) {
    this._value = value;
    this._left = null;
    this._right = null;
}

Node.prototype._updateNode = function (oldNode, newNode) {
    return oldNode != null ? oldNode.addNode(newNode) : newNode;
}

Node.prototype.addNode = function (node) {
    if (this._value > node._value) {
        this._left = this._updateNode(this._left, node)
    } else if (this._value < node._value) {
        this._right = this._updateNode(this._right, node)
    }
    return this;
}
/* BFS - Breadth-first search */
Node.prototype.bfsDisplay = function () {
    var queue = [this];
    while (queue.length != 0) {
        var node = queue.shift();
        console.log(node._value);
        if (node._left != null) {
            queue.push(node._left)
        }
        if (node._right != null) {
            queue.push(node._right)
        }
    }
}


Node.prototype.bfsDisplayR = function () {
    this._bfsDisplay([this]);
}

Node.prototype._bfsDisplay = function (nodes) {
    if (nodes.length == 0) {
        return;
    }
    var childs = []
    for (var node of nodes) {
        console.log(node._value);
        if (node._left != null) {
            childs.push(node._left)
        }
        if (node._right != null) {
            childs.push(node._right)
        }
    }
    this._bfsDisplay(childs);
}

/* DFS - Depth-first search */
Node.prototype.displayPreOrder = function () {
    console.log(this._value)
    if (this._left != null) {
        this._left.displayPreOrder()
    }
    if (this._right != null) {
        this._right.displayPreOrder()
    }
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

Node.prototype.displayPostOrder = function () {
    if (this._left != null) {
        this._left.displayPostOrder()
    }
    if (this._right != null) {
        this._right.displayPostOrder()
    }
    console.log(this._value)
}

var root = new Node(50)
for (var i = 0; i < 10; i++) {
    var nodeValue = Math.floor(Math.random() * 100)
    root.addNode(new Node(nodeValue));
}

root.bfsDisplay();