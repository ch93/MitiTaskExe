package com.ch.task;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

public class MutiThreadMonitor<T> {
	
	/**
	 * 任务总数
	 */
	private final int totalN ;
	
	private BlockingQueue<T> queue;
	
	//任务启动开始时间
	private final long startTime = System.currentTimeMillis();  
	
	private Timer timer;
	
	private DecimalFormat decimalFormat = new DecimalFormat(".00");
	
	public MutiThreadMonitor(BlockingQueue<T> queue) {
		this.queue = queue; 
		totalN = queue.size();
		timer = new Timer();
	}
	/**
	 * 监视当前任务的情况
	 */
	public void monitorTaskCondtion() {
        timer.schedule(new TimerTask() {  
            public void run() {  
            	long endTime = System.currentTimeMillis(); //获取结束时间 
            	float tempm = (float)(totalN - queue.size())/totalN;
            	String costTime = HandleTime(endTime-startTime);
                System.out.println("/============================================\n"
                		+ "  The current progress： " + decimalFormat.format(tempm*100) + "%" 
                		+ "  costTime： " + costTime
                		+ "\n/============================================");
                
                if (queue.isEmpty()) {
                	timer.cancel();
				}
            }
        }, 1000, 1000*30);// 设定指定的时间time,此处为0.5秒  
	}
	

	private String HandleTime(long mTime) {
		String result = null;
		if (mTime < 1000) { // 1s
			result = "" + mTime + " ms" ;
		} else  if (mTime < 60*1000) { // 1min
			result = "" + mTime/1000 + " s" ;
		} else  if (mTime < 60*60*1000) {  // 1h
			result = "" + decimalFormat.format((float)mTime/(60*1000)) + " min" ;
		} else {
			result = "" + decimalFormat.format((float)mTime/(60*60*1000)) + " h" ;
		} 
		return result;
	}


}
