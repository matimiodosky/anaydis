package anaydis.compression;
import anaydis.bit.Bits;
import anaydis.bit.BitsOutputStream;
import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

import static anaydis.compression.MyBits.buildBits;

public class Huffman implements Compressor {

    /**
         Encoding format:
         [amount of symbols]{[symbol](byte)[length of code](byte)[code](amount of bytes specified)
         [length of message](int)[codes](amount of bytes needed)
     */
    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        Map<Byte, Integer> counts = countSymbols(input);
        Node head = buildTree(counts);
        int size = head.size;
        Map<Byte, MyBits> codes = buildCodes(head);
        long tableBytes = writeTable(codes, output);
        long messageBytes = writeMessage(size, codes, input, output);
        System.out.println("Encoding took " + (double)tableBytes/KILO + " Kb for the table");
        System.out.println("and " + (double)messageBytes/MEGA + " Mb for the encoded data");
        System.out.println("while original data was " + (double)size/MEGA + " Mb");
        System.out.println("The compression rate is:" + (tableBytes + messageBytes)/(double)size);
    }

    /**
     Encoding format:
     [amount of symbols]{[symbol](byte)[length of code](byte)[code](amount of bytes specified)
     [length of message](int)[codes](amount of bytes needed)
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        Map<MyBits, Byte> symbols = buildTable(input);
        int lengthOfMessage = readInt(input);
        LinkedList<Boolean> bits = buildBitsQueue(input);

        for (int i = 0; i < lengthOfMessage; i++) {
            MyBits code = new MyBits().add(bits.poll());
            Byte symbol = symbols.get(code);
            while (symbol == null) {
                code.add(bits.poll());
                symbol = symbols.get(code);
            }
            output.write(symbol);
        }
    }

    //encoding utils
    @NotNull
    private Map<Byte, Integer> countSymbols(InputStream input) throws IOException {

        HashMap<Byte, Integer> counts = new HashMap<>();
        //-1 works as counter;
        byte read = (byte) input.read();
        //count symbols
        while (read != -1) {
            Integer result = counts.put(read, 1);
            if (result != null) {
                counts.put(read, ++result);
            }
            read = (byte) input.read();
        }
        return counts;
    }

    @SuppressWarnings("ConstantConditions")
    private Node buildTree(Map<Byte, Integer> counts) {
        //build simple nodes
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.size));
        for (Byte symbol : counts.keySet()) {
            priorityQueue.add(new Node(symbol, counts.get(symbol)));
        }
        //build complex nodes
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll(), right = priorityQueue.poll();
            priorityQueue.add(new Node(left.size + right.size, left, right));
        }
        return priorityQueue.poll();
    }

    @NotNull
    private Map<Byte, MyBits> buildCodes(Node tree) {
        HashMap<Byte, MyBits> codes = new HashMap<>();
        Stack<Node> stack = new Stack<>();
        stack.push(tree);

        while (!stack.empty()) {
            Node current = stack.pop();
            if (current.left == null && current.right == null) {
                codes.put(current.symbol, current.getCode());
            }
            if (current.left != null) {
                current.left.setCode(current.code.copy());
                current.left.getCode().add(false);
                stack.push(current.left);
            }
            if (current.right != null) {
                current.right.setCode(current.code.copy());
                current.right.getCode().add(true);
                stack.push(current.right);
            }
        }
        return codes;
    }

    private long writeTable(Map<Byte, MyBits> codes, OutputStream output) throws IOException {
        long bytes = 0;
        output.write(codes.size());
        for (Byte symbol : codes.keySet()) {
            output.write(symbol);
            bytes++;
            Bits code = codes.get(symbol);
            code.writeInto(output);
            bytes += code.getLength();
            bytes++;
        }
        return bytes;
    }

    private long writeMessage(int size, @NotNull Map<Byte, MyBits> codes, InputStream inputStream, @NotNull OutputStream outputStream) throws IOException {
        long bytes = 0;
        writeInt(size, outputStream);
        bytes += 4;
        BitsOutputStream bitsOutputStream = new BitsOutputStream();
        inputStream.reset();
        byte read = (byte) inputStream.read();
        while (read != -1) {
            bitsOutputStream.write(codes.get(read));
            read = (byte) inputStream.read();
        }
        byte[] array = bitsOutputStream.toByteArray();
        outputStream.write(array);
        bytes += array.length;
        return bytes;
    }

    static void writeInt(int value, OutputStream outputStream) throws IOException {
        outputStream.write(value >> 24);
        outputStream.write(value >> 16);
        outputStream.write(value >> 8);
        outputStream.write(value);
        System.out.println();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    static int readInt(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[4];
        inputStream.read(bytes);
        return ByteBuffer.wrap(bytes).getInt();
    }

    //decoding utils
    @NotNull
    private Map<MyBits, Byte> buildTable(InputStream input) throws IOException {
        byte amountOfSymbols = (byte) input.read();
        Map<MyBits, Byte> symbols = new HashMap<>();
        for (int i = 0; i < amountOfSymbols; i++) {
            final byte symbol = (byte) input.read();
            final byte length = (byte) input.read();
            final byte arrayLength = (byte) ((length % 8 == 0 )? (length / 8) : ((length / 8) + 1));
            final byte[] code = new byte[arrayLength];
            for (int j = 0; j < arrayLength; j++) {
                code[j] = (byte) input.read();
            }
            symbols.put(buildBits(length, code), symbol);
        }
        return symbols;

    }

    @NotNull
    private LinkedList<Boolean> buildBitsQueue(InputStream input) throws IOException {
        LinkedList<Boolean> bits = new LinkedList<>();
        byte read = (byte) input.read();
        while (input.available() > 0){
            if (bits.size() == 2840){
                System.out.println();
            }
            for (byte i = Byte.SIZE - 1; i >= 0; i--) {
                bits.addLast(((read >> i) & 1) == 1);
            }
            read = (byte) input.read();
        }
        for (byte i = Byte.SIZE - 1; i >= 0; i--) {
            bits.addLast(((read >> i) & 1) == 1);
        }
        return bits;
    }

    //private node class
    private class Node {
        final int size;
        Byte symbol;
        Node left, right;
        MyBits code;

        Node(Byte symbol, int size) {
            this.size = size;
            this.symbol = symbol;
            this.code = new MyBits();
        }

        Node(int size, Node left, Node right) {
            this.size = size;
            this.left = left;
            this.right = right;
            this.code = new MyBits();
        }

        MyBits getCode() {
            return this.code;
        }

        void setCode(MyBits code) {
            this.code = code;
        }
    }}