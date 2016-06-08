package de.xappo.test_android_med_unlimited101.main.exceptions;

import java.io.IOException;

/**
 * Created by knoppik on 07.06.16.
 */
public class BufferedReaderNotClosedException extends Throwable {
    public BufferedReaderNotClosedException(IOException e) {
        setStackTrace(e.getStackTrace());
    }
}
