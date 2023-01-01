import vtk.util.ImageViewerPanel;
import vtk.util.VtkPanelContainer;
import vtk.util.VtkPanelUtil;
import vtk.vtkImageCanvasSource2D;
import vtk.vtkPanel;
import javax.swing.*;
import java.awt.*;

/**
 * A very basic application that simple displays a cone using vtkPanel.
 * Close the window to exit aplication.
 */
public class DrawOnImageCanvas extends JComponent implements VtkPanelContainer {

    private ImageViewerPanel viewer;

    public DrawOnImageCanvas() {
        viewer = new ImageViewerPanel();
        vtkImageCanvasSource2D imCan = new vtkImageCanvasSource2D();
        imCan.SetScalarTypeToUnsignedChar();
        imCan.SetExtent(0, 511, 0, 511, 0, 0);
        imCan.SetDrawColor(86);
        imCan.FillBox(0, 511, 0, 511);
        imCan.SetDrawColor(0);
        imCan.FillTube(500, 20, 30, 400, 5);
        imCan.SetDrawColor(255);
        imCan.DrawSegment(10, 20, 500, 510);
        imCan.SetDrawColor(0);
        imCan.DrawCircle(400, 350, 80.0);
        imCan.SetDrawColor(255);
        imCan.FillPixel(450, 350);
        imCan.SetDrawColor(170);
        imCan.FillTriangle(100, 100, 300, 150, 150, 300);
        viewer.setImageViewerInput(imCan.GetOutput());
        viewer.setImageViewerColorWindow(256);
        viewer.setImageViewerColorLevel(127.5);
        VtkPanelUtil.setSize(getRenWin(), 512, 512);
        setLayout(new BorderLayout());
        add(viewer, BorderLayout.CENTER);
    }

    public vtkPanel getRenWin() {
        return viewer.getRenWin();
    }

    public static void main(String s[]) {
        DrawOnImageCanvas panel = new DrawOnImageCanvas();
        JFrame frame = new JFrame("DrawOnImageCanvas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add("Center", panel);
        frame.pack();
        frame.setVisible(true);
    }
}
