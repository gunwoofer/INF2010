package CircularDeps;
import java.util.HashSet;
import java.util.Stack;

/*
https://www.geeksforgeeks.org/detect-cycle-direct-graph-using-colors/

WHITE : Vertex is not processed yet.  Initially
        all vertices are WHITE.

GRAY : Vertex is being processed (DFS for this 
       vertex has started, but not finished which means
       that all descendants (ind DFS tree) of this vertex
       are not processed yet (or this vertex is in function
       call stack)

BLACK : Vertex and all its descendants are 
        processed.

While doing DFS, if we encounter an edge from current 
vertex to a GRAY vertex, then this edge is back edge 
and hence there is a cycle.
 */



public class CodeBase {
    private SourceFile[] sourceFiles;

    public CodeBase(SourceFile[] sourceFiles)
    {
        this.sourceFiles = sourceFiles;
    }

    public boolean hasCircularDependencies()
    {
        for (SourceFile file : this.sourceFiles) {
            HashSet<SourceFile> dependentFiles = new HashSet<>();
            if (existsCircularDependencies(file, dependentFiles)) {
                return true;
            }
        }
        return false;
    }

    // DFS partant de <<file>> détectant s'il existe
    // des dépendances circulaires dans les fichiers source.
    private boolean existsCircularDependencies(SourceFile file, HashSet<SourceFile> dependentFiles)
    {
    	Stack<SourceFile> parcour = new Stack<SourceFile>();
        file.couleur = Color.Gray;
        parcour.push(file);
        
        while (!parcour.empty()) {
        	SourceFile child = parcour.lastElement();
        	
        	if (child.hasGrayNeighbour()) {
        		return true;
        	}
        	else if (child.hasWhiteNeighboor()) {
        		SourceFile whiteNeighboor = child.peekWhiteNeighboor();
        		whiteNeighboor.couleur = Color.Gray;
        		parcour.push(whiteNeighboor);
        	}
        	else {
        		child.couleur = Color.Black;
        		parcour.pop();
        	}
        }
        
        return false;
    }
}
