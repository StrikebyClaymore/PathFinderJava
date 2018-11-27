import java.awt.*;
import java.util.*;

public class Funcs {
    static int IntRandRange(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
    static void Swap(ArrayList<Objects.Cell> mas, int i, int j){
        Objects.Cell tmp = mas.get(i);
        mas.set(i, mas.get(j));
        mas.set(j, tmp);
    }

    static void CellListReverse(ArrayList<Objects.Cell> mas){
        for (int i = 0, j = mas.size()-1; i < mas.size()-1; i++, j--){
             if ( i == j){ break; }
             Swap(mas, i, j);
        }
    }

    static Objects.Cell CellOrderbyF(java.awt.Point end, ArrayList<Objects.Cell> list){
        Objects.Cell cell = list.get(0);
        for (Objects.Cell c : list){
            c.h = CalsDist(c.position, end);
            if(c.g + c.h < cell.g + cell.h){
                cell = c;
            }
        }
        return cell;
    }
    static int GetCost(Objects.Matrix field, Objects.Cell cell){
        return field.body.get(cell.position.y).get(cell.position.x);
    }

    static int CalsDist(java.awt.Point A, java.awt.Point B){
        return Math.abs(A.x - B.x) + Math.abs(A.y - B.y);
    }
}