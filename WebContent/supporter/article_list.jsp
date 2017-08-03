<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">

    <title>廖志行博客后台管理系统</title>

    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/style.css?v=2.2.0" rel="stylesheet">

</head>

<body>
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">

                        <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="${pageContext.request.contextPath }/${admin.avata }"  width="50%" height="50%"/>
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0);">
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${admin.realname }</strong>
                             </span>  <span class="text-muted text-xs block">超级管理员 <b class="caret"></b></span> </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="${pageContext.request.contextPath }/supporter/edit_profile.jsp">个人资料</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="${pageContext.request.contextPath }/admin/logout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            Jexhen
                        </div>

                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath }"><i class="fa fa-th-large"></i> <span class="nav-label">主页</span></a>
                    </li>
                    <li>
                    	<a href="index.html#"><i class="fa fa-desktop"></i> <span class="nav-label">页面</span></a>
                        <ul class="nav nav-second-level">
                        	<li><a href="${pageContext.request.contextPath }/supporter/edit_profile.jsp">个人资料</a>
                        </ul>
					</li>
                    <li class="active">
                        <a href="javascript:void(0)"><i class="fa fa-table"></i> <span class="nav-label">博客管理</span></a>
                        <ul class="nav nav-second-level">
                            <li class="active"><a href="${pageContext.request.contextPath }/admin/getArticleToSupporter">已发表</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath }/admin/getDraftToSupporter">草稿箱</a>
                            <li><a href="${pageContext.request.contextPath }/admin/getCarouselToSupporter">Carousel</a></li>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="http://www.uyan.cc/sites"><i class="fa fa-comments"></i> <span class="nav-label">评论管理</span></a>
                    </li>
                </ul>

            </div>
        </nav>

        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="javascript:void(0);"><i class="fa fa-bars"></i> </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <span class="m-r-sm text-muted welcome-message"><a href="${pageContext.request.contextPath }/admin/getArticleToSupporter" title="返回首页"><i class="fa fa-home"></i></a>欢迎使用博客后台管理系统</span>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath }/admin/logout">
                                <i class="fa fa-sign-out"></i> 退出
                            </a>
                        </li>
                    </ul>

                </nav>
            </div>
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2>博客管理</h2>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
            <div class="wrapper wrapper-content animated fadeInRight">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>博客表格</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                                        <i class="fa fa-wrench"></i>
                                    </a>
                                    <ul class="dropdown-menu dropdown-user">
                                        <li><a href="${pageContext.request.contextPath }/admin/editArticle">新建</a>
                                        </li>
                                        <li id="delete"><a href="javascript:void(0)">删除</a>
                                        </li>
                                    </ul>
                                    <a class="close-link">
                                        <i class="fa fa-times"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-sm-5 m-b-xs">
                                        <select id="cid" class="input-sm form-control input-s-sm inline">
                                            <option value="">请选择</option>
                                            <c:forEach items="${category }" var="citem">
                                            	<option value="${citem.cid }">${citem.cname }</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="input-group">
                                            <input id="keyWord" type="text" placeholder="请输入关键词" class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary" onclick="searchByCondition()"> 搜索</button> </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>题目</th>
                                                <th>阅读数</th>
                                                <th>评论数</th>
                                                <th>日期</th>
                                                <th>编辑</th>
                                                <th>删除</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach items="${articles }" var="article">
	                                        	<tr>
	                                                <td>
	                                                    <input type="checkbox" name="input[]" value="${article.aid }">
	                                                </td>
	                                                <td>${article.aname }</td>
	                                                <td>${article.aview }</td>
	                                                <td>${article.acomment }</td>
	                                                <td>${article.adate }</td>
	                                                <td><a href="${pageContext.request.contextPath }/admin/editArticle?aid=${article.aid }"><i class="fa fa-file-text text-navy"></i></a>
	                                                </td>
	                                                <td><a href="javascript:void(0)" onclick="deleteArticle('${article.aid }')"><i class="fa fa-times text-navy"></i></a>
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
            
            <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" id="tipsmodal">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title">页面提示</h4>
			      </div>
			      <div class="modal-body" id="tips">
			        	
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.reload(true)">确定</button>
			      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
            
            <div class="footer">
                <div class="pull-right">
                    By：<a href="${pageContext.request.contextPath }" target="_blank">廖志行</a>
                </div>
                <div>
                    <strong>Copyright</strong> Jexhen &copy; 2017
                </div>
            </div>

        </div>
    </div>


    </div>

    <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath }/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="${pageContext.request.contextPath }/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath }/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Peity -->
    <script src="${pageContext.request.contextPath }/js/plugins/peity/jquery.peity.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="${pageContext.request.contextPath }/js/hplus.js?v=2.2.0"></script>
    <script src="${pageContext.request.contextPath }/js/plugins/pace/pace.min.js"></script>

    <!-- iCheck -->
    <script src="${pageContext.request.contextPath }/js/plugins/iCheck/icheck.min.js"></script>

    <!-- Peity -->
    <script src="${pageContext.request.contextPath }/js/demo/peity-demo.js"></script>

    <script>
        $(function(){
        	var arr = [];
        	$("#delete").click(function(){
        		$("input:checked").each(function(){
        			arr.push($(this).val());
        		});
        		var isConfirm = confirm("您确认删除所选的" + arr.length + "篇文章吗？");
        		if (isConfirm) {
	        		$.ajax({
	        			type:"post",
	        			url:"${pageContext.request.contextPath }/admin/removeArticle",
	        			data:{"aids":arr},
	        			success:function(data){
	        				if (data==="true") {
	        					showTips("删除成功！");
	        				} else {
	        					showTips("删除失败！请稍后再试");
	        				}
	        			}
	        		});
        		}
        	});
        	
        });
        function deleteArticle(aid){
        	var isConfirm = confirm("您确认删除该篇文章吗？");
        	if (isConfirm) {
        		$.ajax({
        			type:"post",
        			url:"${pageContext.request.contextPath }/admin/removeArticle",
        			data:{"aid":aid},
        			success:function(data){
        				if (data==="true") {
        					showTips("删除成功！");
        				} else {
        					showTips("删除失败！请稍后再试");
        				}
        			}
        		});
        	}
        }
        function searchByCondition() {
        	var cid = $("#cid").val();
        	var keyWord = encodeURI($("#keyWord").val());
        	var param = {"cid":cid, "keyWord":keyWord};
        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath}/admin/getArticleByCondition",
        		data:param,
        		success:function(articles){
        			var newTbody = "共为您查询到"+articles.length+"条结果<br/>";
        			for (var i = 0; i < articles.length; i++) {
						newTbody = newTbody + getNewTbodyEle(articles[i]);
        			}
        			$("tbody").html(newTbody);
        		},
        		dataType:"json"
        	});
        }
        function getNewTbodyEle(article) {
        	var s1 = "<tr>";
            var s2 = "<td><input type='checkbox' name='input[]' value='"+article.aid+"'></td>";
            var s3 = "<td>"+article.aname+"</td>";
            var s4 = "<td>"+article.aview+"</td>";
            var s5 = "<td>"+article.acomment+"</td>";
            var s6 = "<td>"+article.adate+"</td>";
            var s7 = "<td><a href='/admin/editArticle?aid="+article.aid+"'><i class='fa fa-file-text text-navy'></i></a></td>";
            var s8 = "<td><a href=\"javascript:void(0)\" onclick=\"deleteArticle('"+article.aid+"')\"><i class='fa fa-times text-navy'></i></a></td>";
            var s9 = "</tr>";
            return s1+s2+s3+s4+s5+s6+s7+s8+s9;    
        }
      	//弹出提示信息的模态框
        function showTips(tips) {
        	$("#tips").empty();
        	$("#tips").append(tips);
        	$("#tipsmodal").modal();
        }
    </script>


</body>

</html>
