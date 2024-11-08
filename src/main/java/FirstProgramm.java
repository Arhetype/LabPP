import FirstPackage.FirstPackage;

public class FirstProgramm {
    public static void main(String[] s) {
        FirstPackage o = new FirstPackage();

        int i, j;
        for (i = 1; i <= 8; i++) {
            for (j = 1; j <= 8; j++) {
                o.setFirstNumber(i);
                o.setSecondNumber(j);
                System.out.print(o.performAction());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}