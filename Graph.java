import java.io.*;
import java.util.*;

class Graph
{
   public static class Vertex
   {
   public int id;
   public String name;
   public static int count=0;
   public Vertex(String n)
   {
   id=count++;
   name=n;
   }
   }

   public static class Edge implements Comparable<Edge>
   {
   public Vertex start,end;
   public int weight;
   public Edge(Vertex a,Vertex b,int w)
   {
   start=a;
   end=b;
   weight=w;
   }
   @Override
   public int compareTo(Edge second)
   {
   if(weight<second.weight)
   return -1;
   else if(weight>second.weight)
   return 1;
   else
   return 0;
   }
   }

public int NUM_VERTICES;
public ArrayList<Edge> edges;

public Graph(int a,ArrayList<Edge> b)
{
NUM_VERTICES=a;
edges=b;
}

public ArrayList<Edge> getEdges()
{
return edges;
}

public ArrayList<Edge> kruskal()
{
//Algorithm as given in the book

DisjSets ds = new DisjSets(NUM_VERTICES);
PriorityQueue<Edge> pq=new PriorityQueue<Edge>(getEdges());
ArrayList<Edge> mst=new ArrayList<>();
while(mst.size()!=NUM_VERTICES-1)
{
   Edge e=pq.remove();
   int uset= ds.find(e.start.id);
   int vset= ds.find(e.end.id);
   if(uset!=vset)
   {
   mst.add(e);
   ds.union(uset,vset);
   }
}
return mst;
}

public static Vertex getVertex(String a,Vertex v[])
{
for(Vertex z:v)
{
if(z.name.equals(a))
return z;
}
return null;
}

public static void main(String args[])throws Exception
{
String filename="assn9_data.csv";
File file=new File(filename);
Scanner input=new Scanner(file);
input.useDelimiter("\n");
ArrayList<String[]> words=new ArrayList<String[]>();

while(input.hasNext())
{
String data=input.next();
String[] temp=data.trim().split(",");
//System.out.println(temp[0].trim());
words.add(temp);
}
int num_vertices=words.size();
//System.out.println(num_vertices);
Vertex v[]=new Vertex[num_vertices];
for(int i=0;i<num_vertices;i++)
v[i]=new Vertex(words.get(i)[0]);

ArrayList<Edge> elf= new ArrayList<Edge>();
for(int i=0;i<num_vertices;i++)
{
   for(int j=0;j<words.get(i).length-1;j=j+2)
   {
   Edge a=new Edge(getVertex(words.get(i)[0],v),getVertex(words.get(i)[j+1],v),Integer.parseInt(words.get(i)[j+2]));
   elf.add(a);
   }
   
}

System.out.println("Minimum Spanning Tree by Kruskal's Algorithm");
System.out.println();
Graph g=new Graph(num_vertices,elf);
ArrayList<Edge> mst=g.kruskal();
int sum=0;
for(Edge e: mst)
{
System.out.println(e.start.name+" "+e.end.name+" "+e.weight);
sum=sum+e.weight;
}
System.out.println();
System.out.println("Sum of Weights:"+sum);


/*Vertex v1=new Vertex("Hi");
Vertex v2=new Vertex("Hello");
Vertex v3=new Vertex("Hiya");
Edge a=new Edge(v1,v2,5);
Edge b=new Edge(v2,v3,10);
Edge c=new Edge(v1,v3,15);
ArrayList<Edge> elf= new ArrayList<Edge>();
elf.add(a);
elf.add(b);
elf.add(c);
Graph g=new Graph(3,elf);
ArrayList<Edge> mst=g.kruskal();
for(Edge e: mst)
System.out.println(e.start.name+" "+e.end.name+" "+e.weight);
*/
}
}