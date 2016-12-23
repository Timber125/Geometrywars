/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrywars.Game;

/**
 *
 * @author admin
 */
public class HighScoreEntry implements Comparable{
    public String id;
    public String name;
    public int score;
    public HighScoreEntry(String id, String name, String score){
        this.id = id;
        this.name = name;
        this.score = Integer.parseInt(score);
    }

    @Override
    public int compareTo(Object o) {
        int oscore = ((HighScoreEntry)o).score;
        return (oscore - score);
    }
}
