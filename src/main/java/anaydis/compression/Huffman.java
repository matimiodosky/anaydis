package anaydis.compression;
import anaydis.bit.BitsOutputStream;
import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import static anaydis.compression.MyBits.buildBits;

public class Huffman implements Compressor {

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

        Map<Integer, Integer> counts = countSymbols(input);
        //-1 works as counter.
        int size = counts.remove(-1);
        Node head = buildTree(counts);
        Map<Integer, MyBits> codes = buildCodes(head);
        int tableSize = writeTable(codes, output);
        int messageSize = writeMessage(size, codes, input, output);
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        Map<MyBits, Integer> symbols = buildTable(input);
        int lengthOfMessage = input.read();
        ArrayBlockingQueue<Boolean> bits = buildBitsQueue(input, lengthOfMessage);
        for (int i = 0; i < lengthOfMessage; i++) {
            MyBits code = new MyBits().add(bits.poll());
            Integer symbol = symbols.get(code);
            while (symbol == null){
                code.add(bits.poll());
                symbol = symbols.get(code);
            }
            output.write(symbol);
        }
    }

    //encoding utils
    private Map<Integer, Integer> countSymbols(InputStream input) throws IOException {

        HashMap<Integer, Integer> counts = new HashMap<>();
        //-1 works as counter;
        int read = input.read();
        int count = 0;
        //count symbols
        while (read != -1) {
            Integer result = counts.put(read, 1);
            if (result != null) {
                counts.put(read, ++result);
            }
            read = input.read();
            count++;
        }
        counts.put(-1, count);
        return counts;
    }

    private Node buildTree(Map<Integer, Integer> counts) {
        //build simple nodes
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.size));

        for (Integer symbol : counts.keySet()) {
            priorityQueue.add(new Node(symbol, counts.get(symbol)));
        }

        //build complex nodes
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll(), right = priorityQueue.poll();
            priorityQueue.add(new Node(left.size + right.size, left, right));
        }
        return priorityQueue.poll();
    }

    private Map<Integer, MyBits> buildCodes(Node tree) {
        HashMap<Integer, MyBits> codes = new HashMap<>();
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

    private int writeTable(Map<Integer, MyBits> codes, OutputStream output) throws IOException {
        output.write(codes.size());
        for (Integer integer : codes.keySet()) {
            output.write(integer);
            codes.get(integer).writeInto(output);
        }
        return 0;
    }

    private int writeMessage(int size, Map<Integer, MyBits> codes, InputStream inputStream, OutputStream outputStream) throws IOException {
        BitsOutputStream bitsOutputStream = new BitsOutputStream();
        inputStream.reset();
        int read = inputStream.read();
        outputStream.write(size);
        while (read != -1) {
            bitsOutputStream.write(codes.get(read));
            read = inputStream.read();
        }
        outputStream.write(bitsOutputStream.toByteArray());
        return 0;
    }

    //decoding utils
    private Map<MyBits, Integer> buildTable(InputStream input) throws IOException {
        int amountOfSymbols = input.read();
        Map<MyBits, Integer> symbols = new HashMap<>();

        for (int i = 0; i < amountOfSymbols; i++) {
            final int symbol = input.read();
            final byte length = (byte) input.read();
            final byte code = (byte) input.read();
            symbols.put(buildBits(length, code), symbol);
        }
        return symbols;
    }

    private ArrayBlockingQueue<Boolean> buildBitsQueue(InputStream input, int lengthOfMessage) throws IOException {
        ArrayBlockingQueue<Boolean> bits = new ArrayBlockingQueue<>(lengthOfMessage * Byte.SIZE);
        byte read = (byte) input.read();
        while (read != -1){
            for (byte i = Byte.SIZE - 1; i >= 0; i--) {
                bits.add(((read >> i) & 1) == 1);
            }
            read = (byte) input.read();
        }
        return bits;
    }

    //bits utils

    //private node class
    private class Node {
        int size;
        int symbol;
        Node left, right;
        MyBits code;

        Node(int symbol, int size) {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return symbol == node.symbol;
        }

        @Override
        public int hashCode() {
            return Objects.hash(symbol);
        }

        MyBits getCode() {
            return this.code;
        }

        void setCode(MyBits code) {
            this.code = code;
        }
    }

}