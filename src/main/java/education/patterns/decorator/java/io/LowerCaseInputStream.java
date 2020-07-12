package education.patterns.decorator.java.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LowerCaseInputStream extends FileInputStream {
    public LowerCaseInputStream(BufferedInputStream in) throws FileNotFoundException {
        super("in");
//        super(in);
    }

    public int read() throws IOException {
        int c = super.read();
        return c == -1 ? c : Character.toLowerCase(c);
    }

    public int read(byte[] b, int offset, int len) throws IOException {
        int result = super.read(b, offset, len);
        for (int i = offset; i < offset + result; i++) {
            b[i] = (byte) Character.toLowerCase(b[i]);
        }
        return result;
    }

}
