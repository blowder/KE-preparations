var array = [3, 5, 7, 1, 2, 1, 1];
console.log(sort(array));

function sort(array) {
    if (array.length == 0) {
        return [];
    }
    var pivot = array[0];
    var less = [];
    var greater = [];
    for (var i = 1; i < array.length; i++) {
        if (array[i] < pivot) {
            less.push(array[i]);
        } else {
            greater.push(array[i]);
        }
    }
    return sort(greater).concat(pivot).concat(sort(less));
}