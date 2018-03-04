var array = [3, 5, 7, 1, 2, 1, 1];

for (var i = 0; i < array.length - 1; i++) {
    for (var j = 0; j < array.length - i - 1; j++) {
        if (array[j] < array[j + 1]) {            
            var temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
        }
    }
}
console.log(array)