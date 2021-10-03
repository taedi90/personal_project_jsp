<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="loginModal hidden">
	<div class="modalOverlay"></div>
	<div class="modalContent">
		<div class="modalClose">
			<span class="btnCloseLogin">❌</span>
		</div>
		<div id="loginFormTitle">로그인</div>  <!-- 또는 회원가입 -->
		<div id="loginWrap" class="form"></div>
			<form name="loginForm">
				<div class="formRow">
					<div class="formCol1">
						<label for="id">아이디</label>
					</div>
					<div class="formCol2">
						<input id="id" name="id" type="text" required>
					</div>
				</div>
				<div id="idChkRes" class="formRow registerOption hidden">
				</div>
				<div class="formRow">
					<div class="formCol1">
						<label for="password">패스워드</label>
					</div>
					<div class="formCol2">
						<input id="password" name="password" type="password" required>
					</div>
				</div>
				<div class="registerOption hidden">
					<div class="formRow">
						<div class="formCol1">
							<label for="name">이름</label>
						</div>
						<div class="formCol2">
							<input id="name" name="name" type="text">
						</div>
					</div>
					<div class="formRow">
						<div class="formCol1">
							<label for="email">이메일</label>
						</div>
						<div class="formCol2">
							<input id="email" name="email" type="email">
						</div>
					</div>
					<button type="button" class="loginFormBtns" onclick="registerProc()">회원가입</button>
					<div class="loginFormSwap"><a href="javascript:registerOff();">로그인으로</a></div>
				</div>
				<div class="loginOption">
					<button type="button" class="loginFormBtns" onclick="loginProc()">로그인</button>
					<div class="loginFormSwap">또는&nbsp;<a href="javascript:registerOn();">회원가입</a></div>
				</div>
			</form>
		</div>
	</div>
</div>



<div class="modal hidden">
	<div class="modalOverlay"></div>
	<div class="modalContent">
		<div id="modalBody">

		</div>
		<div id="modalButton">
			<button type="button" class="button type0 closeModal" >확인</button>
			<button type="button" class="button type1" onclick="yesFunc()">예</button>
			<button type="button" class="button type1 closeModal">아니오</button>

		</div>
	</div>
</div>