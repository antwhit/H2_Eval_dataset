import vtk.util.VtkPanelContainer;
import vtk.util.VtkPanelUtil;
import vtk.util.VtkUtil;
import vtk.*;
import javax.swing.*;
import java.awt.*;

/**
 * This example shows how to extract portions of an unstructured grid
 * using vtkExtractUnstructuredGrid. vtkConnectivityFilter is also used
 * to extract connected components.
 * <p/>
 * The data found here represents a blow molding process. Blow molding
 * requires a mold and parison (hot, viscous plastic) which is shaped
 * by the mold into the final form. The data file contains several steps
 * in time for the analysis.
 */
public class ExtractUGrid extends JComponent implements VtkPanelContainer {

    private vtkPanel renWin;

    public ExtractUGrid() {
        renWin = new vtkPanel();
        vtkDataSetReader reader = new vtkDataSetReader();
        reader.SetFileName(VtkUtil.getVtkDataRoot() + "/Data/blow.vtk");
        reader.SetScalarsName("thickness9");
        reader.SetVectorsName("displacement9");
        vtkCastToConcrete castToUnstructuredGrid = new vtkCastToConcrete();
        castToUnstructuredGrid.SetInputConnection(reader.GetOutputPort());
        vtkWarpVector warp = new vtkWarpVector();
        warp.SetInput(castToUnstructuredGrid.GetUnstructuredGridOutput());
        vtkConnectivityFilter connect = new vtkConnectivityFilter();
        connect.SetInputConnection(warp.GetOutputPort());
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
        connect2.SetInputConnection(warp.GetOutputPort());
        connect2.SetExtractionModeToSpecifiedRegions();
        connect2.AddSpecifiedRegion(2);
        vtkExtractUnstructuredGrid extractGrid = new vtkExtractUnstructuredGrid();
        extractGrid.SetInputConnection(connect2.GetOutputPort());
        extractGrid.CellClippingOn();
        extractGrid.SetCellMinimum(0);
        extractGrid.SetCellMaximum(23);
        vtkGeometryFilter parison = new vtkGeometryFilter();
        parison.SetInputConnection(extractGrid.GetOutputPort());
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
        renWin.GetRenderer().AddActor(parisonActor);
        renWin.GetRenderer().AddActor(moldActor);
        renWin.GetRenderer().SetBackground(1, 1, 1);
        renWin.GetRenderer().ResetCamera();
        renWin.GetRenderer().GetActiveCamera().Azimuth(60);
        renWin.GetRenderer().GetActiveCamera().Roll(-90);
        renWin.GetRenderer().GetActiveCamera().Dolly(2);
        renWin.GetRenderer().ResetCameraClippingRange();
        VtkPanelUtil.setSize(renWin, 500, 375);
        setLayout(new BorderLayout());
        add(renWin, BorderLayout.CENTER);
    }

    public vtkPanel getRenWin() {
        return renWin;
    }

    public static void main(String s[]) {
        ExtractUGrid panel = new ExtractUGrid();
        JFrame frame = new JFrame("ExtractUGrid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add("Center", panel);
        frame.pack();
        frame.setVisible(true);
    }
}
