package com.ch.task;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 多任务的实现
 *  其中集成但任务的具体实现（MutiTaskExe）
 * @author ch
 *
 */
public class MutiThreadTask <T>  extends Thread {
	/**
	 * 任务列表
	 */
	private List<T> takslist;
	/**
	 * 任务具体实现接口
	 * 需有具体的类实现该接口
	 */
	private MutiTaskExe taskImp;
	/**
	 * 当前正在做的任务
	 */
	private int currentN = 0;
	/**
	 * 任务总数
	 */
	private int totalN = 1;

	/**
	 * 当前为第几个线程
	 */
	private int CurrentT = -1;
	
	private Timer timer;
	
	private DecimalFormat decimalFormat = new DecimalFormat(".00");
	/**
	 * 
	 * @param takslist 任务列表
	 * @param taskImp 任务具体实现接口
	 * @param CurrentT 当前为第几个线程
	 */
	public MutiThreadTask(List<T> takslist, MutiTaskExe taskImp,int CurrentT) {
		this.takslist = takslist;
		timer = new Timer();
		this.CurrentT = CurrentT;
		this.taskImp = taskImp;
	}
	

	@Override
	public void run() {
	    //智能提醒
	    TimeTip();
	    totalN = takslist.size();
		if (totalN > 0) {
			
			for (int i = 0; i < takslist.size(); i++) {
				//任务列表中传过来的参数
				T task = takslist.get(i);
				//执行任务
				taskImp.exeMutiTask(task);
				
				currentN = i;
			}
			
		} else {
			System.out.println("this is no taks in taskList");
		}
		System.out.println("Thread " + CurrentT + " Mission Accomplished ！");
	    timer.cancel();
	}
	
	private void TimeTip() {
		final long startTime=System.currentTimeMillis();   //获取开始时间

        timer.schedule(new TimerTask() {  
            public void run() {  
            	long endTime=System.currentTimeMillis(); //获取结束时间 
            	float tempm = (float)currentN/totalN;
            	String costTime = HandleTime(endTime-startTime);
                System.out.println("Thread: "+ CurrentT 
                		+ "  The current progress： " + decimalFormat.format(tempm*100) + "%" 
                		+ "  costTime(min)： " + costTime);
//                System.out.println("线程"+ CurrentT + " deal with " + takslist.get(currentN).toString());
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
			result = "" + mTime/(60*1000) + " min" ;
		} else {
			result = "" + mTime/(60*60*1000) + " h" ;
		} 
		return result;
	}

}
