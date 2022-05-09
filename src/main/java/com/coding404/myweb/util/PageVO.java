package com.coding404.myweb.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;


@Data //getter, setter
public class PageVO {
	//페이지네이션을 그리는 클래스
	private int start; //첫페이지번호
	private int end; //마지막페이지번호
	private boolean prev; //이전버튼
	private boolean next; //다음버튼
	
	private int page; //조회하는 페이지 번호
	private int amount; //데이터 개수
	private int total; //전체게시글 수
	private int realEnd; //실제 끝번호
	
	private Criteria cri;
	
	private List<Integer> pageList; //타임리프에서는 향상된 for문을 제공하기 때문에 페이지번호목록을 리스트에 저장
	
	
	
	//생성자 - pageVO는 생성될 떄, Criteria와 전체게시글 수를 받음.
	public PageVO(Criteria cri, int total) {
		//페이지번호, 데이터개수, 총게시글 수 초기화
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		//1. 끝페이지 계산
		//page가 5라면 -> 끝페이지 번호 10
		//page가 15라면 -> 끝페이지 번호 20
		//공식 = (int)Math.ceil( 조회하는 페이지 / 페이지네이션 개수) * 페이지네이션 개수
		this.end = (int)Math.ceil( this.page / 5.0 ) * 5;
		
		//2. 첫 페이지 계산
		//공식 = 끝페이지 - 페이지네이션 개수 + 1
		this.start = this.end - 5 + 1;
		
		//3. 실제 끝페이지 번호 계산
		//총 게시글 수가 53개 -> 실제끝번호 6, 마지막 페이지 10
		//총 게시글 수가 171개 -> 실제끝번호는 18, 마지막 페이지 20
		
		//공식 = (int)Math.ceil(전체게시글 수 / 데이터 개수 )
		this.realEnd = (int)Math.ceil( this.total / (double)this.amount);
		
		//4. 실제 끝번호를 다시 결정
		//141게시글 -> 
		//1~10 조회시에는 끝페이지는 10, 실제 끝번호 15
		//11~20 조회시에는 끝페이지는 20, 실제 끝번호 15
		if(this.end > this.realEnd ) {
			this.end = this.realEnd;
		}
		
		//5. 이전버튼 활성화 여부
		//start는 1, 11, 21, 31 .........
		//시작버튼이 활성화 되는 경우는 11번 부터
		this.prev = this.start > 1;
		
		//6. 다음버튼 활성화 여부 (조건 = 4번의 계산과 반대)
		this.next = this.realEnd > this.end;
		
		
		//7. pageList처리
//		for(int i = this.start; i <= this.end; i++) {
//			this.pageList.add(i);
//		}
		
		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect( Collectors.toList() ) ;
		
		
		
	}
	
	
	
}
