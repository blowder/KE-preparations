function Node(value) {
    this.value = value;
    this.next = null;
}

function LinkedList() {
    this.head = null;
    this.size = 0;
}

LinkedList.prototype.add = function (value) {
    if (this.head == null) {
        this.head = new Node(value);
    } else {
        var last = this.head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = new Node(value);
    }
    this.size++;
}

LinkedList.prototype.get = function (index) {
    if (index >= this.size) {
        throw "Index out if bound exception";
    } else {
        var counter = 0;
        var result = this.head;
        while (counter < index) {
            result = result.next;
            counter++;
        }
        return result.value;
    }
}
LinkedList.prototype.delete = function (index) {
    if (index >= this.size) {
        throw "Index out of bounds exception"
    } else if (index == 0) {
        this.head = this.head.next;
        this.size--;
    } else {
        var counter = 1;
        var result = this.head
        while (counter < index) {
            result = result.next;
            counter++;
        }
        result.next = result.next.next;
        this.size--;
    }
}

var list = new LinkedList();
list.add(1)
list.add(2)
list.add(3)

for (var i = 0; i < list.size; i++) {
    console.log(list.get(i))
}
list.delete(0);

for (var i = 0; i < list.size; i++) {
    console.log(list.get(i))
}

