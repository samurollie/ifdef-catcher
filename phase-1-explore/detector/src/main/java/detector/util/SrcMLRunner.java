package detector.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public final class SrcMLRunner {
    private SrcMLRunner(){}

    /**
     * Transform a C source file to xml file, in the output folder.
     * The xml file will have the same name of the source file with ".xml" added to the end. Example: "test.c" will
     * produce "test.c.xml".
     * @param sourceFile source file with c extension.
     * @return xml file, or null
     */
    public static File go(File sourceFile) {
        String filename = sourceFile.getName();
        String destiny = sourceFile.getAbsolutePath().substring(0, sourceFile.getAbsolutePath().
                lastIndexOf(File.separator));
        destiny = destiny.substring(0, destiny.lastIndexOf(File.separator)) + File.separator + "output" +
                File.separator + filename + ".xml";
        File destinyFile = new File(destiny);

        Runtime rt = Runtime.getRuntime();
        String[] commands = {"srcml", "--position", sourceFile.getAbsolutePath(), "-o", destiny};
        try {
            Process process = rt.exec(commands, null);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            process.waitFor();

            return destinyFile;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
