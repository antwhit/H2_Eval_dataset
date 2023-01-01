import vtk.util.VtkPanelContainer;
import vtk.util.VtkPanelUtil;
import vtk.util.VtkUtil;
import vtk.*;
import javax.swing.*;
import java.awt.*;

/**
 * This example demonstrates the conversion of point data to cell data.
 * The conversion is necessary because we want to threshold data based
 * on cell scalar values.
 */
public class PointToCellData extends JComponent implements VtkPanelContainer {

    private vtkPanel renWin;

    public PointToCellData() {
        renWin = new vtkPanel();
        vtkUnstructuredGridReader reader = new vtkUnstructuredGridReader();
        reader.SetFileName(VtkUtil.getVtkDataRoot() + "/Data/blow.vtk");
        reader.SetScalarsName("thickness9");
        reader.SetVectorsName("displacement9");
        vtkPointDataToCellData p2c = new vtkPointDataToCellData();
        p2c.SetInputConnection(reader.GetOutputPort());
        p2c.PassPointDataOn();
        vtkWarpVector warp = new vtkWarpVector();
        warp.SetInput(p2c.GetUnstructuredGridOutput());
        vtkThreshold thresh = new vtkThreshold();
        thresh.SetInputConnection(warp.GetOutputPort());
        thresh.ThresholdBetween(0.25, 0.75);
        thresh.SetInputArrayToProcess(1, 0, 0, 0, "thickness9");
        vtkConnectivityFilter connect = new vtkConnectivityFilter();
        connect.SetInputConnection(thresh.GetOutputPort());
        connect.SetExtractionModeToSpecifiedRegions();
        connect.AddSpecifiedRegion(0);
        connect.AddSpecifiedRegion(1);
        vtkDataSetMapper moldMapper = new vtkDataSetMapper();
        moldMapper.SetInputConnection(reader.GetOutputPort());
        moldMapper.ScalarVisibilityOff();
        vtkActor moldActor = new vtkActor();
        moldActor.SetMapper(moldMapper);
        moldActor.GetProperty().SetColor(.2, .2, .2);
        moldActor.GetProperty().SetRepresentationToWireframe();
        vtkConnectivityFilter connect2 = new vtkConnectivityFilter();
        connect2.SetInputConnection(thresh.GetOutputPort());
        vtkGeometryFilter parison = new vtkGeometryFilter();
        parison.SetInputConnection(connect2.GetOutputPort());
        vtkPolyDataNormals normals2 = new vtkPolyDataNormals();
        normals2.SetInputConnection(parison.GetOutputPort());
        normals2.SetFeatureAngle(60);
        vtkLookupTable lut = new vtkLookupTable();
        lut.SetHueRange(0.0, 0.66667);
        vtkPolyDataMapper parisonMapper = new vtkPolyDataMapper();
        parisonMapper.SetInputConnection(normals2.GetOutputPort());
        parisonMapper.SetLookupTable(lut);
        parisonMapper.SetScalarRange(0.12, 1.0);
        vtkActor parisonActor = new vtkActor();
        parisonActor.SetMapper(parisonMapper);
        vtkContourFilter cf = new vtkContourFilter();
        cf.SetInputConnection(connect2.GetOutputPort());
        cf.SetValue(0, .5);
        vtkPolyDataMapper contourMapper = new vtkPolyDataMapper();
        contourMapper.SetInputConnection(cf.GetOutputPort());
        vtkActor contours = new vtkActor();
        contours.SetMapper(contourMapper);
        vtkRenderer ren = renWin.GetRenderer();
        ren.AddActor(moldActor);
        ren.AddActor(parisonActor);
        ren.AddActor(contours);
        ren.ResetCamera();
        ren.GetActiveCamera().Azimuth(60);
        ren.GetActiveCamera().Roll(-90);
        ren.GetActiveCamera().Dolly(2);
        ren.ResetCameraClippingRange();
        ren.SetBackground(1, 1, 1);
        VtkPanelUtil.setSize(renWin, 750, 400);
        setLayout(new BorderLayout());
        add(renWin, BorderLayout.CENTER);
    }

    public vtkPanel getRenWin() {
        return renWin;
    }

    public static void main(String s[]) {
        PointToCellData panel = new PointToCellData();
        JFrame frame = new JFrame("PointToCellData");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add("Center", panel);
        frame.pack();
        frame.setVisible(true);
    }
}
