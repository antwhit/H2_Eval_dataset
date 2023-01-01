import vtk.util.VtkPanelContainer;
import vtk.util.VtkPanelUtil;
import vtk.*;
import javax.swing.*;
import java.awt.*;

/**
 * This example demonstrates the use of vtkAssembly.  In an assembly,
 * the motion of one actor affects the position of other actors.
 */
public class Assembly extends JComponent implements VtkPanelContainer {

    private vtkPanel renWin;

    public Assembly() {
        renWin = new vtkPanel();
        vtkSphereSource sphere = new vtkSphereSource();
        vtkPolyDataMapper sphereMapper = new vtkPolyDataMapper();
        sphereMapper.SetInputConnection(sphere.GetOutputPort());
        vtkActor sphereActor = new vtkActor();
        sphereActor.SetMapper(sphereMapper);
        sphereActor.SetOrigin(2, 1, 3);
        sphereActor.RotateY(6);
        sphereActor.SetPosition(2.25, 0, 0);
        sphereActor.GetProperty().SetColor(1, 0, 1);
        vtkCubeSource cube = new vtkCubeSource();
        vtkPolyDataMapper cubeMapper = new vtkPolyDataMapper();
        cubeMapper.SetInputConnection(cube.GetOutputPort());
        vtkActor cubeActor = new vtkActor();
        cubeActor.SetMapper(cubeMapper);
        cubeActor.SetPosition(0.0, .25, 0);
        cubeActor.GetProperty().SetColor(0, 0, 1);
        vtkConeSource cone = new vtkConeSource();
        vtkPolyDataMapper coneMapper = new vtkPolyDataMapper();
        coneMapper.SetInputConnection(cone.GetOutputPort());
        vtkActor coneActor = new vtkActor();
        coneActor.SetMapper(coneMapper);
        coneActor.SetPosition(0, 0, .25);
        coneActor.GetProperty().SetColor(0, 1, 0);
        vtkCylinderSource cylinder = new vtkCylinderSource();
        vtkPolyDataMapper cylinderMapper = new vtkPolyDataMapper();
        cylinderMapper.SetInputConnection(cylinder.GetOutputPort());
        cylinderMapper.SetResolveCoincidentTopologyToPolygonOffset();
        vtkActor cylinderActor = new vtkActor();
        cylinderActor.SetMapper(cylinderMapper);
        cylinderActor.GetProperty().SetColor(1, 0, 0);
        vtkAssembly assembly = new vtkAssembly();
        assembly.AddPart(cylinderActor);
        assembly.AddPart(sphereActor);
        assembly.AddPart(cubeActor);
        assembly.AddPart(coneActor);
        assembly.SetOrigin(5, 10, 15);
        assembly.AddPosition(5, 0, 0);
        assembly.RotateX(15);
        renWin.GetRenderer().AddActor(assembly);
        renWin.GetRenderer().AddActor(coneActor);
        renWin.GetRenderer().SetBackground(0.1, 0.2, 0.4);
        VtkPanelUtil.setSize(renWin, 200, 200);
        vtkCamera camera = new vtkCamera();
        camera.SetClippingRange(21.9464, 30.0179);
        camera.SetFocalPoint(3.49221, 2.28844, -0.970866);
        camera.SetPosition(3.49221, 2.28844, 24.5216);
        camera.SetViewAngle(30);
        camera.SetViewUp(0, 1, 0);
        renWin.GetRenderer().SetActiveCamera(camera);
        setLayout(new BorderLayout());
        add(renWin, BorderLayout.CENTER);
    }

    public vtkPanel getRenWin() {
        return renWin;
    }

    public static void main(String s[]) {
        Assembly panel = new Assembly();
        JFrame frame = new JFrame("Assembly");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add("Center", panel);
        frame.pack();
        frame.setVisible(true);
    }
}
