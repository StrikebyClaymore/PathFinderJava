import java.awt.*;
import java.util.*;

public class Objects{
    static class Cell{
        java.awt.Point position = new java.awt.Point(0, 0); // Координаты точки.
        int g; // Длина пути от старта.
        int h; // Расстояние до цели.
        int f = this.g + this.h; // Полное расстояние до цели.
        Cell cameFrom; // Точка из которой пришли в эту точку.
    }

    static class Matrix{
        ArrayList<ArrayList<Integer>> body = new ArrayList<>();
        java.awt.Point size = new java.awt.Point(0, 0);

        Matrix(int y, int x){
            size.y = y; size.x = x; 
            CreateBody();
        }

        void CreateBody(){
            for (int i = 0; i < size.y; i++){
                ArrayList<Integer> part = new ArrayList<>();
                for (int j = 0; j < size.x; j++){
                    part.add(Funcs.IntRandRange(1, 9));
                }
                body.add(part);
            }
        }

    }
}