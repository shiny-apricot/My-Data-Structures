import java.io.IOException;

class Graph {

    Node[] friendList;

    Graph(int size) {
        friendList = new Node[size];

        for (int i = 0; i < size; i++)
            friendList[i] = new Node();
    }

    public void addEdge(int from, int to, int weight) {
        Edge edge = new Edge(from, to, weight);
        friendList[from].list.add(edge);
    }


    /* Does this graph include (from,to) ? */
    public boolean isInclude(int from, int to){
        LinkedList<Edge> list = friendList[from].list;
        for (int i = 0; i < list.length(); i++)
        {
            int destination = list.get(i).destination;
            if(destination == to)
                return true;
        }
        return false;
    }

    public int findCommonFriend(LinkedList<Edge> sourceNode, LinkedList<Edge> destinationNode, boolean appendMode) throws IOException
    {

        int commonFriendCount = 0;

        for (int j = 0; j < destinationNode.length(); j++) {
            int destinationIndex = destinationNode.get(j).destination;
            for (int k = 0; k < sourceNode.length(); k++) {
                int sourceIndex = sourceNode.get(k).destination;
                if(sourceIndex == destinationIndex ){
                    commonFriendCount++;
                }
            }
        }

        return commonFriendCount;
    }

    public void printEdgeListOf(int index){
        LinkedList<Edge> edgeList = friendList[index].list;
        for (int i = 0; i < edgeList.length(); i++) {
            Edge edge = edgeList.get(i);
            System.out.println("("+edge.source+","+edge.destination+")   "+edge.weight);
        }
    }

}

