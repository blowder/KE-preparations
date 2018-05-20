var array = [3, 5, 7, 1, 2, 1, 1];
sort(array, 0, array.length - 1);
console.log(array)

function sort(array, left, right) {
    if (left == right || left > right) {
        return;
    }
    var pivot = partition(array, left, right);
    sort(array, left, pivot - 1);
    sort(array, pivot + 1, right);
}
function partition(array, left, right) {
    var pivot = left + Math.floor((right - left) / 2);
    while (left != pivot || right != pivot) {
        if (array[left] < array[pivot]) {
            swap(array, left, pivot);
            pivot = left;
        } else if (left < pivot) {
            left++;
        }
        if (array[right] > array[pivot]) {
            swap(array, right, pivot);
            pivot = right;
        } else if (right > pivot) {
            right--;
        }
    }
    return pivot;
}
function swap(array, left, right) {
    var temp = array[left];
    array[left] = array[right];
    array[right] = temp;
}