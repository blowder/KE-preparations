//longest suffix-prefix

function calcLpsTable(pattern) {
    var result = {};
    result[0] = 0;
    var i = 1;
    var len = 0;
    while (i < pattern.length) {
        //display data
        var pointer = "";
        for (var j = 0; j <= i; j++) {
            if (j < i) {
                pointer += " ";
            } else {
                pointer += pattern[i] == pattern[len] ? "|" : "X";
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
        //display data ends

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
        //display data
        var lpsString = "";
        for (var j = 0; j < i; j++) {
            lpsString += result[j];
        }
        console.log(lpsString);
        //display data ends
    }
    return result;
}


var result = search("BCBAABACAABABACAA", "ABABAC");
console.log(result);

function search(string, pattern) {
    var failIndex = calcLpsTable(pattern);

    var stringIndex = 0;
    var patternIndex = 0;
    while (stringIndex < string.length) {
        display(string, stringIndex, pattern, patternIndex);
        if (string[stringIndex] == pattern[patternIndex]) {
            if (patternIndex == pattern.length - 1) {
                return stringIndex - patternIndex;
            }
            stringIndex++;
            patternIndex++;
        } else if (patternIndex == 0) {
            stringIndex++;
        } else {
            patternIndex = failIndex[patternIndex];
        }
    }

}

function display(string, stringIndex, pattern, patternIndex) {
    var pointerLine = "";
    for (var i = 0; i <= stringIndex; i++) {
        pointerLine += i == stringIndex ? "|" : " ";
    }
    var patternLine = "";
    for (var i = 0; i <= stringIndex - patternIndex - 1; i++) {
        patternLine += " ";
    }
    patternLine += pattern;
    console.log(pointerLine);
    console.log(string);
    console.log(patternLine);
    console.log("---");
}