<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="loginModal hidden">
	<div class="modalOverlay"></div>
	<div class="modalContent">
		<div class="loginModalClose">
			<img class="btnCloseLogin" src="resources/imgs/x.svg">
		</div>
		<div id="loginFormTitle">로그인</div>  <!-- 또는 회원가입 -->
		<div id="loginWrap" class="form"></div>
			<form name="loginForm">
				<div class="formRow">

					<div class="formCol1">
						<input id="id" name="id" type="text" required placeholder="id" autocomplete='username'>
					</div>
					<div class="formCol2">
						<img id="chkOkId" class="hidden" src="resources/imgs/check.svg">
					</div>
				</div>
				<div id="idChkRes" class="chkRes formRow registerOption hidden">
				</div>
				<div class="formRow">
					<div class="formCol1">
						<input id="password" name="password" type="password" required placeholder="password" autocomplete="current-password"> 
					</div>
					<div class="formCol2">
						<img id="chkOkConfirm" class="hidden" src="resources/imgs/check.svg">
					</div>
				</div>
				<div class="registerOption hidden">
					<div class="formRow">
						<div class="formCol1">
							<input id="confirm" name="confirm" type="password" required placeholder="confirm" autocomplete="new-password"> 
						</div>
						<div class="formCol2">
						</div>
					</div>
					<div id="confirmChkRes" class="chkRes formRow registerOption hidden">
					</div>
					<div class="formRow">
						<div class="formCol1">
							<input id="name" name="name" type="text" placeholder="이름" autocomplete="name">
						</div>
						<div class="formCol2">
						</div>
					</div>
					<div class="formRow">
						<div class="formCol1">
							<input id="email" name="email" type="email" placeholder="email" autocomplete="email">
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
	</div>
</div>



