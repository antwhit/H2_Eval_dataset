import vtk.util.VtkColors;
import vtk.util.VtkPanelContainer;
import vtk.util.VtkPanelUtil;
import vtk.vtkActor;
import vtk.vtkCylinderSource;
import vtk.vtkPanel;
import vtk.vtkPolyDataMapper;
import javax.swing.*;
import java.awt.*;

/**
 * This simple example shows how to do basic rendering and pipeline
 * creation.
 */
public class Cylinder extends JComponent implements VtkPanelContainer {

    private vtkPanel renWin;

    public Cylinder() {
        renWin = new vtkPanel();
        vtkCylinderSource cylinder = new vtkCylinderSource();
        cylinder.SetResolution(8);
        vtkPolyDataMapper cylinderMapper = new vtkPolyDataMapper();
        cylinderMapper.SetInputConnection(cylinder.GetOutputPort());
        vtkActor cylinderActor = new vtkActor();
        cylinderActor.SetMapper(cylinderMapper);
        cylinderActor.GetProperty().SetColor(VtkColors.TOMATO);
        cylinderActor.RotateX(30.0);
        cylinderActor.RotateY(-45.0);
        renWin.GetRenderer().AddActor(cylinderActor);
        renWin.GetRenderer().SetBackground(0.1, 0.2, 0.4);
        renWin.GetRenderer().ResetCamera();
        VtkPanelUtil.setSize(renWin, 200, 200);
        renWin.GetRenderer().GetActiveCamera().Zoom(1.5);
        setLayout(new BorderLayout());
        add(renWin, BorderLayout.CENTER);
    }

    public vtkPanel getRenWin() {
        return renWin;
    }

    public static void main(String s[]) {
        Cylinder panel = new Cylinder();
        JFrame frame = new JFrame("Cylinder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add("Center", panel);
        frame.pack();
        frame.setVisible(true);
    }
}
