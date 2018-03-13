//longest suffix-prefix

function calcLpsTable(pattern) {
    var result = [];
    for (var suffixSize = 1; suffixSize < pattern.length + 1; suffixSize++) {
        var leftBorder = 0;
        var rightBorder = suffixSize - 1
        var cursor = 0;
        var lps = 0;
        while (cursor < suffixSize && rightBorder != 0) {
            if (pattern[leftBorder + cursor] == pattern[rightBorder - suffixSize-1 + cursor]) {
                cursor++
                lps++;
            } else {
                break;
            }
        }
        result.push(lps);
    }
    return result;
}


var result = calcLpsTable("ababab");
console.log(result);