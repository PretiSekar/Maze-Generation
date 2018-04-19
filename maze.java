import java.util.*;

public class maze
{
private static int rows;
private static int columns;
private static int total;
public static class cell
{
int x;
int y;
   public cell(int a,int b)
   {
   x=(a>b)?a:b;                
   y=(a>b)?b:a;
   }
   public boolean equals(Object o)
   {
   if(o!=null && o instanceof cell)
   {
   cell e =(cell)o;
   if(x == e.x && y== e.y)
   return true;
   }
   return false;
   }
}
public static boolean checkright(int i)
{
if(((i+1)%rows)==0)
return true;
else
return false;
}
public static boolean checkbottom(int i)
{
if((total-rows)<(i+1) && (i+1)<=total)
return true;
else
return false;
}
public static void main(String args[])
{
Scanner s=new Scanner(System.in);
System.out.print("Enter the number of rows: ");
rows=s.nextInt();
System.out.print("Enter the number of columns: ");
columns=s.nextInt();
total=rows*columns;
DisjSets ds = new DisjSets(total);
ArrayList<cell> neighbourcells=new ArrayList<cell>();
ArrayList<cell> temp=new ArrayList<cell>();
HashSet<Integer> h=new HashSet<Integer>();
for(int i=0;i<total;i++)
h.add(ds.find(i));
for(int i=0;i<total;i++)
{
   if(checkright(i)!=true)
      temp.add(new cell(i,i+1));
   if(checkbottom(i)!=true)
      temp.add(new cell(i,i+rows));
}
Random r=new Random();
while(h.size()>1)
{
   cell randomcell=temp.remove(r.nextInt(temp.size()));
   int u=ds.find(randomcell.x);
   int v=ds.find(randomcell.y);
      if(u != v)
        ds.union(u,v);
      else
        neighbourcells.add(randomcell);
   h.clear();
   for(int i=0;i<total;i++)
   h.add(ds.find(i));
}
for(cell a:temp)
   neighbourcells.add(a);
System.out.print(" ");
for(int i=0;i<rows;i++)
   System.out.print("- ");
System.out.println();
int i=0;
while(i<columns)
{  
   int j=0;
   if(i==0)
      System.out.print(" ");
   else
      System.out.print("|");
   while(j<rows)
   { 
      if((i*rows+j)+1 == total)
         System.out.print(" ");         
      else if(checkright(i*rows+j)|| neighbourcells.contains(new cell(i*rows+j,(i*rows+j)+1)))
         System.out.print(" |");
      else
         System.out.print("  ");
      j++;
   }
   System.out.println();
   System.out.print(" ");
   j=0;
   while(j<rows)
   {
      if(checkbottom(i*rows+j) || neighbourcells.contains(new cell(i*rows+j,(i*rows+j)+rows)))
         System.out.print("- ");
      else
         System.out.print("  ");
      j++;
   }
   System.out.println();
   i++;
}
}
}