package detector;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Preprocessor Block
 */
@Getter @AllArgsConstructor
public class PrepBlock {
    private final String filePath;
    private final String head;
    private final String body;
    private final int line;
}
