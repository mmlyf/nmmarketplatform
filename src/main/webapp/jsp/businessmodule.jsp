<!-- 业务管理 -->
<%@page import="com.mtpt.service.impl.TBReviewService"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="com.mtpt.service.impl.TBRecordService"%>
<%@page import="com.mtpt.bean.TBRecord"%>
<%@page import="java.util.List"%>
<%@page import="com.mtpt.service.ITBRecordService"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
<title></title>

<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<ul class="layui-tab-title">
					<li class="layui-this">订单查询</li>
					<li>号码查询</li>
				</ul>

				<div class="layui-tab-content">
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated">
							<blockquote class="layui-elem-quote">
								<div class="selectorder">
								<form action="" class="layui-form" class="layui-inline">
									<div class="layui-input-inline">
										<input type="text" name="key" id="phone" class="layui-input"
											placeholder="请输入手机号">
									</div>
									<div class="layui-input-inline">
										<input type="text" name="busdate_star" id="busdate_star"
											lay-verify="date" placeholder="营销开始日期" class="layui-input">
									</div>
									<div class="layui-input-inline">
										<input type="text" name="busdate_end" id="busdate_end"
											lay-verify="date" placeholder="营销结束日期" class="layui-input">
									</div>
									<div class="layui-input-inline">
										<input type="text" name="modate_star" id="modate_star"
											lay-verify="date" placeholder="反馈开始时间" class="layui-input">
									</div>
									<div class="layui-input-inline">
										<input type="text" name="modate_end" id="modate_end"
											lay-verify="date" placeholder="反馈结束时间" class="layui-input">
									</div>
									<!-- <div class="layui-input-inline">
										<select name="ddlx" id="ddlx" lay-search="" lay-filter="ddlx">
											<option value="-1">订单状态</option>
											<option value="-1">全部</option>
											<option value="0">成功</option>
											<option value="404">不成功</option>
											<option value="">联通未反馈</option>
										</select>
									</div> -->
									<div class="layui-input-inline">
										<select name="qudao" id="qudao" lay-search=""
											lay-verify="required" lay-filter="qudao">
											<option value="">渠道</option>
											<option value="">全部</option>
											<option value="1">短信</option>
											<option value="2">支付宝</option>
										</select>
									</div>
									<div class="layui-input-inline">
										<select name="dangw" id="dangw" lay-search="" lay-filter="dangw">
											
										</select>

									</div>
									<div class="layui-input-inline">
										<label class="layui-form-label"
											style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
											class="num_peo" id="listnum">
										</span>
									</div>
									
									</form>
									<button class="layui-btn" data-type="allreload">搜索</button>
								</div>
								<script type="text/javascript" src="../layui/layui.js"></script>
								<script type="text/javascript">
									//实现时间的选框的效果
									layui.use('laydate', function() {
										var laydate = layui.laydate;
										//执行一个laydate实例
										laydate.render({
											elem : '#busdate_star' //指定元素
											,
											position : 'abolute',
											type : 'datetime'
										});
										laydate.render({
											elem : '#busdate_end',
											position : 'ablute',
											type : 'datetime'
										});
										laydate.render({
											elem : '#modate_star' //指定元素
											,
											position : 'abolute',
											type : 'datetime'
										});
										laydate.render({
											elem : '#modate_end',
											position : 'ablute',
											type : 'datetime'
										});
									});
								</script>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>订单列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="busimodel" lay-filter="busimodel"></table>

									<script type="text/html" id="toolbarDemo">
 									 <div class="layui-btn-container">
  										</div>
									</script>
									<script type="text/html" id="barDemo">
									<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
									</script>
									<script src="http://code.jquery.com/jquery-1.8.0.min.js"
										type="text/javascript"></script>
									<link rel="stylesheet" href="../layui/css/layui.css"
										media="all">
									<script type="text/javascript" src="../layui/layui.js"></script>
									</head>
									<script>
										layui.use('table',function() {
											var table = layui.table, form = layui.form, layer = layui.layer, $ = layui.jquery, upload = layui.upload;;
											table.render({
												elem : '#busimodel',
												url : '../buscontro/selectbypage',
												cellMinWidth : 80,
												width:window.innerWidth*0.94,
												toolbar:'toolbarDemo',
												title : '群组列表',
												cols : [[{
													type : 'checkbox',
													fixed : 'left'
												},
												{
													field : 'id',
													title : '编号',
													sort : true,
													unresize : true,
													fixed : 'left',
													align : 'center'
												},
												{
													field : 'dn',
													title : '号码',
													align : 'center',
													width:130
												},
												{
													field : 'firp',
													title : '产品',
													align : 'center',
													width:310
												},
												{
													field : 'firdw',
													title : '档位',
													sort : true,
													align : 'center',
													width:80
												},
												{
													field : 'momsg',
													title : '上行',
													align : 'center'
												},
												{
													field : 'city',
													title : '地市',
													align : 'center'
												},
												{
													field : 'systype',
													title : '网段',
													width:80
												},
												{
													field : 'qudao',
													title : '渠道',
													width:80
												},
												{
													field : 'mttime',
													title : '营销时间',
													width:170
												},
												{
													field : 'motime',
													title : '反馈时间',
													width:170
												},
												{
													field : 'state',
													title : '状态',
													align : 'center'
												},
												{
													fixed : 'right',
													title : '操作',
													toolbar : '#barDemo',
													width : 180,
													align : 'center'
												} ]] ,
												page : true,
												limit:10,
												id : 'tasklist',
												done:function(data){
													$('#listnum').text(data.count+"条");
												}
											});table.on('toolbar(busimodel)',function(obj){
												
											});
											table.on('tool(busimodel)',function(obj) {
												var data = obj.data;
													 if(obj.event === 'detail'){
														var str = "{<br>id:"
															+ data.id
															+ "<br>号码:"
															+ data.dn
															+ "<br>产品:"
															+ data.firp
															+ "<br>档位:"
															+ data.dangw
															+ "<br>上行："
															+ data.momsg
															+ "<br>城市："
															+ data.city
															+ "<br>网段："
															+ data.systype
															+ "<br> 渠道："
															+ data.qudao
															+ "<br>营销时间:"
															+ data.mttime
															+ "<br>反馈时间："
															+ data.motime
															+ "<br>状态:"
															+ data.state
															+ "}";
														layer.alert('当前数据是：'+ str)
													}
												});
											var $ = layui.$, active = {allreload : function() {
												var dn = $('#phone').val();
												var busdate_star = $('#busdate_star').val();
												var busdate_end = $('#busdate_end').val();
												var modate_star = $('#modate_star').val();
												var modate_end = $('#modate_end').val();
												var qudao = $('#qudao').val();
												var dangw = $('#dangw').val(); 
												table.reload('tasklist',{where:{
													dn : dn ,
													busdate_star : busdate_star,
													busdate_end : busdate_end,
													modate_star:modate_star,
													modate_end:modate_end,
													qudao : qudao,
													dangw : dangw 
												}
											});
										}};
										$('.selectorder .layui-btn').on('click',function() {
											var type = $(this).data(	'type');
											active[type] ? active[type].call(this): '';
										});
										
										$.ajax({
											url : '../requestdata/selectdw',
											type : 'post',
											dataType : 'json',
											success : function(result) {
												var json = eval(result);
												var optionstr = "";
												$.each(json, function(index, item) {
														optionstr += "<option value='"+item+"'>"
														+ item + "</option>";
												});
												$("#dangw").html("<option></option> "+optionstr);
												form.render('select');
											}
										});
									});
									</script>
								</div>
							</fieldset>
						</div>
					</div>
					<!-- 通过号码查询数据 -->
					<div class="layui-tab-item">
						<div class="admin-main fadeInUp animated">
							<blockquote class="layui-elem-quote">
								<div class="modelTable">
									搜索：
									<div class="layui-inline">
										<input class="layui-input" name="id" id="phonenum"
											autocomplete="off" placeholder="请输入号码">
									</div>
									<button class="layui-btn" data-type="reload">搜索</button>
									<div class="layui-inline">
										<label class="layui-form-label"
											style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
											class="num_peo" id="dnselectnum"> </span>
									</div>
								</div>
								<div
									style="width: 100%; height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>订单列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="dnselect" lay-filter="dnselect"></table>

									<script type="text/html" id="toolbarDemo">
 									 <div class="layui-btn-container">
  										</div>
									</script>
									<script type="text/html" id="barDemo">
 									 	<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
									</script>
									<script>
										layui
												.use(
														'table',
														function() {
															var table = layui.table;

															table.render({
																		elem : '#dnselect',
																		url : '',
																		cellMinWidth : 80,
																		width : window.innerWidth*0.94,
																		toolbar : 'toolbarDemo',
																		title : '群组列表',
																		cols : [ [
																				{
																					type : 'checkbox',
																					fixed : 'left'
																				},
																				{
																					field : 'id',
																					title : '编号',
																					sort : true,
																					unresize : true,
																					fixed : 'left',
																					align : 'center'
																				},
																				{
																					field : 'dn',
																					title : '号码',
																					align : 'center',
																					width : 130
																				},
																				{
																					field:'firp',
																					title:'产品',
																					align:'center',
																					width:80
																				},
																				{
																					field:'firdw',
																					title:'档位',
																					align:'center'
																				},
																				{
																					field:'momsg',
																					title:'上行',
																					align:'center'
																				},
																				{
																					field:'mttime',
																					title:'营销时间',
																					align:'center',
																					width:170
																				},
																				{
																					field:'motime',
																					title:'反馈时间',
																					align:'center',
																					width:170
																				},
																				{
																					field : 'qudao',
																					title : '渠道',
																					width : 80
																				},
																				
																				{
																					field:'msgContent',
																					title:'营销内容',
																					width:300,
																					align:'center'
																					
																				},
																				{
																					field:'city',
																					title:'城市',
																					width:100,
																					fixed:'right'
																				}
																				] ],
																		page : true,
																		limit : 10,
																		where : {
																			phonenum:''
																		},
																		id : 'dnselectlist',
																		done : function(
																				data) {
																			$('#dnselectnum').text(data.count+ "条");
																		}
																	});

															table.on('toolbar(dnselect)',function(obj) {});
															var $ = layui.$, active = {
																	reload : function() {
																		var dn = $('#phonenum').val();
																		 
																		//执行重载
																		table.reload('dnselectlist',
																			{
																			url:'../buscontro/selectbypage',
																			where:{
																				phonenum : dn 
																				}
																			});
																	}
																};
																$('.modelTable .layui-btn').on('click',
																	function() {
																		var type = $(this).data(	'type');
																		layer.msg(type);
																		active[type] ? active[type].call(this): '';
																	});
															});
									</script>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		layui.use('element', function() {
			var $ = layui.jquery, element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

			//触发事件
			var active = {
				tabAdd : function() {
					//新增一个Tab项
					element.tabAdd('demo', {
						title : '新选项' + (Math.random() * 1000 | 0) //用于演示
						,
						content : '内容' + (Math.random() * 1000 | 0),
						id : new Date().getTime()
					//实际使用一般是规定好的id，这里以时间戳模拟下
					})
				},
				tabDelete : function(othis) {
					//删除指定Tab项
					element.tabDelete('demo', '44'); //删除：“商品管理”

					othis.addClass('layui-btn-disabled');
				},
				tabChange : function() {
					//切换到指定Tab项
					element.tabChange('demo', '22'); //切换到：用户管理
				}
			};

			$('.site-demo-active').on('click', function() {
				var othis = $(this), type = othis.data('type');
				active[type] ? active[type].call(this, othis) : '';
			});

			//Hash地址的定位
			var layid = location.hash.replace(/^#test=/, '');
			element.tabChange('test', layid);

			element.on('tab(test)', function(elem) {
				location.hash = 'test=' + $(this).attr('lay-id');
			});

		});

		//监听指定开关

		layui.use('form', function() {
			var form = layui.form, layer = layui.layer;
			form.on('switch(switchTest)', function(data) {
				layer.msg('定时任务' + (this.checked ? '开' : '关'), {
					offset : '6px'
				});
				if (this.checked) {
					$('#timechoice').removeClass('layui-hide');
				} else {
					$('#timechoice').addClass('layui-hide');
				}
			});
		});
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#test1' //指定元素
				,
				position : 'abolute',
				type : 'datetime'
			});
		});
	</script>
</body>
</html>