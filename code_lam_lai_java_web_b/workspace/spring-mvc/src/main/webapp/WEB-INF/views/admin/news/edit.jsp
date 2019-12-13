<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api/news"/>
<c:url var ="NewsURL" value="/quan-tri/bai-viet/danh-sach"/>
<c:url var ="editURL" value="/quan-tri/bai-viet/chinh-sua"/>

<c:url var="uploadURL" value="/api/upload" />

<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa bài viết</li>
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
                        <form:form id="formSubmit" modelAttribute="model">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                                <div class="col-sm-9">
                                    <%-- <select class="form-control" id="categoryCode" name="categoryCode">
									    <option value="">--chọn lại bài viết--</option>
									   	<c:forEach var="item" items="${categories}">
									   		<option value="${item.code}" <c:if test="${model.categoryCode == item.code}">selected="selected"</c:if> >${item.name}</option>
									   	</c:forEach>			  
									  </select> --%>
									  
									 <form:select path="categoryCode" id="categoryCode">
									 	<form:option value="" label="-- chọn thể loại --"/>
									 	<form:options items="${categories}"/>
									 </form:select> 
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                                <div class="col-sm-9">
                                   <%--  <input type="text" class="form-control" id="title" name="title" value="${model.title}"/> --%>
                            		<form:input path="title" cssClass="form-control"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="thumbnail" name="thumbnail" value="${model.thumbnail}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
								<label class="ontrol-label col-sm-3">Upload a image</label>
								<div class="col-sm-9">
									<input type="file" name="file" id="uploadFile" style="width: 300px;" value="${model.base64}"/>	
									<c:if test="${not empty model.id}">
									
											<p> ${model.name} </p>
											<img class="js-file-image" src="${model.base64}" style="width: 50%; height: 50%;margin-bottom: 20px;">
										
										
									</c:if>
									<c:if test="${empty model.id}">
										<img class="js-file-image" style="width: 50%; height: 50%;margin-top: 10px;margin-bottom: 20px;">
									</c:if>
								</div>
							</div>
							<br/>
							<br/>
                            <div class="form-group" >
                                <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                                <div class="col-sm-9" style="margin-bottom: 20px;">
                                  <%--   <input type="text" class="form-control" id="shortDescription" name="shortDescription" value="${model.shortDescription}"/> --%>
                                	<form:input path="shortDescription" cssClass="form-control"/>
                                </div>
                            </div>
                            <br/>
                         	<br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                                <div class="col-sm-9">                                 
                                   <%--  <textarea rows="" cols="" id="content" name="content" style="width: 820px;height: 175px">${model.content}</textarea> --%>
                               		<form:textarea path="content" cssStyle="width: 820px;height: 175px"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <div class="col-sm-12">
                                 	<c:if test="${not empty model.id}">
                                 		<input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật bài viết" id="btnAddOrUpdateNew"/>
                                 	</c:if>
                                    <c:if test="${empty model.id}">
                                    	<input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm bài viết" id="btnAddOrUpdateNew"/>
                                    </c:if>    
                                                                     
                               </div>
                            </div>
                           	<input type="hidden" value="${model.id}" id="id" name="id"/>
                           
                        </form:form>	
                </div>
            </div>
        </div>
    </div>
</div>

<script>
//======load image-file ==============
$(document).ready(function(){
	  $('#uploadFile').on('change', function() {
		var file = $(this)[0].files[0];
	    var fileReader = new FileReader();
	    fileReader.onload = function(e) {
	      var imageSrc = e.target.result;
	      $('.js-file-image').attr('src', imageSrc);
	    };
	    fileReader.readAsDataURL(file);
	  });
	});
//=========code upload file ban dau 
/* $(document).ready(function () {

})
$('#btnAddOrUpdateNew').click(function (e) {
    var dataArray = {};
    var files = $('#uploadFile')[0].files[0];
    if (files != undefined) {
        var reader = new FileReader();
        reader.onload = function(e) {
            dataArray["base64"] = e.target.result;
            dataArray["name"] = files.name;
            uploadFile(dataArray);
        };
        reader.readAsDataURL(files);
	}
});
function uploadFile(data) {
    $.ajax({
        url: '${uploadURL}',
		type: 'POST',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (res) {
            console.log(res);
        },
		error: function (res) {
            console.log(res);
        }
	});
}  */
//=========================================
 $('#btnAddOrUpdateNew').click(function(e){
	  e.preventDefault();
	  var data = {};
	
	  var formData = $('#formSubmit').serializeArray();
	  $.each(formData, function(i, v){
		 data[""+v.name+""] = v.value;
	  });
	  
	  var id = $('#id').val(); 
	  if(id == ""){
		//===============================
		  var files = $('#uploadFile')[0].files[0];
	      if (files != undefined) {
	         var reader = new FileReader();
	         reader.onload = function(e) {
	            data["base64"] = e.target.result;
	            data["name"] = files.name;
	        //    uploadFile(dataArray);
	            addNew(data);
	      	 };
	      	 reader.readAsDataURL(files);
		  }else{
			   data["base64"] = "";
			   data["name"] ="";
			   addNew(data);
		  }
	     
		  //========================
		  //addNew(data);
	  }else{
		  var files = $('#uploadFile')[0].files[0];
	      if (files != undefined) {
	         var reader = new FileReader();
	         reader.onload = function(e) {
	            data["base64"] = e.target.result;
	            data["name"] = files.name;
	        //    uploadFile(dataArray);
	            updateNew(data);
	      	 };
	      	 reader.readAsDataURL(files);
		  }else{
			  data["base64"] = "";
		      data["name"] ="";
			  updateNew(data);
		  }
		  //=========================
		  //updateNew(data);
	  }
	    
	});  
	 function addNew(data) {
		$.ajax({
			url: '${APIurl}',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: 'json',
			success: function (result) {
				window.location.href = "${editURL}?id="+result.id+"&message=insert_success";
			},
			error: function (error) {
				window.location.href = "${NewsURL}?page=1&limit=2&message=error_system";
			}
		});
	}
	function updateNew(data) {
		$.ajax({
			url: '${APIurl}',
			type: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: 'json',
			success: function (result) {
				window.location.href = "${editURL}?id="+result.id+"&message=update_success";
			},
			error: function (error) {
				window.location.href = "${editURL}?id="+result.id+"&message=error_system";
			}
		});
	} 
</script>

</body>
</html>
