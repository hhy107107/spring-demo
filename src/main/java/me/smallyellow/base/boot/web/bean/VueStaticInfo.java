package me.smallyellow.base.boot.web.bean;

/**
 * Vue静态资源
 */
public class VueStaticInfo {
	
	private String appCss;
	private String manifestJs;
	private String vendorJs;
	private String appJS;
	private boolean init = false;
	
	public String getAppCss() {
		return appCss;
	}
	public void setAppCss(String appCss) {
		this.appCss = appCss;
	}
	public String getManifestJs() {
		return manifestJs;
	}
	public void setManifestJs(String manifestJs) {
		this.manifestJs = manifestJs;
	}
	public String getVendorJs() {
		return vendorJs;
	}
	public void setVendorJs(String vendorJs) {
		this.vendorJs = vendorJs;
	}
	public String getAppJS() {
		return appJS;
	}
	public void setAppJS(String appJS) {
		this.appJS = appJS;
	}
	public boolean isInit() {
		return init;
	}
	public void setInit(boolean init) {
		this.init = init;
	}

}