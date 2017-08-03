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
    <link href="${pageContext.request.contextPath }/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
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
                            <li><a href="${pageContext.request.contextPath }/admin/getArticleToSupporter">已发表</a>
                            </li>
                            <li  class="active"><a href="${pageContext.request.contextPath }/admin/getDraftToSupporter">草稿箱</a></li>
                            <li><a href="${pageContext.request.contextPath }/admin/getCarouselToSupporter">Carousel</a></li>
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
                <div class="col-lg-12">
                    <div class="mail-box-header">
                        <div class="pull-right tooltip-demo">
                            <a href="javascript:void(0)" class="btn btn-white btn-sm draft" data-toggle="tooltip" data-placement="top" title="存为草稿"><i class="fa fa-pencil"></i> 存为草稿</a>
                            <a href="javascript:void(0)" class="btn btn-danger btn-sm discardOperation" data-toggle="tooltip" data-placement="top" title="放弃"><i class="fa fa-times"></i> 放弃</a>
                        </div>
                        <h2>编辑文章</h2>
                    </div>
                    <div class="mail-box">


                        <div class="mail-body">

                            <form class="form-horizontal" method="get">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">标题：</label>
                                    <div class="col-sm-9">
                                        <input id="dname" type="text" class="form-control" value="${draft.dname }">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">分类：</label>
                                    <div class="col-sm-9">
                                        <select class="form-control" id="cid">
                                       		<c:forEach items="${category }" var="citem">
                                       			<c:if test="${draft.cid==citem.cid }">
                                       				<option selected="selected" value="${citem.cid }">${citem.cname }</option>
                                       			</c:if>
                                       			<c:if test="${draft.cid!=citem.cid }">
                                       				<option value="${citem.cid }">${citem.cname }</option>
                                       			</c:if>
                                       		</c:forEach>
										</select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">标签：</label>
                                    <div class="col-sm-9">
                                        <input id="dtags" type="text" class="form-control" value="${draft.dtags }">
                                    </div>
                                </div>
                            </form>

                        </div>

                        <div class="mail-text h-200">
                            <div class="col-sm-1"></div>
                            <script id="editor" type="text/plain" class="col-sm-10" style="height:500px;">
							</script>
                            <div class="clearfix"></div>
                        </div>
                        <div class="mail-body text-right tooltip-demo">
                            <a href="javascript:void(0)" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="top" id="send" title="deliver"><i class="fa fa-reply"></i> 发表</a>
                            <a href="javascript:void(0)" class="btn btn-white btn-sm discardOperation" data-toggle="tooltip" data-placement="top" title="Discard"><i class="fa fa-times"></i> 放弃</a>
                            <a href="javascript:void(0)" class="btn btn-white btn-sm draft" data-toggle="tooltip" data-placement="top" title="Move to draft folder"><i class="fa fa-pencil"></i> 存为草稿</a>
                        </div>
                        <div class="clearfix"></div>
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
			        <button id="success" type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.reload()">前往查看</button>
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
    
    <!-- UEditor -->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/supporter/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/supporter/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/supporter/lang/zh-cn/zh-cn.js"></script>

    <script>
    	var ue = UE.getEditor('editor');
    	$(function(){
            //ueditor初始化插入数据库内容,ueditor加载比较慢,而为uditor设置内容又需要等其加载完毕才能执行成功,所以设置定时器一直试探,如果ue发现有内容,说明ue已经加载并且内容已经写入则不再试探
    		timer = setInterval(function(){
            	if (ue.hasContents()) {
            		clearInterval(timer);
            	} else {
            		//draft.dcontent是个多行字符串，如果不采用特殊方式进行转换将出现语法错误，所以调用转换多行字符串的函数hereDoc
            		var value = hereDoc(function(){/*
            			${draft.dcontent }
            		*/});
                	ue.setContent(value);	            			
            	}
            }, 1000); 
            //点击放弃按钮
    		 $(".discardOperation").click(function(){
             	var isConfirm = confirm("您确定放弃本次操作吗?");
             	if (isConfirm) {
             		location.href="${pageContext.request.contextPath}";
             	}
             });
    		 //点击发表按钮
    		 $("#send").click(function(){
    			var target = "${pageContext.request.contextPath}/admin/draftToPublic";
    			var param = getParameters();
              	$.ajax({
              		url:target,
              		type:"post",
              		data:param,
              		success:function(data) {
              			if (data.isOk) {
              				$("#success").attr("onclick","redirect('${pageContext.request.contextPath}/readArticle?aid="+data.aid+"')");
                  			showTips("发表成功！");
                  		} else {
                  			$("#success").attr("onclick", "");
                  			showTips("发表失败！请稍后再试！");
                  		}
              		},
              		dataType:"json"
              	});
              });
    		 $(".draft").click(function(){
    			//获取需要存入草稿箱的参数
    			 var did = "${draft.did }"
    			 var dname = encodeURI(encodeURI($("#dname").val()));
    			 var dcontent = encodeURI(encodeURI(ue.getContent())); 
    			 var dtags = encodeURI(encodeURI($("#dtags").val()));
    			 var cid = $("select").val();
    			 var param = {"did":did,"dname":dname,"dcontent":dcontent,"dtags":dtags,"cid":cid };
    			 
    			 $.ajax({
    				 type:"post",
    				 url:"${pageContext.request.contextPath }/admin/updateDraft",
    				 data:param,
    				 success:function(data){
    					if (data==="true") {
     						$("#success").attr("onclick","redirect('${pageContext.request.contextPath}/admin/getDraftToSupporter')");
                   			showTips("成功存入草稿箱！");
                   		} else {
                  			$("#success").attr("onclick", "");
                  			showTips("发表失败！请稍后再试！");
                   		}
    				 }
    			 });
    			 alert(param);
    			
    		 });
    	});
    	//获取需要提交的数据
    	function getParameters() {
    		var did = "${draft.did }"
    		var aid = "${draft.aid }";
          	var dname = encodeURI(encodeURI($("#dname").val()));
          	var dcontent = encodeURI(encodeURI(ue.getContent()));
          	var	adate = encodeURI(encodeURI(new Date().toLocaleString()));
          	var dtags = encodeURI(encodeURI($("#dtags").val()));
          	var summary = encodeURI(encodeURI(getSummary()));
          	var cid = $("select").val();
          	var param = {"did":did,"aid":aid,"dname":dname,"dcontent":dcontent,"adate":adate,"dtags":dtags,"summary":summary,"cid":cid};
          	return param;
    	}
    	//从内容摘取摘要
       function getSummary() {
    	   var txt = ue.getContentTxt();
    	   var summary = "";
    	   if (txt.length<=100) {
    		   summary = txt;
    	   } else {
    		   summary = txt.substring(0, 100);
    	   }
    	   return summary;
       }
    	//返回一个多行字符串
       	function hereDoc(f) {　
    	    return f.toString().replace(/^[^\/]+\/\*!?\s?/, '').replace(/\*\/[^\/]+$/, '');
    	}
     	//弹出提示信息的模态框
       function showTips(tips) {
       	$("#tips").empty();
       	$("#tips").append(tips);
       	$("#tipsmodal").modal();
       }
       function redirect(url) {
      	 location.href=url;
       }
    </script>
</body>

</html>