package POS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Printy implements Printable, ActionListener {

	@Override
	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		 // We have only one page, and 'page'
	    // is zero-based
	    if (page > 0) {
	         return NO_SUCH_PAGE;
	    }

	    // User (0,0) is typically outside the
	    // imageable area, so we must translate
	    // by the X and Y values in the PageFormat
	    // to avoid clipping.
	    Graphics2D g2d = (Graphics2D)g;
	    g2d.translate(pf.getImageableX(), pf.getImageableY());

	    // Now we perform our rendering
	    g.drawString("My name is Matt Crapia", 100, 100);
	    
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0)
						g.setColor(Color.BLUE);
					else
						g.setColor(Color.GREEN);
				} else {
					if (j % 2 == 0)
						g.setColor(Color.GREEN);
					else
						g.setColor(Color.BLUE);
				}
				g.fillRect(i * 50, j * 50, 50, 50);
			}
		}

	    // tell the caller that this page is part
	    // of the printed document
	    return PAGE_EXISTS;
	}   
	public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                 job.print();
            } catch (PrinterException ex) {
             /* The job did not successfully complete */
            }
        }
   }

   public static void main(String args[]) {

       UIManager.put("swing.boldMetal", Boolean.FALSE);
       JFrame f = new JFrame("Hello World Printer");
       f.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {System.exit(0);}
       });
       JButton printButton = new JButton("Matt Is Dumb");
       printButton.addActionListener(new Printy());
       f.add("Center", printButton);
       f.pack();
       f.setVisible(true);
   }

}
