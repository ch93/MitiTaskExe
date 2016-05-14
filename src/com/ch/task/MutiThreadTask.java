package com.ch.task;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

/**
 * 多任务的实现
 *  其中集成但任务的具体实现（MutiTaskExe）
 * @author ch
 *
 */
public class MutiThreadTask <T>  implements Runnable {
	
	private static int threadCount = 0;
	/**
	 * 线程标识
	 */
	private final int id =  threadCount++;
	/**
	 * 任务列表
	 */
	private BlockingQueue<T> takslist;
	/**
	 * 任务具体实现接口
	 * 需有具体的类实现该接口
	 */
	private MutiTaskExe taskImp;
	
	private Timer timer;
	
	private DecimalFormat decimalFormat = new DecimalFormat(".00");
	//任务启动开始时间
	private final long startTime = System.currentTimeMillis();  
	/**
	 * 当前任务
	 */
	private T task;
	/**
	 * 
	 * @param takslist 任务列表
	 * @param taskImp 任务具体实现接口
	 * @param CurrentT 当前为第几个线程
	 */
	public MutiThreadTask(BlockingQueue<T> takslist, MutiTaskExe taskImp) {
		this.takslist = takslist;
		timer = new Timer();
		this.taskImp = taskImp;
	}
	

	@Override
	public void run() {
	    //启动智能提醒
	    TimeTip();

			while (!takslist.isEmpty()) {
				//任务列表中传过来的参数
				try {
					task = takslist.take();
					//执行任务
					taskImp.exeMutiTask(task);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		System.out.println("Thread " + id + " Mission Accomplished in " + HandleTime(System.currentTimeMillis() - startTime));
	    timer.cancel();
	}
	
	private void TimeTip() {
		
        timer.schedule(new TimerTask() {  
            public void run() { 
            	
            	System.out.println("Thread: "+ id +"\t" 
            			           + task.toString());
            	
//            	long endTime = System.currentTimeMillis(); //获取结束时间 
//            	float tempm = (float)currentN/totalN;
//            	String costTime = HandleTime(endTime-startTime);
//                System.out.println("Thread: "+ id 
//                		+ "  The current progress： " + decimalFormat.format(tempm*100) + "%" 
//                		+ "  costTime(min)： " + costTime);
////                System.out.println("线程"+ CurrentT + " deal with " + takslist.get(currentN).toString());
//                
            }
        }, 1000, 1000*60);// 设定指定的时间time,此处为0.5秒  
        
        
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
