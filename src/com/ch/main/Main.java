package com.ch.main;

import java.util.ArrayList;
import java.util.List;

import com.ch.task.MutiTaskExe;
import com.ch.task.MutiThreadTask;
import com.ch.task.TaskDistribute;

/**
 * 利用多线程实现多任务。
 * 
 * step 1 获取任务列表 taksList
 * step 2 任务分配。把大任务分为多个小任务 （本程序为平摊任务）
 * step 3 实现多任务
 * 
 *   注意 ：任务列表 taksList中的类型
 * @author: ch
 * @date:2016年4月16日 下午5:00:53
 * 
 */
public class Main {

	public static void main(String[] args) {

		//step 1 获取任务列表 taksList
		List<MAttribute> taksList = new ArrayList<MAttribute>();
		for (int i = 0; i < 150; i++) {
			MAttribute mAttribute = new MAttribute("http:\\www " + i, "name: "+ i);
			taksList.add(mAttribute);
		}
		
		System.out.println("task count： " + taksList.size());
		//step2 任务分配。把大任务分为多个小任务
        int threadCount = 4;  // 设定要启动的工作线程数为 4 个  
        List<MAttribute>[] taskListPerThread = TaskDistribute.distributeTasks(taksList, threadCount);
        //step3 实现多任务
        for (int i = 0; i < taskListPerThread.length; i++) {
        	
			List<MAttribute> takslist = taskListPerThread[i];
			//单个任务的具体实现
			MutiTaskExe taskImp = new Processor();
			//开启多线程，多任务同时并发实现
        	MutiThreadTask<MAttribute> taskThread = new MutiThreadTask<MAttribute>(takslist, taskImp, i);
        	taskThread.start();
        	
        }
		
		
		
	}

}
