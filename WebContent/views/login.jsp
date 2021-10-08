<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="loginModal hidden">
	<div class="modalOverlay"></div>
	<div class="modalContent">
		<div class="loginModalClose">
			<img class="btnCloseLogin" src="resources/imgs/x.svg">
		</div>
		<div id="loginFormTitle">로그인</div>  <!-- 또는 회원가입 -->
		<div id="loginWrap" class="form">
			<form name="loginForm">
				<div class="formRow">

					<div class="formCol1">
						<input id="id" name="id" type="text" onkeyup="enterkeyPress()" required placeholder="id" autocomplete='username'>
					</div>
					<div class="formCol2">
						<img id="chkOkId" class="hidden" onkeyup="enterkeyPress()" src="resources/imgs/check.svg">
					</div>
				</div>
				<div id="idChkRes" class="chkRes formRow registerOption hidden">
				</div>
				<div class="formRow">
					<div class="formCol1">
						<input id="password" name="password" type="password" onkeyup="enterkeyPress()" required placeholder="password" autocomplete="current-password">
					</div>
					<div class="formCol2">
						<img id="chkOkConfirm" class="hidden" onkeyup="enterkeyPress()" src="resources/imgs/check.svg">
					</div>
				</div>
				<div class="registerOption hidden">
					<div class="formRow">
						<div class="formCol1">
							<input id="confirm" name="confirm" type="password" onkeyup="enterkeyPress()" required placeholder="confirm" autocomplete="new-password">
						</div>
						<div class="formCol2">
						</div>
					</div>
					<div id="confirmChkRes" class="chkRes formRow registerOption hidden">
					</div>
					<div class="formRow">
						<div class="formCol1">
							<input id="name" name="name" type="text" onkeyup="enterkeyPress()" placeholder="이름" autocomplete="name">
						</div>
						<div class="formCol2">
						</div>
					</div>
					<div class="formRow">
						<div class="formCol1">
							<input id="email" name="email" type="email" onkeyup="enterkeyPress()" placeholder="email" autocomplete="email">
						</div>
						<div class="formCol2">
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

		<div id="passwordChangeWrap" class="form hidden">
			<form name="passwordChangeForm">
				<div class="formRow">
					<div class="formCol1">
						<input name="originPass" type="password" required placeholder="기존 비밀번호">
					</div>
				</div>
				<div class="formRow">
				<div class="formCol1">
					<input name="newPassword" type="password" required placeholder="변경 비밀번호">
				</div>
				</div>
				<div class="formRow">
				<div class="formCol1">
					<input name="confirm" type="password" required placeholder="확인">
				</div>
				</div>
				<button type="button" class="loginFormBtns" onclick="changePassProc()">변경하기</button>
			</form>
		</div>


	</div>
</div>



