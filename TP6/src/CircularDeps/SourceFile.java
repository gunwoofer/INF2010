package CircularDeps;

import java.util.HashSet;

public class SourceFile {
    private String name;
    private HashSet<SourceFile> dependencies = new HashSet<>();
    
    public Color couleur;

    public SourceFile(String name)
    {
        this.name = name;
        this.couleur = Color.White;
    }

    public HashSet<SourceFile> getDependencies()
    {
        return this.dependencies;
    }

    public void addDependency(SourceFile file)
    {
        this.dependencies.add(file);
    }
    
    public boolean hasWhiteNeighboor() {
    	for (SourceFile child : this.getDependencies()) {
    		if (child.couleur.equals(Color.White)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public SourceFile peekWhiteNeighboor() {
    	for (SourceFile child : this.getDependencies()) {
    		if (child.couleur.equals(Color.White)) {
    			return child;
    		}
    	}
    	return null;
    }
    
    public boolean hasGrayNeighbour() {
    	for (SourceFile child : this.getDependencies()) {
    		if (child.couleur.equals(Color.Gray)) {
    			return true;
    		}
    	}
    	return false;
    }
}
