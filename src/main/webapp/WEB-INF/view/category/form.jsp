<%--
  Created by IntelliJ IDEA.
  User: BELL
  Date: 2016-08-10
  Time: 오전 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/view/include/header.jsp" %>
	<link href="${pageContext.request.contextPath}/styles/category/category.css" rel="stylesheet">
</head>

<body>
<div id="wrapper">
	<%@ include file="/WEB-INF/view/include/side_menu.jsp" %>
	<div class="scrollable-wrapper">
		<%@ include file="/WEB-INF/view/include/header_hero.jsp" %>
		<div class="container">
			<div class="row">
				<div class="area-wrapper">
					<div class="area-left">
						<label>카테고리 목록</label>
						<div class="alert alert-info alert-wrap">
							카테고리명을 Drag & Drop으로 순서를 변경하고 <button type="button" class="btn btn-success btn-sm">순서 변경</button>
							버튼을 클릭하여 변경정보가 변경됩니다. <br/> <span style="color:red">순서변경은 PC에서만 가능합니다.</span></div>
						<div style="border:1px solid #ddd; border-radius: 10px">
							<table class="category-list-table">
								<colgroup>
									<col width="*"/>
									<col width="35%"/>
								</colgroup>
								<tbody id="tbodyList">
									<tr>
										<%--<td class="text-warning text-center" colspan="2">--%>
											<%--카테고리를 추가해주세요--%>
										<%--</td>--%>
										<td class="tr-title text-left" onclick="CategoryUtil.select(this)">
											JAVA
										</td>
										<td class="text-center">
											<button type="button" class="btn btn-warning btn-sm">수정</button>
											<button type="button" class="btn btn-danger btn-sm">삭제</button>
										</td>
									</tr>
									<tr>
										<%--<td class="text-warning text-center" colspan="2">--%>
										<%--카테고리를 추가해주세요--%>
										<%--</td>--%>
										<td class="tr-title text-left" onclick="CategoryUtil.select(this)">
											JSP
										</td>
										<td class="text-center">
											<button type="button" class="btn btn-warning btn-sm">수정</button>
											<button type="button" class="btn btn-danger btn-sm">삭제</button>
										</td>
									</tr>
									<tr>
										<td class="tr-title text-left" onclick="CategoryUtil.select(this)">
											HTML
										</td>
										<td class="text-center">
											<button type="button" class="btn btn-warning btn-sm">수정</button>
											<button type="button" class="btn btn-danger btn-sm">삭제</button>
										</td>
									</tr>
									<tr>
										<td class="tr-title text-left" onclick="CategoryUtil.select(this)">
											JAVASCRIPT
										</td>
										<td class="text-center">
											<button type="button" class="btn btn-warning btn-sm">수정</button>
											<button type="button" class="btn btn-danger btn-sm">삭제</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="area-right">
						<div class="form-group">
							<label for="title">카테고리명</label>
							<input type="text" class="form-control" id="title" name="title" placeholder="카테고리명을 입력하세요" alt="카테고리명">
						</div>
						<div class="form-group">
							<label for="description">카테고리 설명</label>
							<input type="text" class="form-control" id="description" name="description" placeholder="카테고리 설명을 입력하세요" alt="카테고리설명">
						</div>
						<div class="text-center"><button type="button" id="categoryInsertBtn" class="btn btn-info btn-lg">등록</button></div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</div>
</div>
<script src="/scripts/plugin/jquery.tablednd.js"></script>
<script src="/scripts/category/category.js"></script>
</body>
</html>