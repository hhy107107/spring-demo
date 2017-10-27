package me.smallyellow.base.boot.mybatis.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 基础表结构
 */
public class BaseLongEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
