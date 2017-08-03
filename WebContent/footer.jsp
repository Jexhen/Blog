<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container-fluid">
    <!--占位-->
    <div class="row">
        <div class="col-lg-12" style="height: 20px"></div>
    </div>
    <div class="row">
        <div class="col-lg-2 col-md-2 hidden-sm hidden-xs"></div>
        <div class="col-lg-4 col-md-3 col-sm-6 hidden-xs">
            <span style="color: black;">Jexhen&copy;2017.07</span>
        </div>
        <div class="hidden-lg hidden-md hidden-sm col-xs-12" style="text-align: center">
            <span style="color: black">Jexhen&copy;2017.07</span>
        </div>
        <div class="col-lg-4 col-md-5 col-sm-6 hidden-xs">
            <a href="${pageContext.request.contextPath }">首页</a>
            <c:forEach items="${category }" var="citem">
            	<a href="${pageContext.request.contextPath }/getArticle?cid=${citem.cid }">${citem.cname }</a>
            </c:forEach>
        </div>
    </div>
    <!--占位-->
    <div class="row">
        <div class="col-lg-12" style="height: 20px"></div>
    </div>
</div>