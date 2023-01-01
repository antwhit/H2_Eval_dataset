import vtk.util.VtkPanelContainer;
import vtk.util.VtkPanelUtil;
import vtk.util.VtkUtil;
import vtk.*;
import javax.swing.*;
import java.awt.*;

/**
 * This example demonstrates the generation of a streamsurface.
 */
public class StreamSurface extends JComponent implements VtkPanelContainer {

    private vtkPanel renWin;

    public StreamSurface() {
        renWin = new vtkPanel();
        vtkPLOT3DReader pl3d = new vtkPLOT3DReader();
        pl3d.SetXYZFileName(VtkUtil.getVtkDataRoot() + "/Data/combxyz.bin");
        pl3d.SetQFileName(VtkUtil.getVtkDataRoot() + "/Data/combq.bin");
        pl3d.SetScalarFunctionNumber(100);
        pl3d.SetVectorFunctionNumber(202);
        pl3d.Update();
        vtkLineSource rake = new vtkLineSource();
        rake.SetPoint1(15, -5, 32);
        rake.SetPoint2(15, 5, 32);
        rake.SetResolution(21);
        vtkPolyDataMapper rakeMapper = new vtkPolyDataMapper();
        rakeMapper.SetInputConnection(rake.GetOutputPort());
        vtkActor rakeActor = new vtkActor();
        rakeActor.SetMapper(rakeMapper);
        vtkRungeKutta4 integ = new vtkRungeKutta4();
        vtkStreamLine sl = new vtkStreamLine();
        sl.SetInputConnection(pl3d.GetOutputPort());
        sl.SetSource(rake.GetOutput());
        sl.SetIntegrator(integ);
        sl.SetMaximumPropagationTime(0.1);
        sl.SetIntegrationStepLength(0.1);
        sl.SetIntegrationDirectionToBackward();
        sl.SetStepLength(0.001);
        vtkRuledSurfaceFilter scalarSurface = new vtkRuledSurfaceFilter();
        scalarSurface.SetInputConnection(sl.GetOutputPort());
        scalarSurface.SetOffset(0);
        scalarSurface.SetOnRatio(2);
        scalarSurface.PassLinesOn();
        scalarSurface.SetRuledModeToPointWalk();
        scalarSurface.SetDistanceFactor(30);
        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        mapper.SetInputConnection(scalarSurface.GetOutputPort());
        mapper.SetScalarRange(pl3d.GetOutput().GetScalarRange());
        vtkActor actor = new vtkActor();
        actor.SetMapper(mapper);
        vtkStructuredGridOutlineFilter outline = new vtkStructuredGridOutlineFilter();
        outline.SetInputConnection(pl3d.GetOutputPort());
        vtkPolyDataMapper outlineMapper = new vtkPolyDataMapper();
        outlineMapper.SetInputConnection(outline.GetOutputPort());
        vtkActor outlineActor = new vtkActor();
        outlineActor.SetMapper(outlineMapper);
        outlineActor.GetProperty().SetColor(0, 0, 0);
        renWin.GetRenderer().AddActor(rakeActor);
        renWin.GetRenderer().AddActor(actor);
        renWin.GetRenderer().AddActor(outlineActor);
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
        StreamSurface panel = new StreamSurface();
        JFrame frame = new JFrame("StreamSurface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add("Center", panel);
        frame.pack();
        frame.setVisible(true);
    }
}
