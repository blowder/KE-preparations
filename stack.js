class Node {
    constructor(value) {
        this.value = value;
        this.next = null;
    }
}

class Stack {
    constructor() {
        this.size = 0;
        this._top = null;
    }
    put(value) {
        var oldTop = this._top;
        this._top = new Node(value);
        this._top.next = oldTop;
        this.size++;
    }
    top() {
        return this._top != null ? this._top.value : null;
    }
    pop() {
        var value = null;
        if (this._top != null) {
            value = this._top.value;
            this._top = this._top.next;
            this.size--;
        }
        return value;
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
