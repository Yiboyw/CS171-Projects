/*
THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS. __Yibo Wang__
*/

// Homework: revise this file, WRITE YOUR NAME UPSTAIRS, see TODO items below.

// Given a maze of size N, the method findPath(maze) finds an open
// path from (0,0) to (N-1,N-1), if such a path exists.
//
// See main() comments for its command line usage.

// TODO: the current solution is recursive (dfs() calls itself).  You
// need to make it non-recursive.  One way is to use an explicit stack
// instead of the runtime stack.  You do not need to find exactly the
// same open paths as found by the given code.

// TODO(EC): modify findPath so it finds a *shortest* open path from
// (0,0) to (N-1,N-1), when one exists.  You can read about a method
// for this, "breadth first search", in Section 4.1 of your book.

// TODO(EC): define method findWallPath (it currently returns null).
// When findPath fails to find a path, there should be a "blocking"
// path of walls separating S and T.  This path can start at any wall
// on the top row or right column of the maze (i==0 or j==N-1), and
// end at any wall at the bottom row or left column of the maze
// (i==N-1 or j==0).  Two walls can be adjacent by a cardinal step OR
// a diagonal step (so, each wall has 8 potential neighbors).  Again,
// recursion is not allowed here.

// TODO(EC): Finding a wall path is good, finding a shortest wall path
// is even better.  Note that if S (or T) is a wall, it is a wall path
// of size one, the smallest possible.

// TODO: Write your name in the header!

// For grading, we ignore the main() method, so do what you like with
// that.  We only test your findPath and findWallPath methods.

public class PathFinder
{
    // Any data fields here should be private and static.  They exist
    // only as a convenient way to share search context between your
    // static methods here.   It should be possible to call your
    // findPath() method more than once, on different mazes, and
    // to get valid results for each maze.

    // The maze we are currently searching, and its size.
    private static Maze m;      // current maze
    private static int N;       
    // its size (N by N)

    // The parent array:
    private static Position[][] parent;
    // In the path-finding routines: for each position p, as soon as
    // we find a route to p, we set parent[p.i][p.j] to be a position
    // one step closer to the start (i.e., the parent discovered p).

    // Get parent of p (assumes p in range)
    static Position getParent(Position p) { return parent[p.i][p.j]; }

    // Set parent of p, if not yet set.  Value indicates success.
    static boolean setParent(Position p, Position par) {
        if (getParent(p) != null)
            return false;       // p already has a parent
        parent[p.i][p.j] = par; 
        return true;
    }

    public static Deque<Position> findPath(Maze maze)
    {
        m = maze;                           // save the maze
        N = m.size();                       // save size (maze is N by N)
        parent = new Position[N][N];        // initially all null
        Position S = new Position(0,0);     // start of open path
        Position T = new Position(N-1,N-1); // end of open path

        // If either of these is a wall, there is no open path.
        if (!m.isOpen(S)) return null;
        if (!m.isOpen(T)) return null;

        // GOAL: for each reachable open position p, parent[p.i][p.j]
        // should be an open position one step closer to S.  That is,
        // it is the position that first discovered a route to p.
        // These parent links will form a tree, rooted at S.

        // Compute parent for each position reachable from S.
        // Since S is the root, we will let S be its own parent.

        // Compute parent links, by recursive depth-first-search!
        dfs(S, S);

        // If T has no parent, it is not reachable, so no path.
        if (getParent(T)==null)
            return null;
        // Otherwise, we can reconstruct a path from S to T.
        Deque<Position> path = new LinkedDeque<Position>();
        for (Position u=T; !u.equals(S); u=getParent(u))
            path.addFirst(u);
        path.addFirst(S);
        return path;
    }

    // depth-first-search: set parent for each newly reachable p.
    private static void dfs(Position p, Position from)
    {
         Deque<Position> position  = new LinkedDeque<Position>();
         //new doubly linked list called position
         
         if (!m.isOpen(p) || getParent(p) != null)
              return;
         //Makes sure the starting position meets the conditions set in isOpen, and getParent
         
         int dir = 0; 
         //stores the direction. Position.java gives more info on the Position. e.g. direction(0) is one row up. 
         
         position.addFirst(p);
        //adds the starting position to the position doubly linked list
         
         //This while loop checks if position is empty and breaks if it is. 
         while(!position.isEmpty()) {

            p = (Position) position.removeFirst();
             //This for inner loop goes through each north,south,east, and west direction of the current position
              for (dir =0;dir<4 ;++dir ) {
                  
                  
                  //check if the neighbor's parent is 0, and has no parent means that it's a valid position 
                   if(m.isOpen(p.neighbor(dir)) && getParent(p.neighbor(dir)) == null)
                   {
                        position.addLast(p.neighbor(dir));
                        //add the current position's neighbor into the linked list 
                    
                                         
                        setParent(p.neighbor(dir), p);
                        //sets the current position's parent

                    }
              
            }
              dir = 0; //resets direction and rerun the inner loop
        }
    }

    // Return a wall path separating S and T, or null.
    public static Deque<Position> findWallPath(Maze maze)
    {       
        
        return null;            
    }

    // Command-line usage:
    //
    //    java PathFinder ARGS...
    //
    // Constructs maze (using same rules as Maze.main()), prints it,
    // finds the paths (open path and/or wall path), and reprints the
    // maze with the path highlighted.
    public static void main(String[] args)
    {
        Maze m = Maze.mazeFromArgs(args);
        System.out.println(m);
        Deque<Position> oPath = findPath(m);
        if (oPath != null)
            System.out.println("findPath() found an open path of size "
                               + oPath.size());
        Deque<Position> wPath = findWallPath(m);
        if (wPath != null)
            System.out.println("findWallPath() found a wall path of size "
                               + wPath.size());
        if (oPath==null && wPath==null)  {
            System.out.println("WARNING: neither path was found");
            // This may be OK, if you are not doing findWallPath (EC).
            // No point in reprinting the map.
            return;
        }
        if (oPath != null && wPath != null) // crossing?
            System.out.println("WARNING: cannot have both paths!");

        // Copy map of maze, and mark oPath with 'o', wPath with 'w'.
        char[][] map = m.copyArray();
        if (oPath != null)
            for (Position p: oPath)
                map[p.i][p.j] = 'o';
        if (wPath != null)
            for (Position p: wPath)
                map[p.i][p.j] = 'w';
        // Now print the marked map.
        System.out.println(Maze.toString(map));
    }

    // Java "defensive programming": we should not instantiate this
    // class.  To enforce that, we give it a private constructor.
    private PathFinder() {}
}

