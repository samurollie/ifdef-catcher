package detector;

public class Main {
    public static void main(String[] args) {
        Detector detector = new Detector();
        for (PrepRefactored prepRefactored : detector.getPrepRefactoredList()) {
            // TODO print refactoring
        }
    }
}
