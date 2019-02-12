<%@page import="java.util.List"%>
<%@page import="com.mtpt.bean.TBHfcs"%>
<%@page import="com.mtpt.service.impl.TBHfcsService"%>
<%@page import="com.mtpt.bean.DataTotal"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.mtpt.service.impl.DataTotalService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="../layui/css/layui.css" />
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="../layer/layer.js"></script>
</head>
<body>
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-cord">
					<div class=”layui-card-header“>
						活动:<label id="title"></label>
					</div>
					<div class="layui-card-body">
						<table class="layui-table" style="text-align:center">
							<colgroup>
								<col width="10.6%">
								<col width="10.6%">
								<col width="10.6%">
								<col width="10.6%">
								<col width="10.6%">
								<col width="10.6%">
							</colgroup>
							<thead>
								<tr>
									<td>首页PV</td>
									<td>测试点击数</td>
									<td>查看结果点击数</td>
									<td>订购页PV</td>
									<td colspan=6>验证码点击数</td>
								</tr>
							</thead>
							<%
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							ServletContext sc = this.getServletContext();
							ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
							TBHfcsService hfcs = (TBHfcsService)ac.getBean("hfcsService");
							List<TBHfcs> tbHfcslist = hfcs.selectByAll();
							for(TBHfcs tbHfcs:tbHfcslist){
								
							%>
							<tbody>
								<tr>
									<td><%=tbHfcs.getHfSpv() %></td>
									<td><%=tbHfcs.getHfCsks() %></td>
									<td><%=tbHfcs.getHfCheckresult() %></td>
									<td><%=tbHfcs.getHfOrderpv() %></td>
									<td colspan=6><%=tbHfcs.getHfVercode() %></td>
								</tr>
							<tr>
									<td>10元100M国内</td>
									<td>20元300M国内</td>
									<td>30元500M国内</td>
									<td>50元1G国内</td>
									<td>70元2G国内</td>
									<td>100元3G国内</td>
									<td>20元1.5G省内</td>
									<td>30元3G省内</td>
									<td>50元8G省内</td>
									<td>100元20G省内</td>
								</tr>
								<tr>
									<td>立即订购：<%=tbHfcs.getHfSgnum() %></td>
									<td>立即订购：<%=tbHfcs.getHfEgnum() %></td>
									<td>立即订购：<%=tbHfcs.getHfTgnum() %></td>
									<td>立即订购：<%=tbHfcs.getHfFgnum() %></td>
									<td>立即订购：<%=tbHfcs.getHfQgnum() %></td>
									<td>立即订购：<%=tbHfcs.getHfYgnum() %></td>
									<td>立即订购：<%=tbHfcs.getHfEsnum() %></td>
									<td>立即订购：<%=tbHfcs.getHfTsnum() %></td>
									<td>立即订购：<%=tbHfcs.getHfFsnum() %></td>
									<td>立即订购：<%=tbHfcs.getHfYbsnum() %></td>
								</tr>
								<tr>
									<td>确定订购：<%=tbHfcs.getHfQrnums() %></td>
									<td>确定订购：<%=tbHfcs.getHfQrnume() %></td>
									<td>确定订购：<%=tbHfcs.getHfQrnumt() %></td>
									<td>确定订购：<%=tbHfcs.getHfQrnumf() %></td>
									<td>确定订购：<%=tbHfcs.getHfQrnumq() %></td>
									<td>确定订购：<%=tbHfcs.getHfQrnumyb() %></td>
									<td>确定订购：<%=tbHfcs.getHfQrnumse() %></td>
									<td>确定订购：<%=tbHfcs.getHfQrnumss() %></td>
									<td>确定订购：<%=tbHfcs.getHfQrnumsf() %></td>
									<td>确定订购：<%=tbHfcs.getHfQrnumsyb() %></td>
								</tr>
							</tbody>
							<%} %>
						</table>
					</div>
				</div>
			</div>
			<div class="layui-col-md6">
				<div class="layui-card">
					<div class="layui-card-header">
						活动:<label id="etitle"></label>
					</div>
					<div class="layui-card-body">
						<div id="actidetail"  style="width: 100%; height:400px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../layui/layui.js"></script>
	<script type="text/javascript" src="../js/echarts.min.js"></script>
	<script type="text/javascript">
		$(function(){
			
			var str = window.location.href;
			 var datastr = decodeURI(str.split('?')[1]);
			var dataarr = datastr.split(',');
			$("#title").text(dataarr[0]);
			$("#etitle").text(dataarr[0]);
			var detail = echarts.init(document.getElementById('actidetail'));
			detail.showLoading();
			$.ajax({
				url:'../reqact/selectdetail',
				type:'post',
				dataType:'json',
				success:succF
			});
			
			function succF(result){
				var name=[];
				var datali = [];
				var json = eval(result);
				name = json["nstr"];
				var datalijson = json["data"];
				$.each(name,function(index,item){
					datali[index]={value:datalijson[item],name:item}
				})
				var option = {
						 color : [
						        'rgba(255, 69, 0, 0.5)',
						        'rgba(255, 150, 0, 0.5)',
						        'rgba(255, 200, 0, 0.5)',
						        'rgba(155, 200, 50, 0.5)',
						        'rgba(55, 200, 100, 0.5)'
						    ],
						    title : {
						        text: '漏斗图',
						        subtext: '纯属虚构'
						    },
						    tooltip : {
						        trigger: 'item',
						        formatter: "{a} <br/>{b} : {c}"
						    },
						    toolbox: {
						        show : true,
						        feature : {
						            mark : {show: true},
						            dataView : {show: true, readOnly: false},
						            restore : {show: true},
						            saveAsImage : {show: true}
						        }
						    },
						    legend: {
						        data : name
						    },
						    calculable : true,
						    series : [
						        {
						            name:'实际',
						            type:'funnel',
						            x: '10%',
						            width: '80%',
						            maxSize: '80%',
						            itemStyle: {
						                normal: {
						                    borderColor: '#fff',
						                    borderWidth: 2,
						                    label: {
						                        position: 'inside',
						                        formatter: '{c}',
						                        textStyle: {
						                            color: '#fff'
						                        }
						                    }
						                },
						            data:datali
						        }
						    ]
				};
				detail.hideLoading();	
				detail.setOption(option);
			}
			
		});
	</script>
</body>
</html>