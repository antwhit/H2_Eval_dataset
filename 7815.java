import vtk.util.VtkPanelContainer;
import vtk.util.VtkPanelUtil;
import vtk.util.VtkUtil;
import vtk.*;
import javax.swing.*;
import java.awt.*;

/**
 * This example shows how to generate and manipulate texture coordinates.
 * A random cloud of points is generated and then triangulated with
 * vtkDelaunay3D. Since these points do not have texture coordinates,
 * we generate them with vtkTextureMapToCylinder.
 */
public class GenerateTextureCoords extends JComponent implements VtkPanelContainer {

    private vtkPanel renWin;

    public GenerateTextureCoords() {
        renWin = new vtkPanel();
        vtkPointSource sphere = new vtkPointSource();
        sphere.SetNumberOfPoints(25);
        vtkDelaunay3D delny = new vtkDelaunay3D();
        delny.SetInputConnection(sphere.GetOutputPort());
        delny.SetTolerance(0.01);
        vtkTextureMapToCylinder tmapper = new vtkTextureMapToCylinder();
        tmapper.SetInputConnection(delny.GetOutputPort());
        tmapper.PreventSeamOn();
        vtkTransformTextureCoords xform = new vtkTransformTextureCoords();
        xform.SetInputConnection(tmapper.GetOutputPort());
        xform.SetScale(4, 4, 1);
        vtkDataSetMapper mapper = new vtkDataSetMapper();
        mapper.SetInputConnection(xform.GetOutputPort());
        vtkBMPReader bmpReader = new vtkBMPReader();
        bmpReader.SetFileName(VtkUtil.getVtkDataRoot() + "/Data/masonry.bmp");
        vtkTexture atext = new vtkTexture();
        atext.SetInputConnection(bmpReader.GetOutputPort());
        atext.InterpolateOn();
        vtkActor triangulation = new vtkActor();
        triangulation.SetMapper(mapper);
        triangulation.SetTexture(atext);
        renWin.GetRenderer().AddActor(triangulation);
        renWin.GetRenderer().SetBackground(1, 1, 1);
        renWin.GetRenderer().ResetCamera();
        VtkPanelUtil.setSize(renWin, 300, 300);
        setLayout(new BorderLayout());
        add(renWin, BorderLayout.CENTER);
    }

    public vtkPanel getRenWin() {
        return renWin;
    }

    public static void main(String s[]) {
        GenerateTextureCoords panel = new GenerateTextureCoords();
        JFrame frame = new JFrame("GenerateTextureCoords");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add("Center", panel);
        frame.pack();
        frame.setVisible(true);
    }
}
