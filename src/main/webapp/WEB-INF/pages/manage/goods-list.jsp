<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="./public/meta.jsp"></jsp:include>
<title>商品列表</title>
</head>
<body>
<jsp:include page="./public/head.jsp"></jsp:include>
<jsp:include page="./public/menu.jsp"></jsp:include>

<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 商品列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="cl pd-5 bg-1 bk-gray mt-20"> 
				<span class="l">
					<!-- <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>  -->
					<a class="btn btn-primary radius" onclick="picture_add('添加商品','goods-add.html')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加商品</a>
				</span> 
				<span class="r">共有数据：<strong>${count}</strong> 条</span> 
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="40" hidden><input name="" type="checkbox" value=""></th>
							<td hidden></td>
							<th width="100">分类</th>
							<th width="100">商品图片</th>
							<th>商品名称</th>
							<th width="150">价格</th>
							<th width="60">评论</th>
							<th width="60">商品状态</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${goodsList}" var="goods">
						<tr class="text-c">
							<td hidden><input name="" type="checkbox" value=""></td>
							<td hidden></td>
							<td>${goods.category}</td>
							<td><img width="100" class="picture-thumb" src="${goods.image}" style="width:60px; height:60px;"></td>
							<td class="text-l">${goods.title}</td>
							<td class="text-c">${goods.price }</td>
							<td>${goods.comments}</td>
							<c:if test="${goods.state == 1}">
								<td class="td-status"><span class="label label-success radius">已上架</span></td>
							</c:if>
							<c:if test="${goods.state == 0}">
								<td class="td-status"><span class="label label-defaunt radius">已下架</span></td>
							</c:if>
							<td class="td-manage">
								<c:if test="${goods.state == 1}">
									<a style="text-decoration:none" onClick="picture_stop(this,'10001')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a> 
								</c:if>
								<c:if test="${goods.state == 0}">
									<a style="text-decoration:none" onClick="picture_start(this,id)" href="javascript:;" title="上架"><i class="Hui-iconfont">&#xe603;</i></a>
								</c:if>
								<a style="text-decoration:none" class="ml-5" onClick="picture_edit('商品编辑','goods-alter/${goods.id}.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> 
								<a style="text-decoration:none" class="ml-5" onClick="picture_del(this,'10001')" href="javascript:;" title="删除" data-goods="${goods.id}"><i class="Hui-iconfont">&#xe6e2;</i></a>
							</td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		</article>
	</div>
</section>

<jsp:include page="./public/foot.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/staticfile/manage/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="/staticfile/manage/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/staticfile/manage/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,8]}// 制定列不参与排序
	]
});
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}

/*图片-下架*/
function picture_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
		$.ajax({
			type: "post",
			url: "updateGoodsState.html",
			data: {
				'id': $(obj).next().next().attr("data-goods"),
				'state': 0
			},
			success: function(result) {
				var jsonObj = JSON.parse(result);
				if( jsonObj.state == 1) {
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="picture_start(this,id)" href="javascript:;" title="上架"><i class="Hui-iconfont">&#xe603;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
					$(obj).remove();
					layer.msg('已下架!',{icon: 5,time:1000});
				} else {
					layer.msg('下架失败,请稍后再试!',{icon: 5,time:1000});
				}
			},
			error: function(errorMsg) {
				layer.msg('下架错误,请稍后再试!',{icon: 5,time:1000});
			}
		});
	});
}

/*图片-发布*/
function picture_start(obj,id){
	layer.confirm('确认要上架吗？',function(index){
		$.ajax({
			type: "post",
			url: "updateGoodsState.html",
			data: {
				'id': $(obj).next().next().attr("data-goods"),
				'state': 1
			},
			success: function(result) {
				var jsonObj = JSON.parse(result);
				if( jsonObj.state == 1) {
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="picture_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已上架</span>');
					$(obj).remove();
					layer.msg('已上架!',{icon: 6,time:1000});
				} else {
					layer.msg('上架失败,请稍后再试!',{icon: 5,time:1000});
				}
			},
			error: function(errorMsg) {
				layer.msg('上架错误,请稍后再试!',{icon: 5,time:1000});
			}
		});
	});
}

/*图片-编辑*/
function picture_edit(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-删除*/
function picture_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: "post",
			url: "updateGoodsState.html",
			data: {
				'id': $(obj).attr("data-goods"),
				'state': -1
			},
			success: function(result) {
				var jsonObj = JSON.parse(result);
				if( jsonObj.state == 1) {
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				} else {
					layer.msg('删除失败,请稍后再试!',{icon: 5,time:1000});
				}
			},
			error: function(errorMsg) {
				layer.msg('删除错误,请稍后再试!',{icon: 5,time:1000});
			}
		});
	});
}
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>