package com.cts.fse.domain;

public class ViewTask {

	private Task task;
	private ParentTask parentTask;

	public ViewTask(Task task, ParentTask parentTask) {
		this.task = task;
		this.parentTask = parentTask;
	}

	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * @return the parentTask
	 */
	public ParentTask getParentTask() {
		return parentTask;
	}

	/**
	 * @param parentTask the parentTask to set
	 */
	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}

}
