package rest.model;

import java.time.Instant;
import rest.util.BodyHelper;

public class PostBookRequest implements IRequestModel {

	private int id;
	private String title;
	private String description;
	private int pageCount;
	private String excerpt;
	private Instant publishDate;
	
	public int getId() {
		return this.id;
	}
	
	public PostBookRequest setId(int id) {
		this.id = id;
		return this;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public PostBookRequest setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public PostBookRequest setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public int getPageCount() {
		return this.pageCount;
	}
	
	public PostBookRequest setPageCount(int pageCount) {
		this.pageCount = pageCount;
		return this;
	}
	
	public String getExcerpt() {
		return this.excerpt;
	}
	
	public PostBookRequest setExcerpt(String excerpt) {
		this.excerpt = excerpt;
		return this;
	}
	
	public Instant getPublishDate() {
		return this.publishDate;
	}
	
	public PostBookRequest setPublishDate(Instant publishDate) {
		this.publishDate = publishDate;
		return this;
	}
	
	@Override
	public String GetRequestBody() {
		return new BodyHelper()
			.addBodyPrefix()
			.addBodyFieldMiddle("id", id)
			.addBodyFieldMiddle("title", title)
			.addBodyFieldMiddle("description", description)
			.addBodyFieldMiddle("pageCount", pageCount)
			.addBodyFieldMiddle("excerpt", excerpt)
	        .addBodyFieldLast("publishDate", publishDate)
	        .addBodySuffix()
	        .getBody();
	}

}
