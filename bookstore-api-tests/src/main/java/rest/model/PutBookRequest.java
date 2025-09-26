package rest.model;

import java.time.Instant;
import rest.util.BodyHelper;

public class PutBookRequest implements IRequestModel {

	private int id;
	private String title;
	private String description;
	private int pageCount;
	private String excerpt;
	private Instant publishDate;
	
	public int getId() {
		return this.id;
	}
	
	public PutBookRequest setId(int id) {
		this.id = id;
		return this;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public PutBookRequest setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public PutBookRequest setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public int getPageCount() {
		return this.pageCount;
	}
	
	public PutBookRequest setPageCount(int pageCount) {
		this.pageCount = pageCount;
		return this;
	}
	
	public String getExcerpt() {
		return this.excerpt;
	}
	
	public PutBookRequest setExcerpt(String excerpt) {
		this.excerpt = excerpt;
		return this;
	}
	
	public Instant getPublishDate() {
		return this.publishDate;
	}
	
	public PutBookRequest setPublishDate(Instant publishDate) {
		this.publishDate = publishDate;
		return this;
	}
	
	@Override
	public String getRequestBody() {
		return BodyHelper.GSON.toJson(this);
	}

}
