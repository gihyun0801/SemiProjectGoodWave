package edu.kh.goodWave.boardService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.goodWave.board.model.dto.Comment;
import edu.kh.goodWave.boardMapper.CommentMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentMapper mapper;
	
	
	@Override
	public List<Comment> select(int boardNo) {

		
		return mapper.select(boardNo);
	}
	
	
	// 댓글 등록
	@Override
	public int insert(Comment comment) {
		
		return mapper.insert(comment);
	}
	
	// 댓글 삭제
	@Override
	public int delete(int commentNo) {
		
		return mapper.delete(commentNo);
	}
	
	// 댓글 수정
	@Override
	public int update(Comment comment) {
		
		return mapper.update(comment);
	}
} 
