var array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

for (var i of array) {
    console.log(search3(array, i));
}
console.log(search3(array, 50));
console.log(search3(array, -1));

/* without creation of additional arrays */
function search2(array, start, end, number) {
    if (start >= end) {
        return -1;
    }
    var middle = Math.floor((end - start) / 2);
    var middleValue = array[middle + start];

    if (middleValue == number) {
        return middle + start;
    }
    if ((middle + start) == 0 || (middle + start) == array.length - 1) {
        return -1;
    }
    if (middleValue > number) {
        return search2(array, start, end - middle, number);
    } else {
        return search2(array, start + middle, end, number);
    }
}
/* with creation of additional array */
function search(array, number) {
    if (array.length == 0) {
        return -1;
    }
    var middle = Math.floor(array.length / 2);
    var middleValue = array[middle]
    if (middleValue == number) {
        return middle;
    }
    if (middleValue > number) {
        return search(array.slice(0, middle), number);
    } else {
        var result = search(array.slice(middle + 1, array.length), number);
        if (result == -1) {
            return -1;
        }
        return middle + 1 + result;
    }
}
/* IMHO most elegant variant */
function search3(array, number) {
    var middle;
    var min = 0;
    var max = array.length;
    while (min <= max) {
        middle = min + Math.floor((max - min) / 2);
        if (array[middle] == number)
            return middle;
        else if (array[middle] < number)
            min = middle + 1;
        else
            max = middle - 1;
    }
    return -1
}