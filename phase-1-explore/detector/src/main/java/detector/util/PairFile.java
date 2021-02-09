package detector.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@Getter
@AllArgsConstructor
public class PairFile {
    private final File left;
    private final File right;
}
