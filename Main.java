
////////////////////////////////////////////
//  The Graphics Project
//  ICS4U
//
//  The purpose of this project to show your 
//  understanding of creating an effective GUI 
//
//  DESCRIPTION OF PROGRAM
/*

*/
////////////////////////////////////////////
import javax.swing.*;
import java.awt.*;
import java.util.EventObject;

class Main {

  public static Color backgroundColor(int bColor, JCanvas c){
    switch (bColor) {
      case 1:
        return Color.red;
      case 2:
        return Color.green;   
      case 3:
        return Color.yellow;
      case 4:
        return Color.black;
      case 5:
        return Color.white;
      case 6:
        return Color.lightGray;
      default:
        return Color.black;
    }
  }
  
  public static void drawSize(int b , JCanvas c2, JSlider s) {
    c2.setBackground(Color.gray);    
    int fSize = s.getValue();
    color(b,c2);
    if(fSize > 11){
      c2.fillOval(fSize*4,1/2,fSize,fSize);
    }else{
      c2.fillOval(fSize*4-4,1/2,fSize,fSize);
        }
  }

  public static void color(int nColor, JCanvas c) {
    switch (nColor) {
      case 1:
        c.setColor(Color.red);
        break;
      case 2:
        c.setColor(Color.green);
        break;
      case 3:
        c.setColor(Color.yellow);
        break;
      case 4:
        c.setColor(Color.black);
        break;
      case 5:
        c.setColor(Color.white);
        break;
      case 6:
        c.setColor(Color.lightGray);
        break;
      default:
        c.setColor(Color.black);
        break;
    }
  }

  public static JButton Button(Icon img) {
    JButton newB = new JButton(img);
    newB.setBackground(Color.gray);
    JBox.setSize(newB, 20, 20);
    return newB;
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Drawing Software");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    JEventQueue events = new JEventQueue();
    JCanvas canvas = new JCanvas();

    JCanvas canvas2 = new JCanvas();
    JBox.setSize(canvas2, 105, 20);
    

    frame.add(canvas);
    ImageIcon cImg = new ImageIcon("images/clear.png");
    ImageIcon rImg = new ImageIcon("images/red.png");
    ImageIcon gImg = new ImageIcon("images/green.png");
    ImageIcon yImg = new ImageIcon("images/yellow.png");
    ImageIcon bImg = new ImageIcon("images/black.png");
    ImageIcon wImg = new ImageIcon("images/white.png");
    ImageIcon rectImg = new ImageIcon("images/rectangle.png");
    ImageIcon tImg = new ImageIcon("images/triangle.png");
    ImageIcon lImg = new ImageIcon("images/line.png");
    ImageIcon oImg = new ImageIcon("images/oval.png");
    ImageIcon dImg = new ImageIcon("images/pencil.png");
    ImageIcon eImg = new ImageIcon("images/eraser.png");
    ImageIcon fImg = new ImageIcon("images/fill.png");

    
    JButton fill = Button(fImg);
    JButton red = Button(rImg);
    JButton green = Button(gImg);
    JButton yellow = Button(yImg);
    JButton black = Button(bImg);
    JButton white = Button(wImg);
    JButton draw = Button(dImg);
    JButton line = Button(lImg);
    JButton eraser = Button(eImg);
    JButton oval = Button(oImg);
    JButton rectangle = Button(rectImg);
    JButton triangle = Button(tImg);
    JButton clear = Button(cImg);
    


    JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 3, 20, 12);
   JBox.setSize(slider, 100, 20);

    

    JBox layout = JBox.vbox(JBox.hbox(canvas),
        JBox.hbox( slider, JBox.hglue(),red, green, yellow, black, white, JBox.hglue(), JBox.hglue()),
        JBox.hbox( canvas2, JBox.hglue(), fill, draw, line, oval, rectangle, triangle, eraser, clear, JBox.hglue(),JBox.hglue(),JBox.hglue()));

    layout.setBackground(Color.gray);
    canvas2.setBackground(Color.gray);
    canvas.setBackground(Color.lightGray);

    canvas2.fillOval(48, 1/2, 12, 12);
    frame.add(layout);
    frame.setVisible(true);

    events.listenTo(canvas, "c");
    events.listenTo(draw, "d");
    events.listenTo(line, "/");
    events.listenTo(red, "🔴");
    events.listenTo(green, "🟢");
    events.listenTo(yellow, "🟡");
    events.listenTo(black, "⚫");
    events.listenTo(white, "⚪");
    events.listenTo(eraser, "e");
    events.listenTo(oval, "o");
    events.listenTo(rectangle, "rect");
    events.listenTo(triangle, "t");
    events.listenTo(clear, "cl");
    events.listenTo(slider, "s");
    events.listenTo(fill, "f");


    int y = 0;
    int x = 0;
    int y2 = 0;
    int x2 = 0;
    int i = 0;
    int s = 0;
    boolean isPressed = false;
    int fSize = 12;
    String typedKey = "";
    long startTime = 0;
    long endTime = 0;


    while (true) {
      EventObject event = events.peekEvent();

      if (isPressed) {
          x2 = (int) MouseInfo.getPointerInfo().getLocation().getX();
        y2 = (int) MouseInfo.getPointerInfo().getLocation().getY();

          fSize = slider.getValue();
          if(fSize > 11){
            canvas.fillOval( (x2 - fSize)+3, y2 - 33, fSize, fSize);
          }else{
            canvas.fillOval( (x2 - fSize)+3, y2 - 27, fSize, fSize);
          }
           


          if (events.isMouseReleased(event)) {
          isPressed = false;
        }
        

        

      } else {
        event = events.waitEvent();

        String whatHappened = events.getName(event);

        if(events.isKeyTyped(event)){
          x = events.getMouseX(event);
          y = events.getMouseY(event);
          typedKey = events.getKeyText(event);
          canvas.drawString(typedKey, x,y);
          System.out.println(typedKey);
        }
        if (whatHappened.equals("s")) {
          if(!slider.getValueIsAdjusting()){
            drawSize(i, canvas2, slider);
          }
        }
        if (whatHappened.equals("f")) {
          if (s == 8) {
            fill.setBackground(Color.gray);
            s = 0;
          } else {
            line.setBackground(Color.gray);
            eraser.setBackground(Color.gray);
            oval.setBackground(Color.gray);
            rectangle.setBackground(Color.gray);
            triangle.setBackground(Color.gray);
            draw.setBackground(Color.gray);
            fill.setBackground(Color.lightGray);
            s = 8;
          }
        }
        if (whatHappened.equals("d")) {
          if (s == 1) {
            draw.setBackground(Color.gray);
            s = 0;
          } else {
            line.setBackground(Color.gray);
            eraser.setBackground(Color.gray);
            oval.setBackground(Color.gray);
            rectangle.setBackground(Color.gray);
            triangle.setBackground(Color.gray);
            draw.setBackground(Color.lightGray);
            fill.setBackground(Color.gray);
            s = 1;
          }
        }
        if (whatHappened.equals("/")) {
          if (s == 2) {
            line.setBackground(Color.gray);
            s = 0;
          } else {
            line.setBackground(Color.lightGray);
            eraser.setBackground(Color.gray);
            oval.setBackground(Color.gray);
            rectangle.setBackground(Color.gray);
            triangle.setBackground(Color.gray);
            draw.setBackground(Color.gray);
            fill.setBackground(Color.gray);
            s = 2;
          }
        }
        if (whatHappened.equals("🔴")) {
          if (i == 1) {
            red.setBackground(Color.gray);
            i = 0;
            color(i, canvas);
          } else {
            
            eraser.setBackground(Color.gray);
            red.setBackground(Color.lightGray);
            green.setBackground(Color.gray);
            yellow.setBackground(Color.gray);
            black.setBackground(Color.gray);
            white.setBackground(Color.gray);

            i = 1;
            drawSize(i, canvas2, slider);
            color(i, canvas);
          }

        }
        if (whatHappened.equals("🟢")) {
          if (i == 2) {
            green.setBackground(Color.gray);
            i = 0;
            color(i, canvas);
          } else {
            eraser.setBackground(Color.gray);
            red.setBackground(Color.gray);
            green.setBackground(Color.lightGray);
            yellow.setBackground(Color.gray);
            black.setBackground(Color.gray);
            white.setBackground(Color.gray);

            i = 2;
            drawSize(i, canvas2, slider);
            color(i, canvas);
          }
        }
        if (whatHappened.equals("🟡")) {
          if (i == 3) {
            yellow.setBackground(Color.gray);
            i = 0;
            color(i, canvas);
          } else {
            eraser.setBackground(Color.gray);
            red.setBackground(Color.gray);
            green.setBackground(Color.gray);
            yellow.setBackground(Color.lightGray);
            black.setBackground(Color.gray);
            white.setBackground(Color.gray);

            i = 3;
            drawSize(i, canvas2, slider);
            color(i, canvas);
          }
        }
        if (whatHappened.equals("⚫")) {
          if (i == 4) {
            black.setBackground(Color.gray);
            i = 0;
            color(i, canvas);
          } else {
            eraser.setBackground(Color.gray);
            red.setBackground(Color.gray);
            green.setBackground(Color.gray);
            yellow.setBackground(Color.gray);
            black.setBackground(Color.lightGray);
            white.setBackground(Color.gray);

            i = 4;
            drawSize(i, canvas2, slider);
            color(i, canvas);
          }
        }
        if (whatHappened.equals("⚪")) {
          if (i == 5) {
            white.setBackground(Color.gray);
            i = 0;
            color(i, canvas);
          } else {
            eraser.setBackground(Color.gray);
            red.setBackground(Color.gray);
            green.setBackground(Color.gray);
            yellow.setBackground(Color.gray);
            black.setBackground(Color.gray);
            white.setBackground(Color.lightGray);
            i = 5;
            drawSize(i, canvas2, slider);
            color(i, canvas);
          }
        }
        if (whatHappened.equals("cl")) {
            canvas.setColor(Color.lightGray);
            canvas.fillRect(0, 0, 400, 400);
            color(i,canvas);
        }
        if (whatHappened.equals("e")) {
          if (s == 6) {
            eraser.setBackground(Color.gray);
            s = 0;
          } else {
            red.setBackground(Color.gray);
            green.setBackground(Color.gray);
            yellow.setBackground(Color.gray);
            black.setBackground(Color.gray);
            white.setBackground(Color.gray);
            line.setBackground(Color.gray);
            eraser.setBackground(Color.lightGray);
            oval.setBackground(Color.gray);
            rectangle.setBackground(Color.gray);
            triangle.setBackground(Color.gray);
            draw.setBackground(Color.gray);
            fill.setBackground(Color.gray);
            // canvas.get
            s = 6;
            i = 6;
            drawSize(i, canvas2, slider);
            color(6,canvas);
          }
        }
        if (whatHappened.equals("o")) {
          if (s == 3) {
            oval.setBackground(Color.gray);
            s = 0;
          } else {
            line.setBackground(Color.gray);
            eraser.setBackground(Color.gray);
            oval.setBackground(Color.lightGray);
            rectangle.setBackground(Color.gray);
            triangle.setBackground(Color.gray);
            draw.setBackground(Color.gray);
            fill.setBackground(Color.gray);
            s = 3;
          }
        }
        if (whatHappened.equals("rect")) {
          if (s == 4) {
            rectangle.setBackground(Color.gray);
            s = 0;
          } else {
            line.setBackground(Color.gray);
            eraser.setBackground(Color.gray);
            oval.setBackground(Color.gray);
            rectangle.setBackground(Color.lightGray);
            triangle.setBackground(Color.gray);
            draw.setBackground(Color.gray);
            fill.setBackground(Color.gray); 
            s = 4;
          }
        }
        if (whatHappened.equals("t")) {
          if (s == 5) {
            triangle.setBackground(Color.gray);
            s = 0;
          } else {
            line.setBackground(Color.gray);
            eraser.setBackground(Color.gray);
            oval.setBackground(Color.gray);
            rectangle.setBackground(Color.gray);
            triangle.setBackground(Color.lightGray);
            draw.setBackground(Color.gray);
            fill.setBackground(Color.gray);
            s = 5;
          }
        }

        if (whatHappened.equals("c")) {
          if (s == 1 || s == 6) {
            if(s==6){
              canvas.setColor(canvas.getBackground());
            }
            if (events.isMousePressed(event)) {
              isPressed = true;
            }

          }
          if(s == 8){
            if(events.isMousePressed(event)){
              Color backColor = backgroundColor(i,canvas);
              canvas.setBackground(backColor);
            }
          }
          if (s == 2) {
            
            if (events.isMousePressed(event)) {
              x = events.getMouseX(event);
              y = events.getMouseY(event);
            }
            if (events.isMouseReleased(event)) {
              x2 = events.getMouseX(event);
              y2 = events.getMouseY(event);
              canvas.drawLine(x, y, x2, y2);
            }
          }
          if (s == 3) {
            if (events.isMousePressed(event)) {
              x = events.getMouseX(event);
              y = events.getMouseY(event);
            }
            if (events.isMouseReleased(event)) {
              x2 = events.getMouseX(event);
              y2 = events.getMouseY(event);

              if (x2 > x && y2 > y) {
                canvas.drawOval(x, y, x2 - x, y2 - y);
              } else if (x > x2 && y > y2) {
                canvas.drawOval(x2, y2, x - x2, y - y2);
              } else if (x2 > x && y2 < y) {
                canvas.drawOval(x, y2, x2 - x, y - y2);
              } else {
                canvas.drawOval(x2, y, x - x2, y2 - y);
              }

            }
          }
          if (s == 4) {
            if (events.isMousePressed(event)) {
              x = events.getMouseX(event);
              y = events.getMouseY(event);
            }
            if (events.isMouseReleased(event)) {
              x2 = events.getMouseX(event);
              y2 = events.getMouseY(event);
              if (x2 > x && y2 > y) {
                canvas.drawRect(x, y, x2 - x, y2 - y);
              } else if (x > x2 && y > y2) {
                canvas.drawRect(x2, y2, x - x2, y - y2);
              } else if (x2 > x && y2 < y) {
                canvas.drawRect(x, y2, x2 - x, y - y2);
              } else {
                canvas.drawRect(x2, y, x - x2, y2 - y);
              }

            }
          }
          if (s == 5) {
            if (events.isMousePressed(event)) {
              x = events.getMouseX(event);
              y = events.getMouseY(event);
            }
            if (events.isMouseReleased(event)) {
              x2 = events.getMouseX(event);
              y2 = events.getMouseY(event);
              canvas.drawLine(x, y, x2, y2);
              canvas.drawLine(x2, y2, x - (x2 - x), y2);
              canvas.drawLine(x - (x2 - x), y2, x, y);
            }
          }
        }
      }
    }

  }
}
