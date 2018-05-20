function Node(value) {
    this.value = value;
    this.next = null;
}

function Queue() {
    this.head = null;
    this.tail = null;
    this.size = 0;
}

Queue.prototype.add = function (value) {
    if (this.head == null && this.tail == null) {
        this.head = new Node(value);
        this.tail = this.head;
    } else {
        this.tail.next = new Node(value);
        this.tail = this.tail.next;
    }
    this.size++;
}
Queue.prototype.get = function () {
    if (this.size == 0) {
        return null;
    } else {
        var result = this.head.value;
        this.head = this.head.next;
        this.size--;
        return result;
    }
}

Queue.prototype.top = function () {
    return this.head.value;
}

var queue = new Queue();
queue.add(11);
queue.add(1);
queue.add(5);
while (queue.size != 0) {
    console.log(queue.get())
}