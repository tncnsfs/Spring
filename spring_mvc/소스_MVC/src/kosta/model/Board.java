package kosta.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class Board implements Serializable{
	private int seq;
	
	/*@NotEmpty(message="�ۼ��ڸ� �Է��ϼ���")
	@Size(min=2, max=5, message="2�� �̻� 5�� �̸� �Է�")*/
	private String writer;
	
	@Pattern(regexp="[0-9a-zA-Z��-�R]*", message="Ư������ ����")
	private String title;
	
	private String contents;
	private String regdate;
	private int hitcount;
	
	private MultipartFile uploadFile;
	private String fname;
	
	public Board(){}	
		
	public Board(int seq, String writer, String title, String contents, String regdate, int hitcount,
			MultipartFile uploadFile, String fname) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.regdate = regdate;
		this.hitcount = hitcount;
		this.uploadFile = uploadFile;
		this.fname = fname;
	}



	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getHitcount() {
		return hitcount;
	}

	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}

	@Override
	public String toString() {
		return "Board [seq=" + seq + ", writer=" + writer + ", title=" + title + ", contents=" + contents + ", regdate="
				+ regdate + ", hitcount=" + hitcount + "]";
	}
	
	
}
