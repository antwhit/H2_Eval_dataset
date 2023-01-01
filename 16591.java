import vtk.util.VtkPanelContainer;
import vtk.util.VtkPanelUtil;
import vtk.*;
import javax.swing.*;
import java.awt.*;

/**
 * This example demonstrates how to combine a "geometric" implicit
 * function with noise at different frequencies to produce the
 * appearance of a landscape.
 */
public class PerlinTerrain extends JComponent implements VtkPanelContainer {

    private vtkPanel renWin;

    public PerlinTerrain() {
        renWin = new vtkPanel();
        vtkPlane plane = new vtkPlane();
        vtkPerlinNoise p1 = new vtkPerlinNoise();
        p1.SetFrequency(1, 1, 0);
        vtkPerlinNoise p2 = new vtkPerlinNoise();
        p2.SetFrequency(3, 5, 0);
        p2.SetPhase(0.5, 0.5, 0);
        vtkPerlinNoise p3 = new vtkPerlinNoise();
        p3.SetFrequency(16, 16, 0);
        vtkImplicitSum sum = new vtkImplicitSum();
        sum.SetNormalizeByWeight(1);
        sum.AddFunction(plane);
        sum.AddFunction(p1, 0.2);
        sum.AddFunction(p2, 0.1);
        sum.AddFunction(p3, 0.02);
        vtkSampleFunction sample = new vtkSampleFunction();
        sample.SetImplicitFunction(sum);
        sample.SetSampleDimensions(65, 65, 20);
        sample.SetModelBounds(-1, 1, -1, 1, -0.5, 0.5);
        sample.ComputeNormalsOff();
        vtkContourFilter surface = new vtkContourFilter();
        surface.SetInputConnection(sample.GetOutputPort());
        surface.SetValue(0, 0.0);
        vtkPolyDataNormals smooth = new vtkPolyDataNormals();
        smooth.SetInputConnection(surface.GetOutputPort());
        smooth.SetFeatureAngle(90);
        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        mapper.SetInputConnection(smooth.GetOutputPort());
        mapper.ScalarVisibilityOff();
        vtkActor actor = new vtkActor();
        actor.SetMapper(mapper);
        actor.GetProperty().SetColor(0.4, 0.2, 0.1);
        renWin.GetRenderer().AddActor(actor);
        renWin.GetRenderer().SetBackground(1, 1, 1);
        VtkPanelUtil.setSize(renWin, 500, 500);
        renWin.GetRenderer().ResetCamera();
        renWin.GetRenderer().GetActiveCamera().Elevation(-45);
        renWin.GetRenderer().GetActiveCamera().Azimuth(10);
        renWin.GetRenderer().GetActiveCamera().Dolly(1.35);
        renWin.GetRenderer().ResetCameraClippingRange();
        setLayout(new BorderLayout());
        add(renWin, BorderLayout.CENTER);
    }

    public vtkPanel getRenWin() {
        return renWin;
    }

    public static void main(String s[]) {
        PerlinTerrain panel = new PerlinTerrain();
        JFrame frame = new JFrame("PerlinTerrain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add("Center", panel);
        frame.pack();
        frame.setVisible(true);
    }
}
