import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
public class answer implements Runnable
{
int qu;
JFrame fu;JLabel ll; Checkbox cb,cb1,cb2,cb3,cb4; String s1; JButton nxt,prvs,jmp,dne,pse,mrk,go,hlp; JTextField jf; Choice sel;//for answering frame
private boolean val[][];private int use; // variables for complete usage
JFrame tm;JLabel jl;Thread mov; int min; String s12;Boolean clk=true;
answer()
{
//to get no of questions
	do{
	try
	{
	String iv = JOptionPane.showInputDialog("Please input the no. of questions betwwen 10 and 999");
	if(iv==null){use=60;}// works on cancel and cross to set question to 60
	else {use=Integer.parseInt(iv);}
	}
	catch(Exception e)
	{System.out.println(e);use=0;}
	}while((use<10)||(use>=1000));// question bw 10 and 999
	Object[] possibleValues = { "120 min,2 Hours", "150 min,2:30 Hours", "180 min,3 Hours" };
    Object selectedValue = JOptionPane.showInputDialog(null,"Choose one", "Enter duration",JOptionPane.INFORMATION_MESSAGE, null,possibleValues, possibleValues[0]);
	s12=selectedValue.toString();
	System.out.println(s12);
			min=Integer.parseInt(s12.substring(0,3));
	min*=60;min+=5;
	//check array value
System.out.println("\nvalue="+use+"\n");
//declaration
val=new boolean[use][5];
//initialization of all answers to false
for(int i=0;i<use;++i)
{
for(int j=0;j<=4;++j)
{
val[i][j]=false;
}
}                     //frame settings begin, of answers
fu=new JFrame("Answer Sheet");
fu.setResizable(false);
fu.setVisible(true);
fu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
fu.setSize(255,225);
fu.setLayout(new FlowLayout(FlowLayout.CENTER));
fu.setFont(new Font("TimesRoman",Font.BOLD,22));
					//frame settings end
//frame settings of time begin
tm=new JFrame("Time");
jl=new JLabel(""+min);
tm.setVisible(true);
tm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);tm.setResizable(false);
tm.setSize(200,75);
tm.setLayout(new FlowLayout());
tm.add(jl);
//thread to manage event quene
mov=new Thread(this);//this has to be explicitly defined
mov.start();
s1="1)";qu=1; 
// checkbox a,b,c,d,e
ll=new JLabel(("Question"+s1)); fu.add(ll);
cb=new Checkbox("A"); fu.add(cb);
cb1=new Checkbox("B"); fu.add(cb1);
cb2=new Checkbox("C"); fu.add(cb2);
cb3=new Checkbox("D"); fu.add(cb3);
cb4=new Checkbox("E"); fu.add(cb4);
// creation of button and text field
hlp=new JButton("Help"); 
hlp.setActionCommand(hlp.getText());
prvs=new JButton("Previous"); 
prvs.setActionCommand(prvs.getText());
nxt=new JButton("Next"); 
nxt.setActionCommand(nxt.getText());
jf=new JTextField(10);
jmp=new JButton("Jump");
jmp.setActionCommand(jmp.getText());
dne=new JButton("Submit");
dne.setActionCommand(dne.getText());
pse=new JButton("Pause");
pse.setActionCommand(pse.getText());
mrk=new JButton("Mark");
mrk.setActionCommand(mrk.getText());
go=new JButton("Go to Mark");
go.setActionCommand(go.getText());
sel=new Choice();
// sequence addition af elements
fu.add(prvs);fu.add(nxt);fu.add(jf);fu.add(jmp);fu.add(mrk);
JTextField rgh=new JTextField(20);fu.add(rgh);
// actions
fu.add(sel);fu.add(go);
fu.add(pse);fu.add(dne);fu.add(hlp);
fu.add(rgh);
nxt.addActionListener(new gur()); prvs.addActionListener(new gur());
jmp.addActionListener(new gur());dne.addActionListener(new beg());
pse.addActionListener(new gur());mrk.addActionListener(new gur());
go.addActionListener(new gur());hlp.addActionListener(new gur());
  }
  public void run() {
//(testing code)System.out.println("obj created");
while(true)
{
System.out.println("\n"+clk);
if(clk){
try{
Thread.sleep(1000);//1 sec
}
catch(Exception e)
{
System.out.println("exception = "+e);
}
System.out.println(min);
jl.setText(""+(min)+"sec,"+((int)(min/60))+" min");
if(clk)
min-=1;
System.out.println("updated"+clk);
if((min==0)&&(clk))
{ clk=false;
result();
}
}
}} //action class starts
  class gur implements ActionListener {
    public void actionPerformed(ActionEvent ea) 
	{
      val[qu-1][0]=cb.getState();
	  val[qu-1][1]=cb1.getState();
	  val[qu-1][2]=cb2.getState();
	  val[qu-1][3]=cb3.getState();
	  val[qu-1][4]=cb4.getState();
	  System.out.println("change occured \n");
		if(ea.getActionCommand().equals(prvs.getText()))
		{
			--qu;
		}
		else if(ea.getActionCommand().equals(nxt.getText()))
		{
			++qu;
		}
		else if(ea.getActionCommand().equals(pse.getText()))
		{
			if(clk==false) {clk=true;}
			else clk=false;
		}
		else if(ea.getActionCommand().equals(go.getText()))
		{
			try{
			qu=Integer.parseInt(sel.getSelectedItem());System.out.println("----------------------");
			}
			catch(Exception e)
			{ System.out.println("---------panga-------------");}
		}
		else if(ea.getActionCommand().equals(mrk.getText()))
		{ System.out.println("help executed");sel.add(""+qu); sel.select(""+qu);}
		else if(ea.getActionCommand().equals(hlp.getText()))
		{
			JOptionPane.showMessageDialog (null, "Answer Sheet App is a © version of Gurleen Singh, it may be distributed as a freeware.For suggestion,\nEmailid:thugsatbay@gmail.com" , "Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else 
		{
			try{
			int l=Integer.parseInt(jf.getText());jf.setText("");
			if((l>=1)&&(l<=use))qu=l;
			}
			catch (Exception e)
			{jf.setText("");}
		}
	//++qu;
	  if(qu==0)
	  {qu=use;}
	  else if(qu==(use+1))
	  {qu=1;}
	  s1="";
	  s1=qu+s1+')';
	  ll.setText(("Question"+s1));
	  
	  cb.setState(val[qu-1][0]);
	  cb1.setState(val[qu-1][1]);
	  cb2.setState(val[qu-1][2]);
	  cb3.setState(val[qu-1][3]);
	  cb4.setState(val[qu-1][4]);
	
	}
  }

  //result class to display results
  class beg implements ActionListener
  {
  public void actionPerformed(ActionEvent ea)
  {
  int j=JOptionPane.showConfirmDialog(null,"Are you Sure you want to submit", "WARNING!!!", JOptionPane.YES_NO_OPTION);
  if (j==JOptionPane.YES_OPTION)
  {result();}
  }
  }
  
  public void result()
  {
  String out="",Bout="";char ch;clk=false;
	  val[qu-1][0]=cb.getState();
	  val[qu-1][1]=cb1.getState();
	  val[qu-1][2]=cb2.getState();
	  val[qu-1][3]=cb3.getState();
	  val[qu-1][4]=cb4.getState();
	fu.dispose();tm.dispose();
JFrame fn=new JFrame("Result:"+(Integer.parseInt(s12.substring(0,3))-(min/60))+ " min");
fn.setResizable(false);
fn.setVisible(true);
fn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
fn.setSize(250,500);
	for(int i=0;i<use;++i)
	{
	out+=(i+1)+(")=");
	for(int j=0;j<5;++j)
	{
	if(val[i][j])
	{ch=((char)(j+65));out+=ch;out+=" ";}
	}
		out+="\n\n";Bout+=out;out="";
	}
TextArea text = new TextArea(Bout, use, 15);fn.add(text);
  text.setEditable(false);
  }
  
  //void main with event thread calling can also use another thread runnable method specially for swing swing utilities  
public static void main(String ar[])
{
SwingUtilities.invokeLater(new Runnable() {
public void run() {
new answer();
}
});}
}