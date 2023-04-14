package com.back.balbadak.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MessageResponse {

	String code;
	@JsonProperty(value = "description")
	String message;
	List<Detail> details;
	
	
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Detail {
		String key;
		String value;
	}
}
