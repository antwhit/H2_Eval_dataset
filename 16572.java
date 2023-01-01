import java.util.regex.*;

public class regexplam {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        try {
            Pattern pt = Pattern.compile("(a(b)?c)+.*");
            Matcher mc = pt.matcher("acbcfasd");
            boolean isMatched = mc.matches();
            System.out.println(isMatched);
            int count = mc.groupCount();
            for (int i = 0; i < count; i++) {
                System.out.println(mc.group(i));
            }
        } catch (PatternSyntaxException e) {
            System.out.println("������ʽ�﷨����");
        } catch (IllegalStateException e) {
            System.out.println("�Ҳ���ƥ����ַ�");
        }
    }
}
