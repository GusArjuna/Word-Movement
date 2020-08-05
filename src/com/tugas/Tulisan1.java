package com.tugas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class Tulisan1 extends Thread{
    boolean supen=true;
    String inf="                  -------------------------How to Use this Program!---------------------------" +
            "\n* Start button for Start moving text" +
            "\n* Suspend button for pause text's Movement and resume it in start button(resume button)" +
            "\n* stop button for Stop this program" +
            "\n* delay button for delay movement text in 3 Seconds" +
            "\n* Exit button for exit this program!" +
            "\n*** THANK YOU FOR USING THIS PROGRAM , CODE BY M. TAUFAN MA'RUF - 1461900089***";
    Thread Oby1=new Thread(this);
	private JLabel Tulisan;
	private JFrame Frm;
	private int X=0;
	private int LebarTulisan,LebarFrame=500;
	private boolean Kanan=true;
    private JButton mulai=new JButton("Start");
    private JButton enteni=new JButton("Delay");
    private JButton sementara=new JButton("Suspend");
    private JButton mandeg=new JButton("Stop");
    private JButton selesai=new JButton("Exit");
    private JButton carae=new JButton("How To Use");
	Tulisan1(String Str){
		Tulisan=new JLabel(Str);
		LebarTulisan=Tulisan.getWidth();
		Frm=new JFrame("Menjalankan Teks dengan Thread");
		Frm.setLayout(null);
		Frm.setDefaultCloseOperation(Frm.EXIT_ON_CLOSE);
		Frm.add(Tulisan);
		Tulisan.setFont(new Font("ARIAL",Font.BOLD,36));
		Tulisan.setBounds(X,100,460,50);
		Frm.setSize(800,500);
		Frm.setVisible(true);
		adbuton();
		butonklik();
	    JOptionPane.showMessageDialog(null,inf);
	}
	public void butonklik(){
	    mulai.addActionListener(this::mulaiklik);
        selesai.addActionListener(this::selesaiklik);
        sementara.addActionListener(this::sementaraklik);
        mandeg.addActionListener(this::mandegklik);
        enteni.addActionListener(this::enteniklik);
        carae.addActionListener(this::caraeklik);
    }
    public void mulaiklik(ActionEvent e){
        if(supen){
	        Oby1.start();
        }else{
            Oby1.resume();
            supen=true;
            mulai.setText("Start");
        }
    }
    public void sementaraklik(ActionEvent e){
	    Oby1.suspend();
	    supen=false;
        mulai.setText("Resume");
    }
    public void mandegklik(ActionEvent e){Oby1.stop();}
    public void caraeklik(ActionEvent e){JOptionPane.showMessageDialog(null,inf);}
    public void enteniklik(ActionEvent e){
            synchronized (Oby1){
                try {
                    Oby1.suspend();
                    Oby1.wait(3000);
                }catch (InterruptedException I){}
                Oby1.resume();
            }
    }
    public void selesaiklik(ActionEvent e){System.exit(0);}
	public void adbuton(){
        Frm.add(mulai);
        mulai.setLocation(200, 250);
        mulai.setSize(100, 40);
        Frm.add(sementara);
        sementara.setLocation(310, 250);
        sementara.setSize(100, 40);
        Frm.add(mandeg);
        mandeg.setLocation(420, 250);
        mandeg.setSize(100, 40);
        Frm.add(enteni);
        enteni.setLocation(530, 250);
        enteni.setSize(100, 40);
        Frm.add(selesai);
        selesai.setLocation(360, 295);
        selesai.setSize(100, 40);
        Frm.add(carae);
        carae.setLocation(10, 400);
        carae.setSize(130, 40);
    }
	public void run(){
        while(true){
            LebarTulisan=Tulisan.getWidth();
            LebarFrame=Frm.getWidth();
            if(Kanan){
                if(X+LebarTulisan < LebarFrame){
                    //X=LebarFrame-LebarTulisan+20;
                    Kanan=true;
                    X++;
                }else
                    Kanan=false;
            }else{
                if(X > 0){
                    X--;
                    Kanan=false;
                }else
                    Kanan=true;
            }
            try{
                Thread.sleep(10);
            }catch(InterruptedException ie){}
            Tulisan.setBounds(X,100,LebarTulisan,50);
        }
	}
	public static void main(String Args[]){
        new Tulisan1("PEMROGRAMAN LANJUT");
	}
}