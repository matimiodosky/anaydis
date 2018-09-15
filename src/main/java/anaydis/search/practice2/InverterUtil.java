package anaydis.search.practice2;

import java.io.*;

class InverterUtil {

    private static final String filename = "/Users/matiasmiodosky/projects/austral/anaydis/src/main/resources/books/quijote.txt";
    private static final String emanelif = "/Users/matiasmiodosky/projects/austral/anaydis/src/main/resources/books/etojiuq.txt";

    private static void invertWords() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(emanelif));


        int i = 0;
        String line = bufferedReader.readLine();
        while(line != null){
            for (String part : line.split(" ")) {
                if (validate(part)) {
                    part = part.replace(".", "");
                    part = part.replace(",", "");
                    part = part.replace(";", "");
                    part = part.replace("-", "");
                    part = new StringBuilder(part).reverse().toString();
                    bufferedWriter.append(part).append(" ");
                }
            }
            bufferedWriter.append("\n");
            line = bufferedReader.readLine();
            i++;
        }
        System.out.println(i);
        bufferedWriter.close();
    }



    private static boolean validate(String part) {

        switch (part){
            case "" : return false;
            case "." : return false;
            case "," : return false;
            case " " : return false;
            case ";" : return false;
            case "-" : return false;
            case "\n" : return false;
            default: return true;
        }

    }

    public static void main(String[] args) {
        try {
            invertWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
