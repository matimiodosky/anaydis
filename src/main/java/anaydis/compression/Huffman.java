package anaydis.compression;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class Huffman implements Compressor{

    private static final char SCAPE_CHARACTER = (char) 27;


    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        /*
        * encoding structure: [amount of codes] {(code)(symbol)} {coded symbols} {scpae character}
        * */

        HashMap<Integer, Integer> counts = new HashMap<>();
        int read = input.read();

        //count simple nodes
        while(read != -1){
            Integer result = counts.put(read, 1);
            if (result != null){
                counts.put(read, ++result);
            }
            read = input.read();
        }

        //build simple nodes
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.size));
        for (Integer symbol : counts.keySet()) {
            priorityQueue.add(new Node(symbol, counts.get(symbol)));
        }

        //build and complex nodes
        while(priorityQueue.size() > 1){
            Node left = priorityQueue.poll(), right = priorityQueue.poll();
            priorityQueue.add(new Node(left.size + right.size, left, right));
        }

        //assign codes and filter simple nodes
        Node head = priorityQueue.poll();
        HashMap<Integer, Byte> codes = new HashMap<>();
        if (head.size == 1){
            head.code.add(true);
        }
        else {
            //iterates tree
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.empty()) {
                Node current = stack.pop();
                if (current.left == null && current.right == null){
                    codes.put(current.symbol, listToByte(current.code));
                }
                if (current.left != null){
                    current.left.code.addAll(current.code);
                    current.left.code.add(false);
                    stack.push(current.left);
                }
                if (current.right != null){
                    current.right.code.addAll(current.code);
                    current.right.code.add(true);
                    stack.push(current.right);
                }
            }
        }

        //write output header
        output.write(codes.size()); //amount of codes
        for (Integer symbol : codes.keySet()) {
            output.write(symbol); //writes code
            output.write(codes.get(symbol)); //writes symbol
        }

        //write output body
        input.reset();
        read = input.read();
        while (read != -1){
            output.write(codes.get(read));
            read = input.read();
        }
        output.write(SCAPE_CHARACTER);

        //escribir el codigo como una sola cadena de bits y mandar eso en bytes al stream.
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

    }

    private static byte listToByte(List<Boolean> list){

        byte b = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)){
                b |= 1 << i;
            }else {
                b *= 2;
            }
        }
        return b;
    }

    private class Node{
        int size;
        int symbol;
        Node left, right;
        ArrayList<Boolean> code;

        Node(int symbol, int size) {
            this.size = size;
            this.symbol = symbol;
            this.code = new ArrayList<>();
        }

        Node(int size, Node left, Node right) {
            this.size = size;
            this.left = left;
            this.right = right;
            this.code = new ArrayList<>();
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
    }

    public static void main(String[] args) throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(readFile("/Users/matiasmiodosky/projects/austral/anaydis/src/main/resources/books/quijote.txt").getBytes());

        ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        Huffman h = new Huffman();
        h.encode(input, encoded);
        System.out.println();

    }

    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader (file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

}
