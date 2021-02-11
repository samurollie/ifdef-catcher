package detector;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Preprocessor Block
 */
@Getter @AllArgsConstructor
public class PrepBlock {
    private final String filePath;
    private final String commit;
    private final String head;
    private final String body;
    private final int line;

    public String toString() {
        return "file: " + filePath + System.lineSeparator()
                + "line: " + line + System.lineSeparator()
                + "vvvvvvvvvvvvvvvvvvv" + System.lineSeparator()
                + head + System.lineSeparator() + body + "#endif";
    }
}
