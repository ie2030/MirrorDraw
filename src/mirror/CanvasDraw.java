package mirror;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class CanvasDraw extends JPanel implements MouseListener, MouseMotionListener{

 
	private static final long serialVersionUID = 1L;
	private int x;
    private int y;
    
    private Color color = Color.black;                          // brush color 
    private Mirroring mode = Mirroring.HorizontalMirroring;     // model mirrors 
    private BufferedImage buffer;                               // a buffer where we will draw
    private boolean crosshairs = true;                          // flag sight
       
    public CanvasDraw() {
        super();
        addMouseListener(this);                                 // Add a listener to a button click
        addMouseMotionListener(this);                           // Add a listener for mouse movement
    }
    
    public void setMode(Mirroring mode) {                        // regime change mirrors
        this.mode = mode;
    }
    
    public void setPaint(Color color) {                          // color change
        this.color = color;
    }
    
    public void setCrosshairs(boolean crosshairs){               // set / clear sight
        this.crosshairs = crosshairs;                            
        repaint();
    }
    
    public void clear() {                                        // cleaning
        buffer = null;                                           // zero out buffer
        repaint();                                               // redraws
    }

    @Override
    protected void paintComponent(Graphics g) {                  // redefinition
        super.paintComponent(g);               
        if (buffer == null) {                                    // if the buffer has not been created
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);   // create it
             Graphics2D g2d = (Graphics2D)buffer.getGraphics();                               
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        } else {                                                 // if the buffer was created  
            if (buffer.getWidth()<getWidth() || buffer.getHeight()<getHeight()) {   // if was resize (resizable)
                BufferedImage newBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB); // create a new buffer
                Graphics2D g2d = (Graphics2D)newBuffer.getGraphics();
                g2d.drawImage(buffer, 0, 0, this);          // drawn on its old image
                buffer = newBuffer;                         // replacement buffer 
            }
        } 
        g.drawImage(buffer, 0, 0, this);                     //  and drawing buffer panel
        if (crosshairs) {                                    // draw or no sight  
            g.setColor(Color.lightGray);
            g.drawLine(0, getSize().height/2, getSize().width, getSize().height/2);
            g.drawLine(getSize().width/2, 0, getSize().width/2, getSize().height);
        }
    }
       
    @Override
    public void mouseClicked(MouseEvent e) {}                 // click

    @Override
    public void mousePressed(MouseEvent e) {                  // mouse button is pressed
         x = e.getX();                                      // remember the position of clamping mouse
         y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}               // mouse button is depressed

    @Override
    public void mouseEntered(MouseEvent e) {}                // the mouse pointer over a component appeared

    @Override
    public void mouseExited(MouseEvent e) {}                 // Mouse out of the component

    @Override
    public void mouseDragged(MouseEvent e) {                  // mouse button is pressed and moved
        Graphics2D g2d = (Graphics2D)buffer.getGraphics();  
        g2d.setPaint(color);                                  // sets the color of paint
        g2d.setStroke(new BasicStroke(5.0f));                 // thickness of the line 
        g2d.drawLine(x, y, e.getX(), e.getY());               // draw the line
        if (mode == Mirroring.HorizontalMirroring) {          //  if HorizontalMirroring, rendering a mirror image
            g2d.drawLine(x, getSize().height-y, e.getX(), getSize().height-e.getY());
        } 
        if (mode == Mirroring.VerticalMirroring) {             //  if VerticalMirroring, rendering a mirror image 
            g2d.drawLine(getSize().width-x, y, getSize().width-e.getX(), e.getY());
        }       
        if (mode == Mirroring.DiagonalMirroring) {        // if DiagonalMirriring, rendering a mirror image
            g2d.drawLine(getSize().width-x, getSize().height-y, getSize().width-e.getX(), getSize().height-e.getY());
        }        
        x = e.getX();      // and remember the coordinates where the mouse was moved                  
        y = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}                 // mouse moves
}
