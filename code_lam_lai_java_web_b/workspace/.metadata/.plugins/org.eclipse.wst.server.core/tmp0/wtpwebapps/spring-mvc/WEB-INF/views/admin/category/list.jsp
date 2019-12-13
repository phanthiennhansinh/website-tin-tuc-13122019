<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<c:url var="APIurl" value="/api/category"/>
<c:url var ="listURL" value="/quan-tri/the-loai/danh-sach"/>
<c:url var ="editURL" value="/quan-tri/the-loai/chinh-sua"/>
<c:url var ="searchURL" value="/quan-tri/the-loai/search"/>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Danh Sách Thể Loại</title>
</head>

<body>
	<div class="main-content">
		<form action="#" id="formSubmit" method="get">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">Trang chủ</a>
						</li>
					</ul><!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<c:if test="${not empty message}">
                     			<div class="alert alert-${alert}">
  									${message}
								</div>
                     		</c:if>
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-left tableTools-container">
										<div class="row">
			  								<div class="col-sm-9">
			  									<input type="text" class="form-control" id="keyword" placeholder="Enter name, code,..." name="keyword">				
			  								</div>
			  								<div class="col-sm-3">
			  									<button id="btnSearch" type="button" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold">Search</button>
			  								</div>
										</div>	
									</div>
									<div class="pull-right tableTools-container">
										<div class="dt-buttons btn-overlap btn-group">
											<a flag="info"
												class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Thêm thể loại'
												href="<c:url value='/quan-tri/the-loai/chinh-sua' />" >
												<span>
													<i class="fa fa-plus-circle bigger-110 purple"></i>
												</span>
											</a>
											<button id="btnDelete" type="button"
												class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Xóa thể loại'>
												<span>
													<i class="fa fa-trash-o bigger-110 pink"></i>
												</span>
											</button>
										</div>
									</div>
								</div>
							</div>
							<div class="col-xs-12">
								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th><input type="checkbox" id="checkAll"></th>
														<th>Tên thể loại</th>
														<th>code</th>
														<th>Thao tác</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${categoryModel.listResult}">
														<tr>
															<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
															<td>${item.name}</td>
															<td>${item.code}</td>													
															<td>
																<a class="btn btn-sm btn-primary btn-edit"
																	data-toggle="tooltip" title="Cập nhật thể loại"
																	href="<c:url value='/quan-tri/the-loai/chinh-sua?id=${item.id}'/>">
																	<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																</a>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</form>
	</div>
	<!-- /.main-content -->

	<script type="text/javascript">
	$("#btnDelete").click(function() {
		var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
		if(ids.length === 0){
			window.location.href = "${listURL}?message=error_system";
		}else{
			deleteCategory(ids);
		}
	});
	function deleteCategory(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${listURL}?message=delete_success";
            },
            error: function (error) {
            	window.location.href = "${listURL}?message=error_system";
            }
        });
    }
	$("#btnSearch").click(function(){
		var keyword = $('#keyword').val();
		window.location.href = "${listURL}?keyword="+keyword+"";
	});
	</script>
</body>

</html>