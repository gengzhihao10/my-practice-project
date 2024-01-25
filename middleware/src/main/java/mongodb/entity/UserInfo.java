package mongodb.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Indexed;


/**
 * user Entry
 */
@Document(collection = "users")
public class UserInfo {
	@Id
	private ObjectId id;
	private String name;  
	/*
	 * 用户名不允许重复,设置唯一索引
	 */
//	@Indexed(unique=true)
	private String username; 

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", username=" + username + "]";
	}
	
	
}
