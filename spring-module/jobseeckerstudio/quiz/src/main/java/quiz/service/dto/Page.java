package quiz.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Page<T> {

	private int totalPage;

	private final int pageSize = 10;

	private int page;

	private T items;

	@Builder
	public Page(final int totalPage, final int page, final T items) {
		this.totalPage = totalPage;
		this.page = page;
		this.items = items;
	}

	@Builder
	public Page(final int page, final T items) {
		this.page = page;
		this.items = items;
	}


	public int getOffset() {
		return (this.page - 1) * pageSize;
	}
}
