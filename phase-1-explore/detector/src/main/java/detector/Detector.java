package detector;

import detector.util.PairFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Detector {

    private static final int N = 10;

    public List<PrepRefactored> getPrepRefactoredList() {
        var prepRefactoredList = new ArrayList<PrepRefactored>();

        var pairFiles = getPairFiles();
        if (pairFiles == null || pairFiles.isEmpty()) {
            System.out.println("error to get files");
            return null;
        }

        for (var pairFile : getPairFiles()) {
            var leftBlocks = getPrepBlockList(pairFile.getLeft());
            var rightBlocks = getPrepBlockList(pairFile.getRight());

            // TODO compare each PrepBlock on the left with the PrepBlocks on the right
            // TODO match? add to list of PrepRefactored
        }

        return prepRefactoredList;
    }

    private List<PrepBlock> getPrepBlockList(File file) {
        // TODO implement
        // run srcml on file
        throw new UnsupportedOperationException("not implemented yet");
    }

    private List<PairFile> getPairFiles() {
        // TODO implement
        throw new UnsupportedOperationException("not implemented yet");
    }
}
