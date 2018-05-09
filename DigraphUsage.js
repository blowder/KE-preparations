const Digraph = require('./Digraph.js');
const DirectedDFS = require('./DirectedDFS.js');
var fs = require('fs');

console.log(recognize(".*(AsA|BB).*", "11AsA22"));

/**
 * 
 * @param {String} pattern
 * @param {String} text
 * @returns {boolean} 
 */
function recognize(pattern, text) {
    var digraph = buildNFA(pattern);
    var dfs = new DirectedDFS().build(digraph, [0]);
    var bag = [];
    for (var i = 0; i < digraph.getVerticlesSize(); i++) {
        if (dfs.marked(i)) {
            bag.push(i);
        }
    }
    for (var i of text) {
        var match = [];
        for (var j of bag) {
            if (j < digraph.getVerticlesSize()) {
                if (i == pattern[j] || "." == pattern[j]) {
                    match.push(j + 1);
                }
            }
        }
        if (match.length == 0) {
            match.push(0);
        }
        dfs = new DirectedDFS().build(digraph, match);
        bag = [];
        for (var i = 0; i < digraph.getVerticlesSize(); i++) {
            if (dfs.marked(i)) {
                bag.push(i);
            }
        }
    }
    return bag.includes(digraph.getVerticlesSize() - 1);
}

/**
 * 
 * @param {String} pattern 
 * @returns {DirectedDFS} 
 */
function buildNFA(pattern) {
    var nfa = new Digraph(pattern.length + 1); //plus finish symbol
    var stack = [];
    for (var i = 0; i < pattern.length; i++) {
        var symbol = pattern[i];
        var lp = i;
        //brackets and ORs
        if (symbol == "(" || symbol == "|") {
            stack.push(i);
        } else if (symbol == ")") {
            var or = stack.pop();
            if (pattern[or] == "|") {
                lp = stack.pop();
                nfa.addEdge(lp, or + 1);
                nfa.addEdge(or, i);
            } else { //if(symbol==")")
                lp = or;
            }
        }
        //in case of *
        if (i < pattern.length - 1 && pattern[i + 1] == "*") {
            nfa.addEdge(lp, i + 1)
            nfa.addEdge(i + 1, lp)
        }

        if (symbol == "(" || symbol == ")" || symbol == "*") {
            nfa.addEdge(i, i + 1);
        }
    }
    return nfa;
}


function readGraph(filepath) {
    var content = fs.readFileSync(filepath, "utf-8");

    var digraph = null;

    var lines = content.split("\n");
    for (var i = 0; i < lines.length; i++) {
        var line = lines[i];
        if (i == 0) {
            digraph = new Digraph(Number.parseInt(line));
        }
        var verticles = line.trim().split("\t")
        if (verticles.length != 2)
            continue;
        digraph.addEdge(Number.parseInt(verticles[0]), Number.parseInt(verticles[1]));
    }
    return digraph;
}


