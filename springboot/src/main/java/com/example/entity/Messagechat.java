package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 聊天记录
 */
@Data
@TableName("messagechat")
public class Messagechat  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 消息记录 */
	private String chatid;
	/** 学生id */
	@TableField(exist = false)
	private String studentid;
	/** 开始时间 */
	private String starttime;
	/** 结束时间 */
	private String endtime;
	/** AI总结 */
	private String chatsummary;
	/** 存放记录 */
	private String pertensepath;
	/** AI聊天本体 */
	private String chatself;
	/** 学生Id */
	private Integer studentId;


	public Integer getId() {
		return id;
	}

	public String getChatid() {
		return chatid;
	}

	public String getStudentid() {
		return studentid;
	}

	public String getStarttime() {
		return starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public String getChatsummary() {
		return chatsummary;
	}

	public String getPertensepath() {
		return pertensepath;
	}

	public String getChatself() {
		return chatself;
	}

	public Integer getStudentId() {
		return studentId;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public void setChatid(String chatid) {
		this.chatid = chatid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setChatsummary(String chatsummary) {
		this.chatsummary = chatsummary;
	}

	public void setPertensepath(String pertensepath) {
		this.pertensepath = pertensepath;
	}

	public void setChatself(String chatself) {
		this.chatself = chatself;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
}
