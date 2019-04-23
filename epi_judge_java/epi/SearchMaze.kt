package epi

import epi.test_framework.EpiTest
import epi.test_framework.EpiUserType
import epi.test_framework.GenericTest
import epi.test_framework.TestFailure
import java.util.*

object SearchMaze {
    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Coordinate(var x: Int, var y: Int) {

        override fun equals(o: Any?): Boolean {
            if (this === o) {
                return true
            }

            if (o == null || javaClass != o.javaClass) {
                return false
            }

            val that = o as Coordinate?
            return if (x != that!!.x || y != that.y) {
                false
            } else true
        }
    }

    enum class Color {
        WHITE, BLACK
    }

    @JvmStatic
    fun searchMaze(maze: List<List<Color>>,
                   s: Coordinate, e: Coordinate): List<Coordinate> {


        val path = mutableListOf<Coordinate>()
        searchMazeHelper(maze, s, e, path, mutableSetOf())
        return path
    }

    @JvmStatic
    fun dfs(maze: List<List<Color>>, s: Coordinate, e: Coordinate): List<Coordinate> {

        val path = Stack<Coordinate>()
        val visited = mutableSetOf<Coordinate>()
        val parents = hashMapOf<Coordinate, Coordinate>()
        path.push(s)
        visited.add(s)
        while (!path.isEmpty()) {
            val curr = path.pop()
            getNeighbors(maze, curr, visited).forEach { neighbor ->
                parents[neighbor] = curr
                path.push(neighbor)
                if (neighbor == e) {
                    return getPath(parents, s, e)
                }
            }

        }
        return emptyList()
    }

    fun getPath(parents: Map<Coordinate, Coordinate>, s: Coordinate, e: Coordinate): List<Coordinate> {
        val path = mutableListOf<Coordinate>()
        var curr = e
        path.add(curr)
        while (curr != s) {
            curr = parents[curr] ?: Coordinate(0, 0)
            path.add(curr)
        }
        return path.reversed()
    }

    @JvmStatic
    fun searchMazeHelper(maze: List<List<Color>>, curr: Coordinate, e: Coordinate,
                         path: MutableList<Coordinate>, visited: MutableSet<Coordinate>): Boolean {

        if (curr.equals(e)) {
            return true
        }
        path.add(curr)
        visited.add(curr)
        getNeighbors(maze, curr, visited).forEach { neighbor ->
            if (searchMazeHelper(maze, neighbor, e, path, visited)) {
                return true
            }
        }

        path.remove(curr)
        return false
    }

    fun getNeighbors(maze: List<List<Color>>, curr: Coordinate, visited: MutableSet<Coordinate>): List<Coordinate> { return emptyList()}

    @JvmStatic
    fun pathElementIsFeasible(maze: List<List<Int>>,
                              prev: Coordinate, cur: Coordinate): Boolean {
        return if (!(0 <= cur.x && cur.x < maze.size && 0 <= cur.y &&
                        cur.y < maze[cur.x].size && maze[cur.x][cur.y] == 0)) {
            false
        } else cur.x == prev.x + 1 && cur.y == prev.y ||
                cur.x == prev.x - 1 && cur.y == prev.y ||
                cur.x == prev.x && cur.y == prev.y + 1 ||
                cur.x == prev.x && cur.y == prev.y - 1
    }

    @EpiTest(testDataFile = "search_maze.tsv")
    @JvmStatic
    @Throws(TestFailure::class)
    fun searchMazeWrapper(maze: List<List<Int>>,
                          s: Coordinate, e: Coordinate): Boolean {
        val colored = ArrayList<List<Color>>()
        for (col in maze) {
            val tmp = ArrayList<Color>()
            for (i in col) {
                tmp.add(if (i == 0) Color.WHITE else Color.BLACK)
            }
            colored.add(tmp)
        }
        val path = searchMaze(colored, s, e)
        if (path.isEmpty()) {
            return s == e
        }

        if (path[0] != s || path[path.size - 1] != e) {
            throw TestFailure("Path doesn't lay between start and end points")
        }

        for (i in 1 until path.size) {
            if (!pathElementIsFeasible(maze, path[i - 1], path[i])) {
                throw TestFailure("Path contains invalid segments")
            }
        }

        val v = HashSet<Int>()
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchMaze.kt",
                                object : Any() {

                                }.javaClass.enclosingClass)
                        .ordinal)
    }
}

