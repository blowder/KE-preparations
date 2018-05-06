const Digraph = require('./Digraph.js');

class DirectedDFS {

    /**
    * @param {Digraph} graph 
    * @param {Number[]} vertexes     
    */
    build(graph, vertexes) {
        this._graph = graph;
        this._marked = [];
        this._trace(vertexes);
        return this;
    }

    _trace(vertexes) {
        this._marked = this._marked.concat(vertexes);
        for (var vertex of vertexes) {
            var linkedVertex = this._graph.adjacencyList(vertex)
                .filter(vertex => !this._marked.includes(vertex));
            this._trace(linkedVertex);
        }
    }

    marked(vertex) {
        return this._marked.includes(vertex);
    }

    reached(){
        return this._marked;
    }
}

module.exports = DirectedDFS;
