package com.back.balbadak.model;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import cbhs.framework.utils.MessageUtils;
//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CommonResponse<T> {

//	@Schema(required = false, description = "Details of failure when the API response fails")
    private MessageResponse message;
    private T data;
    
//    @Schema(required = true, description = "Whether the API response is successful")
    @Builder.Default
    private boolean isSuccess = true;
    
//    @Schema(required = false, description = "total page count if API respond with a pageable")
//    private Integer totalPages;


    /**
     * 메시지 설정
     *
     */
    @JsonIgnore
    public void setMessage(boolean isSuccess, MessageResponse message ) {
    	this.isSuccess = isSuccess;
        this.message = message;
    }

    /**
     * 정상 처리 시 메시지 설정
     *
     * @param code
     * @param args
     */
    @JsonIgnore
    public void setMessage(boolean isSuccess, String code, String... args) {
    	this.isSuccess = isSuccess;
    	if(message == null) {
    		message = new MessageResponse();
    	}
        message.setCode(code);
//        message.setMessage(MessageUtils.getMessage(code, args));
    }

    /**
     * @param data
     * @return
     */
    public static <T> CommonResponse<T> create(T data) {
        return CommonResponse.<T>builder().data(data)
                .build()
                .setResponseInfo();
    }

    public static CommonResponse<Void> createWithNoData() {
        return CommonResponse.<Void>builder()
                .build()
                .setResponseInfo();
    }

    public void setData(T data) {
        this.data = data;

        this.setResponseInfo();
    }

    /**
     * CommonResponse CommonHeader 정보 설정
     * 객체 생성 시, Data 설정 시 호출
     *
     * @return
     */
    private CommonResponse<T> setResponseInfo() {
        //nothing
        return this;
    }

}
