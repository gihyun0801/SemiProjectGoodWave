package edu.kh.goodWave.volunteer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.goodWave.board.model.dto.Board;
import edu.kh.goodWave.boardService.BoardService;
import edu.kh.goodWave.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("community")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {

	private final BoardService service;

	@GetMapping("FAQ")
	public String FAQ() {
		
		return "/community/FAQ";
	}
  
  
	
	@GetMapping("QNA")
	public String QNA(
			@RequestParam(value="cp" , required=false, defaultValue="1") int cp,
			@RequestParam(value="searchInput", required=false) String searchInput,
			Model model
			) {
  
		Map<String, Object> map = null;
				
		if(searchInput == null) {
			
			map = service.selectBoardList(cp);
		} else {
			
			 map = service.search(searchInput,cp);
			 model.addAttribute("searchInput",searchInput);
		}
				
			
      
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));
  
  

		return "/community/qnaboard";

}
//  
//	/** 검색하기
//	 * @param searchInput
//	 * @return
//	 */
//	@GetMapping("search")
//	public String search(@RequestParam("searchInput") String searchInput,
//			@RequestParam(value="cp" , required=false, defaultValue="1") int cp,
//			Model model) {
//		
//		Map<String, Object> map = service.search(searchInput,cp);
//		
//		model.addAttribute("pagination", map.get("pagination"));
//		model.addAttribute("boardList", map.get("boardList"));
//		model.addAttribute("searchInput",searchInput);
//		
//		
//		
//		return"/community/qnaboard";
//	}
//  

	@GetMapping("qnaboard")
	public String QNA() {
		return "/community/qnaboard";
	}

	
	@GetMapping("qnawrite")
	public String QNAwrite() {
		return "/community/qnawrite";
	}
	
	@PostMapping("qnawrite")
	public String QNAwrite(Board board,
							RedirectAttributes ra,
							@SessionAttribute("loginMember") Member loginMember,
							@RequestParam("images") List<MultipartFile> images
							
			) {

		
		board.setMemberNo(loginMember.getMemberNo());
		
		

		int result = service.qnaWrite(board,images);
		
		if(result >0) {
			
			ra.addFlashAttribute("message", "등록되었습니다.");
			return "redirect:/community/QNA";
		} else {
			
			ra.addFlashAttribute("message", "등록이 완료되지 않았습니다. 다시 작성해주세요");
			return "redirect:/community/qnawrite";
		}

		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	@GetMapping("Comment")
	public String Comment() {
		return "/community/Comment";
	}
	
	@GetMapping("QNAupdate")
	public String QNAupdate() {
		return "/community/QNAupdate";
	}
	
	
	
	// 목록으로
	
//		@GetMapping("{boardNo:[0-9]+}")
//		public String listGo(
//				@RequestParam("cp") int cp,
//				@PathVariable("boardNo") int boardNo
//				) {
//			return "board/" + boardNo + "cp=" + cp;
//		}
	
	
	
}
