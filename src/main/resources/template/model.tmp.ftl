/*
 * 文件名称：${modelName}.java
 */
package ${pakageName}.model;

import javax.persistence.Table;
import javax.persistence.Column;
import me.smallyellow.base.boot.mybatis.bean.Base${idType!''}Entity;
${improtField!''}

/**
 * 名称：${modelCaption!''}
 * 模块描述：数据库表对应实体类
 * 作者：系统自动生成
 */
 @Table(name="${tableName}")
public class ${modelName} extends Base${idType!''}Entity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
${strutField!''}
	/**属性**/
${nameField!''}
	/**属性Get、Set函数**/
${getSetField!''}
}