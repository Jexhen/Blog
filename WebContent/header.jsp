<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container-fluid">
   <div class="row">
       <div class="col-lg-2 col-md-1 hidden-sm hidden-xs"></div>
       <div class="col-lg-4 col-md-4 col-sm-7 col-xs-12">
       		<img alt="" src="${pageContext.request.contextPath }/img/admin/1/profile_pic/logo.png" width="15%">
           <div style="display:inline-block;">
           <span style="color: black; font-size: x-large">J<span style="color: #1ABC9C">e</span>xhen</span>&nbsp;
           <span style="color: #8c8c8c; font-size: x-small">欢迎访问<strong style="color:#333;">廖志行</strong>博客</span>
           </div>
        </div>
       <!--  适用于大屏幕和中等屏幕的导航栏 -->
        <div class="col-lg-5  col-md-7 hidden-sm hidden-xs" style="margin-top:10px;">
            <ul class="nav nav-pills" role="tablist" id="tablist">
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath }">首页</a></li>
                <%-- 当导航栏目录个数小于3时没有下拉栏，大于3时增加下拉栏 --%>
                <c:forEach items="${category }" var="citem" varStatus="cnt">
                	<c:if test="${cnt.count<=3 }">
						  <li role="presentation">
						  	<a href="${pageContext.request.contextPath }/getArticle?cid=${citem.cid }">
						  		 ${citem.cname }
						  		<span class="badge">${citem.count }</span>
						  	</a>
						  </li>             	
                	</c:if>
                	<c:if test="${cnt.count>3}">
                		<c:if test="${cnt.count==4 }">
	                		<li role="presentation" class="dropdown">
			                    <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);" role="button" aria-haspopup="true" aria-expanded="false">
			                       	 其他 <span class="caret"></span>
			                    </a>
			                    <ul class="dropdown-menu">
		                </c:if>
						<li role="presentation">
						  	<a href="${pageContext.request.contextPath }/getArticle?cid=${citem.cid }">
						  		 ${citem.cname }
						  		<span class="badge">${citem.count }</span>
						  	</a>
						 </li>
		                 <c:if test="${cnt.last }">
		                 		</ul>
		                 	</li>
		                 </c:if>
                	</c:if>
                </c:forEach>
            </ul>
        </div>
        <!--  适用于小屏幕和超小屏幕的导航栏 -->
        <div class="col-xs-12 col-sm-5 hidden-lg hidden-md" style="margin:10px 0px;">
            <ul class="nav nav-pills" id="tablist">
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath }">首页</a></li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);" role="button" aria-haspopup="true" aria-expanded="false">
                       	 博客分类 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                    	<c:forEach items="${category }" var="citem">
                    		<li role="presentation">
							  	<a href="${pageContext.request.contextPath }/getArticle?cid=${citem.cid }">
							  		 ${citem.cname }
							  		<span class="badge">${citem.count }</span>
							  	</a>
							 </li>  
                    	</c:forEach>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="col-lg-1 hidden-md  hidden-xs"></div>
    </div>
</div>