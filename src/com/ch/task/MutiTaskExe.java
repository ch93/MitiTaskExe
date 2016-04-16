package com.ch.task;

/**
 * 任务具体实现接口
 *   需有具体的类实现该接口
 * @author: ch
 * @date:2016年4月16日 下午3:28:44
 * 
 */
public interface MutiTaskExe {
	
	/**
	 * 各种操作逻辑的集合
	 * @param param 为任务列表中的任务，
	 * T的类型 具体参见 MutiThreadTask中 run() 方法
	 */
	<T> void exeMutiTask(T param);

}
