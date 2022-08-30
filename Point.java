import java.awt.*;

public class Point
{
    private int x,y,width,heigth;
    private boolean draw,dik,change;
    public Color color;
    public Color color2;
    private Rectangle shape;



            public Point(int x,int y,boolean draw,Color color)
            {

             this.color = color;
             this.draw=draw;
             this.x=x;
             this.y=y;
            }

    public Point( Rectangle shape,boolean dik,boolean change,Color color)
    {
        this.change=change;
        this.color2=color;
        this.dik=dik;
        this.shape=shape;
    }


            public void drawPoint(Graphics p)
            {

                if(draw && !dik ) {
                    p.setColor(color);
                    p.fillRect(x, y, 3, 3);
                }
                if((dik || change) && !draw) {
                    p.setColor(color2);
                    p.fillRect((int)shape.getX(), (int)shape.getY(),(int)shape.getWidth() ,(int) shape.getHeight());
                }


            }
            public Rectangle getShape()
            {
                return shape;
            }


}
