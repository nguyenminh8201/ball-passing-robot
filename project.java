package project;

import java.awt.Color;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jgl.GL;
import jgl.GLCanvas;

public class project extends GLCanvas{
	// create checkerboard texture
		private static final int checkImageWidth = 256;
		private static final int checkImageHeight = 256;
		private byte imageCenter[][][] = new byte[checkImageWidth][checkImageHeight][4];
		private byte imageRight[][][] = new byte[checkImageWidth][checkImageHeight][4];
		private byte imageLeft[][][] = new byte[checkImageWidth][checkImageHeight][4];
		private byte imageTop[][][] = new byte[checkImageWidth][checkImageHeight][4];
		private byte imageBottom[][][] = new byte[checkImageWidth][checkImageHeight][4];
		private byte imageFront[][][] = new byte[checkImageWidth][checkImageHeight][4];

		private int[] texName = new int[6];
		private static float r;
		
		private static double x, y;
		private static double a=0, shoulder1 = 0, shoulder2=0, joint=0, elbow1 = 0,elbow2 =0,
				head = 0, body = 0, leg1 = 0, leg2 = 0, knee2 =0, knee1 = 0, ball =0,
						shoulder12 = 0, shoulder22=0, joint2=0, elbow12 = 0,elbow22 =0,
						head2 = 0, body2 = 0, leg12 = 0, leg22 = 0, knee22 =0, knee12 = 0;
		


		private void makeCheckImage() {
			String path = "C:/ImagesDH/";

			File bmpCenterFile = new File(path + "Center.bmp");
			File bmpLeftFile = new File(path + "Left.bmp");
			File bmpRightFile = new File(path + "Right.bmp");
			File bmpTopFile = new File(path + "Top.bmp");
			File bmpBottomFile = new File(path + "Bottom.bmp");
			File bmpFrontFile = new File(path + "Front.bmp");

			try {
				BufferedImage iCenter = ImageIO.read(bmpCenterFile);
				BufferedImage iLeft = ImageIO.read(bmpLeftFile);
				BufferedImage iRight = ImageIO.read(bmpRightFile);
				BufferedImage iTop = ImageIO.read(bmpTopFile);
				BufferedImage iBottom = ImageIO.read(bmpBottomFile);
				BufferedImage iFront = ImageIO.read(bmpFrontFile);

				for (int i = 0; i < checkImageWidth; i++) {
					for (int j = 0; j < checkImageHeight; j++) {
						Color cCenter = new Color(iCenter.getRGB(i, j));
						Color cLeft = new Color(iLeft.getRGB(i, j));
						Color cRight = new Color(iRight.getRGB(i, j));
						Color cTop = new Color(iTop.getRGB(i, j));
						Color cBottom = new Color(iBottom.getRGB(i, j));
						Color cFront = new Color(iFront.getRGB(i, j));

						imageCenter[j][i][0] = (byte) (cCenter.getRed());
						imageCenter[j][i][1] = (byte) (cCenter.getGreen());
						imageCenter[j][i][2] = (byte) (cCenter.getBlue());
						imageCenter[i][j][3] = (byte) 255;

						imageRight[j][i][0] = (byte) (cRight.getRed());
						imageRight[j][i][1] = (byte) (cRight.getGreen());
						imageRight[j][i][2] = (byte) (cRight.getBlue());
						imageRight[i][j][3] = (byte) 255;

						imageLeft[j][i][0] = (byte) (cLeft.getRed());
						imageLeft[j][i][1] = (byte) (cLeft.getGreen());
						imageLeft[j][i][2] = (byte) (cLeft.getBlue());
						imageLeft[i][j][3] = (byte) 255;

						imageTop[j][i][0] = (byte) (cTop.getRed());
						imageTop[j][i][1] = (byte) (cTop.getGreen());
						imageTop[j][i][2] = (byte) (cTop.getBlue());
						imageTop[i][j][3] = (byte) 255;

						imageBottom[i][j][0] = (byte) (cBottom.getRed());
						imageBottom[j][i][1] = (byte) (cBottom.getGreen());
						imageBottom[j][i][2] = (byte) (cBottom.getBlue());
						imageBottom[i][j][3] = (byte) 255;
						
						imageFront[i][j][0] = (byte) (cFront.getRed());
						imageFront[j][i][1] = (byte) (cFront.getGreen());
						imageFront[j][i][2] = (byte) (cFront.getBlue());
						imageFront[i][j][3] = (byte) 255;
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void myinit() {
			
			myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			myGL.glShadeModel(GL.GL_SMOOTH);
			//light
			
			float ambient[] = { 0.0f, 0.0f, 0.0f, 1.0f };
			float diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
			float position[] = { 1.0f, 1.0f, 1.0f, 0.0f };
			float lmodel_ambient[] = { 0.4f, 0.4f, 0.4f, 1.0f };
			float local_view[] = { 0.0f };

			myGL.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient);
			myGL.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse);
			myGL.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position);
			myGL.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
			
//			myGL.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient);
//			myGL.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse);
//			myGL.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position);
//			myGL.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
		myGL.glLightModelfv(GL.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view);
//			
//			float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
//			float mat_shininess[] = { 50.0f };
//			float mat_diffuse_body[] = { 0.1f, 0.5f, 0.8f, 1.0f };
//			float mat_diffuse_elbow[] = { 0.0f, 1.0f, 1.0f, 1.0f};


//			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient);
//			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
//			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
//			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, mat_shininess);
//			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_body);
//			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_elbow);

			//float lightIntensity[] = { 1.0f, 1.0f, 1.0f, 1.0f };
			//float light_position[] = { 2.0f, 2.0f, 3.0f, 0.0f };

			//myGL.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position);
			//myGL.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, lightIntensity);

			myGL.glEnable(GL.GL_LIGHTING);
			myGL.glEnable(GL.GL_LIGHT0);
			myGL.glDepthFunc(GL.GL_LEQUAL);
			myGL.glEnable(GL.GL_DEPTH_TEST);
			myGL.glEnable(GL.GL_NORMALIZE);
			myGL.glEnable(GL.GL_COLOR_MATERIAL);

			makeCheckImage();
			myGL.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);

			myGL.glGenTextures(6, texName);

			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[0]);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
			myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, checkImageWidth, checkImageHeight, 0, GL.GL_RGBA,
					GL.GL_UNSIGNED_BYTE, imageCenter);

			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[1]);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
			myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, checkImageWidth, checkImageHeight, 0, GL.GL_RGBA,
					GL.GL_UNSIGNED_BYTE, imageRight);

			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[2]);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
			myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, checkImageWidth, checkImageHeight, 0, GL.GL_RGBA,
					GL.GL_UNSIGNED_BYTE, imageLeft);

			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[3]);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
			myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, checkImageWidth, checkImageHeight, 0, GL.GL_RGBA,
					GL.GL_UNSIGNED_BYTE, imageTop);

			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[4]);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
			myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, checkImageWidth, checkImageHeight, 0, GL.GL_RGBA,
					GL.GL_UNSIGNED_BYTE, imageBottom);
			
			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[5]);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
			myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
			myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, checkImageWidth, checkImageHeight, 0, GL.GL_RGBA,
					GL.GL_UNSIGNED_BYTE, imageFront);

			myGL.glEnable(GL.GL_TEXTURE_2D);

		}

		public void display() {
			myGL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			
//			float mat_ambient[] = { 0.0f, 0.0f, 1.0f, 1.0f };
			// float light_position[] = {0.0f, 0.0f, 2.0f, 1.0f};
//			float mat_diffuse[] = { 0.0f, 0.6f, 0.6f, 1.0f };
//			//float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
//			float mat_shininess[] = { 50.0f };
			float mat_diffuse_body[] = { 0.1f, 0.5f, 0.8f, 1.0f };
			float mat_diffuse_elbow[] = { 1.0f, 0.8f, 0.5f, 1.0f };
			float mat_diffuse_head[] = { 1.0f, 1.0f, 0.2f, 0.9f };
			float mat_diffuse_shoulder[] = {0.5f, 0.7f, 0.3f, 1.0f};
			float mat_diffuse_ball[] = {1.5f, 0.5f, 0.0f, 1.0f};
			
			float no_mat[] = { 0.0f, 0.0f, 0.0f, 1.0f };
			float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
			float high_shininess[] = { 100.0f };

			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, no_mat);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, high_shininess);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_EMISSION, no_mat);
//			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient);
//			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
//			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
//			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, mat_shininess);
			
			
			myGL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			//myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
			myGL.glColor3f(0.0f, 0.0f, 1.0f);
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, -1.0f, 0.0f);
			//myGL.glRotatef((float) body, 1.0f, 0.0f, 0.0f);
			myGL.glTranslatef(0.0f, 1.0f, 0.0f);
			myGL.glRotatef((float) (x + 5), 1.0f, 0.0f, 0.0f);
			myGL.glRotatef((float) (y + 15), 0.0f, 1.0f, 0.0f);
			myGL.glDisable(GL.GL_LIGHTING);
			myGL.glDisable(GL.GL_LIGHT0);

			// room
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, 0.0f, 0.0f);
			myGL.glRotatef(r, 0.0f, 1.0f, 0.0f);
			myGL.glEnable(GL.GL_TEXTURE_2D);

			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[0]);
			myGL.glBegin(GL.GL_QUADS);
			myGL.glTexCoord2f(0.0f, 0.0f);
			myGL.glVertex3f(-5.0f, -4.0f, -15.0f);
			myGL.glTexCoord2f(1.0f, 0.0f);
			myGL.glVertex3f(5.0f, -4.0f, -15.0f);
			myGL.glTexCoord2f(1.0f, 1.0f);
			myGL.glVertex3f(5.0f, 6.0f, -15.0f);
			myGL.glTexCoord2f(0.0f, 1.0f);
			myGL.glVertex3f(-5.0f, 6.0f, -15.0f);
			myGL.glEnd();

			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[1]);
			myGL.glBegin(GL.GL_QUADS);
			myGL.glTexCoord2f(0.0f, 0.0f);
			myGL.glVertex3f(5.0f, 6.0f, -15.0f);
			myGL.glTexCoord2f(1.0f, 0.0f);
			myGL.glVertex3f(5.0f, -4.0f, -15.0f);
			myGL.glTexCoord2f(1.0f, 1.0f);
			myGL.glVertex3f(5.0f, -4.0f, 4.0f);
			myGL.glTexCoord2f(0.0f, 1.0f);
			myGL.glVertex3f(5.0f, 6.0f, 4.0f);
			myGL.glEnd();

			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[2]);
			myGL.glBegin(GL.GL_QUADS);
			myGL.glTexCoord2f(0.0f, 0.0f);
			myGL.glVertex3f(-5.0f, 6.0f, -15.0f);
			myGL.glTexCoord2f(1.0f, 0.0f);
			myGL.glVertex3f(-5.0f, -4.0f, -15.0f);
			myGL.glTexCoord2f(1.0f, 1.0f);
			myGL.glVertex3f(-5.0f, -4.0f, 4.0f);
			myGL.glTexCoord2f(0.0f, 1.0f);
			myGL.glVertex3f(-5.0f, 6.0f, 4.0f);
			myGL.glEnd();

			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[3]);
			myGL.glBegin(GL.GL_QUADS);
			myGL.glTexCoord2f(0.0f, 0.0f);
			myGL.glVertex3f(-5.0f, 6.0f, 4.0f);
			myGL.glTexCoord2f(1.0f, 0.0f);
			myGL.glVertex3f(5.0f, 6.0f, 4.0f);
			myGL.glTexCoord2f(1.0f, 1.0f);
			myGL.glVertex3f(5.0f, 6.0f, -15.0f);
			myGL.glTexCoord2f(0.0f, 1.0f);
			myGL.glVertex3f(-5.0f, 6.0f, -15.0f);
			myGL.glEnd();

			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[4]);
			myGL.glBegin(GL.GL_QUADS);
			myGL.glTexCoord2f(0.0f, 0.0f);
			myGL.glVertex3f(-5.0f, -4.0f, 4.0f);
			myGL.glTexCoord2f(1.0f, 0.0f);
			myGL.glVertex3f(5.0f, -4.0f, 4.0f);
			myGL.glTexCoord2f(1.0f, 1.0f);
			myGL.glVertex3f(5.0f, -4.0f, -15.0f);
			myGL.glTexCoord2f(0.0f, 1.0f);
			myGL.glVertex3f(-5.0f, -4.0f, -15.0f);
			myGL.glEnd();
			
			myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[5]);
			myGL.glBegin(GL.GL_QUADS);
			myGL.glTexCoord2f(0.0f, 0.0f);
			myGL.glVertex3f(-5.0f, -4.0f, 4.0f);
			myGL.glTexCoord2f(1.0f, 0.0f);
			myGL.glVertex3f(5.0f, -4.0f, 4.0f);
			myGL.glTexCoord2f(1.0f, 1.0f);
			myGL.glVertex3f(5.0f, 6.0f, 4.0f);
			myGL.glTexCoord2f(0.0f, 1.0f);
			myGL.glVertex3f(-5.0f, 6.0f, 4.0f);
			myGL.glEnd();

			myGL.glDisable(GL.GL_TEXTURE_2D);
			

			// begin
			myGL.glEnable(GL.GL_LIGHTING);
			myGL.glEnable(GL.GL_LIGHT0);
			//myGL.glPushMatrix();
			myGL.glTranslatef(-0.3f, -1.5f, (float)body +  -13.0f);
			myGL.glScalef(1.0f, 1.0f, 1.4f);
			// body
			// myGL.glScalef(0.1f, 0.07f, .01f);
			
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, 0.4f,0.0f);
			myGL.glScalef(1.0f, 1.5f, 0.5f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_body);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//joint left shoulder
			myGL.glPushMatrix();
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_body);
			myGL.glTranslatef(-0.35f, 0.7f, 0.0f);
			myGL.glScalef(1.1f, 0.85f, 0.8f);
			myUT.glutSolidSphere(0.15, 18, 18);
			myGL.glPopMatrix();
			
			//joint right shoulder
					myGL.glPushMatrix();
					myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_body);
					myGL.glTranslatef(0.35f, 0.7f, 0.0f);
					myGL.glScalef(1.1f, 0.85f, 0.8f);
					myUT.glutSolidSphere(0.15, 18, 18);
					myGL.glPopMatrix();
					
			//joint left leg
					myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_body);
					myGL.glPushMatrix();
					myGL.glTranslatef(-0.13f, 0.0f, 0.0f);
					myGL.glScalef(1.1f, 0.7f, 0.82f);
					myUT.glutSolidSphere(0.15, 18, 18);
					myGL.glPopMatrix();

			//joint right leg
					myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_body);
					myGL.glPushMatrix();
					myGL.glTranslatef(0.13f, 0.0f, 0.0f);
					myGL.glScalef(1.1f, 0.7f, 0.82f);
					myUT.glutSolidSphere(0.15, 18, 18);
					myGL.glPopMatrix();

			// head
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, 1.0f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_head);
			myGL.glRotatef((float) head, 0.0f, 1.0f, 0.0f);
			// myGL.glTranslatef(0.0f, 0.5f, 0.0f);
			myUT.glutSolidSphere(0.25, 18, 18);
			//myGL.glScalef(0.0f, 0.01f, 0.0f);
			myGL.glPopMatrix();
			
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, 1.0f, 0.0f);
			//myGL.glColor3f(0.1f, 0.2f, 0.3f);
			myGL.glRotatef((float) head, 0.0f, 1.0f, 0.0f);
			// myGL.glTranslatef(0.0f, 0.5f, 0.0f);
			myUT.glutSolidSphere(0.26, 18, 18);
			myGL.glScalef(0.0f, -0.03f, 0.0f);
			myGL.glPopMatrix();

			// right-arm
			//shoulder
			myGL.glPushMatrix();
			myGL.glPushMatrix();
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_shoulder);
			myGL.glTranslatef(-0.3f, 0.725f, 0.0f);
			myGL.glRotatef((float) shoulder1, 1.0f, 0.0f, 0.0f);
			myGL.glTranslatef(0.65f, -0.2f, 0.0f);
			//myGL.glColor3f(0.0f, 0.0f, 1.0f);
			myGL.glPushMatrix();
			myGL.glScalef(0.3f, -0.7f, 0.2f);
			myUT.glutSolidCube(0.6);
			myGL.glPopMatrix();
			
			//joint
			//myGL.glColor3f(1.0f, 0.0f, 1.5f);
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, -0.25f, 0.0f);
			myGL.glScalef(0.8f, 0.8f, 0.75f);
			myUT.glutSolidSphere(0.1, 18, 18);
			myGL.glPopMatrix();

			//elbow
			myGL.glPushMatrix();
			myGL.glTranslatef(-0.65f, -0.425f, 0.0f);
			myGL.glRotatef((float) elbow1, 0.0f, 1.0f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_elbow);
			myGL.glTranslatef(0.65f, -0.05f, 0.0f);
			myGL.glScalef(0.3f, -0.8f, 0.2f);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			//myGL.glPopMatrix();
			//hand
			myGL.glPushMatrix();
			//myGL.glColor3f(0.7f, 0.0f, 1.8f);
			myGL.glTranslated(-0.0f, -0.7f, 0.0f);
			myUT.glutSolidSphere(0.07, 18, 18);
			myGL.glScalef(0.5f, 0.8f, 0.5f);
			myGL.glPopMatrix();
			myGL.glPopMatrix();
			myGL.glPopMatrix();
			
//				//left-arm
			//shoulder
			myGL.glPushMatrix();
			myGL.glPushMatrix();
			myGL.glTranslatef(-1.0f, 0.725f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_shoulder);
			myGL.glRotatef((float) shoulder2, 1.0f, 0.0f, 0.0f);
			myGL.glTranslatef(0.65f, -0.2f, 0.0f);
			//myGL.glColor3f(0.0f, 0.0f, 1.0f);
			myGL.glPushMatrix();
			myGL.glScalef(0.3f, -0.7f, 0.2f);
			myUT.glutSolidCube(0.6);
			myGL.glPopMatrix();
			
			//joint
			//myGL.glColor3f(1.0f, 0.0f, 1.5f);
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, -0.25f, 0.0f);
			myGL.glScalef(0.8f, 0.8f, 0.75f);
			myUT.glutSolidSphere(0.1, 18, 18);
			myGL.glPopMatrix();

			//elbow
			myGL.glPushMatrix();
			myGL.glTranslatef(-0.65f, -0.425f, 0.0f);
			myGL.glRotatef((float) elbow2, 0.0f, 1.0f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_elbow);
			myGL.glTranslatef(0.65f, -0.05f, 0.0f);
			myGL.glScalef(0.3f, -0.8f, 0.2f);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//hand
					myGL.glPushMatrix();
					//myGL.glColor3f(0.7f, 0.0f, 1.8f);
					myGL.glTranslated(-0.0f, -0.7f, 0.0f);
					myUT.glutSolidSphere(0.07, 18, 18);
					myGL.glScalef(0.5f, 0.8f, 0.5f);
					myGL.glPopMatrix();
					
			myGL.glPopMatrix();
			myGL.glPopMatrix();

			// right-leg
			//leg
			myGL.glPushMatrix();
			myGL.glPushMatrix();
			myGL.glTranslatef(0.18f, -0.07f, 0.0f);
			myGL.glRotatef((float) leg1, 1.0f, 0.0f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_shoulder);
			//myGL.glColor3f(0.0f, 0.0f, 1.0f);
			myGL.glTranslatef(0.0f, -0.15f, 0.0f);
			myGL.glPushMatrix();
			myGL.glScalef(0.3f, 0.8f, 0.45f);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//joint
			//myGL.glColor3f(1.0f, 0.0f, 1.5f);
			myGL.glPushMatrix();
			myGL.glTranslatef(0.01f, -0.25f, 0.01f);
			myGL.glScalef(0.8f, 0.85f, 0.9f);
			myUT.glutSolidSphere(0.1, 18, 18);
			myGL.glPopMatrix();
			
			//knee
			myGL.glPushMatrix();
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_elbow);
			myGL.glTranslatef(0.0f, -0.4857f, 0.0f);
			//myGL.glRotatef((float) knee1, 1.0f, 0.0f, 0.0f);
			//myGL.glTranslatef(0.0f, -0.5f, 0.0f);
			//myGL.glColor3f(0.0f, 1.0f, 1.0f);
			myGL.glScalef(0.3f, 0.8f, 0.45f);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//shoe
			myGL.glPushMatrix();
			//myGL.glColor3f(1.0f, 0.0f, 0.0f);
			myGL.glTranslatef(0.0f, -0.68f, 0.15f);
			myGL.glScalef(1.0f, 0.6f, 1.2f);
			myUT.glutSolidSphere(0.1f, 18, 18);
			myGL.glPopMatrix();
			
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, -0.68f, -0.05f);
			myGL.glScalef(1.0f, 0.65f, 1.2f);
			myUT.glutSolidSphere(0.1, 18, 18);
			myGL.glPopMatrix();
			
			myGL.glPopMatrix();
			myGL.glPopMatrix();
//			
//			//left-leg
			//
			//leg
			myGL.glPushMatrix();
			myGL.glTranslatef(-0.18f, -0.07f, 0.0f);
			myGL.glRotatef((float) leg2, 1.0f, 0.0f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_shoulder);
			//myGL.glColor3f(0.0f, 0.0f, 1.0f);
			myGL.glTranslatef(0.0f, -0.15f, 0.0f);
			myGL.glPushMatrix();
			myGL.glScalef(0.3f, 0.8f, 0.45f);
			//myGLU.gluCylinder(obj, 0.3,0.8, 0.45, 10, 10);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//joint
					//myGL.glColor3f(1.0f, 0.0f, 1.5f);
					myGL.glPushMatrix();
					myGL.glTranslatef(0.01f, -0.25f, 0.01f);
					myGL.glScalef(0.8f, 0.85f, 0.9f);
					myUT.glutSolidSphere(0.1, 18, 18);
					myGL.glPopMatrix();
			
			//knee
			myGL.glPushMatrix();
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_elbow);
			myGL.glTranslatef(0.0f, -0.4857f, 0.0f);
			//myGL.glRotatef((float) knee2, 1.0f, 0.0f, 0.0f);
			//myGL.glColor3f(0.0f, 1.0f, 1.0f);
			myGL.glScalef(0.3f, 0.8f, 0.45f);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//shoe
			myGL.glPushMatrix();
			//myGL.glColor3f(1.0f, 0.0f, 0.0f);
			myGL.glTranslatef(0.0f, -0.68f, 0.15f);
			myGL.glScalef(1.0f, 0.6f, 1.2f);
			myUT.glutSolidSphere(0.1f, 18, 18);
			myGL.glPopMatrix();
			
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, -0.68f, -0.05f);
			myGL.glScalef(1.0f, 0.65f, 1.2f);
			myUT.glutSolidSphere(0.1, 18, 18);
			myGL.glPopMatrix();
			
			//myGL.glPopMatrix();
			
			//ball
			//myGL.glPushMatrix();
//			myGL.glTranslatef(0.0f, -0.6f, (float) ball+ 0.55f);
//			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_ball);
//			//myGL.glColor3f(0.7f, 0.2f, 0.3f);
//			//myGL.glRotatef((float) ball, 0.0f, 1.0f, 0.0f);
//			//myGL.glTranslatef(0.0f, 0.0f, (float) ball + 0.01f);
//			myUT.glutSolidSphere(0.2, 18, 18);
//			myGL.glPopMatrix();
			myGL.glPopMatrix();
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, (float) a, (float) ball+ 0.55f);
			myGL.glTranslatef(-0.1f, -0.6f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_ball);
			//myGL.glColor3f(0.7f, 0.2f, 0.3f);
			//myGL.glRotatef((float) ball, 0.0f, 1.0f, 0.0f);
			//myGL.glTranslatef(0.0f, 0.0f, (float) ball + 0.01f);
			myUT.glutSolidSphere(0.2, 18, 18);
			myGL.glPopMatrix();
			
			
			
			//robot2
			// begin
			myGL.glEnable(GL.GL_LIGHTING);
			myGL.glEnable(GL.GL_LIGHT0);
			myGL.glTranslatef(-0.3f, -1.5f, (float)body2 +  8.2f);
			myGL.glRotatef(180, 0.0f, 1.0f, 0.0f);
			//myGL.glScalef(1.0f, 1.0f, 1.4f);
			// body
			// myGL.glScalef(0.1f, 0.07f, .01f);
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, 0.4f,0.0f);
			myGL.glScalef(1.0f, 1.5f, 0.5f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_body);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//joint left shoulder
			myGL.glPushMatrix();
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_body);
			myGL.glTranslatef(-0.35f, 0.7f, 0.0f);
			myGL.glScalef(1.1f, 0.85f, 0.8f);
			myUT.glutSolidSphere(0.15, 18, 18);
			myGL.glPopMatrix();
			
			//joint right shoulder
					myGL.glPushMatrix();
					myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_body);
					myGL.glTranslatef(0.35f, 0.7f, 0.0f);
					myGL.glScalef(1.1f, 0.85f, 0.8f);
					myUT.glutSolidSphere(0.15, 18, 18);
					myGL.glPopMatrix();
					
			//joint left leg
					myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_body);
					myGL.glPushMatrix();
					myGL.glTranslatef(-0.13f, 0.0f, 0.0f);
					myGL.glScalef(1.1f, 0.7f, 0.82f);
					myUT.glutSolidSphere(0.15, 18, 18);
					myGL.glPopMatrix();

			//joint right leg
					myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_body);
					myGL.glPushMatrix();
					myGL.glTranslatef(0.13f, 0.0f, 0.0f);
					myGL.glScalef(1.1f, 0.7f, 0.82f);
					myUT.glutSolidSphere(0.15, 18, 18);
					myGL.glPopMatrix();

			// head
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, 1.0f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_head);
			myGL.glRotatef((float) head2, 0.0f, 1.0f, 0.0f);
			// myGL.glTranslatef(0.0f, 0.5f, 0.0f);
			myUT.glutSolidSphere(0.25, 18, 18);
			//myGL.glScalef(0.0f, 0.01f, 0.0f);
			myGL.glPopMatrix();
			
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, 1.0f, 0.0f);
			//myGL.glColor3f(0.1f, 0.2f, 0.3f);
			myGL.glRotatef((float) head2, 0.0f, 1.0f, 0.0f);
			// myGL.glTranslatef(0.0f, 0.5f, 0.0f);
			myUT.glutSolidSphere(0.26, 18, 18);
			myGL.glScalef(0.0f, -0.03f, 0.0f);
			myGL.glPopMatrix();

			// right-arm
			//shoulder
			myGL.glPushMatrix();
			myGL.glPushMatrix();
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_shoulder);
			myGL.glTranslatef(-0.3f, 0.725f, 0.0f);
			myGL.glRotatef((float) shoulder12, 1.0f, 0.0f, 0.0f);
			myGL.glTranslatef(0.65f, -0.2f, 0.0f);
			//myGL.glColor3f(0.0f, 0.0f, 1.0f);
			myGL.glPushMatrix();
			myGL.glScalef(0.3f, -0.7f, 0.2f);
			myUT.glutSolidCube(0.6);
			myGL.glPopMatrix();
			
			//joint
			//myGL.glColor3f(1.0f, 0.0f, 1.5f);
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, -0.25f, 0.0f);
			myGL.glScalef(0.8f, 0.8f, 0.75f);
			myUT.glutSolidSphere(0.1, 18, 18);
			myGL.glPopMatrix();

			//elbow
			myGL.glPushMatrix();
			myGL.glTranslatef(-0.65f, -0.425f, 0.0f);
			myGL.glRotatef((float) elbow12, 0.0f, 1.0f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_elbow);
			myGL.glTranslatef(0.65f, -0.05f, 0.0f);
			myGL.glScalef(0.3f, -0.8f, 0.2f);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			//myGL.glPopMatrix();
			//hand
			myGL.glPushMatrix();
			//myGL.glColor3f(0.7f, 0.0f, 1.8f);
			myGL.glTranslated(-0.0f, -0.7f, 0.0f);
			myUT.glutSolidSphere(0.07, 18, 18);
			myGL.glScalef(0.5f, 0.8f, 0.5f);
			myGL.glPopMatrix();
			myGL.glPopMatrix();
			myGL.glPopMatrix();
			
//				//left-arm
			//shoulder
			myGL.glPushMatrix();
			myGL.glPushMatrix();
			myGL.glTranslatef(-1.0f, 0.725f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_shoulder);
			myGL.glRotatef((float) shoulder22, 1.0f, 0.0f, 0.0f);
			myGL.glTranslatef(0.65f, -0.2f, 0.0f);
			//myGL.glColor3f(0.0f, 0.0f, 1.0f);
			myGL.glPushMatrix();
			myGL.glScalef(0.3f, -0.7f, 0.2f);
			myUT.glutSolidCube(0.6);
			myGL.glPopMatrix();
			
			//joint
			//myGL.glColor3f(1.0f, 0.0f, 1.5f);
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, -0.25f, 0.0f);
			myGL.glScalef(0.8f, 0.8f, 0.75f);
			myUT.glutSolidSphere(0.1, 18, 18);
			myGL.glPopMatrix();

			//elbow
			myGL.glPushMatrix();
			myGL.glTranslatef(-0.65f, -0.425f, 0.0f);
			myGL.glRotatef((float) elbow22, 0.0f, 1.0f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_elbow);
			myGL.glTranslatef(0.65f, -0.05f, 0.0f);
			myGL.glScalef(0.3f, -0.8f, 0.2f);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//hand
					myGL.glPushMatrix();
					//myGL.glColor3f(0.7f, 0.0f, 1.8f);
					myGL.glTranslated(-0.0f, -0.7f, 0.0f);
					myUT.glutSolidSphere(0.07, 18, 18);
					myGL.glScalef(0.5f, 0.8f, 0.5f);
					myGL.glPopMatrix();
					
			myGL.glPopMatrix();
			myGL.glPopMatrix();

			// right-leg
			//leg
			myGL.glPushMatrix();
			myGL.glPushMatrix();
			myGL.glTranslatef(0.18f, -0.07f, 0.0f);
			myGL.glRotatef((float) leg12, 1.0f, 0.0f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_shoulder);
			//myGL.glColor3f(0.0f, 0.0f, 1.0f);
			myGL.glTranslatef(0.0f, -0.15f, 0.0f);
			myGL.glPushMatrix();
			myGL.glScalef(0.3f, 0.8f, 0.45f);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//joint
			//myGL.glColor3f(1.0f, 0.0f, 1.5f);
			myGL.glPushMatrix();
			myGL.glTranslatef(0.01f, -0.25f, 0.01f);
			myGL.glScalef(0.8f, 0.85f, 0.9f);
			myUT.glutSolidSphere(0.1, 18, 18);
			myGL.glPopMatrix();
			
			//knee
			myGL.glPushMatrix();
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_elbow);
			myGL.glTranslatef(0.0f, -0.4857f, 0.0f);
			//myGL.glRotatef((float) knee1, 1.0f, 0.0f, 0.0f);
			//myGL.glTranslatef(0.0f, -0.5f, 0.0f);
			//myGL.glColor3f(0.0f, 1.0f, 1.0f);
			myGL.glScalef(0.3f, 0.8f, 0.45f);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//shoe
			myGL.glPushMatrix();
			//myGL.glColor3f(1.0f, 0.0f, 0.0f);
			myGL.glTranslatef(0.0f, -0.68f, 0.15f);
			myGL.glScalef(1.0f, 0.6f, 1.2f);
			myUT.glutSolidSphere(0.1f, 18, 18);
			myGL.glPopMatrix();
			
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, -0.68f, -0.05f);
			myGL.glScalef(1.0f, 0.65f, 1.2f);
			myUT.glutSolidSphere(0.1, 18, 18);
			myGL.glPopMatrix();
			
			myGL.glPopMatrix();
			myGL.glPopMatrix();
//			
//			//left-leg
			//
			//leg
			myGL.glPushMatrix();
			myGL.glTranslatef(-0.18f, -0.07f, 0.0f);
			myGL.glRotatef((float) leg22, 1.0f, 0.0f, 0.0f);
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_shoulder);
			//myGL.glColor3f(0.0f, 0.0f, 1.0f);
			myGL.glTranslatef(0.0f, -0.15f, 0.0f);
			myGL.glPushMatrix();
			myGL.glScalef(0.3f, 0.8f, 0.45f);
			//myGLU.gluCylinder(obj, 0.3,0.8, 0.45, 10, 10);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//joint
					//myGL.glColor3f(1.0f, 0.0f, 1.5f);
					myGL.glPushMatrix();
					myGL.glTranslatef(0.01f, -0.25f, 0.01f);
					myGL.glScalef(0.8f, 0.85f, 0.9f);
					myUT.glutSolidSphere(0.1, 18, 18);
					myGL.glPopMatrix();
			
			//knee
			myGL.glPushMatrix();
			myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse_elbow);
			myGL.glTranslatef(0.0f, -0.4857f, 0.0f);
			//myGL.glRotatef((float) knee2, 1.0f, 0.0f, 0.0f);
			//myGL.glColor3f(0.0f, 1.0f, 1.0f);
			myGL.glScalef(0.3f, 0.8f, 0.45f);
			myUT.glutSolidCube(0.5);
			myGL.glPopMatrix();
			
			//shoe
			myGL.glPushMatrix();
			//myGL.glColor3f(1.0f, 0.0f, 0.0f);
			myGL.glTranslatef(0.0f, -0.68f, 0.15f);
			myGL.glScalef(1.0f, 0.6f, 1.2f);
			myUT.glutSolidSphere(0.1f, 18, 18);
			myGL.glPopMatrix();
			
			myGL.glPushMatrix();
			myGL.glTranslatef(0.0f, -0.68f, -0.05f);
			myGL.glScalef(1.0f, 0.65f, 1.2f);
			myUT.glutSolidSphere(0.1, 18, 18);
			myGL.glPopMatrix();
			
			
			myGL.glPopMatrix();
			
			myGL.glPopMatrix();

			myGL.glPopMatrix();
			myGL.glFlush();
		}

		public void myReshape(int w, int h) {
			myGL.glViewport(0, 0, w, h);
			myGL.glMatrixMode(GL.GL_PROJECTION);
			myGL.glLoadIdentity();
			myGLU.gluPerspective(90.0, (double) w / (double) h, 1.0, 20.0);
			// myGL.glOrtho(-8.0f, 8.0f, -8.0f, 8.0f, -8.0f, 8.0f);
			myGL.glMatrixMode(GL.GL_MODELVIEW);
			myGL.glLoadIdentity();
			myGL.glTranslatef(0.0f, 0.0f, -3.0f);
		}

		/* ARGSUSED1 */
		public void keyboard(char key, int x, int y) {
			switch (key) {
			//dat bong
			case 'm':
				ball = (ball + 0.5)%8.0;
				a = (a-0.1);
				//body = (body + 1.0)%13;
				leg1 =  (leg1 + 35)%35.01;
				//knee1 =  (knee1 -30)%30.01;
				leg2 =  (leg2 - 35)%35.01;
				//knee22 =  (knee22- 30)%30.01;
				shoulder1 =(shoulder1 +60)%60.01;
				shoulder2 =(shoulder2 -60)%60.01;
				myUT.glutPostRedisplay();
				break;
			case 'i':
				ball = (ball + 0.5)%8.0;
				a = (a-0.1);
				//body = (body + 1.0)%13;
				leg2 =  (leg2 + 35)%35.01;
				//knee1 =  (knee1 -30)%30.01;
				leg1 =  (leg1 - 35)%35.01;
				//knee22 =  (knee22- 30)%30.01;
				shoulder2 =(shoulder2 +60)%60.01;
				shoulder1 =(shoulder1 -60)%60.01;
				myUT.glutPostRedisplay();
				break;
				
			case 'n':
				ball = (ball - 0.5)%8.0;
				a =(a+0.1);
				//body =(body + 1.0)%13;
				leg22 =  (leg22 - 35)%35.01;
				//knee1 = (knee12 -30)%30.01;
				leg12 = (leg12 + 35)%35.01;
				leg1 =  (leg1 - 35)%35.01;
				//knee1 =  (knee1 -30)%30.01;
				leg2 =  (leg2 + 35)%35.01;
				//knee22 =  (knee22- 30)%30.01;
				shoulder1 =(shoulder1 -60)%60.01;
				shoulder2 =(shoulder2 +60)%60.01;
				//knee22 = (knee22- 30)%30.01;
				shoulder22 =(shoulder22 -60)%60.01;
				shoulder12 =(shoulder12+60)%60.01;
				myUT.glutPostRedisplay();
				break;
			case 'j':
				ball = (ball - 0.5)%8.0;
				a =(a+0.1);
				//body =(body + 1.0)%13;
				leg12 =  (leg12 - 35)%35.01;
				//knee1 = (knee12 -30)%30.01;
				leg22 = (leg22 + 70)%70.01;
				leg2 =  (leg2 - 35)%35.01;
				//knee1 =  (knee1 -30)%30.01;
				leg1 =  (leg1 + 35)%35.01;
				//knee22 =  (knee22- 30)%30.01;
				shoulder2 =(shoulder2 -60)%60.01;
				shoulder1 =(shoulder1 +60)%60.01;
				//knee22 = (knee22- 30)%30.01;
				shoulder12 =(shoulder12 -60)%60.01;
				shoulder22 =(shoulder22+60)%60.01;
				myUT.glutPostRedisplay();
				break;
//			case 'j':
//				ball = (ball + 0.5)%8.0;
//				a = (a-0.1);
//				
//				if(leg1 == 0 && shoulder1 ==0) {
//					leg1 = (leg1 - 20) % 360;
//					
//					
//					myUT.glutPostRedisplay();
//				}
//				else if(leg1 < 0 && shoulder1 < 0) {
//					leg1 = (leg1 + 40) % 360;
//					shoulder1 = (shoulder1 + 60) % 360;
//					myUT.glutPostRedisplay();
//				}
//				else {
//					leg1 = (leg1 - 40) % 360;
//					shoulder1 = (shoulder1 - 60) % 360;
//					myUT.glutPostRedisplay();
//				}
//			case 'i':
//				ball = (ball - 0.5)%8.0;
//				a =(a+0.1);
				
			
			//tang bong
			
			case 'a':
				y = (y + 5);
				myGL.glRotatef((float) y, 0.0f, 1.0f, 0.0f);
				myUT.glutPostRedisplay();
				break;
			case 'd':
				y = (y - 5);
				myGL.glRotatef((float) y, 0.0f, 1.0f, 0.0f);
				myUT.glutPostRedisplay();
				break;
			case 27:
				System.exit(0);
			default:
				break;
			}
		}

		public void init() {
			myUT.glutInitWindowSize(500, 500);
			myUT.glutInitWindowPosition(0, 0);
			myUT.glutCreateWindow(this);
			myinit();
			myUT.glutDisplayFunc("display");
			myUT.glutReshapeFunc("myReshape");
			myUT.glutKeyboardFunc("keyboard");
			myUT.glutMainLoop();
		}

		static public void main(String args[]) throws IOException {
			Frame mainFrame = new Frame();
			mainFrame.setSize(508, 527);
			project mainCanvas = new project();
			mainCanvas.init();
			mainFrame.add(mainCanvas);
			mainFrame.setVisible(true);
		}
}
