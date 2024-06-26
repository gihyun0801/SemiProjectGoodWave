package edu.kh.goodWave.member.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.thymeleaf.spring6.SpringTemplateEngine;
import edu.kh.goodWave.member.model.dto.Member;
import edu.kh.goodWave.member.model.mapper.MemberMapper;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional(rollbackFor=Exception.class)
@Service
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
public class MemberServiceImpl implements MemberService{

	private final MemberMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
	private final JavaMailSender mailSender;
	
	private final SpringTemplateEngine templateEngine;
	
    @Value("${naver.client_id}")
    private String naverClientId;
    
    @Value("${naver.redirect_uri}")
    private String naverRedirectUri;
    
    @Value("${naver.client_secret}")
    private String naverClientSecret;
	
    
	
	// 로그인
	@Override
	public Member loginMember(Member inputMember) {

		Member loginMember = mapper.login(inputMember.getMemberId());
		

//		 String bcryptPassword = bcrypt.encode(loginMember.getMemberPw());
		
		
		if(loginMember == null) return null;
		
		
		if(loginMember.getMemberPw() == null) return loginMember;

		if(!bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
			return null;
		}
		
		
		
		loginMember.setMemberPw(null);
		
		
		return loginMember;
	}
	

	

	/**
	 * 회원가입
	 */
	@Override
	public int signup(Member inputMember, String[] member) {
		
		
		if(!inputMember.getMemberAddress().equals(",,")) {
			String address = String.join("^^^", member);
			
			inputMember.setMemberAddress(address);
		}else {
			inputMember.setMemberAddress(null);
		}
		
		String encPw = bcrypt.encode(inputMember.getMemberPw());
		
		inputMember.setMemberPw(encPw);
		
		log.debug("inputMember : " + inputMember);
		
		int result = mapper.signup(inputMember);
	
		
		return result;
	}



	/**
	 * 맴버 이메일 중복확인검사
	 */
	@Override
	public int checkEmail(String email) {
		
		return mapper.checkEmail(email);
	}

	// 아이디 찾기
	@Override
	public String idSearch(Map<String, String> paramMap) {
		
		int result = mapper.idCheck(paramMap);
		if(result == 0 ) return null;
		
		return mapper.idSearch(paramMap);
	}


	@Override
	public String pwSearch(Map<String, String> paramMap) {
		
		int result = mapper.pwCheck(paramMap);
		
		if(result == 0 ) return null;
		
		String randomPw = createRandomPw();
		
		try {
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
			
			helper.setTo(paramMap.get("search2Email"));
			helper.setSubject("비밀번호 찾기");
			
			helper.setText(loadHtml(randomPw),true);
		
			helper.addInline("logo", new ClassPathResource("static/images/logo.png"));
		
			mailSender.send(mimeMessage);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		paramMap.put("randomPw", randomPw);
		
		String bcryptPassword = bcrypt.encode(randomPw);
		
		paramMap.put("bcryptPassword", bcryptPassword);
		
		int updateRandomPw = mapper.updateRandomPw(paramMap);
		
		if(updateRandomPw >0 ) return paramMap.get("randomPw");
		else return null;
	}

	
	// HTML 파일 읽어와서 String으로 변환하기
	private String loadHtml(String randomPw) {
		
		Context context = new Context();
		
		context.setVariable("randomPw", randomPw);
		
		return templateEngine.process("email/searchPw" , context);
	}

	// 인증번호 생성
	 public String createRandomPw() {
		 
		   String key = "";
	       for(int i=0 ; i< 6 ; i++) {
	          
	           int sel1 = (int)(Math.random() * 3); 
	          
	           if(sel1 == 0) {
	              
	               int num = (int)(Math.random() * 10);
	               key += num;
	              
	           }else {
	              
	               char ch = (char)(Math.random() * 26 + 65); 
	              
	               int sel2 = (int)(Math.random() * 2); 
	              
	               if(sel2 == 0) {
	                   ch = (char)(ch + ('a' - 'A')); 
	               }
	              
	               key += ch;
	           }
	          
	       }
	       return key;
	   }




	@Override
	public int checkMemberId(String memberId) {
		return mapper.checkMemberId(memberId);
	}




	@Override
	public String loginUrl(Model model,HttpServletRequest request2) {
		
	 
	    String state = "state";
	    
	
	    String baseUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append(baseUrl);
	    sb.append("?response_type=code");
	    sb.append("&client_id=" + naverClientId);
	    sb.append("&redirect_uri=" + naverRedirectUri);
	    sb.append("&state=" + state);
	    
	    log.debug("aaaaaaaaaaa                "+ sb.toString());
	    
//	    request2.getSession().setAttribute("state", state);
	    
	    
	    
	    
		return sb.toString();
	}




	@Override
	public int signUpNaver(Member inputMember) {
		
		return mapper.signUpNaver(inputMember);
	}


}
