package com.thc.sprbasic2025fall.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(name = "user_role_type",
		indexes = {
				@Index(name = "IDX_userroletype_createdAt", columnList = "createdAt")
				,@Index(name = "IDX_userroletype_modifiedAt", columnList = "modifiedAt")
		}
)
@Entity
public class UserRoleType extends AuditingFields {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "role_type_id")
	private RoleType roleType;

	protected UserRoleType(){}
	private UserRoleType(User user, RoleType roleType) {
		this.user = user;
		this.roleType = roleType;
	}

	public static UserRoleType of(User user, RoleType roleType) {
		return new UserRoleType(user, roleType);
	}
	
}