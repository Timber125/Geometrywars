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
public abstract class Collidable extends ObservedRenderable
{
    private HitBox hitbox;

    public Collidable(long ID, int xPos, int yPos, HitBox hb) {
        super(ID, xPos, yPos);
        this.hitbox = hb;
    }
    
    
    public HitBox getHitBox(){
        return hitbox;
    }
    
    public abstract void onCollide(Collidable other);
    
    public abstract Point getCenter();
    
    public boolean collidesWith(Collidable c2){
        if(hitbox instanceof CircularHitBox){
            
            if(c2.getHitBox() instanceof CircularHitBox){
                return circle_circle_collide(this, c2);
            }
            
            else if(c2.getHitBox() instanceof RectangularHitBox){
                return circle_rectangle_collide(this, c2);
            }
            
            else if(c2.getHitBox() instanceof TriangularHitBox){
                return circle_triangle_collide(this, c2);
            }
            
            
        }else if(hitbox instanceof RectangularHitBox){
            
            if(c2.getHitBox() instanceof CircularHitBox){
                return circle_rectangle_collide(c2, this);
            }
            
            else if (c2.getHitBox() instanceof RectangularHitBox){
                return rectangle_rectangle_collide(this, c2);
            }
            
            else if (c2.getHitBox() instanceof TriangularHitBox){
                return rectangle_triangle_collide(this, c2);
            }
            
        }else if(hitbox instanceof TriangularHitBox){
            
            if(c2.getHitBox() instanceof CircularHitBox){
                return circle_triangle_collide(c2, this);
            }
            
            else if(c2.getHitBox() instanceof RectangularHitBox){
                return rectangle_triangle_collide(c2, this);
            }
            
            else if(c2.getHitBox() instanceof TriangularHitBox){
                return triangle_triangle_collide(this, c2);
            }
            
        }
        System.err.println("[SEVERE] Collide check failed");
        System.err.flush();
        return false;
    }

    private boolean circle_circle_collide(Collidable c1, Collidable c2) {
        Point c1center = c1.getCenter();
        Point c2center = c2.getCenter();
        
        int dX = Math.abs(c1center.X - c2center.X);
        int dY = Math.abs(c1center.Y - c2center.Y);
        
        int c1radius = ((((CircularHitBox) c1.getHitBox()).getDiameter()-2) / 2);
        int c2radius = ((((CircularHitBox) c2.getHitBox()).getDiameter()-2) / 2);
        
        int combinedradius = c1radius + c2radius;
        
        if((dX < combinedradius) && (dY < combinedradius)) return true;
        return false;
    }

    private boolean circle_rectangle_collide(Collidable c1, Collidable c2) {
       
        // relative Distance of positions
        int dX = Math.abs(c1.getX() - c2.getX());
        int dY = Math.abs(c1.getY() - c2.getY());
        
        RectangularHitBox rect = (RectangularHitBox) c2.getHitBox();
        CircularHitBox circle = (CircularHitBox) c1.getHitBox();
        
        // If X or Y is too far away to have a collide, return false already
        if(dX > (rect.getWidth() / 2 + circle.getDiameter()/2)) return false;
        if(dY > (rect.getWidth() / 2 + circle.getDiameter()/2)) return false;
        
        if(dX <= circle.getDiameter() / 2) return true;
        if(dY <= circle.getDiameter() / 2) return true;
        if(dX <= rect.getWidth()/2) return true;
        if(dY <= rect.getHeight()/2) return true;
        
        double cornerdistance = Math.pow((dX - rect.getWidth()/2), 2) + Math.pow((dY - rect.getHeight() /2), 2);
        return cornerdistance <= Math.pow((circle.getDiameter()/2),2);
                
        
    }
    // ONLY TRIANGLES WITH NORTH_DIRECTION
    private boolean circle_triangle_collide(Collidable c1, Collidable c2) {
        CircularHitBox circle = (CircularHitBox) c1.getHitBox();
        TriangularHitBox triangle = (TriangularHitBox) c2.getHitBox();
        
        int cX = c1.getX();
        int cY = c1.getY();
        int tX = c2.getX();
        int tY = c2.getY();
        // Damn, teveel maths, tis voor de volgende build.
        return false;
    }

    private boolean rectangle_rectangle_collide(Collidable c1, Collidable c2) {
        return false;
    }

    private boolean rectangle_triangle_collide(Collidable c1, Collidable c2) {
        return false;
    }

    private boolean triangle_triangle_collide(Collidable c1, Collidable c2) {
        return false;
    }
    
}
