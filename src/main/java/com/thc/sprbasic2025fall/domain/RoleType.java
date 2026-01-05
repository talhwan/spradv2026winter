package com.thc.sprbasic2025fall.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roleType")
@Entity
public class RoleType{

	@Id
	@Column(length = 32, columnDefinition = "CHAR(32)")
	private String id;

	@Column(length = 191, nullable = false, unique = true)
	private String typeName;

	@Builder.Default
	@OneToMany(mappedBy = "roleType", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<UserRoleType> userRoleType = new ArrayList<>();

	public static RoleType of(String id, String typeName) {
		return new RoleType(id, typeName, null);
	}

}