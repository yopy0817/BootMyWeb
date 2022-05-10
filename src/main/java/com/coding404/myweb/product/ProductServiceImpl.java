package com.coding404.myweb.product;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
		
	
	//업로드 할 경로(application.properties값을 참조)
	@Value("${project.upload.path}")
	private String uploadPath;
	
	//폴더생성함수
	public String makeFolder() {
		//날짜별로 폴더생성
		DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyMMdd");
		String date = LocalDateTime.now().format(datetime);
	
//		File file = new File(uploadPath + "\\" + date ); //java.io (업로드 경로 \\ 폴더명 )
		File file = new File(uploadPath + "/" + date ); //java.io (업로드 경로 \\ 폴더명 )
		if(file.exists() == false ) { //폴더가 존재하면 true, 존재하지 않으면 false
			file.mkdir(); //폴더가 생성
		}
			
		return date; //년월일 리턴
	}
	
	
	//multi insert작업 (상품 insert -> 파일 업로드 -> 업로드테이블 insert)
	//transaction라이브러리 필요(부트에서는 포함됨)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int regist(ProductVO vo, List<MultipartFile> list) {
		
		int result = productMapper.regist(vo);
		
		
		
		for(MultipartFile f : list) {
			//1. 파일명추출(브라우저 별로 다를 수 있기 때문에 \\기준으로 파일명 추출)
			String originName = f.getOriginalFilename();
			String filename = originName.substring( originName.lastIndexOf("\\") + 1); 
			
			//2. 업로드 된 파일을 폴더별로 저장(파일생성)
			String filepath = makeFolder(); 
			
			//3. 랜덤값을 이용해서 동일한 파일명의 처리
			String uuid = UUID.randomUUID().toString();
			
			//최종경로
//			String savename = uploadPath + "\\" + filepath + "\\" + uuid + "_" + filename;
			String savename = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
			
			
			//업로드 진행
			try {
				f.transferTo( new File(savename));
			} catch (Exception e) {
				e.printStackTrace();
				return 0; //실패의 의미 0
			} 
			
			//업로드 테이블에 insert진행
			//prod_id는 화면에서 전달되지 않기 때문에 현재 사용할 수 없음. 
			//mybatis에 selectKey기능을 사용. (마이바티스에서 insert전 후에 특정테이블의 키를 구하는 기능)
			ProductUploadVO prodVO = ProductUploadVO.builder()
												   .filename(filename) 
												   .filepath(filepath)
												   .uuid(uuid)
												   .prod_writer( vo.getProd_writer() ) 
												   .build();
			//insert
			productMapper.registFile(prodVO);
			
			
			
			
		}
		
		
		
		
		return result; 
	}


	@Override
	public ArrayList<ProductVO> getList(Criteria cri) {
		return productMapper.getList(cri);
	}


	@Override
	public int getTotal(Criteria cri) {
		return productMapper.getTotal(cri);
	}

	
	
	
	@Override
	public ProductVO getDetail(int prod_id) {
		return productMapper.getDetail(prod_id);
	}


	@Override
	public int update(ProductVO vo) {
		return productMapper.update(vo);
	}


	@Override
	public int delete(int prod_id) {
		return productMapper.delete(prod_id);
	}


	@Override
	public ArrayList<CategoryVO> getCategory() {
		return productMapper.getCategory();
	}


	@Override
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo) {
		return productMapper.getCategoryChild(vo);
	}


	@Override
	public ArrayList<ProductUploadVO> getDetailImg(int prod_id) {
		return productMapper.getDetailImg(prod_id);
	}


}
