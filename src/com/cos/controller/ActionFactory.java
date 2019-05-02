package com.cos.controller;

import com.cos.controller.board.BoardDeleteAction;
import com.cos.controller.board.BoardListPagingAction;
import com.cos.controller.board.BoardUpdateAction;
import com.cos.controller.board.BoardUpdateProcAction;
import com.cos.controller.board.BoardViewAction;
import com.cos.controller.board.BoardWriteAction;
import com.cos.controller.board.BoardWriteProcAction;
import com.cos.controller.member.MemberEditAction;
import com.cos.controller.member.MemberEditProcAction;
import com.cos.controller.member.MemberJoinAction;
import com.cos.controller.member.MemberJoinProcAction;
import com.cos.controller.member.MemberLoginAction;
import com.cos.controller.member.MemberLoginProcAction;
import com.cos.controller.member.MemberLogoutAction;

//공장 - 하나라는 것이 보증됨 = 싱글톤 패턴
public class ActionFactory {
	private static ActionFactory instance = 
			new ActionFactory();
	
	private ActionFactory() {}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	//라우팅 기계
	public Action getAction(String cmd) {
		if(cmd == null) {
			return new BoardListPagingAction();
		}else if(cmd.equals("boardListPage")) {
			return new BoardListPagingAction();
		}else if(cmd.equals("boardView")) {
			return new BoardViewAction();
		}else if(cmd.equals("boardWrite")) {
			return new BoardWriteAction();
		}else if(cmd.equals("boardWriteProc")) {
			return new BoardWriteProcAction();
		}else if(cmd.equals("boardUpdate")) {
			return new BoardUpdateAction();
		}else if(cmd.equals("boardUpdateProc")) {
			return new BoardUpdateProcAction();
		}else if(cmd.equals("boardDelete")) {
			return new BoardDeleteAction();
		}else if(cmd.equals("memberJoin")) {
			return new MemberJoinAction();
		}else if(cmd.equals("memberLogin")) {
			return new MemberLoginAction();
		}else if(cmd.equals("memberJoinProc")) {
			return new MemberJoinProcAction();
		}else if(cmd.equals("memberLoginProc")) {
			return new MemberLoginProcAction();
		}else if(cmd.equals("memberLogout")) {
			return new MemberLogoutAction();
		}else if(cmd.equals("memberEdit")) {
			return new MemberEditAction();
		}else if(cmd.equals("memberEditProc")) {
			return new MemberEditProcAction();
		}
		
		return null;
	}
}
