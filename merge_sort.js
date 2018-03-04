var array = [3, 5, 7, 1, 2, 1, 1];

console.log(sort(array));

function sort(array) {
    if (array.length <= 1) {
        return array;
    }
    var middle = Math.floor(array.length / 2)

    return merge(
        sort(array.slice(0, middle)),
        sort(array.slice(middle, array.length))
    );
}

function merge(left, right) {
    var result = [];
    var leftIndex = 0;
    var rightIndex = 0;
    while (rightIndex < right.length && leftIndex < left.length) {
        if (left[leftIndex] < right[rightIndex]) {
            result.push(left[leftIndex++]);
        } else {
            result.push(right[rightIndex++]);
        }
    }
    /*
    while (rightIndex != right.length) {
         result.push(right[rightIndex]);
         rightIndex++;
     }
     while (leftIndex != left.length) {
         result.push(left[leftIndex]);
         leftIndex++;
     }
     */
    return result.concat(right.slice(rightIndex)).concat(left.slice(leftIndex));
}