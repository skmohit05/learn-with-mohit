package com.learnwithmohit.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="solution")
public class Solution {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;
	
	@Column(name = "code")
	private String code;
}
