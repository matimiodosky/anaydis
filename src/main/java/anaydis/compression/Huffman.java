package anaydis.compression;

import anaydis.immutable.Node;
import anaydis.search.RandomizedTreeMap;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class Huffman implements Compressor{

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        /*
        * encoding structure: [amount of codes] {[length of code](code)(symbol)} {codes}
        * */
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        int read = input.read();
        //add simple nodes
        while(read != -1){
            priorityQueue.add(new Node(1, read));
            read = input.read();
        }
        //build and add complex nodes
        while(priorityQueue.size() > 1){
            Node left = priorityQueue.poll(), right = priorityQueue.poll();
            priorityQueue.add(new Node(left.size + right.size, left, right));
        }

        //assign codes and filter valid nodes
        Node head = priorityQueue.poll();
        ArrayList<Node> simpleNodes = new ArrayList<>(head.size);
        if (head.size == 1){
            head.code.add(true);
        }
        else {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.empty()) {
                Node current = stack.pop();
                if (current.size == 1){
                    simpleNodes.add(current);
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
        output.write(simpleNodes.size()); //amount of codes
        for (Node simpleNode : simpleNodes) {
            output.write(simpleNode.code.size());//length of code
            for (Boolean b : simpleNode.code) {
                output.write(b? 1: 0);
            }
            output.write(simpleNode.elem);//symbol
        }
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

    }

    private class Node implements Comparable<Node>{
        int size;
        int elem;
        Node left, right;
        ArrayList<Boolean> code;

        Node(int size, int elem) {
            this.size = size;
            this.elem = elem;
            this.code = new ArrayList<>();
        }

        Node(int size, Node left, Node right) {
            this.size = size;
            this.left = left;
            this.right = right;
            this.code = new ArrayList<>();
        }

        @Override
        public int compareTo(@NotNull Node o) {
            return Integer.compare(this.size, o.size);
        }
    }

    public static void main(String[] args) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("12345".getBytes());
        new Huffman().encode(byteArrayInputStream, new ByteArrayOutputStream());
    }
}
