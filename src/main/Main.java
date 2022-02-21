package main;

import checker.Checker;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fileio.FinalVersion;
import fileio.Input;

import java.io.File;
import java.io.IOException;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) {
        final int tests = 30;
        try {
            for (int i = 1; i <= tests; i++) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String filepath = "output/out_" + i + ".json";
                    Input input = objectMapper.readValue(new File("tests/test" + i + ".json"),
                            Input.class);
                    Solution start = Solution.getInstance();
                    FinalVersion childList = start.action(input);

                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                    DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
                    prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
                    objectMapper.writeValue(new File(filepath), childList);
            }
            Checker.calculateScore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
