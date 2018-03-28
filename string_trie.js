function Node() {
    this.childs = [];
    this.value = null;
}

function TrieST() {    
    var root = new Node();
}

TrieST.prototype.keys = function () {
    var queue = [];
    this._keys(this.root, "", queue);
    return queue;
}

TrieST.prototype._keys = function (node, prefix, queue) {
    if (node == null) {
        return;
    }
    if (node.value != null) {
        queue.push(prefix);
    }

    for (var i = 0; i < node.childs.length; i++) {
        this._keys(node.childs[i], prefix + String.fromCharCode(i), queue);
    }
}

TrieST.prototype.get = function (key) {
    return this._get(this.root, key, 0);
}

TrieST.prototype._get = function (node, key, level) {
    if (node == null) {
        return -1;
    }
    if (level == key.length) {
        return node.value;
    } else {
        var keyIndex = key[level].charCodeAt();
        return this._get(node.childs[keyIndex], key, level + 1);
    }
}

TrieST.prototype.put = function (key, value) {
    this.root = this._put(this.root, key, value, 0);
}

TrieST.prototype._put = function (node, key, value, level) {
    var result = node == null ? new Node() : node;
    if (level == key.length) {
        result.value = value;
    } else {
        var keyIndex = key[level].charCodeAt();
        result.childs[keyIndex] = this._put(result.childs[keyIndex], key, value, level + 1);
    }
    return result;
}


var trie = new TrieST();
trie.put("abc", "hello");
trie.put("abd", "hello2");
trie.put("abs", "hello3");
console.log(trie.get("abc"));
console.log(trie.get("abd"));

console.log(trie.keys());
//console.log(trie);