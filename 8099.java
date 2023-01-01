import vtk.util.VtkPanelContainer;
import vtk.*;
import javax.swing.*;
import java.awt.*;

/**
 * This example demonstrates the use of VTK data arrays as attribute
 * data as well as field data. It creates geometry (vtkPolyData) as
 * well as attribute data explicitly.
 */
public class Arrays extends JComponent implements VtkPanelContainer {

    private vtkPanel renWin;

    public Arrays() {
        renWin = new vtkPanel();
        vtkFloatArray pcoords = new vtkFloatArray();
        pcoords.SetNumberOfComponents(3);
        pcoords.SetNumberOfTuples(4);
        pcoords.SetTuple3(0, 0.0, 0.0, 0.0);
        pcoords.SetTuple3(1, 0.0, 1.0, 0.0);
        pcoords.SetTuple3(2, 1.0, 0.0, 0.0);
        pcoords.SetTuple3(3, 1.0, 1.0, 0.0);
        vtkPoints points = new vtkPoints();
        points.SetData(pcoords);
        vtkCellArray strips = new vtkCellArray();
        strips.InsertNextCell(4);
        strips.InsertCellPoint(0);
        strips.InsertCellPoint(1);
        strips.InsertCellPoint(2);
        strips.InsertCellPoint(3);
        vtkIntArray temperature = new vtkIntArray();
        temperature.SetName("Temperature");
        temperature.InsertNextValue(10);
        temperature.InsertNextValue(20);
        temperature.InsertNextValue(30);
        temperature.InsertNextValue(40);
        vtkDoubleArray vorticity = new vtkDoubleArray();
        vorticity.SetName("Vorticity");
        vorticity.InsertNextValue(2.7);
        vorticity.InsertNextValue(4.1);
        vorticity.InsertNextValue(5.3);
        vorticity.InsertNextValue(3.4);
        vtkPolyData polydata = new vtkPolyData();
        polydata.SetPoints(points);
        polydata.SetStrips(strips);
        polydata.GetPointData().SetScalars(temperature);
        polydata.GetPointData().AddArray(vorticity);
        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        mapper.SetInput(polydata);
        mapper.SetScalarRange(0, 40);
        vtkActor actor = new vtkActor();
        actor.SetMapper(mapper);
        renWin.GetRenderer().AddActor(actor);
        renWin.GetRenderer().ResetCamera();
        setLayout(new BorderLayout());
        add(renWin, BorderLayout.CENTER);
    }

    public vtkPanel getRenWin() {
        return renWin;
    }

    public static void main(String s[]) {
        Arrays panel = new Arrays();
        JFrame frame = new JFrame("Arrays");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add("Center", panel);
        frame.pack();
        frame.setVisible(true);
    }
}
