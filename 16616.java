import javax.swing.*;
import java.awt.event.*;

/**
 * @author Massimo Bartoletti
 * @version 1.0
 */
public class TwoSegmentIntersectionPalette extends CGPalette {

    private static final String[] tools = { "MovePointTool", "GridModeTool", "ViewLabelsTool", "AnimateTool", "ViewIntersectionTypeTool", "ViewIntersectionTool" };

    public TwoSegmentIntersectionPalette(CGDesktop desktop) {
        super(desktop);
        disableTool("AnimateTool");
    }

    public String[] getTools() {
        return tools;
    }
}

/************************************************************
 *                 View Intersection Type Tool
 ************************************************************/
class ViewIntersectionTypeTool extends CGCanvasTool {

    public ViewIntersectionTypeTool(CGPalette palette) {
        super(palette, "ViewIntersectionTypeTool");
        Action viewIntersectionTypeAction = new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                TwoSegmentIntersectionCanvas canvas = (TwoSegmentIntersectionCanvas) getSelectedCanvas();
                if (canvas != null) {
                    if (getButton().isSelected()) canvas.showIntersectionType(); else canvas.hideIntersectionType();
                }
            }
        };
        addActionListener(viewIntersectionTypeAction);
    }

    public void update() {
        TwoSegmentIntersectionCanvas canvas = (TwoSegmentIntersectionCanvas) getDesktop().getSelectedCanvas();
        if (canvas != null) {
            boolean viewIntersectionType = canvas.getViewIntersectionType();
            setSelected(viewIntersectionType);
        }
    }
}

/************************************************************
 *                 View Intersection Tool
 ************************************************************/
class ViewIntersectionTool extends CGCanvasTool {

    public ViewIntersectionTool(CGPalette palette) {
        super(palette, "ViewIntersectionTool");
        Action viewIntersectionAction = new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                TwoSegmentIntersectionCanvas canvas = (TwoSegmentIntersectionCanvas) getSelectedCanvas();
                if (canvas != null) {
                    if (getButton().isSelected()) canvas.showIntersection(); else canvas.hideIntersection();
                }
            }
        };
        addActionListener(viewIntersectionAction);
    }

    public void update() {
        TwoSegmentIntersectionCanvas canvas = (TwoSegmentIntersectionCanvas) getDesktop().getSelectedCanvas();
        if (canvas != null) {
            boolean viewIntersection = canvas.getViewIntersection();
            setSelected(viewIntersection);
        }
    }
}
