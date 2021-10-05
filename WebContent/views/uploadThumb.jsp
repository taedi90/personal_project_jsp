<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="uploadModal hidden">
    <div class="uploadModalOverlay"></div>
    <div class="uploadModalContent">
        <div id="uploadModalBody">

            <div class="uploadModalClose">
                <img class="btnCloseUpload" src="resources/imgs/x.svg">
            </div>

            <form action="../controller/board/uploadThumbProc.jsp" method="post" enctype="multipart/form-data">
                <div class="dropBox">
                    <div id="firstView" class="firstView">
                        <label class="selectBtn " for="hiddenInput">*.jpg & *.png</label>
                        <input type="file" class="btnFile dDone" name="thumb" id="hiddenInput" hidden
                            accept=".jpg, .jpeg, .gif, .png">
                    </div>
                    <div id="secondView" class="secondView hidden">
                        <button class="submitBtn" type="button">업로드</button>
                    </div>
                    <div class="alwaysView">
                        <p class="uploadStat">파일을 여기에 끌어다 놓을 수 있습니다.</p>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>  