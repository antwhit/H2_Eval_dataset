public class Converter {

    public static ASCIIMathConverter converter;

    public static String asciiMathML = "<math xmlns=\"http://www.w3.org/1998/Math/MathML\">\n" + "  <mstyle mathcolor=\"blue\" fontfamily=\"serif\" displaystyle=\"true\">\n" + "    <mfrac>\n" + "      <mrow>\n" + "        <mn>5</mn>\n" + "        <mi>x</mi>\n" + "      </mrow>\n" + "      <mrow>\n" + "        <mn>1</mn>\n" + "        <mo>-</mo>\n" + "        <mi>x</mi>\n" + "      </mrow>\n" + "    </mfrac>\n" + "  </mstyle>\n" + "</math>";

    public static void main(String[] args) {
        converter = new ASCIIMathConverter();
        converter.init(asciiMathML);
        System.out.println(converter.getMaximaAnnotation());
    }
}
