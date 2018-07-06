


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRcode {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		  int version=8;
		  int imageSize=151;
	      Qrcode qrcode=new Qrcode();
	     
	      qrcode.setQrcodeEncodeMode('B');
	      qrcode.setQrcodeErrorCorrect('H');
	      qrcode.setQrcodeVersion(version);
	      BufferedImage bufferedImage=new BufferedImage(imageSize,imageSize,BufferedImage.TYPE_INT_RGB);
	      Graphics2D gs=bufferedImage.createGraphics();
	      gs.setBackground(Color.WHITE);
	      gs.setColor(Color.BLACK);
	      gs.clearRect(0, 0, imageSize, imageSize);
	      int pixoff=2;
	      int startR=255,startG=0,startB=0;
	      int endR=0,endG=0,endB=255;
	      String content="https://hao.360.cn";
	      byte[] data=content.getBytes("utf-8");
	      boolean[][] qrdata=qrcode.calQrcode(data);
	      for(int i=0;i<qrdata.length;i++){
	    	  for(int j=0;j<qrdata.length;j++){
	    		  if(qrdata[i][j]){
	    			  int num1=startR+(endR-startR)*(i+1)/qrdata.length;
	    			  int num2=startG+(endG-startG)*(i+1)/qrdata.length;
	    			  int num3=startB+(endB-startB)*(i+1)/qrdata.length;
	    			  Color color =new Color(num1,num2,num3);
	    			  gs.setColor(color);
	    		  gs.fillRect(i*3+pixoff, j*3+pixoff, 3, 3);
	    		  }
	    	  }
	      }
	      BufferedImage logo=scale("D:/logo.jpg",60,60,true);
	     
//	      int logoSize=imageSize/4;
//	      int o=(imageSize-logoSize)/2;
	      int location=(imageSize-logo.getHeight())/2;
	      gs.drawImage(logo, location, location, 60,60,null);
	      gs.dispose();
	      bufferedImage.flush();
	      ImageIO.write(bufferedImage, "png", new File("D:/qrcode.png"));
	    	  System.out.println("¶þÎ¬ÂëÉú³É");
	}
	

	private static BufferedImage scale(String logoPath, int width, int height, boolean hasFiller) throws Exception {
		// TODO Auto-generated method stub
		 double ratio = 0.0; 
		File file=new File(logoPath);
		 BufferedImage srcImage=ImageIO.read( file);
		 Image destImage = srcImage . getScaledInstance (width, height, BufferedImage. SCALE_SMOOTH);
		  
		 if ((srcImage.getHeight() > height )||( srcImage. getWidth() > width)){
		  if (srcImage.getHeight () > srcImage. getWidth()){
		  ratio =(new Integer (height)).doubleValue()/srcImage.getHeight();
		  } else{ 
		    ratio = (new Integer (width)).doubleValue()/srcImage.getWidth();
		  }
		  AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio,ratio),null);
		  destImage =op.filter(srcImage, null);
		  }
		  
		 if (hasFiller){
		  BufferedImage image=new BufferedImage (width, height, BufferedImage.TYPE_INT_RGB);
		  Graphics2D graphic =image.createGraphics();
		  graphic. setColor (Color.WHITE);
		  graphic. fillRect(0, 0, width, height);
		  if(width==destImage.getHeight(null)){
			  graphic.drawImage(destImage,0,(height-destImage.getHeight(null))/2,destImage.getHeight(null),destImage.getHeight(null),Color.WHITE,null);
			        destImage.getHeight(null);
		  }else{
			  graphic.drawImage(destImage,0,(height-destImage.getWidth(null))/2,destImage.getWidth(null),destImage.getHeight(null),Color.WHITE,null);
		        destImage.getHeight(null);
		  }
		  graphic.dispose() ;
		  destImage = image;
		}
		  return (BufferedImage) destImage;
	}
		
} 
