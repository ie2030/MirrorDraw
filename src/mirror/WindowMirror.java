package mirror;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WindowMirror extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;
    private JPanel modebar;
        private JLabel note;
    private CanvasDraw canvas;  
    private JPanel toolbar;
    private JButton horizontalButton;
    private JButton verticalButton;
    private JButton diagonalButton;
    private JCheckBox crosshairs;
    private JPanel colorPanel;
        private JButton redButton;
        private JButton greenButton;
        private JButton blueButton;
        private JButton blackButton;
    private JButton clearButton;
    
    public WindowMirror() throws HeadlessException {
        super("MirrorDraw");	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());
        initGUI();       
        pack();
        setLocationRelativeTo(null);    
    }

    private void initGUI() {   
        ActionMode actionMode = new ActionMode();
        ActionColor actionColor = new ActionColor();
        toolbar = new JPanel(new GridLayout(6, 1));                     // toolbar
            horizontalButton = new JButton("Horizontal Mirroring");
                horizontalButton.addActionListener(actionMode);
            verticalButton = new JButton("Vertical Mirroring");
                verticalButton.addActionListener(actionMode);
            diagonalButton = new JButton("Diagonal Mirroring");
                diagonalButton.addActionListener(actionMode);
            crosshairs = new JCheckBox("Crosshairs", true);
                crosshairs.addActionListener(actionColor);
            colorPanel = new JPanel(new GridLayout(2, 2, 3, 3));
                redButton = new JButton();
                    redButton.setBackground(Color.red);
                    redButton.addActionListener(actionColor);
                greenButton = new JButton();
                    greenButton.setBackground(Color.green);
                    greenButton.addActionListener(actionColor);
                blueButton = new JButton();
                    blueButton.setBackground(Color.blue);
                    blueButton.addActionListener(actionColor);
                blackButton = new JButton();
                    blackButton.setBackground(Color.black);
                    blackButton.addActionListener(actionColor);
                colorPanel.add(redButton);
                colorPanel.add(greenButton);
                colorPanel.add(blueButton);
                colorPanel.add(blackButton);
                clearButton = new JButton("Clear");  
                    clearButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            canvas.clear();
                        }
                    });
            toolbar.add(horizontalButton);
            toolbar.add(verticalButton);
            toolbar.add(diagonalButton);
            toolbar.add(crosshairs);
            toolbar.add(colorPanel);
            toolbar.add(clearButton);             
        modebar = new JPanel();                                          // modebar 
            modebar.setLayout(new FlowLayout(FlowLayout.LEFT));
            note = new JLabel("Mode: "+horizontalButton.getText());
            modebar.add(note);
        canvas = new CanvasDraw();                                       // canvas
            canvas.setBackground(Color.YELLOW);
            canvas.setPreferredSize(new Dimension(500, 400));            // set the size of the canvas to draw (default blank panel hasn't size)    
        mainPanel = new JPanel(new BorderLayout());                      // The main panel shows all other
            mainPanel.add(canvas, BorderLayout.CENTER);                  // add canvas to the center
            mainPanel.add(toolbar, BorderLayout.EAST);                   // add a toolbar to the west 
            mainPanel.add(modebar, BorderLayout.SOUTH);                  // add modebar south      
        getContentPane().add(mainPanel);
    }
    
    class ActionMode implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == horizontalButton) canvas.setMode(Mirroring.HorizontalMirroring);            
            if (e.getSource() == verticalButton) canvas.setMode(Mirroring.VerticalMirroring);
            if (e.getSource() == diagonalButton) canvas.setMode(Mirroring.DiagonalMirroring);
            note.setText("Mode: " + ((JButton)e.getSource()).getText());          
        }      
    }
    
    class ActionColor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==redButton) canvas.setPaint(Color.red);
            if (e.getSource()==greenButton) canvas.setPaint(Color.green);
            if (e.getSource()==blueButton) canvas.setPaint(Color.blue);
            if (e.getSource()==blackButton) canvas.setPaint(Color.black);
            if (e.getSource()==crosshairs) canvas.setCrosshairs(crosshairs.isSelected());
        } 
    }
    
    public static void main(String[] args) {
        new WindowMirror().setVisible(true);
    }
       
}
