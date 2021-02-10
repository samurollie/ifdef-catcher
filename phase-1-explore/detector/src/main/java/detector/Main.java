package detector;

public class Main {
    public static void main(String[] args) {
        for (var prepRefactored : new Detector().getPrepRefactoredList()) {
            // TODO change later
            System.out.println(prepRefactored.getLeft().getHead());
        }
    }
}
