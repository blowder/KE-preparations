//longest suffix-prefix

function calcLpsTable(pattern) {
    var result = {};
    result[0] = 0;
    var i = 1;
    var len = 0;
    while (i < pattern.length) {
        var pointer = "";
        for (var j = 0; j <= i; j++) {
            if (j < i) {
                pointer += " ";
            } else {
                pointer += "|";
            }
        }
        console.log("");
        console.log(pointer);
        var shift = i - len;
        if (shift > 0) {
            console.log(pattern);
            var padding = "";
            while (shift != 0) {
                padding += "-";
                shift--;
            }
            console.log(padding + pattern);
        }
        //console.log(pattern[i] + " and " + pattern[len]);
        if (pattern[i] == pattern[len]) {
            len++;
            result[i] = len;
            i++;
        } else {
            if (len != 0) {
                len = result[len - 1];
            } else {
                result[i] = 0;
                i++;
            }
        }
    }
    return result;
}


var result = calcLpsTable("ababca");
console.log(result);