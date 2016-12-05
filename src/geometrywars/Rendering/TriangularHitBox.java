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
public class TriangularHitBox extends HitBox{

    @Override
    public int getXCenter() {
        switch(direction){
            case POINT_NORTH : return (Integer) this.baseWidth/2;
            case POINT_SOUTH : return (Integer) this.baseWidth/2;
            case POINT_EAST : return (Integer) this.height / 2;
            case POINT_WEST : return (Integer) this.height / 2;
            default : return 0;
        }
    }

    @Override
    public int getYCenter() {
        switch(direction){
            case POINT_NORTH : return (Integer) this.height / 2;
            case POINT_SOUTH : return (Integer) this.height / 2;
            case POINT_EAST : return (Integer) this.baseWidth/2;
            case POINT_WEST : return (Integer) this.baseWidth/2;
            default : return 0;
        }
    }
    
    /* In a later stage, this enum can be replaced with a rotation angle to implement rotating objects*/
    public enum TriangleDirection {
        POINT_NORTH(0), 
        POINT_EAST(1), 
        POINT_SOUTH(2), 
        POINT_WEST(3)
        ;
        private int numVal;

    TriangleDirection(int numVal) {
        this.numVal = numVal;
    }

    public int value() {
        return numVal;
    }
    };
    
    private int baseWidth;
    private int height;
    private TriangleDirection direction;
    public TriangularHitBox(int baseWidth, int height, TriangleDirection dir){
        this.baseWidth = baseWidth;
        this.height = height;
        this.direction = dir;
    }
    public int getWidth(){
        return baseWidth;
    }
    public int getHeight(){
        return height;
    }
    public TriangleDirection getDirection(){
        return direction;
    }
    public int getDirectionAsInt(){
        return direction.value();
    }
    
}
