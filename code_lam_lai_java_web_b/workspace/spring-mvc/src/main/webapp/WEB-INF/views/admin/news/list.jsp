<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api/news"/>
<c:url var ="NewURL" value="/quan-tri/bai-viet/danh-sach"/>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Danh Sách Bài Viết</title>
</head>

<body>
	<div class="main-content">
		<form action="<c:url value='/quan-tri/bai-viet/danh-sach'/>" id="formSubmit" method="get">
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
							<c:if test="${not empty message }">
      		                	<div class="alert alert-${alert}">
  										${message}
								</div>
                      		</c:if>
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-left tableTools-container">
										<div class="row">
			  								<div class="col-sm-9">
			  									<input type="text" class="form-control" id="keyword" placeholder="Enter title, content,..." name="keyword">				
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
												data-toggle="tooltip" title='Thêm bài viết'
												href="<c:url value="/quan-tri/bai-viet/chinh-sua" />">
												<span>
													<i class="fa fa-plus-circle bigger-110 purple"></i>
												</span>
											</a>
											<button id="btnDelete" type="button" onclick="waringBeforDelete()"
												class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Xóa bài viết'>
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
														<th>Tên bài viết</th>
														<th>Mô tả ngắn</th>
														<th>Thao tác</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${model.listResult}">
														<tr>
															<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
															<td>${item.title}</td>
															<td>${item.shortDescription}</td>
															<td>
																<a class="btn btn-sm btn-primary btn-edit"
																	data-toggle="tooltip" title="Cập nhật bài viết"
																	href="<c:url value='/quan-tri/bai-viet/chinh-sua?id=${item.id}' />">
																	<i class="fa fa-pencil-square-o"
																		aria-hidden="true"></i>
																</a>
															</td>
														</tr>
														
													</c:forEach>
												</tbody>
											</table>
											<ul class="pagination" id="pagination"></ul>
											<input type="hidden" id="page" value="" name="page"/>
											<input type="hidden" id="limit" value="" name="limit"/>
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
	var totalPage = ${model.totalPage};
	var currentPage = ${model.page};
	var limit = ${model.limit};
	$(function () {
	       window.pagObj = $('#pagination').twbsPagination({
	           totalPages: totalPage,
	           visiblePages: 5,
	           startPage: currentPage,
	           onPageClick: function (event, page) {
	        	   if(currentPage != page){
	        		   	$('#limit').val(limit);
						$('#page').val(page);
						$('#formSubmit').submit(); 
	        	   }	
	           }
	        });
	    }); 
	function  waringBeforDelete() {
		swal({
			  title: "xác nhận xóa",
			  text: "bạn sẽ không thể lấy lại dữ liệu nếu xóa!",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  cancelButtonClass: "btn-danger",
			  confirmButtonText: "có, xóa!",
			  cancelButtonText: "không, hủy!",
			}).then(function(isConfirm) {
			  if(isConfirm) {
				//	var data = {};
					var ids = $('tbody input[type=checkbox]:checked').map(function () {
			            return $(this).val();
			        }).get();
			//		data['ids'] = ids;
					deleteNew(ids);
			  }
			});
	}
	 function deleteNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${NewURL}?page=1&limit=2&message=delete_success";
            },
            error: function (error) {
            	window.location.href = "${NewURL}?page=1&limit=2&message=error_system";
            }
        });
    } 
	 
	 $('#btnSearch').click(function(){
		 var limit = ${model.totalItem};
		 var keyword = $('#keyword').val();
		 window.location.href = "${NewURL}?page=1&limit="+limit+"&keyword="+keyword+"";
	 });
	</script>
</body>

</html>