package ll.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Upload {
	
	public static final String IMAGE_URL="upload/image/";
	public static final String MINIMAGE_URL="upload/minImage/";
	public static final String VOICE_URL="upload/voice/";
	
	public static final String WEIBO_IMAGE_URL="upload/weibo/";
	public static final String WEIBO_MINIMAGE_URL="upload/miniweibo/";
	
	public static final String HEADPORTRAIT_URL="upload/headportrait/";
	public static final String MINIHEADPORTRAIT_URL="upload/miniheadportrait/";
	
	public static void save(String url,File file) throws IOException{
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			outputStream = new FileOutputStream(url);
			byte[] bs = new byte[1024];
			inputStream = new FileInputStream(file);
			int length=0;
			while((length=inputStream.read(bs))>0){
				outputStream.write(bs,0,length);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.close();
			}
			if(inputStream!=null){
				inputStream.close();
			}
		}
	}
}
