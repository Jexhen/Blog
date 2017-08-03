<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link type="text/css" href="css/bootstrap.css" rel="stylesheet">
    <link type="text/css" href="css/bootstrap-theme.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/npm.js"></script>
    <script type="text/javascript">
	    $(function(){
	    	   //异步修改推荐栏的文章
	    	   $("#popular").hover(function(){
	    		   $("#recent").css({border:""});
	    		   $(this).css({border:"1px dashed grey"});
	    		   var url = "${pageContext.request.contextPath }/getRecommend?cid=${cid }";
	    		   var params = {"type":"popular"};
	    		   $.post(url,params,function(data){
	    				$("#recommendArticles").html("");
	    				var newHtml = "";
	    				for (var i=0;i<data.length;i++){
	    					newHtml = newHtml + "<a href='${pageContext.request.contextPath }/readArticle?cid=${cid }&aid="+data[i].aid+"' style='color:#333;'><h4>"+ data[i].aname + "</h4></a>" + "<span class='glyphicon glyphicon-calendar'></span>&nbsp;<span>"+ data[i].adate + "</span><hr style='border:1px dashed grey;'>";
	    				}
	    				$("#recommendArticles").html(newHtml);
	    		   },"json");
	    	   });
	    	   
	    	   $("#recent").hover(function(){
	    		   $("#popular").css({border:""});
	    		   $(this).css({border:"1px dashed grey"});
	    		   var url = "${pageContext.request.contextPath }/getRecommend?cid=${cid }";
	    		   var params = {"type":"recent"};
	    		   $.post(url,params,function(data){
	    				$("#recommendArticles").html("");
	    				var newHtml = "";
	    				for (var i=0;i<data.length;i++){
	    					newHtml = newHtml + "<a href='${pageContext.request.contextPath }/readArticle?cid=${cid }&aid="+data[i].aid+"' style='color:#333;'><h4>"+ data[i].aname + "</h4></a>" + "<span class='glyphicon glyphicon-calendar'></span>&nbsp;<span>"+ data[i].adate + "</span><hr style='border:1px dashed grey;'>";
	    				}
	    				$("#recommendArticles").html(newHtml);
	    		   },"json");
	    	   });
	    	   //修改导航栏当前页面标识
	    	   if ("${cid }"!="index") {
	    		   $("#tablist li[class='active']").removeClass("active");
	    		   $("#tablist li a").each(function(){
	    			   var href = $(this).attr("href");
	    			   if (href.indexOf("${cid }")!=-1) {
	    				   $(this).parent().addClass("active");
	    			   }
	    		   });
	    	   }
	    	});
    </script>
    <style type="text/css">
    	.nav-pills > li.active > a,
        .nav-pills > li.active > a:hover,
        .nav-pills > li.active > a:focus {
            color: #fff;
            background-color: #1ABC9C;
        }
        a {
            color: #8c8c8c;
        }
        .nav-pills > li > a:hover {
            color: #1ABC9C;
        }
        .pagination > .active > a,
        .pagination > .active > span,
        .pagination > .active > a:hover,
        .pagination > .active > span:hover,
        .pagination > .active > a:focus,
        .pagination > .active > span:focus{
            background-color: #409440;
            border-color: #409440;
        }
        .pagination > li > a,
        .pagination > li > span,
        .pagination > li > a:hover,
        .pagination > li > span:hover,
        .pagination > li > a:focus,
        .pagination > li > span:focus {
            color: #435568;
            background-color: #ECF0F1;
        }
    </style>
</head>
<body>
    <!--插入header-->
    <jsp:include page="header.jsp"></jsp:include>
    <!--核心内容-->
    <div class="container-fluid" style="background-image: url('img/background.jpg');">
        <!--blog头-->
        <div class="row" style="color: #ffffff;margin-top: 20px">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">
                <div style="font-size: xx-large">Blog</div>
                To learn without thinking is blindness, to think without learning is idleness
            </div>
            <div class="col-lg-2"></div>
        </div>
        <!--内容展示-->
        <div class="row" style="margin-top: 30px;">
            <!--占位-->
            <div class="col-lg-2 hidden-md hidden-sm hidden-xs"></div>
            <div class="col-lg-8 col-md-12 col-sm-12 col-xs-12" style="background-color: #fff; border: 1px solid silver;">
                <div class="row" style="margin-top: 15px;">
                    <!--内容左侧-->
                    <div class="col-lg-8 ">
                    <!--题目-->
                    <div class="row" >
                        <div class="col-lg-12">
                            <h1>${article.aname }</h1>
                            <span class="glyphicon glyphicon-calendar"></span>&nbsp;<span>${article.adate }</span>&nbsp;&nbsp;
                            <span class="glyphicon glyphicon-eye-open"></span>&nbsp;<span>${article.aview }</span>&nbsp;&nbsp;
                            <span class="glyphicon glyphicon-comment"></span>&nbsp;<span>${article.acomment }</span>&nbsp;&nbsp;
                            <span class="glyphicon glyphicon-tags"></span>&nbsp;
                            <span>
                            	<c:forEach items="${category }" var="citem">
                            		<c:if test="${citem.cid==article.cid }">
                            			${citem.cname }
                            		</c:if>
                            	</c:forEach>	
                            </span>
                            <br>
                            
                           <span>标签:</span>
                           <c:forEach items="${atags }" var="tag">
                           		<a href="${pageContext.request.contextPath }/getArticlesByTagName?cid=${cid }&tname=${tag }">${tag }</a>&nbsp;&nbsp;
                           </c:forEach> 
                            <hr>
                        </div>
                    </div>
                    <!--文章内容-->
                    <div class="row">
                        <div class="col-lg-12">
                            ${article.acontent }
                            <hr>
                        </div>
                    </div>
                    <!--评论-->
                    <div class="row">
                        <div class="col-lg-12">
                            <span class="glyphicon glyphicon-comment"></span>&nbsp;<span>所有评论</span>
                            <!--友言评论插件-->
                            <!-- UY BEGIN -->
							<div id="uyan_frame" style="margin-top:10px;"></div>
							<script type="text/javascript" src="http://v2.uyan.cc/code/uyan.js?uid=2140134"></script>
							<!-- UY END -->
                            <hr class="hidden-lg">
                        </div>
                    </div>
                </div>
                    <!--内容右侧-->
                    <div class="col-lg-4">
                        <!--联系方式-->
                        <div class="row">
                            <div class="col-lg-3 col-xs-3" >
                                <div style="width:52px; background-color: #FB7922; text-align: center">
                                    <img src="img/maillogo.png">
                                    <div style="background-color: silver;">E-mail</div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-xs-3" >
                                <div style="width:52px; background-color: #4cae4c; text-align: center">
                                    <img src="img/wechatlogo.png">
                                    <div style="background-color: silver;">微信</div>
                                </div>
                            </div><div class="col-lg-3 col-xs-3" >
                            <div style="width:52px; background-color:#41ABE1; text-align: center">
                                <img src="img/qqlogo.png">
                                <div style="background-color: silver;">QQ</div>
                            </div>
                        </div><div class="col-lg-3 col-xs-3" >
                            <div style="width:52px; background-color: #E74C3C; text-align: center">
                                <img src="img/weibologo.png">
                                <div style="background-color: silver;">微博</div>
                            </div>
                        </div>
                        </div>
                        <!--标签搜索-->
                        <div class="row" style="margin-top: 30px">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <span class="glyphicon glyphicon-th-list"></span>
                                        <strong style="color:#3A4F63;">所有标签</strong>
                                        <hr>
                                    </div>
                                </div>
                                <div class="row">
                                	<c:forEach items="${tags }" var="tag">
                                		<div class="col-lg-6"><a href="${pageContext.request.contextPath }/getArticlesByTagName?cid=${cid }&tname=${tag.tagName }">${tag.tagName }(${tag.tcount })</a><hr></div>
                                	</c:forEach>
                                </div>
                            </div>
                        </div>
                        <!--热门或最近展示-->
                        <div class="row">
                        	<div class="col-lg-12">
                        		<span id="popular" style="border:1px dashed grey;display:inline-block;width:94px;height:30px;text-align:center;">热门文章</span>
                        		<span id="recent" style="display:inline-block;width:94px;height:30px;text-align:center;">最近发表</span>
                        	</div>
                            <div class="col-lg-12" style="margin-bottom: 25px;">
                            	<div class="col-lg-12" style="border:1px dashed grey;" id="recommendArticles">
	                            	<c:forEach items="${popular }" var="article">
		                                <a href="${pageContext.request.contextPath }/readArticle?cid=${cid }&aid=${article.aid }" style='color:#333;'><h4>${article.aname }</h4></a>
		                                <span class="glyphicon glyphicon-calendar"></span>&nbsp;<span>${article.adate }</span>
		                                <hr style="border:1px dashed grey;">
		                            </c:forEach>
	                            </div>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
            <!--占位-->
            <div class="col-lg-2 hidden-md hidden-sm hidden-xs"></div>
        </div>
        <!--占位-->
        <div class="row">
            <div class="col-lg-12" style="height: 20px"></div>
        </div>
    </div>
    <!--插入footer-->
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>