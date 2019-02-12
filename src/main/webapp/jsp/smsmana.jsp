<!-- 短信任务创建 -->
<%@page import="com.mtpt.service.impl.TBProdLxService"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="../layui/css/layui.css" media="all" />
<script src="http://code.jquery.com/jquery-1.8.0.min.js" type="text/javascript"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<ul class="layui-tab-title">
					<li class="layui-this">文件导入</li>
					<li>自动筛选</li>
				</ul>
				<div class="layui-tab-content">
					<!-- 文件导入 -->
					<div class="layui-tab-item layui-show" style="height: 1000px;" >
						<div class="admin-main fadeInUp animated ng-scope">
							<fieldset class="layui-elem-field layui-field-title"
								style="margin-top: 20px;">
								<legend>文件导入任务</legend>
							</fieldset>
							<form class="layui-form layui-form-pane" method="post" action="../smscreate/filein"
								  enctype="multipart/form-data">
								<div class="layui-form-item">
									<label class="layui-form-label">用户群名称</label>
									<div class="layui-input-block">
										<input type="text" name="groupname" required lay-verify="required"
											placeholder="请输入名称" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">导入文件</label>
									<div class="layui-input-block" style="position: relative;">
										<input class="layui-input" type="file" name="file_stu"
											id='daos'
											onchange="document.getElementById('textfield').value=this.value"
											style="position: absolute; top: 0; left: 150px; height: 20px; opacity: 0; width: 60px;" />
										<input type='text' name='textfield' id='textfield' class='txt'
											style="height: 30px;" /> <input type='button' class='btn'
											value='浏览...' style="height: 30px;" autocomplete="off"/>

									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">定时任务</label>
									<div class="layui-input-block">
										<input type="checkbox" name="istimework"
											lay-filter="switchTest" lay-skin="switch" lay-text="ON|OFF">
									</div>
								</div>
								
								<div class="layui-form-item layui-hide" id="timechoice">
									<label class="layui-form-label">时间选择</label>
									<div class="layui-input-inline">
										<input type="text" class="layui-input" id="test1"
											name="worktime" placeholder="yyyy-MM-dd hh:mm:ss"
											autocomplete="off" />
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">短信语</label>
									<div class="layui-input-block">
										<select name="migId1" id="filesmsm" lay-search="" lay-filter="smsms" required lay-verify="required" lay-search>
										</select>
									</div>
								</div>
								
								<div class="layui-form-item">
									<div class="layui-input-inline">
										<input type="checkbox" name="isdelblack" title="去黑名单"/>
									</div> 
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">重复运营</label>
									<div class="layui-input-inline">
										<input type="checkbox" name="isdeldays"
											lay-filter="switchdel" lay-skin="switch" lay-text="ON|OFF">
									</div>
									<div class="layui-input-inline layui-hide" id="deldays">
										<input type="text" class="layui-input"  name="deldays" autocomplete="false"/>
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">审核人</label>
									<div class="layui-input-block">
										<select name="reviewman" lay-search="" lay-verify="required">
											<option value=""></option>
											<option value="裴秋婷">裴秋婷</option>
										</select>
									</div>
								</div>
								<div class="layui-form-item layui-hide">
									<label class="layui-form-label"></label>
									<div class="layui-input-block">
										<input type="hidden" value="<%=session.getAttribute("realname") %>"
											name="addman">
									</div>
								</div>
								
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="submit" lay-submit
											lay-filter="formDemo">立即提交</button>
										<button type="reset" class="layui-btn layui-btn-primary">重置</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					
					
					<!-- 数据维度筛选 -->
					<div class="layui-tab-item">
						<div class="admin-main fadeInUp animated ng-scope" style="height:1000px">
							<fieldset class="layui-elem-field layui-field-title"
								style="margin-top: 20px;">
								<legend>添加筛选规则</legend>
							</fieldset>
							<form class="layui-form layui-form-pane" method="post" action="../smscreate/modelIn"
								enctype="multipart/form-data" >
								<div class="layui-form-item" id="city">
									<label class="layui-form-label">城市</label>
									<div class="layui-input-block">
										<select name="city" lay-search="">
											<option value="all" selected >全部</option>
											<option value="471">呼市</option>
											<option value="472">包头</option>
											<option value="473">乌海</option>
											<option value="474">乌盟</option>
											<option value="475">通辽</option>
											<option value="476">赤峰</option>
											<option value="474">鄂市</option>
											<option value="478">巴盟</option>
											<option value="479">锡盟</option>
											<option value="482">兴安</option>
											<option value="483">阿萌</option>
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">产品</label>
									<div class="layui-input-block">
										<select name="product" id="prod" lay-search="" lay-filter="prod" required lay-verify="required">
											<option selected value="0">畅越大流量</option>
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">档位</label>
									<div class="layui-input-block">
										<select name="dangw" id="dangw" lay-search="" lay-filter="dangw" required lay-verify="required">
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">网络类型</label>
									<div class="layui-input-block">
										<select name="sourceType">
											<option value="all" selected="">全部</option>
											<option value="2G">2G</option>
											<option value="3G">3G</option>
											<option value="4G">4G</option>
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">短信语</label>
									<div class="layui-input-block">
										<select name="migId" id="smsm" lay-search="" lay-filter="smsm" required lay-verify="required">
										</select>
									</div>
								</div>
									<div class="layui-form-item" id="timechoice">
									<label class="layui-form-label">营销时间</label>
									<div class="layui-input-inline">
										<input type="text" class="layui-input" id="test"
											name="secTime" placeholder="yyyy-MM-dd"
											autocomplete="off" />
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">定时任务</label>
									<div class="layui-input-block">
										<input type="checkbox" name="isitmework"
											lay-filter="switchwtime" lay-skin="switch" lay-text="ON|OFF">
									</div>
								</div>
								
								<div class="layui-form-item layui-hide" id="timeIN">
									<label class="layui-form-label">时间选择</label>
									<div class="layui-input-inline">
										<input type="text" class="layui-input" id="test2"
											name="worktime" placeholder="yyyy-MM-dd HH:mm:ss"
											autocomplete="off" />
									</div>
								</div>
								
								<div class="layui-form-item">
									<div class="layui-input-inline">
										<input type="checkbox" name="isdelblack" title="去黑名单"/>
									</div> 
									
								</div>
								
								<div class="layui-form-item" >
									<label class="layui-form-label">重复运营</label>
									<div class="layui-input-inline">
										<input type="checkbox" name="isdeldays"
											lay-filter="swithdel1" lay-skin="switch" lay-text="ON|OFF" >
									</div>
									<div class="layui-input-inline layui-hide" id="deldays1">
										<input type="text" class="layui-input"  name="deldays" autocomplete = "false"/>
									</div>
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">审核人</label>
									<div class="layui-input-block">
										<select name="reUser" lay-search="" lay-verify="required">
											<option value=""></option>
											<option value="裴秋婷">裴秋婷</option>
										</select>
									</div>
								</div>
								<input type="hidden" value="<%=session.getAttribute("realname")%>"
									name="rdUser">

								<div class="layui-form-item">
									<button class="layui-btn" type="submit" lay-submit=""
										lay-filter="demo2">提交</button>
									<button class="layui-btn" type="reset" lay-filter="demo2">
										重置</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.8.0.min.js" type="text/javascript"></script>
	<script src="../layui/layui.js"></script>
	<script>
		
		

		/*********************界面上两个tab按钮的切换事件*****************************/
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

		/*************************监控form表单中的一些事件和动态效果************************************/
		layui.use([ 'form', 'laypage', 'layer', 'upload' ],
						function() {
							var form = layui.form, layer = layui.layer, $ = layui.jquery, upload = layui.upload;
							

							/*********************************请求msg的值**************************************/
							$.ajax({
								url : '../requestdata/selectmsg',
								type : 'POST',
								dataType : 'json',
								success : function(result) {
									var json = eval(result);
									var optionstr = "";
									$.each(json, function(index, item) {
										var jsonvalue = eval(item);
										$.each(jsonvalue, function(indexv, itemv) {
											console.log(itemv);
											optionstr += "<option value='"+itemv.msgid+"'>"
													+ itemv.msgtitle + "</option>";
										});
									});
									$("#smsm").html("<option></option> "+optionstr);
									form.render('select');
								}
							});
							$.ajax({
								url : '../requestdata/selectmsg',
								type : 'POST',
								dataType : 'json',
								success : function(result) {
									var json = eval(result);
									var optionstr ="";
									$.each(json, function(index, item) {
										var jsonvalue = eval(item);
										$.each(jsonvalue, function(indexv, itemv) {
											optionstr += "<option value='"+itemv.msgid+"'>"
													+ itemv.msgtitle + "</option>";
										});
									});
									$("#filesmsm").html("<option></option> "+optionstr);
									form.render('select');
								}
							});
							//监控switch开发并实现时间选框的显示和隐藏
							form.on('switch(switchTest)', function(data) {
								layer.msg('定时任务' + (this.checked ? '开' : '关'),
										{
											offset : '6px'
										});
								if (this.checked) {
									$('#timechoice').removeClass('layui-hide');
								} else {
									$('#timechoice').addClass('layui-hide');
								}
							});
							
							form.on('switch(switchwtime)',function(data){
								layer.msg('维度筛选定时任务' + (this.checked ? '开' : '关'),
										{
											offset : '6px'
										});
								if(this.checked){
									$('#timeIN').removeClass('layui-hide');
								}else{
									$('#timeIN').addClass('layui-hide');
								}
							});
							
							form.on('switch(switchdel)',function(data){
								layer.msg('避免重复运营' + (this.checked ? '开' : '关'),
										{
											offset : '6px'
										});
								if(this.checked){
									$('#deldays').removeClass('layui-hide');
								}else{
									$('#deldays').addClass('layui-hide');
								}
							});
							form.on('switch(swithdel1)',function(data){
								layer.msg('避免重复运营' + (this.checked ? '开' : '关'),
										{
											offset : '6px'
										});
								if(this.checked){
									$('#deldays1').removeClass('layui-hide');
								}else{
									$('#deldays1').addClass('layui-hide');
								}
							});
								$.ajax({
									url : '../requestdata/selectdw',
									type : 'post',
									dataType : 'json',
									success : function(result) {
										var json = eval(result);
										$('dangw').empty();
										var optionstr = "";
										$.each(json,function(index,item) {
											optionstr += "<option value='"+item+"'>"+ item+ "</option>";
											
										});
										$('#dangw').append("<option value='all' selected>全部</option>"+optionstr);
										form.render('select');
										}
									});
								
						});

		//实现时间的选框的效果
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#test1' //指定元素
				,
				position : 'abolute',
				type : 'datetime'
			});
			laydate.render({
				elem:'#test',
				position:'ablute',
				type:'datetime'
			});
			laydate.render({
				elem:'#test2',
				position:'ablute',
				type:'datetime'
			});
		});
	</script>
</body>
</html>