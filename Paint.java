import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/* bu ödevde bizden 3 farklı mod istendi buttonlar ile ciz,dıkdortgen ve tasi bu programda her mod ayri calisir
 * mesela ciz modu calısması icin dıger modların inaktif olmasi lazım.dikdortgen modunda dikdörtgeni sol üsten sağ alta doğru oluşturabiliyorsunuz.
 * Bu bilgilendirmek icindir.Onun haricinde kodum biraz karisik olabilir.
 *
 */

public class Paint extends JFrame {
//variable
    JButton drag, rec, draw;
    JPanel black,blue,green;
    boolean draw_b = true;
    private Color color = Color.black;
    boolean enter = false;
    boolean rec_B = false;
    int offsetX, offsetY;
    boolean drag_b = true;
    boolean change_p = false;
    boolean flag = false;
    boolean show = false;
    boolean dragged = false;
    boolean show2 = false;
    int x_click=0,y_click=0,x_drag=0,y_drag=0,x,y;
    ArrayList<Point> pointlist ;// arraylist for points for drawing
    ArrayList<Rectangle>shapelist;// arraylist for shapes also known as Rectangle
//end

    public Paint() {
        // set main things and creating
        super();
        pointlist = new ArrayList<Point>();
        shapelist = new ArrayList<Rectangle>();
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Paint");
        setLocationRelativeTo(null);
        JPanel uppanel = new JPanel();
        uppanel.setLayout(new FlowLayout(0));
        //button
        draw = new JButton("ciz");
        rec = new JButton("dikdortgen");
        drag = new JButton("tasi");
        draw.setBackground(Color.gray);
        rec.setBackground(Color.gray);
        drag.setBackground(Color.gray);
        draw.addActionListener(new border_draw());
        rec.addActionListener(new border_rec());
        drag.addActionListener(new border_drag());
        draw.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rec.setBorder(BorderFactory.createLineBorder(Color.white));
        drag.setBorder(BorderFactory.createLineBorder(Color.white));
        drag.setPreferredSize(new Dimension(50, 25));
        draw.setPreferredSize(new Dimension(50, 25));
        rec.setPreferredSize(new Dimension(90, 25));
        //end
        // color
        black = new JPanel();
         green = new JPanel();
         blue = new JPanel();
        black.setBackground(Color.BLACK);
        green.setBackground(Color.GREEN);
        blue.setBackground(Color.BLUE);
        black.setPreferredSize(new Dimension(40, 30));
        green.setPreferredSize(new Dimension(45, 35));
        blue.setPreferredSize(new Dimension(50, 40));
        black.addMouseListener(new color_p());
        blue.addMouseListener(new color_p());
        green.addMouseListener(new color_p());
        //end
        // gray
        JPanel gray = new JPanel();
        gray.setBackground(Color.GRAY);
        gray.setPreferredSize(new Dimension(40, 30));
        JPanel gray2 = new JPanel();
        gray2.setBackground(Color.GRAY);
        gray2.setPreferredSize(new Dimension(40, 30));
        JPanel gray3 = new JPanel();
        gray3.setBackground(Color.GRAY);
        //end
        //panels to uppanel
        uppanel.add(draw);
        uppanel.add(rec);
        uppanel.add(drag);
        uppanel.add(gray);
        uppanel.add(gray2);
        uppanel.add(gray3);
        uppanel.add(black);
        uppanel.add(green);
        uppanel.add(blue);
        uppanel.setBackground(Color.GRAY);
        // end
        //add Jframe
        add(uppanel, BorderLayout.NORTH);
        add(new centerpanel(), BorderLayout.CENTER);
        setVisible(true);

    }

    public static void main(String[] args) {//start
        Paint paint = new Paint();
    }


    class border_drag implements ActionListener {//Drag button activation
        int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            count++;
            if (count % 2 == 1) {
                drag.setBorder(BorderFactory.createLineBorder(Color.black));
                drag.setPreferredSize(new Dimension(50, 25));
                change_p=true;

            } else {
                drag.setBorder(BorderFactory.createLineBorder(Color.white));
                drag.setPreferredSize(new Dimension(50, 25));
                change_p=false;

            }
        }
    }

    class border_rec implements ActionListener {//Rec button activation
        int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            count++;
            if (count % 2 == 1) {
                rec.setBorder(BorderFactory.createLineBorder(Color.black));
                rec.setPreferredSize(new Dimension(90, 25));
                rec_B =true;

            } else {
                rec.setBorder(BorderFactory.createLineBorder(Color.white));
                rec.setPreferredSize(new Dimension(90, 25));
                rec_B =false;
            }
        }
    }

    class border_draw implements ActionListener {//Draw button activation
        int count = 1;

        @Override
        public void actionPerformed(ActionEvent e) {
            count++;
            if (count % 2 == 1) {
                draw.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                draw.setPreferredSize(new Dimension(50, 25));
                draw_b = true;
            } else {
                draw.setBorder(BorderFactory.createLineBorder(Color.white));
                draw.setPreferredSize(new Dimension(50, 25));
                draw_b = false;

            }
        }



    }

   class draw implements MouseInputListener {//Draw method

       @Override
       public void mouseClicked(MouseEvent e) {
       }

       @Override
       public void mousePressed(MouseEvent e) {
           if(!rec_B && !change_p) {
               int current_x = e.getX();
               int current_y = e.getY();

               if (current_x > x && current_x < x + 3 && current_y > y && current_y < y + 3) {
                   drag_b = true;
                   offsetX = current_x - x;
                   offsetY = current_y - y;
                   pointlist.add(new Point(offsetX, offsetY, draw_b, color));
                   repaint();
               }
           }

       }

       @Override
       public void mouseReleased(MouseEvent e) {

       }

       @Override
       public void mouseEntered(MouseEvent e) {

       }

       @Override
       public void mouseExited(MouseEvent e) {

       }

       @Override
       public void mouseDragged(MouseEvent e) {
           if(!rec_B && !change_p) {
               if (drag_b) {
                   int current_x = e.getX();
                   int current_y = e.getY();
                   x = current_x - offsetX;
                   y = current_y - offsetY;
                   pointlist.add(new Point(x, y, draw_b, color));
                   repaint();


               }
           }
       }

       @Override
       public void mouseMoved(MouseEvent e) {

       }
   }
 public class centerpanel extends JPanel {// Paint area also known as centerpanel
     boolean first = true;
     public centerpanel()
     {
         setBackground(Color.white);
         setOpaque(true);
         addMouseListener(new draw());
         addMouseMotionListener(new draw() );
         addMouseMotionListener(new Drag_Square());
         addMouseListener(new Drag_Square());
         setLocationRelativeTo(null);
         addMouseListener(new poz());
         addMouseMotionListener(new poz());
     }

     public void paint(Graphics p) {//Main paint method
         if (first) {
             super.paint(p);
             first = false;
         }
         for (Point point : pointlist)
             point.drawPoint(p);

         if(show)
         {

             p.setColor(color);
             //2 şekildede oluyor ben şahsen drawLineyı daha fazla sevdim
           /*  p.drawLine(x_click,y_click,x_click,y_drag+y_click);
             p.drawLine(x_click,y_click,x_drag+x_click,y_click);
             p.drawLine(x_click,y_drag+y_click,x_drag+x_click,y_drag+y_click);
             p.drawLine(x_drag+x_click,y_click,x_drag+x_click,y_drag+y_click);*/

             p.fillRect(x_click,y_click,x_drag,y_drag);
         }
         if(show2){

                 p.setColor(color);
                 p.fillRect(x_click, y_click, x_drag, y_drag);

         }

     }
 }
        class color_p implements MouseListener//choose color from Jpanels which is in the uppanel
        {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                if(enter)
                {
                    if(e.getComponent()==black)
                    {
                    color = Color.black;
                    }
                   else if(e.getComponent()==green)
                    {

                        color = Color.green;
                    }
                   else if(e.getComponent()==blue)
                    {
                        color = Color.BLUE;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            enter=true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
            enter = false;
            }

        }
        class Drag_Square implements MouseInputListener// creating square
        {


            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                if(rec_B && !draw_b && ! change_p) {
                    show=true;
                    x_click = e.getX();
                    y_click = e.getY();
                    x_drag =  1;
                    y_drag = 1 ;
                    repaint();
                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(rec_B && !draw_b && ! change_p) {
                    show = false;
                    Rectangle shape = new Rectangle();
                    shape.setBounds(x_click,y_click,x_drag,y_drag);
                    pointlist.add(new Point(shape, rec_B,change_p, color));
                    shapelist.add(shape);
                    repaint();
                    flag = false;
                }


            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                flag=true;
                if(rec_B && flag && !draw_b) {
                    x_drag =Math.abs( e.getX() - x_click);
                    y_drag =Math.abs(e.getY() - y_click);
                    repaint();
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        }
        class poz implements MouseInputListener// change shape poz
        {
            Rectangle shapex;
            Point point;
            boolean control = false;




            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                control=false;
                dragged = true;

               if (change_p && !rec_B && !draw_b)
               {
                 for(int i = 0;i<shapelist.size();i++)
                 {
                     if(shapelist.get(i).contains(e.getX(),e.getY())) {

                         for (int j = 0; j < pointlist.size(); j++) {
                             if (pointlist.get(j).getShape() == shapelist.get(i)) {
                                 int current_x = e.getX();
                                 int current_y = e.getY();
                                     point = pointlist.get(j);
                                     color = point.color2;
                                     shapex = shapelist.get(i);
                                     x_drag =(int)shapex.getWidth();
                                     y_drag = (int)shapex.getHeight();
                                     shapelist.remove(i);
                                     pointlist.remove(j);
                                     show2 = true;
                                     control=true;

                                     break;
                                 }
                             }
                         }
                     }

                 }


            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(control && dragged && !rec_B && !draw_b && change_p)
                {
                    x_click = e.getX()-(int)shapex.getWidth()/2;
                    y_click = e.getY()-(int)shapex.getHeight()/2;
                    Rectangle shape = new Rectangle();
                    shape.setBounds(x_click,y_click,(int)shapex.getWidth(),(int)shapex.getHeight());
                    pointlist.add(new Point(shape, rec_B,change_p,color));
                    shapelist.add(shape);
                    repaint();
                    setVisible(true);
                    show2=false;
                    dragged=false;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if ( !rec_B && !draw_b && change_p) {
                            x_click=e.getX()-x_drag/2;
                            y_click=e.getY()-y_drag/2;
                            repaint();


                    }

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        }

}




