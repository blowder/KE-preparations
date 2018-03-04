function Node(value) {
    this.value = value;
    this.next = null;
}

function Stack() {
    this.size = 0;
    this._top = null;
}

Stack.prototype.put = function (value) {
    var newTop = new Node(value);
    newNode.next = this._top;
    this._top 

    if (this._top == null) {
        this._top = new Node(value);
    } else {
        var newTop = new Node(value);
        newTop.next = this._top;
        this._top = newTop;
    }
    this.size++;
}
Stack.prototype.top = function () {
    return this._top == null ? null : this._top.value;
}

Stack.prototype.pop = function () {
    if (this._top == null) {
        return null;
    } else {
        var result = this._top;
        this._top = this._top.next;
        return result.value;
    }
}


var stack = new Stack();
console.log(stack.top());
stack.put("Hello");
console.log(stack.top());
console.log(stack.top());
stack.put(" World");
console.log(stack.top());
console.log(stack.pop());
console.log(stack.top());
