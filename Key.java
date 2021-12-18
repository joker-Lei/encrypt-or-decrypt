import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This is a java program to unlock the "locked.txt" file.It is very easy to run this 
 * program if you have already downloaded JDK.
 * 
 * You just need to copy the "locked.txt" and "Key.txt" to desktop and change the 
 * suffix of "Key.txt" to ".java" ,then input "cd desktop" into DOS (for Windows) or 
 * Terminal/Shell (for Unix/Linux) to change directory to desktop and then input "java Key.java" 
 * to run this program.(JDK11 or later is required).
 * 
 * If you can see the following contents in DOS,it means the program has been
 * successfully running:
 * 
 * +------------------------------------------------+
 * |C:\WINDOWS\system32\cmd.exe  	            |
 * |--------------------------------------------------|
 * |Microsoft Windows [版本 10.0.19043.1348]  |
 * |(c) Microsoft Corporation。保留所有权利。   |
 * |	       			            |
 * |C:\Users\27795>cd desktop	            |
 * |				            |
 * |C:\Users\27795\Desktop>java Key.java        |
 * |input account:			            |
 * +------------------------------------------------+
 * 
 * After that,input the "account" and "password" that stored in the map.The map is declared 
 * in the InnerClass at the end of this program that acted like a repository.You can see the end of 
 * this file to check the data.
 * 
 * In other words,when the program start running(after you type "java Key.java" and press
 * Enter),you can input  
 * "panasonic" as "account" and "panasonic" as "password" 
 * or "lihaoxian" as "account" and "lihaoxian" as "password" 
 * or "joker" as "account" and "joker" as "password".
 * 
 * Note:make sure the Charset of "locked.txt" is UTF-8.
 * Note:If the version of your JDK is NOT 11+(earlier than JDK11).You should input 
 * "javac Key.java" first,to create "class file".After the "Key.class" is generated,input 
 * "java Key" to run the program.（you can input "java -version" into DOS for windows
 * or Terminal/Shell for Unix/Linux to check you JDK version).
 * Although you can run this program with JDK11-. I still suggest JDK11+ because
 * it might have some problems when you use "javac" to compile this file.Ofcourse,
 * you can ignore this suggestion if you use IDE to run this program.But if you do so,
 * you need to change the filepath of InputStream and OutputStream.
 * 
 * After the "verify",file "locked.txt" will be unlocked(decrypted).Check the new file:"unlocked.txt"
 * on your desktop.Congratulations,you have found the "Easter Egg".Or I should say:
 * Thank you for watching my "post-credits scene".Thanks for watching what I have written.
 * And also,Thank you for running my program.
 *（"Easter Egg" and "post-credits scene" in Chinese is the same,calls "彩蛋"）
 * 
 * 这是一个可以解锁“locked.txt”文件的java程序，如果你已经安装了JDK，运行此程序将会非常简单。
 * 
 * 你只需要把“locked.txt”和“Key.txt”复制到桌面，然后把“Key.txt”的后缀改成“.java”，打开windows
 * 的DOS命令行或者Linux/Unix的Terminal/Shell,输入“cd desktop”进入桌面，输入“java Key.java”即可运行程序
 * (需要JDK11或以上版本)。
 * 
 * 如果你在DOS中看到了以下内容，表示程序已经成功运行：
 * 
 * +------------------------------------------------+
 * |C:\WINDOWS\system32\cmd.exe  	            |
 * |--------------------------------------------------|
 * |Microsoft Windows [版本 10.0.19043.1348]  |
 * |(c) Microsoft Corporation。保留所有权利。   |
 * |	       			            |
 * |C:\Users\27795>cd desktop	            |
 * |				            |
 * |C:\Users\27795\Desktop>java Key.java        |
 * |input account:			            |
 * +------------------------------------------------+
 * 
 * 然后输入“账号”和“密码”，它们被保存在了本程序的末尾。你可以在本文件的最下面找到一个内部类，
 * 里面声明了一个哈希表，表中存放了三条数据。你可以查看里面的数据。
 * 
 * 说人话的话，就是：当程序开始运行后（在你输入“java Key.java”并按下回车后），你可以输入
 * "账号"：panasonic,"密码"：panasonic
 * 或者"账号"：lihaoxian,"密码"：lihaoxian
 * 或者"账号"：joker,"密码"：joker
 * 
 * 注意：请确保“locked.txt”的字符集为UTF-8。
 * 注意：如果你的JDK版本低于11，需要先输入“javac Key.java”生成字节码文件，当“Key.class”生成后，
 * 再输入“java Key”运行程序。(你可以在Windows的DOS命令行里输入"java -version"查看你当前的JDK版本)
 * 虽然JDK11以下依然可以运行此程序，但是当你使用javac指令编译此文件时可能会有莫名的错误，
 * 所以我还是推荐使用11+。当然，如果你是使用IDE去跑这个程序的话，你可以无视这个建议。但如果你这样做
 * 的话，你需要修改输出流和输入流的文件路径
 * 
 * 完成“验证”后，乱码文件就会被解锁，还原成原来的文字，在你的桌面会生成一个新文件：
 * "unlocked.txt"文件。里面是解密后（被还原）的文字。
 * 恭喜你，你已经找到了我的“彩蛋”。
 * 
 * @author  Li Haoxian (Joker Lei)  ※Lei is the Cantonese Pinyin of "李"
 * @date 2022年2月10日 8:21
 */
public class Key {
	
	public static void main(String[] args){
		
		Key key = new Key();
		/*
		 * 根据verify方法返回的4种状态码进行4种不同的操作。仅在返回VERIFY_PASS时才会有实际的
		 * 操作，其余3种状态码仅会在控制台输出一句话并退出程序。当然，由于这个程序是开源的，
		 * 你完全可以通过修改源码的方式跳过"验证"，让它直接执行解密方法。
		 */
		switch(key.verify()) {
			case VERIFY_PASS://进行解密（或加密）
				key.lockOrUnlockFile();
				System.out.println("file has been unlocked,check the new file: unlocked.txt");
				break;
			case INPUT_EXIT://主动退出程序
				System.out.println("Bye!");
				break;
			case VERIFY_WRONG://被动退出程序
				System.out.println("10 times wrong input,auto shutdown");
				break;
			case VERIFY_ERROR://进入catch块时的返回值
				System.out.println("did you change my code?");//基本不会出现这种情况，除非有人修改源码并改出了BUG
		}
	}
	
	/**
	 * the method to lock or unlock file
	 * 
	 * 用于加密或解密的方法
	 */
	public void lockOrUnlockFile() {
		InputStreamReader isr = null;
		OutputStreamWriter osw = null;
		try {	
				//创建输入流，将加密文件从磁盘读取到内存中，以进行一些“不可描述”的操作，这个是相对路径
				//如果你需要写绝对路径，可以参考再下面一行的绝对路径，这是在我电脑中的绝对路径。
				InputStream fis = new FileInputStream("locked.txt");
//				InputStream fis = new FileInputStream("C:\\Users\\27795\\desktop\\locked.txt");			
				isr = new InputStreamReader(fis,"utf-8");
			
				//创建输出流，指定生成的解密文件的路径，同样道理，你也可以将以下相对路径改为绝对路径。
				OutputStream fos = new FileOutputStream("unlocked.txt");
//				OutputStream fos = new FileOutputStream("C:\\Users\\27795\\desktop\\unlocked.txt");
				osw = new OutputStreamWriter(fos,"utf-8");

				char[] buf = new char[1024];//创建字符型数组，作为IO的容器			
				int len;
				/*
				 * 反复读取内存中文件的内容，直至读到文件末尾，每次取出内容后，将每一个字符
				 * 与65535进行异或运算，原文件就是通过与65535进行异或得到加密文件的。众所周知：
				 * a与b进行异或运算后，将结果c再次与b进行异或运算，可重新得到a。即：
				 * a⊕b=c,c⊕b=a，b被称作“秘钥”，在这里，65535即为密钥
				 */
				while((len = isr.read(buf)) != -1){
					for(int i = 0;i < len;i++) {
						buf[i] ^= -1;//此处-1和65535是等价的，可将16位bit全部反转，达成加密（或解密）效果
					}
					osw.write(buf, 0, len);//异或操作完成后，将字符数组从内存输出到硬盘，保存在名为"unlocked.txt"的文件中
				}	
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			//关闭资源
			try {
				if(osw != null)
					osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(isr != null)
					isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}		
	}
	
	/**
	 * method to verify your input,you just need to input one set of the data in the InnerClass：Repository
	 * And you can input "exit" to exit program.
	 * 
	 * 此方法用于验证你的输入，你只需要输入下方内部类Repository中三条数据的任意一条即可，输入"exit"可退出程序
	 *  
	 * @return VERIFY_PASS when your input is correct.
	 * 	Return INPUT_EXIT when you input "exit".
	 * 	Return VERIFY_WRONG when you input 10 times wrong data.
	 *  	Return VERIFY_ERROR when the method has bug.
	 */
	private StatusCode verify() {
		
		Scanner scanner = null;
		
		try{
			scanner = new Scanner(System.in);
			Integer e = 0;//用于计算错误次数的变量
			while(e < 10) {
				//获取键盘输入并与数据进行比对
				System.out.println("input account:");
				String account = scanner.next();
				
				//输入exit时退出程序
				if("exit".equals(account)) {
					return StatusCode.INPUT_EXIT;
				}
				
				//不为"exit"时，判断是否存在此“用户名”，即判断哈希表中是否存在这个键
				if(Repository.data.containsKey(account)) {
						System.out.println("input password:");
						String password = scanner.next();
						
						//输入exit时退出程序
						if("exit".equals(password)) {
							return StatusCode.INPUT_EXIT;
						}
						
						//不为"exit"时，将“用户名”作为键，获取它在哈希表中的值，判断是否等于输入的“密码”
						if(Repository.data.get(account).equals(password)) {
							return StatusCode.VERIFY_PASS;
						}else {
							System.out.println("password is not match");
							e++;
							continue;
						}								
				}else {
					System.out.println("account is not exist");
					e++;
					continue;
				}		
			}
			//出现十次错误后返回错误状态码
			return StatusCode.VERIFY_WRONG;
		}catch(Exception e) {
			e.printStackTrace();
			return StatusCode.VERIFY_ERROR;//基本不会进入这里
		}finally {
			//释放资源
			if(scanner != null)
				scanner.close();
		}
		
	}
	
	/**
	 * 
	 * @author Joker Lei
	 *
	 * An InnerClass to declare statuscode for "verifying"
	 * 
	 * 此内部类声明当你进行“验证”时的状态码
	 */
	static enum StatusCode{
		
		INPUT_EXIT,//输入exit后返回的状态码
		
		VERIFY_PASS,//输入正确数据后验证通过
		
		VERIFY_WRONG,//输入10次错误内容后返回的状态码
		
		VERIFY_ERROR;//方法出异常时返回的状态码
		
	}
	
	/**
	 * 
	 * @author Joker Lei
	 * 
	 * An InnerClass to act like a repository,it has only one hashmap and three sets of data
	 * The key is used as "account" and the value is used as "password"
	 * 
	 * 此内部类模拟一个简易数据库，里面只有一张哈希表以及三条数据，键值对中的键用作“用户名”,值用作“密码”
	 */
	static class Repository{
		
		static Map<String,String> data;

		//由于此程序是开源的，你可以往哈希表里添加任何数据作为你的“账号”和“密码”
		static{
			data = new HashMap<String,String>();
			data.put("panasonic", "panasonic");
			data.put("lihaoxian", "lihaoxian");
			data.put("joker", "joker");
		}		
	}
}
