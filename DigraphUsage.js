const Digraph = require('./Digraph.js');
const DirectedDFS = require('./DirectedDFS.js');
var fs = require('fs');
var digraph = readGraph("./digraph.txt");
digraph.toString();


var dfs = new DirectedDFS().build(digraph, [0]);
console.log(dfs.reached());

var pattern = "(.*AB((C|D*E)F)*G)";
console.log(buildNFA(pattern));

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


