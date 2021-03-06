package com.youbox.util.file;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import android.content.Context;
import android.util.Log;
/**
 * 文件下载器
 */
public class Downloader {
	private static final String TAG = "FileDownloader";
	private Context context;
	private FileService fileService;	
	/* 已下载文件长度 */
	private int downloadSize = 0;
	/* 原始文件长度 */
	private int fileSize = 0;
	/* 线程数 */
	private DownloadThread[] threads;
	/* 本地保存文件 */
	public static File saveFile;
	/* 缓存各线程下载的长度*/
	private Map<Integer, Integer> data = new ConcurrentHashMap<Integer, Integer>();
	/* 每条线程下载的长度 */
	private int block;
	/* 下载路径  */
	private String downloadUrl;
	/**
	 * 获取线程数
	 */
	public int getThreadSize() {
		return threads.length;
	}
	/**
	 * 获取文件大小
	 * @return
	 */
	public int getFileSize() {
		return fileSize;
	}
	/**
	 * 累计已下载大小
	 * @param size
	 */
	protected synchronized void append(int size) {
		downloadSize += size;
	}
	/**
	 * 更新指定线程最后下载的位置
	 * @param threadId 线程id
	 * @param pos 最后下载的位置
	 */
	protected void update(int threadId, int pos) {
		this.data.put(threadId, pos);
	}
	/**
	 * 保存记录文件
	 */
	protected synchronized void saveLogFile() {
		this.fileService.update(this.downloadUrl, this.data);
	}
	/**
	 * 构建文件下载器
	 * @param downloadUrl 下载路径
	 * @param fileSaveDir 文件保存目录
	 * @param threadNum 下载线程数
	 */
	public Downloader(Context context, String downloadUrl, File dir, int threadNum) {
		try {
			print("downloadUrl="+downloadUrl);
			print("dir="+dir);
			this.context = context;
			this.downloadUrl = downloadUrl;
			fileService = new FileService(context);
			URL url = new URL(downloadUrl);
			if(!dir.exists()) dir.mkdirs();
			this.threads = new DownloadThread[threadNum];					
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setConnectTimeout(5*1000);
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
//			conn.setRequestProperty("Accept-Language", "zh-CN");
//			conn.setRequestProperty("Referer", downloadUrl); 
//			conn.setRequestProperty("Charset", "UTF-8");
//			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
//			conn.setRequestProperty("Connection", "Keep-Alive");
//			conn.connect();
//			printResponseHeader(conn);
//			if (conn.getResponseCode()==200) {
				this.fileSize = conn.getContentLength();//根据响应获取文件大小
				print("fileSize="+fileSize);
//			}
//			else{
//				throw new RuntimeException("server no response ");
//			}
//			conn.disconnect();
			print("fileSize="+fileSize);
			if (this.fileSize <= 0) throw new RuntimeException("Unkown file size ");
			
//			String filename = getFileName(conn);
			String filename = this.downloadUrl.substring(this.downloadUrl.lastIndexOf('/') + 1);
			print("filename="+filename);
			this.saveFile = new File(dir, filename);/* 保存文件 */
			Map<Integer, Integer> logdata = fileService.getData(downloadUrl);//获取每条线程已经下载的文件长度
			print("logdata.size="+logdata.size());
			if(logdata==null||logdata.size()==0){
				for(int i=0;i<threadNum;i++){
					data.put(i+1, 0);
				}
			}else{
				for(Map.Entry<Integer, Integer> entry : logdata.entrySet())
					data.put(entry.getKey(), entry.getValue());
			}
			this.block = (this.fileSize % this.threads.length)==0? this.fileSize / this.threads.length : this.fileSize / this.threads.length + 1;
			print("block="+block);
			if(this.data.size()==this.threads.length){
				for (int i = 0; i < this.threads.length; i++) {
					this.downloadSize += this.data.get(i+1);
				}
				print("已经下载的长度"+ this.downloadSize);
			}	
			
		} catch (Exception e) {
			print(e.toString());
			throw new RuntimeException("don't connection this url");
		}
	}

	
	/**
	 *  开始下载文件
	 * @param listener 监听下载数量的变化,如果不需要了解实时下载的数量,可以设置为null
	 * @return 已下载文件大小
	 * @throws Exception
	 */
	public int download(DownloadProgressListener listener) throws Exception{
		try {
//			RandomAccessFile randOut = new RandomAccessFile(this.saveFile, "rw");
//			if(this.fileSize>0) randOut.setLength(this.fileSize);
//			randOut.close();
			URL url = new URL(this.downloadUrl);
			if(this.data.size() != this.threads.length){
				this.data.clear();
				for (int i = 0; i < this.threads.length; i++) {
					this.data.put(i+1, 0);
				}
			}
			for (int i = 0; i < this.threads.length; i++) {
				int downLength = this.data.get(i+1);
				if(downLength < this.block && this.downloadSize<this.fileSize){ //该线程未完成下载时,继续下载						
					this.threads[i] = new DownloadThread(this, url, this.saveFile, this.block, this.data.get(i+1), i+1);
					this.threads[i].setPriority(7);
					this.threads[i].start();
				}else{
					this.threads[i] = null;
				}
			}
			this.fileService.save(this.downloadUrl, this.data);
			boolean notFinish = true;//下载未完成
			while (notFinish) {// 循环判断是否下载完毕
				Thread.sleep(900);
				notFinish = false;//假定下载完成
				for (int i = 0; i < this.threads.length; i++){
					if (this.threads[i] != null && !this.threads[i].isFinish()) {
						notFinish = true;//下载没有完成
						if(this.threads[i].getDownLength() == -1){//如果下载失败,再重新下载
							this.threads[i] = new DownloadThread(this, url, this.saveFile, this.block, this.data.get(i+1), i+1);
							this.threads[i].setPriority(7);
							this.threads[i].start();
						}
					}
				}				
				if(listener!=null) listener.onDownloadSize(this.downloadSize);
			}
			fileService.delete(this.downloadUrl);
		} catch (Exception e) {
			print(e.toString());
			throw new Exception("file download fail");
		}
		return this.downloadSize;
	}
	/**
	 * 获取Http响应头字段
	 * @param http
	 * @return
	 */
	public static Map<String, String> getHttpResponseHeader(HttpURLConnection http) {
		Map<String, String> header = new LinkedHashMap<String, String>();
		for (int i = 0;; i++) {
			String mine = http.getHeaderField(i);
			if (mine == null) break;
			header.put(http.getHeaderFieldKey(i), mine);
		}
		return header;
	}
	/**
	 * 打印Http头字段
	 * @param http
	 */
	public static void printResponseHeader(HttpURLConnection http){
		Map<String, String> header = getHttpResponseHeader(http);
		for(Map.Entry<String, String> entry : header.entrySet()){
			String key = entry.getKey()!=null ? entry.getKey()+ ":" : "";
			print(key+ entry.getValue());
		}
	}

	private static void print(String msg){
		Log.i(TAG, msg);
	}
	public int getThreadsNum(){
		return threads.length;
	}
	
	public static void main(String[] args) {
	/*	FileDownloader loader = new FileDownloader(context, "http://browse.babasport.com/ejb3/ActivePort.exe",
				new File("D:\\androidsoft\\test"), 2);
		loader.getFileSize();//得到文件总大小
		try {
			loader.download(new DownloadProgressListener(){
				public void onDownloadSize(int size) {
					print("已经下载："+ size);
				}			
			});
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

}
