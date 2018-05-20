function MapEntry(key, value) {
    this.key = key;
    this.value = value;
    this.next = null;
}

function HashMap() {
    this.buckets = [];
}

HashMap.prototype._hash = function (key) {
    return key % 10;
}

HashMap.prototype.get = function (key) {
    var entry = this.buckets[this._hash(key)];
    while (entry != null) {
        if (entry.key == key)
            return entry.value;
        else
            entry = entry.next;
    }
    return null;
}

HashMap.prototype.put = function (key, value) {
    var entry = this.buckets[this._hash(key)];
    if (entry == null) {
        this.buckets[this._hash(key)] = new MapEntry(key, value);
        return;
    }
    while (entry != null) {
        if (entry.key == key) {
            entry.value = value;
        } else {
            if (entry.next == null) {
                entry.next = new MapEntry(key, value);
                return;
            } else {
                entry = entry.next;
            }
        }
    }
}

var map = new HashMap();
map.put(1, "Hello");
map.put(2, " ");
map.put(11, "World");

console.log(map.get(1));
console.log(map.get(2));
console.log(map.get(11));