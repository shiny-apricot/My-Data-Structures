import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        File graphFile = new File("ExampleGraph.txt");

        Scanner graphScan = new Scanner(graphFile);

        Graph graph = new Graph(7000);

        while(graphScan.hasNext()){
            int firstId = graphScan.nextInt();
            int secondId = graphScan.nextInt();
            byte weight = graphScan.nextByte();

            graph.addEdge(secondId,firstId,weight);
        }
        System.out.println("#### ExampleGraph.txt has loaded... ####");


        /* CONSOLE */
        Scanner console = new Scanner(System.in);
        while(true){
            System.out.println();
            System.out.println("Enter the id you are looking for=  ");
            int lookingId = console.nextInt();
            if(lookingId == -1)
                break;
            graph.printEdgeListOf(lookingId);
        }

    }

//    public static String fixedStr(int number, int length) {
//        return String.format("%1$"+length+ "s", number);
//    }

}
