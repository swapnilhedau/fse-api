package com.cts.fse.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARENT_TASK")
public class ParentTask implements Serializable {

	private static final long serialVersionUID = 4924544452448219519L;

	@Id
	@Column(name = "PARENT_ID", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer parentId;

	@Column(name = "PARENT_TASK")
	private String parentTask;

	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the parentTask
	 */
	public String getParentTask() {
		return parentTask;
	}

	/**
	 * @param parentTask the parentTask to set
	 */
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

}
