public class GenGoBoard {

    public static void main(String[] args) {
        System.out.println("<?xml version='1.0'?>");
        System.out.println("<!DOCTYPE GraphXML SYSTEM \"file:GraphXML.dtd\">");
        System.out.println("  <GraphXML xmlns:xlink='http://www.w3.org/1999/xlink/namespace/'>");
        System.out.println("    <graph enclosingBox='(0.00 7.00 0.00 7.00 0.00 MAX)' id='Multilevel' isAcyclic='true' isDirected='true' preferredLayout='UserDefined'>");
        for (int i = 0; i < 6; i++) {
            char x = (char) (0x61 + i);
            char xright = (char) (0x61 + i + 1);
            for (int j = 0; j < 6; j++) {
                char y = (char) (0x31 + j);
                char yup = (char) (0x31 + j + 1);
                String currentSquare = x + "" + y;
                String upSquare = x + "" + yup;
                String rightSquare = xright + "" + y;
                System.out.println("      <node isMetanode='false' name='" + currentSquare + "'>");
                System.out.println("        <properties horizontalPosition='" + (i + 1) + ".0' verticalPosition='" + (j + 1) + ".0'>");
                System.out.println("        </properties>");
                System.out.println("      </node>");
                if (j < 5) {
                    System.out.println("      <node isMetanode='false' name='up-" + currentSquare + "-" + upSquare + "'>");
                    System.out.println("        <label>up</label>");
                    System.out.println("        <properties horizontalPosition='" + (i + 1) + ".0' verticalPosition='" + (j + 1) + ".5'>");
                    System.out.println("        </properties>");
                    System.out.println("      </node>");
                }
                if (i < 5) {
                    System.out.println("      <node isMetanode='false' name='right-" + currentSquare + "-" + rightSquare + "'>");
                    System.out.println("        <label>right</label>");
                    System.out.println("        <properties horizontalPosition='" + (i + 1) + ".5' verticalPosition='" + (j + 1) + ".0'>");
                    System.out.println("        </properties>");
                    System.out.println("      </node>");
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            char x = (char) (0x61 + i);
            char xright = (char) (0x61 + i + 1);
            for (int j = 0; j < 6; j++) {
                char y = (char) (0x31 + j);
                char yup = (char) (0x31 + j + 1);
                String currentSquare = x + "" + y;
                String upSquare = x + "" + yup;
                String rightSquare = xright + "" + y;
                if (j < 5) {
                    System.out.println("      <edge source='" + currentSquare + "' target='up-" + currentSquare + "-" + upSquare + "'>");
                    System.out.println("      </edge>");
                    System.out.println("      <edge source='up-" + currentSquare + "-" + upSquare + "' target='" + upSquare + "'>");
                    System.out.println("      </edge>");
                }
                if (i < 5) {
                    System.out.println("      <edge source='" + currentSquare + "' target='right-" + currentSquare + "-" + rightSquare + "'>");
                    System.out.println("      </edge>");
                    System.out.println("      <edge source='right-" + currentSquare + "-" + rightSquare + "' target='" + rightSquare + "'>");
                    System.out.println("      </edge>");
                }
            }
        }
        System.out.println("    </graph>");
        System.out.println("  </GraphXML>");
    }
}
