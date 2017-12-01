package com.rpc.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceCenter implements Server {
	
	private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	private static final HashMap<String,Class> serviceRegisty = new HashMap<String,Class>();
	
	private static boolean isRunning = false;
	
	private static int port;
	
	public ServiceCenter(int port) {
		this.port = port;
	}
	@Override
	public void stop() {
		isRunning = false;
		executor.shutdown();

	}

	@Override
	public void start() throws IOException {
		ServerSocket server = new ServerSocket();
		
		server.bind(new InetSocketAddress(port));
		
		System.err.println("start server");
		
		try {
			while(true) {
				//1.监听客户端的TCP连接，接到TCP连接后将其封装成task，由线程池执行
				executor.execute(new ServiceTask(server.accept()));
			}
		} finally  {
			server.close();
		}

	}
	private static class ServiceTask implements Runnable {
		Socket clent = null;
		
		public ServiceTask(Socket client) {
			this.clent = client;
		}

		@Override
		public void run() {
			ObjectInputStream input = null;
			ObjectOutputStream output = null;
			
			try {
				input = new ObjectInputStream(clent.getInputStream());
				String serviceName = input.readUTF();
				String methodName = input.readUTF();
				Class<?>[] parameterTypes = (Class<?>[])input.readObject();
				
				Object[]  arguements = (Object[]) input.readObject();
				Class<?> serviceClass = serviceRegisty.get(serviceName);
				if(serviceClass == null) {
					throw new ClassNotFoundException(serviceName + " not found!");
				}
				Method method = serviceClass.getMethod(methodName, parameterTypes);
				Object result = method.invoke(serviceClass.newInstance(), arguements);
				
				// 3.将执行结果反序列化，通过socket发送给客户端
				output = new ObjectOutputStream(clent.getOutputStream());
				output.writeObject(result);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(clent != null) {
					try {
						clent.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		} 
	}

	@Override
	public void register(@SuppressWarnings("rawtypes") Class serviceInterface, @SuppressWarnings("rawtypes") Class impl) {
		serviceRegisty.put(serviceInterface.getName(), impl);
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public int getPort() {
		return port;
	}

}
