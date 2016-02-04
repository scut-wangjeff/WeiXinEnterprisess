package jsp.weixin.msg.Resp;

/**
 * 视频
 * 
 * @author Engineer.Jsp
 * @date 2014.10.08*
 */
public class Video {
	// 媒体文件id
	private String MediaId;
	// 标题
	private String title;
	// 视频描叙
	private String description;
	// 缩略图的媒体id
	private String ThumbMediaId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
