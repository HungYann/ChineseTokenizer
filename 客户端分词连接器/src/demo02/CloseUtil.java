package demo02;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流
 * @author liuhongyang
 *
 */
public class CloseUtil {
	public static void CloseAll(Closeable... io)
	{
		for(Closeable temp:io)
		{
			try {
				if(null != temp)
				{
					temp.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
