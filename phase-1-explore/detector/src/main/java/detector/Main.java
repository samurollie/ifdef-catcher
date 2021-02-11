package detector;

public class Main {
    public static void main(String[] args) {
        for (var prepRefactored : new Detector().getPrepRefactoredList()) {
            System.out.println("====================");
            System.out.println(prepRefactored.getLeft().toString());
            System.out.println();
            System.out.println(prepRefactored.getRight().toString());
            System.out.println("====================");
        }
    }
}
