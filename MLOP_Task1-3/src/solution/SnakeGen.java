/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

/**
 *
 * @author vegeno-pc
 */
class vec2d{
    public int x;
    public int y;
    
    vec2d(int x, int y){
        this.x = x;
        this.y = y;
    }
}


class SnakeGen{
    private vec2d cur = new vec2d(0, 1);
    private int index = 0;
    private int n, m;
    
    SnakeGen(int m, int n){
        this.n = n;
        this.m = m;
    }
    
    public boolean hasNext(){
        if(index>=m*n)
            return false;
        else
            return true;
    }
    
    public vec2d next(){
        int x = cur.x-1;
        int y =cur.y-1;
        int ix = n-cur.x;
        int iy = m-cur.y;
        int min = Math.min(Math.min(x, y), Math.min(ix, iy));
        
        if(min == x && x!= y &&  x!= y-1 && min != ix){
            cur.y--;
        }
        else if(min == iy && min != y){
            cur.x--;
        }
        else if(min == ix){
            cur.y++;
        }
        else if(min == y || x == y-1){
            cur.x++;
        }
        index++;
        
        return cur;
    }
    
    public int getIndex(){
        return index;
    }
    
    public static int[][] getMatrix(int M, int N){
        int[][] array = new int[M+1][N+1];
        SnakeGen snake = new SnakeGen(M, N);
         
        while(snake.hasNext()){
            vec2d p = snake.next();
            array[p.y][p.x] = snake.getIndex();
        }
        return array;
    }
}