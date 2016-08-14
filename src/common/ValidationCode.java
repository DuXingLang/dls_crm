package common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidationCode extends HttpServlet {
	// ͼ����֤����ַ����ϣ�ϵͳ�����������ַ�����ѡ��һЩ�ַ�����Ϊ��֤��
	private static String codeChars = "%#23456789abcdefghkmnpqrstuvwxyzABCDEFGHKLMNOPQRSTWXYZ";

	// ����һ�������ɫ(Color����)
	private static Color getRandomColor(int minColor, int maxColor) {
		Random random = new Random();
		// ��֤minColor��󲻻ᳬ��255
		if (minColor > 255) {
			minColor = 255;
		}
		// ��֤maxColor��󲻻ᳬ��255
		if (maxColor > 255) {
			maxColor = 255;
		}
		// ��ú�ɫ�������ɫֵ
		int red = minColor + random.nextInt(maxColor - minColor);
		int green = minColor + random.nextInt(maxColor - minColor);
		int blue = minColor + random.nextInt(maxColor - minColor);
		return new Color(red, green, blue);
	}

	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		//�����֤�뼯�ϵĳ���
		int charsLength=codeChars.length();
		//����3������ǹرտͻ���������Ļ�������������汾��ͬ�����֧�ֲ�ͬ������3����д
		response.setHeader("ragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setHeader("Expires","0");
		//����ͼ����֤��ĳ��Ϳ�
		int width=90,height=20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();     //�������������ֵ�Graphics����
		Random random = new Random();
		g.setColor(getRandomColor(180, 250));  //�������Ҫ������ɫ
		g.fillRect(0, 0, width, height);  		//��䱳��ͼƬ
		//���ó�ʼ����
		g.setFont(new Font("Times New Roman",Font.ITALIC,height));
		g.setColor(getRandomColor(180, 250));   //��������������ɫ
		//���ڱ������������ɵ���֤��
		StringBuilder validationCode = new StringBuilder();
		//��֤����������
		String [] fontNames={"Times New Roman","Book antiqua","Arial"};
		//�������3-5����֤��
		for (int i = 0; i < 3+random.nextInt(3); i++) {
			//������õ�ǰ��֤����ַ�����
			g.setFont(new Font(fontNames[random.nextInt(3)],Font.ITALIC,height));
			//�����õ�ǰ��֤����ַ�
			char codeChar = codeChars.charAt(random.nextInt(charsLength));
			validationCode.append(codeChar);
			//������õ�ǰ��֤���ַ�����ɫ
			g.setColor(getRandomColor(10, 100));
			//��ͼ���������֤���ַ���x��y����������ɵ�
			g.drawString(String.valueOf(codeChar), 16*i+random.nextInt(7), height-random.nextInt(6));
		}
		//��ȡHttpSession����
		HttpSession session =request.getSession();
		session.setMaxInactiveInterval(5*60);
		//����֤�뱣����session�����У�keyΪvalidation_code
		session.setAttribute("validation_code", validationCode.toString());
		g.dispose();			//�ر�Graphics����
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "JPEG", os);	//��jpeg��ʽ��ͻ��˷���ͼ����֤��
	}
}

