package Maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) { // Finds maze path
    	if(x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) //checks for negative values and values above number of rows and cols
    	{
    		return false;
    	}
    	else if(!maze.getColor(x, y).equals(NON_BACKGROUND)) //If path is not highlighted red then return false
    	{
    		return false;
    	}
    	else if(x == maze.getNCols()-1 && y == maze.getNRows()-1) //If path reaches end return true
        {
        	maze.recolor(x, y, PATH);
        	return true;
        }
    	else
    	{
    		maze.recolor(x, y, PATH);
    		if(findMazePath(x-1,y) || findMazePath(x+1,y) || findMazePath(x,y-1) || findMazePath(x,y+1))
    		{
    			return true;
    		}
    		else
    		{
    			maze.recolor(x, y, TEMPORARY);
    			return false;
    		}
    	}
    }

    public ArrayList < ArrayList < PairInt >> findAllMazePaths ( int x, int y) {
    	 ArrayList < ArrayList < PairInt >> result = new ArrayList < >();
    	 Stack < PairInt > trace = new Stack < >();
    	 findMazePathStackBased (0 ,0 , result , trace );
    	 return result ;
    }
    
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) //Push visited cell in stack. Push contents of stack in list and pop if path found
    {
    	if(x < 0 || y < 0 || x > maze.getNCols()-1 || y > maze.getNRows()-1)
    	{
    		return;
    	}
    	else if(!maze.getColor(x, y).equals(NON_BACKGROUND))
    	{
    		return;
    	}
    	else if(x == maze.getNCols()-1 && y == maze.getNRows()-1)
    	{
    		trace.push(new PairInt(x,y));
    		ArrayList<PairInt> cells  = new ArrayList<PairInt>();
    		cells.addAll(trace);
    		result.add(cells);
    		trace.pop(); 
    		maze.recolor(x, y, NON_BACKGROUND);
            return;
    	}
    	else
    	{
    		trace.push(new PairInt(x,y));
    		maze.recolor(x, y, PATH);
    		findMazePathStackBased(x + 1, y, result, trace);
    		findMazePathStackBased(x - 1, y, result, trace);
    		findMazePathStackBased(x, y + 1, result, trace);
    		findMazePathStackBased(x, y - 1, result, trace);
    		maze.recolor(x, y, NON_BACKGROUND);
    		trace.pop();
    		return;
    	}
    }
    
    public ArrayList<PairInt> findMazePathMin(int x, int y) //Finds the index of the shortest path and prints the shortest path
    {
    	ArrayList<ArrayList<PairInt>> listAllPath = new ArrayList<ArrayList<PairInt>>();
    	ArrayList<PairInt> emptylist = new ArrayList<PairInt>();
    	if(findAllMazePaths(x,y).size() == 0)
    		return emptylist;
    	listAllPath = findAllMazePaths(x,y);
    	ArrayList<Integer> listObj = new ArrayList<Integer>();
    	for(int i=0;i<listAllPath.size();i++)
    	{
    		listObj.add(listAllPath.get(i).size());
    	}
    	int minSize = Collections.min(listObj);
    	int idx = 0;
    	for(int i=0;i<listAllPath.size();i++)
    	{
    		if(listAllPath.get(i).size() == minSize)
    		{
    			idx = i;
    		}
    	}
		return listAllPath.get(idx) ;	
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
