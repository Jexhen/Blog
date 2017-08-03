<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
		<div class="row">
                 <div class="col-lg-12">
                     <div class="ibox float-e-margins">
                         <div class="ibox-title">
                             <h5>管理员个人资料编辑 <small>Personal profile</small></h5>
                             <div class="ibox-tools">
                                 <a class="collapse-link">
                                     <i class="fa fa-chevron-up"></i>
                                 </a>
                                 <a class="close-link">
                                     <i class="fa fa-times"></i>
                                 </a>
                             </div>
                         </div>
                         <div class="ibox-content">
                             <form action="${pageContext.request.contextPath }/admin/updateProfile" method="post" class="form-horizontal" enctype="multipart/form-data">
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">登录名</label>
                                     <div class="col-sm-10">
                                         <input type="text" class="form-control" name="username" value="${admin.username }">
                                     </div>
                                 </div>
                                 <div class="hr-line-dashed"></div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">真实姓名</label>
                                     <div class="col-sm-10">
                                         <input type="text" class="form-control" name="realname" value="${admin.realname }">
                                     </div>
                                 </div>
                                 <div class="hr-line-dashed"></div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">性别
                                     </label>

                                     <div class="col-sm-10">
                                         <div class="radio">
                                             <label>
                                                 <input type="radio" value="male" id="optionsRadios1" name="gender">男</label>
                                         </div>
                                         <div class="radio">
                                             <label>
                                                 <input type="radio" value="female" id="optionsRadios2" name="gender">女</label>
                                         </div>
                                     </div>
                                 </div>
                                 <div class="hr-line-dashed"></div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">出生日期</label>
                                     <div class="col-sm-10">
                                         <input type="text" class="form-control" name="birthday" value="${admin.birthday }">
                                     </div>
                                 </div>
                                 <div class="hr-line-dashed"></div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">邮箱</label>
                                     <div class="col-sm-10">
                                         <input type="text" class="form-control" name="email" value="${admin.email }">
                                     </div>
                                 </div>
                                 <div class="hr-line-dashed"></div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">更改头像</label>
                                     <div class="col-sm-10">
                                         <input type="file" class="form-control" name="filename">
                                     </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="col-sm-2 control-label">当前头像</label>
                                     <img class="col-lg-2" alt="image" class="img-circle" src="${pageContext.request.contextPath }/${admin.avata }"  width="50%" height="50%"/>
                                 </div>
                                 <div class="hr-line-dashed"></div>
                                 <div class="form-group">
                                     <div class="col-sm-4 col-sm-offset-2">
                                         <button class="btn btn-primary" type="submit">保存内容</button>
                                         <button class="btn btn-white" type="submit">取消</button>
                                     </div>
                                 </div>
                             </form>
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
		        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.reload()">确定</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
</body>
<!-- Mainly scripts -->
<script src="${pageContext.request.contextPath }/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js?v=3.4.0"></script>
<script src="${pageContext.request.contextPath }/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath }/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="${pageContext.request.contextPath }/js/hplus.js?v=2.2.0"></script>
<script src="${pageContext.request.contextPath }/js/plugins/pace/pace.min.js"></script>

<!-- iCheck -->
<script src="${pageContext.request.contextPath }/js/plugins/iCheck/icheck.min.js"></script>
<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
        //回显性别
        $(":radio").each(function(){
			if ($(this).val()==='${admin.gender }') {
        		$(this).attr("checked",true);
        	}
        });
    });
  //弹出提示信息的模态框
    function showTips(tips) {
    	$("#tips").empty();
    	$("#tips").append(tips);
    	$("#tipsmodal").modal();
    }
</script>
</html>