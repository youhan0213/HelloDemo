package com.springmvc.util;
/**
 * java实现生成MD5文件校验码
 */

import java.io.BufferedInputStream;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;  
import java.nio.channels.FileChannel;  
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;  

/**
 *  MD5计算,调用即可得到MD5的128位整数
 *  MessageDigest 类是一个引擎类，它是为了提供诸如 SHA1 或 MD5 等密码上安全的报文摘要功能而设计的。
 *  密码上安全的报文摘要可接受任意大小的输入（一个字节数组），并产生固定大小的输出，该输出称为一个摘要或散列。摘要具有以下属性：
 *  1.无法通过计算找到两个散列成相同值的报文。
 *  2.摘要不反映任何与输入有关的内容。
 *  使用报文摘要可以生成数据唯一且可靠的标识符。有时它们被称为数据的“数字指纹”。
 */
public class MD5 {  
	//首先初始化一个字符数组，用来存放每个16进制字符
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6','7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };    
    protected static MessageDigest messageDigest = null;  
    static {  
        try {
        	/**
        	 *  创建 MessageDigest 对象
        	 *  创建报文摘要实例:
        	 *  象所有的引擎类一样，获取某类报文摘要算法的 MessageDigest 对象的途径是
        	 *  调用 MessageDigest 类中的 getInstance 静态 factory 方法
        	 */
            messageDigest = MessageDigest.getInstance("MD5");
            //拿到一个MD5转换器，返回实现指定摘要算法的 MessageDigest 对象。  
        } catch (NoSuchAlgorithmException nsaex) {  
            System.err.println(MD5.class.getName()+"初始化失败，MessageDigest不支持MD5!");  
            nsaex.printStackTrace();  
        }  
    }  
  
//    public static void main(String[] args) throws IOException {  
//        long begin = System.currentTimeMillis();  
//  
//        File big = new File("F:\\tmp\\aaa.txt");//文件绝对路径  
//        String md5 = getFileMD5String(big);  
//        //String md5 = getMD5String("a");  
//        long end = System.currentTimeMillis();  
//        System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000) + "s");  
//    }  
    /** 
     * 计算文件的MD5 
     * @param file 文件对象 
     * @return 
     * @throws IOException 
     */ 
    public static String getFileMD5String(File file) throws IOException {  
        FileInputStream in = new FileInputStream(file);  
        FileChannel ch = in.getChannel();  
        //缓冲区大小（这个可以抽出一个参数）  
        int maxSize= 256 * 1024;  
          
        long startPosition=0L;  
        long step=file.length()/maxSize;  
        /**
         *  MappedByteBuffer 将文件直接映射到内存（这里的内存指的是虚拟内存，并不是物理内存，后面说证明这一点）。
         *  通常，可以映射整个文件，如果文件比较大的话可以分段进行映射，只要指定文件的那个部分就可以。
         *  而且，与ByteBuffer十分类似，没有构造函数（你不可new MappedByteBuffer（）来构造一个MappedByteBuffer），
         *  我们可以通过 java.nio.channels.FileChannel 的 map() 方法来获取 MappedByteBuffer 。
         *  其实说的通俗一点就是Map把文件的内容被映像到计算机虚拟内存的一块区域，
         *  这样就可以直接操作内存当中的数据而无需操作的时候每次都通过I/O去物理硬盘读取文件，所以效率上有很大的提升！
         *  
         */  
        if(step == 0){
        	/**
             * 1.MappedByteBuffer是ByteBuffer的直接子类
             * 因为Buffer是用字节数组实现的，MappedByteBuffer也是用字节数组实现的
             * 所以，byteBuffer是字节数组类型
        	 * 
             * 2.向已初始化的报文摘要对象messageDigest提供数据：
             * 这将通过调用 update（更新）方法来完成，传入需要计算的字节数组byteBuffer 
             *             
             * 3.执行MessageDigest对象的digest()方法完成计算，计算的结果通过字节类型的数组返回。
             */
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,file.length());//READ_ONLY,（只读）
            messageDigest.update(byteBuffer);            
            return bufferToHex(messageDigest.digest()); 
        } 
        
        /**
         * 但是如果是一个特别大的文件，一下子把一个文件的数组全部读到内存中，那么估计内存也吃不消
         * 所以，当文件大于maxSize时，将文件分段计算，每一段大小都<=maxSize
         */
        for(int i=0;i<step;i++){  
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, startPosition,maxSize);  
            messageDigest.update(byteBuffer);  
            startPosition+=maxSize;  
        }  
          
        if(startPosition==file.length()){  
        	ch.close();
        	in.close();
            return bufferToHex(messageDigest.digest());  
        }  
  
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, startPosition,file.length()-startPosition);  
        messageDigest.update(byteBuffer);   
        ch.close();
        in.close();
        return bufferToHex(messageDigest.digest());  
    }  
  
    public static String getMD5String(String s) {  
        return getMD5String(s.getBytes());  
    }  
  
    public static String getMD5String(byte[] bytes) {  
        messageDigest.update(bytes);  
        return bufferToHex(messageDigest.digest());  
    }  
  
    private static String bufferToHex(byte bytes[]) {  
        return bufferToHex(bytes, 0, bytes.length);  
    }  
  
    private static String bufferToHex(byte bytes[], int m, int n) {
    	// new一个字符串，这个就是用来存放结果字符串的
        StringBuffer stringbuffer = new StringBuffer(2 * n);  
        int k = m + n;  
        for (int l = m; l < k; l++) {  
            appendHexPair(bytes[l], stringbuffer);  
        }  
        return stringbuffer.toString();//返回结果字符串
    }  
  
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
    	//通过位运算（位运算效率高），转换成字符放到字符c0/c1中去：
    	// 取字节中高 4 位的数字转换为逻辑右移，将符号位一起右移
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        // 取字节中低 4位的数字转换 
        char c1 = hexDigits[bt & 0xf]; 
        //将结果添加到stringbuffer里面
        stringbuffer.append(c0);  
        stringbuffer.append(c1);  
    } 
    
    private static String md5file(File file) throws FileNotFoundException, IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		return DigestUtils.md5Hex(in).toLowerCase();
	}
    public static void main(String[] args) throws IOException {  
        long begin = System.currentTimeMillis();  
  
        File big = new File("F:\\tmp\\aaa.txt");//文件绝对路径  
        String md5 = md5file(big);  
        //String md5 = getMD5String("a");  
        long end = System.currentTimeMillis();  
        System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000) + "s");  
    }  
}  