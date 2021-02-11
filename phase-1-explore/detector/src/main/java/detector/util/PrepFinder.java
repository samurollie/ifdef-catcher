package detector.util;

import detector.PrepBlock;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Preprocessor finder
 */
public final class PrepFinder {
    private PrepFinder() {}

    /**
     * Get preprocessor blocks from xml file
     * @param xmlFile xml file to extract the blocks
     * @param commit hash value of the commit
     * @return list of preprocessor blocks
     */
    public static List<PrepBlock> getBlocks(File xmlFile, String commit) {
        var blocks = new ArrayList<PrepBlock>();

        // read xml
        var docBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            var docBuilder = docBuilderFactory.newDocumentBuilder();
            var doc = docBuilder.parse(xmlFile);
            find(blocks, doc, xmlFile.getAbsolutePath(), commit);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return blocks;
    }

    private static void find(ArrayList<PrepBlock> blocks, Document doc, String path, String commit) {
        var unit = doc.getFirstChild();
        recursiveEnter(blocks, unit, path, commit);
    }

    private static void recursiveEnter(ArrayList<PrepBlock> blocks, Node node, String path, String commit) {
        if (!node.hasChildNodes()) return;

        var children = node.getChildNodes();
        for(var i = 0; i < children.getLength(); ++i) {
            var child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                if (isTarget(child)) blocks.add(buildPrepBlock(child, path, commit));
                recursiveEnter(blocks, child, path, commit);
            }
        }
    }

    private static PrepBlock buildPrepBlock(Node node, String path, String commit) {
        var head = node.getTextContent();
        var line = Integer.parseInt(node.getAttributes()
                .getNamedItem("pos:start")
                .getNodeValue().split(":")[0]);

        Node sibling;
        var body = new StringBuilder();
        var currentNode = node;
        var endifs = 1;
        while((sibling = currentNode.getNextSibling()) != null) {
            if (sibling.getNodeType() == Node.ELEMENT_NODE) {
                if (sibling.getNodeName().equals("cpp:endif")) {
                    endifs--;
                } else if (isTarget(sibling)) {
                    endifs++;
                }
                if (endifs == 0) break;

                body.append(sibling.getTextContent());
                body.append("\n");
            }
            currentNode = sibling;
        }
        // if (sibling != null) body.append(sibling.getTextContent());

        return new PrepBlock(path, commit, head, body.toString(), line);
    }

    private static boolean isTarget(Node child) {
        // TODO add more possibilities
        return child.getNodeName().equals("cpp:if")
                || child.getNodeName().equals("cpp:ifdef")
                || child.getNodeName().equals("cpp:ifndef");
    }
}
