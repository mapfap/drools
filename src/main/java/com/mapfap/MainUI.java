package com.mapfap;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.kie.internal.runtime.StatefulKnowledgeSession;

@SuppressWarnings("restriction")
public class MainUI {
	public static void main(String[] args) {
		
		JFrame jFrame = new JFrame();
		
		final JTextField input = new JTextField(50);
		final JTextField output = new JTextField(100);
		
		Font myFont = new Font("Ayuthaya", Font.BOLD, 12);
		input.setFont(myFont);
		output.setFont(myFont);
		
		jFrame.add(input);
		jFrame.add(output);
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(new GridLayout(2,1));
		jFrame.pack();
		jFrame.setVisible(true);
		
		input.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            		StatefulKnowledgeSession kSession = ExpertSystem.createExpertSystem();
            		String request = input.getText();
            		Rice rice = createRice(request);
            		kSession.insert(rice);
            		kSession.fireAllRules();
            		kSession.dispose();
            		output.setText(join(rice.getResultList()));
            		System.out.println(rice.getResultList());
            	}
            }
            public void keyTyped(KeyEvent e) { }
            public void keyPressed(KeyEvent e) { }
        });
	}

	protected static String join(List<String> resultList) {
		String out = "";
		for (String x: resultList) {
			out += x + ", ";
		}
		return out;
	}

	protected static Rice createRice(String request) {
		Rice r = new Rice();
//		for (String x: request.split(",")) {
//			r.setFactor(x, "true");
//		}
//		r.setFactor("ภาคที่ปลูกได้", "อีสาน");
		r.setFactor("ภาคที่ปลูกได้", region(request));
		return r;
	}

	public static String region(String s) {
		char c = s.charAt(0);
		if(c == '1')
			return "กลาง";	
		if(c == '2')
			return "ตะวันออก";
		if(c == '3')
			return "อีสาน";
		if(c == '4')
			return "อีสาน";
		if(c == '5')
			return "เหนือตอนบน";
		if(c == '6')
			return "เหนือตอนล่าง";
		if(c == '7')
			return "ตะวันตก";
		if(c == '8')
			return "ใต้";
		if(c == '9')
			return "ใต้";
		return "";
		
//		if(c == '1')
//			return "กลาง";	
//		if(c == '2')
//			return "ตะวันออก";
//		if(c == '3')
//			return "อีสานตอนล่าง";
//		if(c == '4')
//			return "อีสานตอนบน";
//		if(c == '5')
//			return "เหนือตอนบน";
//		if(c == '6')
//			return "เหนือตอนล่าง";
//		if(c == '7')
//			return "ตะวันตก";
//		if(c == '8')
//			return "ใต้ตอนบน";
//		if(c == '9')
//			return "ใต้ตอนล่าง";
//		return "";		
	}
}
