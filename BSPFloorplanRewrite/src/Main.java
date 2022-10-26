import com.jsevy.jdxf.DXFDocument;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    //variables to have controls added to control the generated room
    private static final int size = 60;
    private static final int maxRoomSize = 30;
    private static final int minRoomSize = 20;
    //the highest point in the bsp tree
    private static Cube mainCube;
    //stores all the Cubes to be drawn by the Cube class
    private static ArrayList<Cube> discoveredLeaves = new ArrayList<>();
    private static final DXFFileGenerator dxfFileGen = new DXFFileGenerator();

    public static void main(String[] args) throws IOException {
        mainCube = new Cube(0,0,size,size);
        discoveredLeaves.add(mainCube);
        recursiveSearch(discoveredLeaves);

        DXFDocument dxfDocument = dxfFileGen.floorPlan(discoveredLeaves);
        String dxfAsText = dxfDocument.toDXFString();
        String path = "dxfFile.dxf";
        FileWriter fw = new FileWriter(path);
        fw.write(dxfAsText);
        fw.flush();
        fw.close();
    }

    public static void recursiveSearch(ArrayList<Cube> leaves) {
        int oldSize = 0;
        int newSize = 1;
        while(oldSize != newSize) {
            oldSize = newSize;
            ArrayList<Cube> toAdd = new ArrayList<Cube>();
            ArrayList<Cube> toRemove = new ArrayList<Cube>();

            for (int i = 0; i < leaves.size(); i++) {

                Cube cube = leaves.get(i);

                if ((cube.getWidth() > maxRoomSize || cube.getHeight() > maxRoomSize) && !cube.getSplit()) {
                    int cubeWidth = cube.getWidth();
                    int cubeX = cube.getX();
                    int cubeY = cube.getY();
                    int cubeHeight = cube.getHeight();
                    int playRoomHeight = cubeHeight - (2*minRoomSize);
                    int playRoomWidth = cubeWidth - (2*minRoomSize);

                    if(cubeHeight*0.8 > cubeWidth) {
                        //split by height
                        int splitPoint =  (int) (Math.random()*playRoomHeight);
                        System.out.println("Height Split Point " + splitPoint);
                        toAdd.add(new Cube(cubeX, cubeY,cubeWidth, minRoomSize + splitPoint));
                        System.out.println("Split by height cube 1: X" + cubeX + " Y" + cubeY + " width"+ cubeWidth + " height" + (minRoomSize + splitPoint));
                        toAdd.add(new Cube(cubeX, cubeY + splitPoint + minRoomSize, cubeWidth, cubeHeight - (splitPoint + minRoomSize), 1));
                        System.out.println("Split by height cube 2: X" + cubeX + " Y" + (cubeY + splitPoint + minRoomSize));
                    } else if(cubeWidth*0.8 > cubeHeight){
                        //split by width
                        int splitPoint = (int) (Math.random()*playRoomWidth);
                        toAdd.add(new Cube(cubeX, cubeY, minRoomSize + splitPoint, cubeHeight));
                        System.out.println("Split by width cube 1: X" + cubeX + " Y" + cubeY);
                        toAdd.add(new Cube(cubeX + splitPoint + minRoomSize, cubeY, cubeWidth - (splitPoint + minRoomSize), cubeHeight, 2));
                        System.out.println("Split by width cube 2: X" + (cubeX + splitPoint + minRoomSize) + " Y" + cubeY);
                    }else {
                        int choice = (int) ((Math.random()*2) +1);
                        if(choice == 1) {
                            //split by height
                            int splitPoint =  (int) (Math.random()*playRoomHeight);
                            System.out.println("Height Split Point " + splitPoint);
                            toAdd.add(new Cube(cubeX, cubeY,cubeWidth, minRoomSize + splitPoint));
                            System.out.println("Split by height cube 1: X" + cubeX + " Y" + cubeY + " width"+ cubeWidth + " height" + (minRoomSize + splitPoint));
                            toAdd.add(new Cube(cubeX, cubeY + splitPoint + minRoomSize, cubeWidth, cubeHeight - (splitPoint + minRoomSize), 1));
                            System.out.println("Split by height cube 2: X" + cubeX + " Y" + (cubeY + splitPoint + minRoomSize));
                        }else if(choice == 2){
                            //split by width
                            int splitPoint = (int) (Math.random()*playRoomWidth);
                            toAdd.add(new Cube(cubeX, cubeY, minRoomSize + splitPoint, cubeHeight));
                            System.out.println("Split by width cube 1: X" + cubeX + " Y" + cubeY);
                            toAdd.add(new Cube(cubeX + splitPoint + minRoomSize, cubeY, cubeWidth - (splitPoint + minRoomSize), cubeHeight, 2));
                            System.out.println("Split by width cube 2: X" + (cubeX + splitPoint + minRoomSize) + " Y" + cubeY);
                        }
                    }
                    cube.setSplit();
                    newSize++;
                }
            }
            leaves.addAll(toAdd);
        }


    }
}
