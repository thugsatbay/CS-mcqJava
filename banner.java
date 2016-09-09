import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
public class banner implements Runnable
{
String s=new String(" This is a banner ");
Thread mov;char ch;String temp;
JFrame fn;JLabel jl;
banner()
{
fn=new JFrame("BANNER");
fn.setVisible(true);fn.setLayout(new BorderLayout());
fn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
fn.setSize(200,200);
fn.setLayout(new FlowLayout());
jl=new JLabel(s);fn.add(jl);
mov=new Thread(this);
mov.start();
}
public void run()
{
for(;;)
{
try{
Thread.sleep(500);
}
catch (Exception e)
{System.out.println("exc == "+e);}
ch=s.charAt(0);
s=s.substring(1,17);
s=s+ch;
jl.setText(s);
}
}
public static void main(String as[])
{
new banner();
}
}