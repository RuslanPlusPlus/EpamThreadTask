package by.ruslan.thread.reader;

import by.ruslan.thread.exception.ThreadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    static final Logger logger = LogManager.getLogger();

    public List<String> readData(String fileName) throws ThreadException {
        if (fileName == null || fileName.isEmpty()){
            throw new ThreadException("Passed filename is null or empty");
        }
        List<String> lines = new ArrayList<>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URI resource = classLoader.getResource(fileName).toURI();
            Path path = Paths.get(resource);
            Files.lines(path, Charset.defaultCharset()).forEach(lines::add);
        } catch (IOException | URISyntaxException e) {
            logger.error("Failed to read file: " + fileName);
            throw new ThreadException("Failed to read file: " + fileName, e);
        }
        logger.info("File " + fileName + " is successfully read");
        return lines;
    }
}
