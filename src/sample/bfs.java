/*input
4 4
0 1
0 2
1 3
2 4
1
2
3
4
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;




public class Solution {
    public void createEdge(Node a, Node b) {
        a.adj.add(b);
    }

    public void allotgroup(Node root,int[] dist) {
        queue Q = new queue();
        Q.insert(root);
        dist[root.data] = 0;
        while(Q.front!=null) {
            if (!Q.front.visited) {
                Q.front.visited = true;
                for (int i=0;i<Q.front.adj.size();i++) {
                    if (!Q.front.adj.get(i).visited && Q.front.adj.get(i).next == null) {
                        Q.insert(Q.front.adj.get(i));
                        dist[Q.front.adj.get(i).data] = Math.max(dist[Q.front.data],Q.front.adj.get(i).data);
                    }
                }
                Node p = Q.front;
                Q.remove();
                p.next = null;
            }
            else {
                Q.remove();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        Solution ob = new Solution();
        int N = Reader.nextInt()+1;
        Node[] bfs = new Node[N];
        for (int i=0;i<N;i++){
            bfs[i] = new Node(i);
        }
        int Q = Reader.nextInt();
        for (int i=0;i<N-1;i++){
            int temp1 = Reader.nextInt();
            int temp2 = Reader.nextInt();
            // System.out.println(temp1+" "+temp2);
            ob.createEdge(Solution[temp1],Solution[temp2]);
        }

        Solution CLK = new Solution();
        int[] dist = new int[N+1];
        for (int i=0;i<N+1;i++){
            dist[i] = -1;
        }
        CLK.allotgroup(Solution[0],dist);
        // System.out.println(Arrays.toString(dist));
        // System.out.println(Q);
        for (int i=0;i<Q;i++){
            int D = Reader.nextInt();
            System.out.println(dist[D]);    
        }
    }
}
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
}

class Node {
    ArrayList<Node> adj =new ArrayList<Node>();
    int data;
    Node next;
    boolean visited;
    public Node() {
        this.data = 0;
        this.visited = false;
        this.next = null;
    }
    public Node(int data) {
        this.data = data;
        this.visited = false;
        this.next = null;
    }
}
class queue{
    Node front = null;
    Node end = null;
    public void insert(Node x) {
        if (front==null) {
            front = x;
            end = x;
        }
        else {
            end.next = x;
            end = end.next;
        }
    }
    public void remove() {
        front = front.next;
    }
}
