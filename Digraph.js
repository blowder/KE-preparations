
class Digraph {
    constructor(verticlesCount) {
        this.verticlesCount = verticlesCount;
        this.buckets = [];
        this.edgesCount = 0;
        for (let i = 0; i < verticlesCount; i++) {
            this.buckets[i] = [];
        }
    }

    getVerticlesSize() {
        return this.verticlesCount;
    }

    getEdgesSize() {
        return this.edgesCount;
    }

    addEdge(fromVerticle, toVerticle) {
        if (fromVerticle >= this.verticlesCount || toVerticle >= this.verticlesCount)
            return;
        this.buckets[fromVerticle].push(toVerticle);
        this.edgesCount++;
    }

    adjacencyList(verticle) {
        return verticle < this.verticlesCount
            ? this.buckets[verticle]
            : [];
    }

    reverse() {
        var result = new Digraph(this.verticlesCount);
        for (let fromVerticle = 0; fromVerticle < this.verticlesCount; fromVerticle++) {
            for (let toVerticleIndex = 0; toVerticleIndex < this.buckets[fromVerticle].length; toVerticleIndex++) {
                result.addEdge(fromVerticle, this.buckets[fromVerticle][toVerticleIndex])
            }
        }
        return result;
    }

    toString() {
        var result = "";
        result += "verticles count is " + this.verticlesCount + "\n";
        result += "edges count is " + this.edgesCount + "\n";
        for (let fromVerticle = 0; fromVerticle < this.verticlesCount; fromVerticle++) {
            for (let toVerticleIndex = 0; toVerticleIndex < this.buckets[fromVerticle].length; toVerticleIndex++) {
                result += "(" + fromVerticle + "->" + this.buckets[fromVerticle][toVerticleIndex] + ")" + "\n";
            }
        }
        console.log(result);
    }
}

module.exports = Digraph;
