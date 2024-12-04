package com.spring.domain;

import java.sql.Timestamp;
import java.util.Date;



public class Post 
{
	public Post() {}
	
	private String id;
	private String title;
	private String contents;
	private Timestamp publishDate;
	private int view;
	private int likes;
	private String region;
	private boolean isPrivate;
	private int p_unique;
	private Date commentDate;
	private int commentLikes;
	private boolean commentIsAllowed;
	private int satisfaction;
	private Dining dining;
	private Landmark landmark;
	private Festival festival;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Timestamp getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Timestamp timestamp) {
		this.publishDate = timestamp;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(String isPrivate) {
	    if(isPrivate != null) {
	        this.isPrivate = "1".equals(isPrivate);
	    }
	}

	public int getP_unique() {
		return p_unique;
	}
	public void setP_unique(int p_unique) {
		this.p_unique = p_unique;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public int getCommentLikes() {
		return commentLikes;
	}
	public void setCommentLikes(int commentLikes) {
		this.commentLikes = commentLikes;
	}
	public boolean isCommentIsAllowed() {
		return commentIsAllowed;
	}
	public void setCommentIsAllowed(boolean commentIsAllowed) {
		this.commentIsAllowed = commentIsAllowed;
	}
	public int getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}
	public Dining getDining() {
		return dining;
	}
	public void setDining(Dining dining) {
		this.dining = dining;
	}
	public Landmark getLandmark() {
		return landmark;
	}
	public void setLandmark(Landmark landmark) {
		this.landmark = landmark;
	}
	public Festival getFestival() {
		return festival;
	}
	public void setFestival(Festival festival) {
		this.festival = festival;
	}
	
	
	
}
