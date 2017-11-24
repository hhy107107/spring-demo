package me.smallyellow.base.boot.web.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 分页
 * @author hhy 
 * 参考自涂少的fast-boot
 * 2017年11月24日下午4:53:48
 */
public class Page<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_COUNT = 20;
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	
	protected int totalCount = 0;// 总记录数
	protected int pageSize = DEFAULT_COUNT;// 一页显示多少条
	protected int pageNo = 1;// 第几页
	private Map<String, Object> condition;// 查询条件
	private String sort;// 排序条件

	private List<T> list;// 结果集
	
	public Page() {}
	
	public Page(int pageNo, int pageSize, int totalCount) {
		setTotalCount(totalCount);
		setPageSize(pageSize);
		setPageNo(pageNo);
		adjustPageNo();
	}
	
	public Page(int pageNo, int pageSize, int totalCount, List<T> list) {
		this(pageNo, pageSize, totalCount);
		this.list = list;
	}
	
	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}
	
	public String buildLimitExample() {
		return getFirstResult()+","+getPageSize();
	}
	
	public List<?> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * 保证pageNo从1开始
	 * @param pageNo
	 * @return
	 */
	public static int checkPageNo(Integer pageNo) {
		return (pageNo == null || pageNo < 1) ? 1 : pageNo;
	}
	
	/**
	 * 调整页码，使其不超过最大页数
	 */
	public void adjustPageNo() {
		if (pageNo == 1) {
			return;
		}
		int totalPage = getTotalPage();
		if (pageNo > totalPage) {
			pageNo = totalPage;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}
	
	public int getTotalPage() {
		int totalPage = totalCount / pageSize;
		if (totalPage == 0 || totalCount % pageSize != 0) {
			totalPage++;
		}
		return totalPage;
	}
	
	/**
	 * 是否第一页
	 * @return
	 */
	public boolean isFirstPage() {
		return pageNo <= 1;
	}
	
	/**
	 * 是否最后一页
	 * @return
	 */
	public boolean isLastPage() {
		return pageNo >= getTotalPage();
	}
	
	/**
	 * 下一页页码
	 * @return
	 */
	public int getNextPage() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}
	
	public int getPretPage() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}
	
	public void setTotalCount(int totalCount) {
		if (totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
	}
	
	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = DEFAULT_COUNT;
		} else {
			this.pageSize = pageSize;
		}
	}
	
	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}

	public Object getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
}
