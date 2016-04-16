package com.ch.main;

import org.apache.log4j.Logger;

import com.ch.task.MutiTaskExe;

/**
 * 
 * 任务的具体实现逻辑
 *    必须实现接口MutiTaskExe
 * @author: ch
 * @date:2016年4月16日 下午5:10:13
 * 
 */
public class Processor implements MutiTaskExe{

	private Logger log = Logger.getLogger(Processor.class);
	
	private boolean method1() {
		return false;
	}
	
	private boolean method2() {
		
		return true;
		
	}
	
	/**
	 * 各种操作逻辑的集合
	 * @param param 为任务列表中的任务，
	 * T的类型 具体参见 MutiThreadTask中 run() 方法
	 */
	@Override
	public <T> void exeMutiTask(T param) {
		
		MAttribute mAttribute = (MAttribute)param;
		
		method1();
		method2();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("do the task: " + mAttribute.toString());
		
		//如果任务执行失败，则写入日志中
		//log.info("任务 失败" + mAttribute.toString());
	}


}
