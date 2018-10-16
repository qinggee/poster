package com.comwer.poster;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Poster {
	private static Logger logger = LoggerFactory.getLogger(Poster.class);
	
	private JFrame frame;
	private JTextField img_input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Poster window = new Poster();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Poster() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 663, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("中文");
		label.setBounds(10, 10, 54, 15);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("英文");
		label_1.setBounds(10, 121, 54, 15);
		frame.getContentPane().add(label_1);

		JTextArea zh_text = new JTextArea();
		zh_text.setText("我们笑着说再见，却深知再见遥遥无期。");
		zh_text.setLineWrap(true);
		zh_text.setBounds(79, 6, 558, 85);
		frame.getContentPane().add(zh_text);

		JTextArea en_text = new JTextArea();
		en_text.setText("We laughed and kept saying see you soon, but inside we both knew we'd never see each other again. ");
		en_text.setLineWrap(true);
		en_text.setBounds(79, 117, 558, 101);
		frame.getContentPane().add(en_text);

		JLabel label_2 = new JLabel("图片路径");
		label_2.setBounds(10, 251, 54, 15);
		frame.getContentPane().add(label_2);

		img_input = new JTextField();
		img_input.setBounds(79, 248, 558, 21);
		frame.getContentPane().add(img_input);
		img_input.setColumns(10);

		JTextArea poster_path = new JTextArea();
		poster_path.setLineWrap(true);
		poster_path.setBounds(79, 387, 558, 71);
		frame.getContentPane().add(poster_path);

		JLabel lblNewLabel = new JLabel("海报路径");
		lblNewLabel.setBounds(10, 406, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JButton button = new JButton("生成海报");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String zh = zh_text.getText();
					if (StringUtils.isEmpty(zh)) {
						throw new Exception("中文不能为空");
					}
					String en = en_text.getText();
					String imgURL = img_input.getText();

					// 获取词霸的图片
					if (StringUtils.isEmpty(imgURL)) {
						String formatDate = DateFormatUtils.format(new Date(), "yyyyMMdd");
						imgURL = "http://cdn.iciba.com/news/word/big_" + formatDate + "b.jpg";
					}

					String filePath = QrcodeUtils.createQrcodeFile(zh, en, imgURL);
					poster_path.setText(filePath);
				} catch (Exception e1) {
					logger.error(e1.getMessage());
					logger.error(e1.getMessage(), e1);
					
					poster_path.setText(e1.getMessage());
				}
			}
		});
		button.setBounds(238, 298, 93, 23);
		frame.getContentPane().add(button);
	}
}