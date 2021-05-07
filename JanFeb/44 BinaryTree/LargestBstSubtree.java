import java.io.*;
import java.util.*;

public class Main {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }
  
  
  
  static class BSTPair{
        boolean isbst;
        int min;
        int max;
        int size;
        int lstdata;
        int lstsize;
    }

    public static BSTPair isBst(Node node){
        if(node == null){
            BSTPair nbst = new BSTPair();
            nbst.isbst = true;
            nbst.min = Integer.MAX_VALUE;
            nbst.max = Integer.MIN_VALUE;
            nbst.size = 0;
            nbst.lstsize = 0;
            return nbst;
        }
        
        BSTPair lbst = isBst(node.left);
        BSTPair rbst = isBst(node.right);
        
        BSTPair mbst = new BSTPair();
        
        mbst.min = Math.min(node.data, Math.min(lbst.min, rbst.min));
        mbst.max = Math.max(node.data, Math.max(lbst.max, rbst.max));
        
        if(node.data>lbst.max && node.data<rbst.min && lbst.isbst && rbst.isbst){
            mbst.isbst = true;
        }else{
            mbst.isbst = false;
        }
        
        mbst.size = lbst.size + rbst.size + 1;
        
        if(mbst.isbst){// true
            mbst.lstdata = node.data;
            mbst.lstsize = mbst.size;
        }else{// false
            if(lbst.lstsize >= rbst.lstsize){
                mbst.lstsize = lbst.lstsize;
                mbst.lstdata = lbst.lstdata;
            }else{
                mbst.lstsize = rbst.lstsize;
                mbst.lstdata = rbst.lstdata;
            }
        }
        return mbst;
    }

  
public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    Node root = construct(arr);
    
    // write your code here
    BSTPair result = isBst(root);
    System.out.println(result.lstdata + "@"+result.lstsize);
  }


}