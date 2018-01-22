package me.smallyellow.hhy.websocket.bean;


/**
 * 文件消息
 * @author hhy
 * 2017年12月21日下午6:03:35
 */
public class FileMessageBean extends MessageBean{

	private static final long serialVersionUID = -2231872928091311795L;

	private String url; //文件地址
	
	private float size; //文件大小
	
	private String suffix; //文件后缀
	
	private FILE_TYPE fileType; //文件类型 （决定了文件的显示方式） 
	
	public enum FILE_TYPE {
		IMAGE((short)1),
		FILE((short)2);
		private final Short value;
	    //构造方法必须是private或者默认
	    private FILE_TYPE(Short value) {
	        this.value = value;
	    }

	    public FILE_TYPE valueOf(Short value) {
	        switch (value) {
	        case 1:
	            return FILE_TYPE.IMAGE;
	        case 2:
	            return FILE_TYPE.FILE;
	        default:
	            return null;
	        }
	    }

		public Short getValue() {
			return value;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public FILE_TYPE getFileType() {
		return fileType;
	}

	public void setFileType(FILE_TYPE fileType) {
		this.fileType = fileType;
	}

	@Override
	public String toString() {
		return "FileMessageBean [url=" + url + ", size=" + size + ", suffix=" + suffix + ", fileType=" + fileType
				+ ", to=" + to + ", from=" + from + ", type=" + type + ", status=" + status + "]";
	}
	
}
