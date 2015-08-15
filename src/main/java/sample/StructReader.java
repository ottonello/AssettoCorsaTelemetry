package sample;

import com.google.common.io.LittleEndianDataInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class StructReader {
    LittleEndianDataInputStream is;

    public StructReader(byte[] received) {
        is = new LittleEndianDataInputStream(new ByteArrayInputStream(received));
    }

    public String readChars( int size) throws IOException {
        char[] arr = new char[size];

        for (int i = 0; i < size; i++) {
            arr[i] = is.readChar();
        }
        int length = arr.length;
        for (int i = 0; i < arr.length; i++) {
            if('%' == arr[i]){
                length = i;
            }
        }
        return new String(arr, 0, length);
    }

    public float[]readFloats( int size) throws IOException {
        float[] arr = new float[size];

        for (int i = 0; i < size; i++) {
            arr[i] = is.readFloat();
        }
        return arr;
    }

    public int readInt() throws IOException {
        return is.readInt();
    }

    public float readFloat() throws IOException {
        return is.readFloat();
    }

    public boolean readBool() throws IOException {
        return is.readBoolean();
    }
}
