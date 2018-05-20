function search(source, pattern) {
    var result = [];
    for (var i = 0; i < source.length - pattern.length + 1; i++) {
        for (var j = 0; j < pattern.length; j++) {
            if (source[i + j] != pattern[j]) {
                break;
            } else if (j == pattern.length - 1) {
                result.push(i);
                
            }
        }
    }
    return result;
}


var line = "sdfsdfasdfasdfasdfasdfsadfasxz";
console.log(search(line, "dfa"));