import com.jsevy.jdxf.DXFDocument;
import com.jsevy.jdxf.DXFGraphics;

import java.awt.*;
import java.util.ArrayList;

import static java.awt.Color.*;

public class DXFFileGenerator {
    DXFDocument dxfDocument = new DXFDocument("Floorplan");
    DXFGraphics dxfGraphics = dxfDocument.getGraphics();

    public DXFDocument floorPlan(ArrayList<Cube> leaves) {
        dxfGraphics.setStroke(new BasicStroke(3));
        dxfGraphics.setColor(black);

        for (Cube cube : leaves
        ) {
            dxfGraphics.drawRect(cube.getX() , cube.getY(), cube.getWidth(),cube.getHeight());
            if(cube.getDoor() == 1) {
                dxfGraphics.drawRect(cube.getX(), cube.getY()-0.05, 0.5, 0.1);
            }
            if(cube.getDoor() == 2) {
                dxfGraphics.drawRect(cube.getX()-0.05, cube.getY(),  0.1,0.5);
            }
        }

        return dxfDocument;
    }
}
