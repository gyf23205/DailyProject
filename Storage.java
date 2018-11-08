package guoyifan.advancedStudentSystem.beans;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Storage {
	public static List<Students> dbs=new LinkedList<Students>();
	public static int point=-1;
	
	public static void readin() throws IOException,FileNotFoundException {
		InputStream is0 = new FileInputStream("studentdata.txt");
		 String line0; // 用来保存每行读取的内容
	        BufferedReader reader0 = new BufferedReader(new InputStreamReader(is0));
	        line0 = reader0.readLine(); // 读取第一行
	        while (line0 != null) { // 如果 line 为空说明读完了
		        String[] strings=line0.split(" ");
		        Students temp=new Students();
		        temp.setName(strings[0]);
		        temp.setId(strings[1]);
		        temp.setGrade(Double.parseDouble(strings[2]));
		        dbs.add(temp);
		        point+=1;
	            line0 = reader0.readLine(); // 读取下一行
	        }
	}
	
	public static void writeBack() throws IOException,FileNotFoundException {
		OutputStream output=new FileOutputStream("studentdata.txt");
		BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(output));
			for(Students s:dbs) {
				writer.write(s.getName()+" ",0,s.getName().length()+1);
				writer.write(s.getId()+" ",0,s.getId().length()+1);
				String temp=String.valueOf(s.getGrade());
				writer.write(temp+"\n",0,temp.length()+1);
			}
		writer.flush();
		writer.close();
	}
	 public static void setFrame() {
		 JFrame frame=new JFrame("学生信息管理系统");//frame初始化
			frame.setBounds(200, 200, 800, 600 );
			frame.setVisible(true);
			//frame.setLayout(new BorderLayout());
			frame.addWindowListener(new WindowListen());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Panel panel=new Panel();
			JButton button1=new JButton("删除");//生成按钮
			button1.setBounds(210, 480, 100, 50);
			JButton button2=new JButton("添加");
			button2.setBounds(210, 400, 100, 50);
			JButton button3=new JButton("前一个");
			button3.setBounds(350, 400, 100, 50);
			JButton button4=new JButton("后一个");
			button4.setBounds(490, 400, 100, 50);
			JButton button5=new JButton("更新");
			button5.setBounds(630, 400, 100, 50);
			
			frame.add(button1);//添加按钮
			frame.add(button2);
			frame.add(button3);
			frame.add(button4);
			frame.add(button5);
			
			TextField textbox1=new TextField(40);//添加提示
			textbox1.setBounds(100,100,600,35);
			JLabel lable1=new JLabel("姓名");
			panel.add(lable1);
			lable1.setBounds(70,100,100,40);
			//frame.add(lable1);
			frame.add(textbox1);
			
			TextField textbox2=new TextField(40);//id文本框和提示
			textbox2.setBounds(100,200,600,35);
			JLabel lable2=new JLabel("学号:");
			panel.add(lable2);
			lable2.setBounds(70,200,100,40);
			//frame.add(lable2);
			frame.add(textbox2);
			
			TextField textbox3=new TextField(40);//成绩文本框和提示
			textbox3.setBounds(100,300,600,35);
			JLabel lable3=new JLabel("成绩");
			panel.add(lable3);
			lable3.setBounds(70,300,100,40);
			//frame.add(lable3);
			frame.add(textbox3);
			
			frame.add(panel);//提示Panel加入
			panel.setBounds(0,0,100,600);
			
			if(!Storage.dbs.isEmpty()) {//初始化显示
				textbox1.setText(dbs.get(Storage.point).name);
				textbox2.setText(dbs.get(Storage.point).id);
				textbox3.setText(String.valueOf(Storage.dbs.get(Storage.point).grade));
			}
			button1.addActionListener(new ActionListener() {//删除		
				@Override
				public void actionPerformed(ActionEvent e) {
					Students temp=new Students();
					temp.setId(textbox2.getText());
					Storage.dbs.remove(temp);
					if(Storage.point!=Storage.dbs.size()-1)
						point-=1;
					if(!Storage.dbs.isEmpty()) {
						textbox1.setText(dbs.get(Storage.point).name);
						textbox2.setText(dbs.get(Storage.point).id);
						textbox3.setText(String.valueOf(Storage.dbs.get(Storage.point).grade));
					}
					else {
						textbox1.setText("");
						textbox2.setText("");
						textbox3.setText("");
					}
				}
				
			});
			button2.addActionListener(new ActionListener() {//添加
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Students temp=new Students();
					temp.setName(textbox1.getText());
					temp.setId(textbox2.getText());
					temp.setGrade(Double.parseDouble(textbox3.getText()));
					if(Storage.dbs.indexOf(temp)==-1) {
						Storage.dbs.add(temp);
						Storage.point+=1;
						textbox1.setText(dbs.get(Storage.point).name);
						textbox2.setText(dbs.get(Storage.point).id);
						textbox3.setText(String.valueOf(Storage.dbs.get(Storage.point).grade));
						JOptionPane.showMessageDialog(null, "成功更改学生的信息！", "成功更改学生的信息！", JOptionPane.PLAIN_MESSAGE);

					}
					else {
						JOptionPane.showMessageDialog(null, "没有更改学生的信息", "没有更改学生的信息", JOptionPane.PLAIN_MESSAGE);

					}
					for(Students s:dbs)
						System.out.println(s.toString());
				}
			});
			button3.addActionListener(new PreLintener(textbox1,textbox2,textbox3));
			button4.addActionListener(new BackListener(textbox1,textbox2,textbox3));
			button5.addActionListener(new ActionListener() {//更新
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Students temp=Storage.dbs.get(point);
					temp.setName(textbox1.getText());
					temp.setId(textbox2.getText());
					temp.setGrade(Double.parseDouble(textbox3.getText()));
					JOptionPane.showMessageDialog(null, "更新了"+textbox2.getText()+"号学生的信息", 
							"更新了"+textbox2.getText()+"号学生的信息", JOptionPane.PLAIN_MESSAGE);
					for(Students s:dbs)
						System.out.println(s.toString());
				}
			});
	 }
}
