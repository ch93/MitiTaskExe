package com.ch.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import com.ch.task.MutiTaskExe;
import com.ch.task.MutiThreadMonitor;
import com.ch.task.MutiThreadTask;

public class BlackingQueueMain {

	public static void main(String[] args) {
		//step 1 获取任务列表 taskList 
		final BlockingQueue<MAttribute> taskList = new LinkedBlockingQueue<MAttribute>();
//		List<MAttribute> taksList = new ArrayList<MAttribute>();
		for (int i = 0; i < 50; i++) {
			MAttribute mAttribute = new MAttribute("http:\\www " + i, "name: "+ i);
			try {
				taskList.put(mAttribute);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("task count： " + taskList.size());
		//step2 任务分配。把大任务分为多个小任务
        final int threadCount = 4;  // 设定要启动的工作线程数为 4 个  
//        List<MAttribute>[] taskListPerThread = TaskDistribute.distributeTasks(taksList, threadCount);
        //step3 实现多任务
        ExecutorService exec = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
        	
//			List<MAttribute> takslist = taskListPerThread[i];
			//单个任务的具体实现
			MutiTaskExe taskImp = new Processor();
			//开启多线程，多任务同时并发实现
        	MutiThreadTask<MAttribute> taskThread = new MutiThreadTask<MAttribute>(taskList, taskImp);
        	exec.execute(taskThread);
        	
        }
        exec.shutdown();
        
        MutiThreadMonitor<MAttribute> monitor = new MutiThreadMonitor<MAttribute>(taskList);
        monitor.monitorTaskCondtion();

	}

}
