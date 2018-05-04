const Digraph = require('./Digraph.js');
const DirectedDFS = require('./DirectedDFS.js');
var fs = require('fs');
var digraph = readGraph("./digraph.txt");
digraph.toString();


var dfs = new DirectedDFS().build(digraph, [0]);
console.log(dfs.reached());

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


