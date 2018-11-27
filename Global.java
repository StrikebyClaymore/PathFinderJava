import java.awt.*;
import java.util.*;

import Objects.Cell;

public class Global{  
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main (String args[]){
        Objects.Matrix matrix = new Objects.Matrix(4, 4);
        java.awt.Point start = new java.awt.Point(0, 0);
        java.awt.Point end = new java.awt.Point(3, 3);

        ArrayList<java.awt.Point> path = FindPath(matrix, start, end);

        PrintPath(matrix, path);
        System.exit(0);
    }

    static void PrintPath(Objects.Matrix matrix, ArrayList<java.awt.Point> path){
        for(int i = 0; i < matrix.body.size(); i++){
            for(int j = 0; j < matrix.body.get(i).size(); j++){
                boolean flag = false;
                for (java.awt.Point point : path) {
                    if (point.y == i && point.x == j){
                        flag = true;
                        break;
                    }
                }
                if (flag){
                    System.out.print(ANSI_RED + matrix.body.get(i).get(j) + " " + ANSI_RESET);
                }
                else{
                    System.out.print(matrix.body.get(i).get(j) + " ");
                }
            }
            System.out.println();
        }
    }

    static ArrayList<java.awt.Point> FindPath(Objects.Matrix field, java.awt.Point start, java.awt.Point end){
        ArrayList<Objects.Cell> openSet = new ArrayList<>();
        ArrayList<Objects.Cell> closetSet = new ArrayList<>();

        Objects.Cell start_cell = new Objects.Cell();
        start_cell.position = start;
        start_cell.g = 0;
        start_cell.h = Funcs.CalsDist(start, end);
        start_cell.cameFrom = null; 

        openSet.add(start_cell);

        while (openSet.size() > 0){
            Objects.Cell currentCell = Funcs.CellOrderbyF(end, openSet);
            
            if(currentCell.position.x == end.x && currentCell.position.y == end.y){ return GetPathFromCell(currentCell); }
            openSet.remove(currentCell);
            closetSet.add(currentCell);

            for (Objects.Cell neighbourCell : GetNeighbours(field, end, currentCell)) {
                int count = 0;
                for(Objects.Cell c : closetSet){
                    if(c.position == neighbourCell.position){ count += 1; }
                }
                if(count > 0){ continue; }
                Objects.Cell openCell = null;
                for (Objects.Cell c : openSet) {
                    if(c.position == neighbourCell.position){
                        openCell = c;
                        break;
                    }
                }
                if (openCell == null){ openSet.add(neighbourCell); }
                else if (openCell.g > neighbourCell.g){
                    openCell.cameFrom = currentCell;
                    openCell.g = neighbourCell.g;
                }
            }
        }
        return null;
    }

    static ArrayList<Objects.Cell> GetNeighbours(Objects.Matrix field, java.awt.Point end, Objects.Cell cell){
        ArrayList<Objects.Cell> result = new ArrayList<>();
        
        java.awt.Point points[] = new java.awt.Point[2];
        points[0] = new java.awt.Point(cell.position.x + 1, cell.position.y);
        points[1] = new java.awt.Point(cell.position.x, cell.position.y + 1);

        for (java.awt.Point point : points) {

            if (point.x < 0 || point.x >= field.body.get(0).size()) continue;
            if (point.y < 0 || point.y >= field.body.size()) continue;

            Objects.Cell neighbourCell = new Objects.Cell();
            neighbourCell.position = point;
            neighbourCell.cameFrom = cell;
            neighbourCell.g = cell.g + Funcs.GetCost(field, cell);
            neighbourCell.h = Funcs.CalsDist(point, end);
            result.add(neighbourCell);
        }
        return result;
    }

    static ArrayList<java.awt.Point> GetPathFromCell(Objects.Cell cell){
        ArrayList<java.awt.Point> result = new ArrayList<>();
        Objects.Cell currentCell = cell;
        while (currentCell != null){
            result.add(currentCell.position);
            currentCell = currentCell.cameFrom;
        }
        Collections.reverse(result);
        return result;
    }
    
}
