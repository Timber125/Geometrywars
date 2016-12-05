/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

/**
 *
 * @author timber
 */
public class Point {
    public int X;
    public int Y;
    public Point(int x, int y){
        this.X = x;
        this.Y = y;
    }
    public int getX(){return X;}
    public int getY(){return Y;}
}
