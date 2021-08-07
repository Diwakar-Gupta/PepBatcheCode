import java.io.*;
import java.util.*;

public class Main {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr, int wt) {
         this.src = src;
         this.nbr = nbr;
         this.wt = wt;
      }
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      // write your code here
        boolean visited[] = new boolean[vtces];
        int group[] = new int[vtces];// even odd 0
      boolean possible = true;
      for(int i=0;i<vtces;i++){
          if(visited[i] == false){
              boolean ans = isPossible(graph, i, visited, group);
              if(ans == false){
                  possible = false;
                  break;
              }
          }
      }
      System.out.println(possible);
   }
   static boolean isPossible(ArrayList<Edge>graph[], int src, boolean visited[], int group[]){
       
       ArrayDeque<Pair> q = new ArrayDeque<>();
       q.add(new Pair(src, 2));
       
       while(q.size() > 0){
           // r m a
           Pair p = q.remove();
           if(visited[p.src] == true){
               // cycle 
               boolean isInEven = group[p.src]%2==0;
               boolean hasToBe = p.group%2==0;
               if(isInEven != hasToBe)return false;
               continue;
           }else{
               visited[p.src] = true;
               group[p.src] = p.group;
           }
           
           for(Edge e: graph[p.src]){
               if(visited[e.nbr] == false){
                   q.add(new Pair(e.nbr, p.group+1));
               }
           }
       }
       return true;
   }
   
   static class Pair{
       int src;
       int group;
       Pair(int s, int p){
           src = s;
           group = p;
       }
   }
}