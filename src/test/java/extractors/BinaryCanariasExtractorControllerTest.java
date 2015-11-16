package extractors;

import org.junit.Test;

/**
 * Created by Ismael Ojeda Perez on 15/11/2015.
 */
public class BinaryCanariasExtractorControllerTest {

    BinaryCanariasExtractorController binaryCanariasExtractorController = new BinaryCanariasExtractorController();
    @Test
    public void testStart() throws Exception {
        binaryCanariasExtractorController.start();
    }
}