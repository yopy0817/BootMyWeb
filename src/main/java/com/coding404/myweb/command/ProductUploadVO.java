package com.coding404.myweb.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUploadVO {
//	CREATE TABLE PRODUCT_UPLOAD(
//			UPLOAD_NO INT PRIMARY KEY AUTO_INCREMENT, # PK
//		    FILENAME VARCHAR(100) NOT NULL, #실제 파일명
//		    FILEPATH VARCHAR(100) NOT NULL, #220407형태의 폴더명
//		    UUID VARCHAR(50) NOT NULL, 		#파일이름앞에 들어가는 랜덤값
//		    REGDATE TIMESTAMP DEFAULT NOW(),
//		    PROD_ID INT, ## FK
//		    PROD_WRITER VARCHAR(20) ##FK(편의성을 위한 FK)
//	);
	
	private Integer upload_no;
	private String filename;
	private String filepath;
	private String uuid;
	private LocalDateTime regdate;
	private Integer prod_id;
	private String prod_writer;
	
	
	
	
}
