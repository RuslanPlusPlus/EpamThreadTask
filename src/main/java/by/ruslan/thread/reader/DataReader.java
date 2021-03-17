package by.ruslan.thread.reader;

import by.ruslan.thread.exception.ThreadException;

import java.util.List;

public interface DataReader {
    List<String> readAllLines(String fileName) throws ThreadException;
}
