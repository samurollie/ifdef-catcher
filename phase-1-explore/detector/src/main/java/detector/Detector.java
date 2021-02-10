package detector;

import detector.util.PairFile;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Detector {

    private static final int N = 10;

    public List<PrepRefactored> getPrepRefactoredList() {
        var prepRefactoredList = new ArrayList<PrepRefactored>();

        for (var pairFile : getPairFiles()) {
            var leftBlocks = getPrepBlockList(pairFile.getLeft());
            var rightBlocks = getPrepBlockList(pairFile.getRight());

            for(var leftBlock : leftBlocks) {
                for (var rightBlock : rightBlocks) {
                    if (shrinkCode(leftBlock.getHead()).equals(shrinkCode(rightBlock.getHead()))) {

                        // out of limits -> skip right
                        if (!(rightBlock.getLine() >= leftBlock.getLine() - N) ||
                                !(rightBlock.getLine() <= leftBlock.getLine() + N)) {
                            continue;
                        }

                        // two sides equals -> not refactored -> skip left
                        if (shrinkCode(leftBlock.getBody()).equals(shrinkCode(rightBlock.getBody()))) {
                            break;
                        }

                        prepRefactoredList.add(new PrepRefactored(leftBlock, rightBlock));
                    }
                }
            }
        }

        return prepRefactoredList;
    }

    private List<PrepBlock> getPrepBlockList(File file) {
        // TODO implement
        // run srcml on file
        throw new UnsupportedOperationException("not implemented yet");
    }

    private List<PairFile> getPairFiles() {
        var pairFiles = new ArrayList<PairFile>();
        var listOfFiles = new File("input").listFiles();
        for (var leftFile : Objects.requireNonNull(listOfFiles)) {
            if (leftFile.isFile() && leftFile.getName().contains("left")) {
                var leftNumber = leftFile.getName().replace("left", "");

                for (var rightFile : Objects.requireNonNull(listOfFiles)) {
                    if (rightFile.isFile() && rightFile.getName().contains("right") &&
                            rightFile.getName().replace("right", "").equals(leftNumber)) {
                        pairFiles.add(new PairFile(leftFile, rightFile));
                    }
                }
            }
        }

        return pairFiles;
    }

    private String shrinkCode(String code) {
        return code.replaceAll("[\\n\\t]","");
    }
}
