<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api/news"/>
<c:url var ="NewURL" value="/quan-tri/bai-viet/danh-sach"/>
<c:url var ="homeURL" value="/trang-chu"/>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Chi Tiết Bài Viết</title>
</head>

<body>
  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">
        <h1 class="my-4">Category</h1>
        <div class="list-group">
        	<ul id="myid">
        		<c:forEach var="item" items="${listCategory}">
        			<li class="list-group-item" value="${item.code}">${item.name}</li>
        	    </c:forEach>
        	</ul>
        </div>
      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">

	        <div class="card mt-4">
	          <img class="card-img-top img-fluid" src="${detailNews.base64}" alt="" style="height: 350px;width: 900px;">
	          <div class="card-body">
	            <h3 class="card-title">${detailNews.title}</h3>
	            <p class="card-text">${detailNews.content}</p>
	            <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
	            4.0 stars
	          </div>
	        </div>

      </div>
      <!-- /.col-lg-9 -->

    </div>

  </div>
  <!-- /.container -->
  <script type="text/javascript">
  $("#myid li").on('click',function(){
	  var code = $(this).attr("value");
	  window.location.href = "${homeURL}?code="+code+"";
  });
  
  </script>
</body>

</html>